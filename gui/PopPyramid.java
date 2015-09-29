/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import structures.AgeDistribution;
import structures.PopValue;
import structures.Population;

/**
 *
 * @author u0214256
 */
public class PopPyramid extends JPanel {

    private final DemApplet1 demApplet;
    private Population currentPop;
    private boolean beginDrawing;
    private int maxXValue;
    public static int TICK_HEIGHT = 10;
    
    //    private boolean popInitialized;
    public PopPyramid(DemApplet1 demAp) {
        this.demApplet = demAp;
//        this.currentPop = demAp.getTreeOfStockPops();
        this.currentPop = demAp.getCcm().getTreeOfStockPops().getSubPopulations()[0];
        Population tempUber = demAp.getCcm().getAllCCPops();
        this.beginDrawing = false;
        this.maxXValue = demApplet.getUdem().SELECTED_PYR_METRIC.getNumber();
//        this.setSize(new Dimension(1090,675));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (!beginDrawing) {
            return;
        }

        Population p = currentPop;
        PopValue pv = p.getPopValue();
        AgeDistribution maleDist = pv.getMaleDistribution();
        AgeDistribution femDist = pv.getFemDistribution();
        double[] malePop = getPyramidDat(maleDist.getData());
        double[] femPop = getPyramidDat(femDist.getData());

        // Set up the size of the bins
        int numBins = malePop.length;

        int midX = getWidth() / 2;
        int midY = getHeight() / 2;

        int leftX = getWidth() / 20;
        int rightX = getWidth() - leftX;

        int topY = getHeight() / 20;
        int bottomY = getHeight() - topY;

        int workingXRange = rightX - midX;

        int[] scaledMale = getNumPixels(malePop, maxXValue, workingXRange);
        int[] scaledFem = getNumPixels(femPop, maxXValue, workingXRange);

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
        DecimalFormat formatter = new DecimalFormat("#,###");
        // Show male and female identifers, and total N
        String maleString = "Male: N=" + formatter.format(pv.getMaleSize());
        String femString = "Female: N=" + formatter.format(pv.getFemSize());
        String totString = "Total: N=" + formatter.format(pv.getTotalSize());
              
        g.drawString(maleString, leftX, topY + g.getFontMetrics().getHeight());
        g.drawString(femString, rightX - g.getFontMetrics().stringWidth(femString), topY + g.getFontMetrics().getHeight());
        g.drawString(totString, leftX, topY + g.getFontMetrics().getHeight() * 3);
        
        g.drawString("Tot Median Age: " + formatter.format(p.getTotMedianAge()), leftX, topY + g.getFontMetrics().getHeight() * 5);
        g.drawString("Dependency Ratio: " + p.getDependencyRatio(), leftX, topY + g.getFontMetrics().getHeight() * 7);
        String titleString = p.getName();
        g.drawString(titleString, midX - g.getFontMetrics().stringWidth(titleString) / 2, g.getFontMetrics().getHeight());

        g.setColor(Color.BLACK);
        double spacePerTick = workingXRange / 5;
        g.drawLine(leftX, bottomY, leftX, bottomY + TICK_HEIGHT);
        g.drawLine(midX, bottomY, midX, bottomY + TICK_HEIGHT);
        g.drawLine(rightX, bottomY, rightX, bottomY + TICK_HEIGHT);

        String metString = formatter.format(maxXValue);

        g.drawString(metString, rightX - g.getFontMetrics().stringWidth(metString) / 2, bottomY + TICK_HEIGHT + g.getFontMetrics().getHeight());
        g.drawString("-" + metString, leftX - g.getFontMetrics().stringWidth(metString) / 2, bottomY + TICK_HEIGHT + g.getFontMetrics().getHeight());

        for (int i = 1; i < 5; i++) {
            g.drawLine((int) (midX + i * spacePerTick), bottomY, (int) (midX + i * spacePerTick), bottomY + TICK_HEIGHT);
            g.drawLine((int) (midX - i * spacePerTick), bottomY, (int) (midX - i * spacePerTick), bottomY + TICK_HEIGHT);
        }

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

        MILLIONS("Million", 1000000), HUNDRED_KS("Hundred Thousand", 100000), TEN_KS("Ten Thousand", 10000), THOUSANDS("Thousand", 1000), HUNDREDS("Hundred", 100), TENS("Ten", 10);
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

    public int getMaxXValue() {
        return maxXValue;
    }

    public void setMaxXValue(int maxXValue) {
        this.maxXValue = maxXValue;
        repaint();
    }
}
