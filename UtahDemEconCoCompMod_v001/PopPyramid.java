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
    private Population currentPop;
    private boolean beginDrawing;
    private PyramidXAxisMetric xmet;
    //    private boolean popInitialized;

    public PopPyramid(DemApplet1 demAp) {
        this.demApplet = demAp;
        this.currentPop = demAp.getUberPop();
        this.beginDrawing = false;
        this.xmet = demApplet.getUdem().SELECTED_PYR_METRIC;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (!beginDrawing) {
            return;
        }
//        if (popInitialized) {
//            Population p = currentPop;
//        Population p = demApplet.getUberPop();
//        Population p = demApplet.getStockPops()[0];
//        Population p = demApplet.getUberPop();
        Population p = currentPop;
        PopValue pv = p.getPopValue();
        AgeDistribution maleDist = pv.getMaleDistribution();
        AgeDistribution femDist = pv.getFemDistribution();
        double[] malePop = getPyramidDat(maleDist.getData());
        double[] femPop = getPyramidDat(femDist.getData());
        double maxObservedValue = Math.max(getLargestValue(malePop), getLargestValue(femPop));

        // Set up the size of the bins
        int numBins = malePop.length;

        int midX = getWidth() / 2;
        int midY = getHeight() / 2;

        int leftX = getWidth() / 20;
        int rightX = getWidth() - leftX;

        int topY = getHeight() / 20;
        int bottomY = getHeight() - topY;

        int workingXRange = rightX - midX;

//        PopPyramid.PyramidXAxisMetric xmet = demApplet.getUdem().getSelected_pyr_metric();

        int[] scaledMale = getNumPixels(malePop, xmet.getNumber(), workingXRange);
        int[] scaledFem = getNumPixels(femPop, xmet.getNumber(), workingXRange);

        int workingYRange = bottomY - topY;
        int pixelsPerBin = workingYRange / numBins;

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
        String maleString = "Male: N=" + Math.round(pv.getMaleSize()) / xmet.getNumber();
        String femString = "Female: N=" + Math.round(pv.getFemSize()) / xmet.getNumber();
        g.drawString(maleString, leftX, topY + g.getFontMetrics().getHeight());
        g.drawString(femString, rightX - g.getFontMetrics().stringWidth(femString), topY + g.getFontMetrics().getHeight());

        String titleString = p.getName() + " (Metric: " + xmet.getName() + ")";
        g.drawString(titleString, midX - g.getFontMetrics().stringWidth(titleString) / 2, g.getFontMetrics().getHeight());
//        }
    }

    public int[] getNumPixels(double[] data, int maxVal, int xRange) {
        int[] temp = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = (int) (data[i] / (maxVal / xRange));
        }

        return temp;
    }

    public int getMaxXAxisValue(PyramidXAxisMetric xmet, double observed) {
        int num = xmet.getNumber();
        return (int) Math.ceil(observed / num) * num;
    }
    /*
     public PyramidXAxisMetric getXMetric(double observed) {
     if (observed >= PyramidXAxisMetric.MILLIONS.getNumber()) {
     return PyramidXAxisMetric.MILLIONS;
     }
     if (observed >= PyramidXAxisMetric.HUNDRED_KS.getNumber()) {
     return PyramidXAxisMetric.HUNDRED_KS;
     }
     if (observed >= PyramidXAxisMetric.TEN_KS.getNumber()) {
     return PyramidXAxisMetric.TEN_KS;
     }
     if (observed >= PyramidXAxisMetric.THOUSANDS.getNumber()) {
     return PyramidXAxisMetric.THOUSANDS;
     }
     if (observed >= PyramidXAxisMetric.HUNDREDS.getNumber()) {
     return PyramidXAxisMetric.HUNDREDS;
     }
     return PyramidXAxisMetric.TWENTIES;
     }
     */

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

    public Population getCurrentPop() {
        return currentPop;
    }

    public void setCurrentPop(Population currentPop) {
        this.currentPop = currentPop;
        this.beginDrawing = true;
        repaint();
    }

    public enum PyramidType {

        SINGLE_YEAR, FIVE_YEAR;
    }

    public enum PyramidXAxisMetric {

        MILLIONS("Millions", 1000000), HUNDRED_KS("Hundred Thousands", 100000), TEN_KS("Ten Thousands", 10000), THOUSANDS("Thousands", 1000), HUNDREDS("Hundreds", 100), TENS("Tens", 10);
        private final String name;
        private final int number;

        private PyramidXAxisMetric(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

    }

    public void setXmet(PyramidXAxisMetric metric) {
        xmet = metric;
    }

    public PyramidXAxisMetric getXMet() {
        return xmet;
    }

}
