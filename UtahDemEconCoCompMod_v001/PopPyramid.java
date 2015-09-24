/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;


import java.awt.*;
import javax.swing.JPanel;
import structures.AgeDistribution;
import structures.PopValue;
import structures.Population;
import tools.CohortComponentModel;

/**
 *
 * @author u0214256
 */
public class PopPyramid extends JPanel {
    private final DemApplet1 demApplet;
    
    public PopPyramid(DemApplet1 demAp){
        this.demApplet=demAp;
    }
    
    
    
    
        public void paintComponent(Graphics g){
        //        if (!udem.isModelBuilt()) {
//        return;    
//        }

        Population p = demApplet.getStockPops()[0];

        PopValue pv = p.getPopValue();
        AgeDistribution maleDist = pv.getMaleDistribution();
        AgeDistribution femDist = pv.getFemDistribution();
        double[] malePop = getPyramidDat(maleDist.getData());
        double[] femPop = getPyramidDat(femDist.getData());
        double maxObservedValue = Math.max(getLargestValue(malePop), getLargestValue(femPop));

        PopPyramid.XAxisMetric xmet = getXMetric(maxObservedValue);
        int maxXAxisValue = getMaxXAxisValue(xmet, maxObservedValue);
        int xmetScaleDiv = 0;
        switch (xmet) {
            case THOUSANDS:
                xmetScaleDiv = 1000;
                break;
            case HUNDREDS:
                xmetScaleDiv = 100;
                break;
            case TWENTIES:
                xmetScaleDiv = 20;
                break;
            default:
                break;
        }

        // Set up the size of the bins
        int numBins = malePop.length;

        int midX = getWidth() / 2;
        int midY = getHeight() / 2;

        int leftX = getWidth() / 20;
        int rightX = getWidth() - leftX;

        int topY = getHeight() / 20;
        int bottomY = getHeight() - topY;

        int workingXRange = rightX - midX;
        int pixelsForEachMetric = workingXRange / (maxXAxisValue / xmetScaleDiv);
        int[] scaledMale = getPixelDat(malePop, xmetScaleDiv, pixelsForEachMetric);
        int[] scaledFem = getPixelDat(femPop, xmetScaleDiv, pixelsForEachMetric);

        int workingYRange = bottomY - topY;
        int pixelsPerBin = workingYRange / numBins;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawLine(midX, topY, midX, bottomY);
        g.drawLine(leftX, bottomY, rightX, bottomY);

        // Draw all the female bins
        for (int i = 0; i < numBins; i++) {
            int tempWidthF = scaledFem[numBins - 1 - i];
            int tempWidthM = scaledMale[numBins - 1 - i];
            int tempY1 = topY + pixelsPerBin * i;
            int tempX1F = midX;
            int tempX1M = midX - tempWidthM;
            // female bins
            g.setColor(Color.BLUE);
            g.fillRect(tempX1F, tempY1, tempWidthF, pixelsPerBin);
            // male bins
            g.setColor(Color.RED);
            g.fillRect(tempX1M, tempY1, tempWidthM, pixelsPerBin);
            // draw the borders
            g.setColor(Color.BLACK);
            g.drawRect(tempX1F, tempY1, tempWidthF, pixelsPerBin);
            g.drawRect(tempX1M, tempY1, tempWidthM, pixelsPerBin);
        }

        // Show male and female identifers, and total N
        String maleString = "Male: N=" + Math.round(pv.getMaleSize());
        String femString = "Female: N=" + Math.round(pv.getFemSize());
        g.drawString(maleString, leftX, topY + g.getFontMetrics().getHeight());
        g.drawString(femString, rightX - g.getFontMetrics().stringWidth(femString), topY + g.getFontMetrics().getHeight());

        String titleString = p.getName();
        g.drawString(titleString, midX - g.getFontMetrics().stringWidth(titleString) / 2, g.getFontMetrics().getHeight());

    }
    
      public int[] getPixelDat(double[] data, int scale, int ppm) {
        int[] temp = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = (int) ((data[i] / scale) * ppm);
        }
        return temp;
    }

    public int getMaxXAxisValue(XAxisMetric xmet, double observed) {

        switch (xmet) {
            case THOUSANDS:
                return (int) Math.ceil(observed / 1000) * 1000;
            case HUNDREDS:
                return (int) Math.ceil(observed / 100) * 100;
            case TWENTIES:
                return (int) Math.ceil(observed / 20) * 20;
            default:
                return -9999;
        }
    }

    public XAxisMetric getXMetric(double observed) {
        if (observed >= 1000) {
            return XAxisMetric.THOUSANDS;
        }
        if (observed >= 100) {
            return XAxisMetric.HUNDREDS;
        }
        return XAxisMetric.TWENTIES;
    }

    public double[] getPyramidDat(double[] dat) {
        switch (demApplet.getUdem().getPyramidType()) {
            case SINGLE_YEAR:
                return dat;
            case FIVE_YEAR:
                return dat;
        }
        return null;
    }

    public double getLargestValue(double[] data) {
        double max = data[0];
        for (double dt : data) {
            if (dt > max) {
                max = Math.max(max, dt);
            }
        }
        return max;

    }

    public enum PyramidType {

        SINGLE_YEAR, FIVE_YEAR;
    }

    public enum XAxisMetric {

        THOUSANDS, HUNDREDS, TWENTIES;
    }

    
}
