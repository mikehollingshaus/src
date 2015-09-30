/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import gui.DemApplet1;
import gui.PopPyramid.PyramidType;
import gui.PopPyramid.PyramidXAxisMetric;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import rules.ModelConstraints;

/**
 *
 * @author u0214256
 */
public class UtahDemEconCoCompMod_v001 extends JFrame {

    
    This line of code prevents this old file from accidentally running
    
    private GregorianCalendar calendar;
    private final WorkBench workbench;
    public static final String filePath = "C:\\Users\\u0214256\\Documents\\Data\\Tables\\cohcompinp1.csv";
    private PyramidType pyramidType;

    public final PyramidXAxisMetric SELECTED_PYR_METRIC = PyramidXAxisMetric.HUNDRED_KS;

    public UtahDemEconCoCompMod_v001(GregorianCalendar calendar) {
        super("Cohort Component Applet");
        this.calendar = calendar;
        ModelConstraints mc1 = new ModelConstraints(2010, 2066);
        this.workbench = new WorkBench(mc1, filePath);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pyramidType = PyramidType.SINGLE_YEAR;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UtahDemEconCoCompMod_v001 udem = new UtahDemEconCoCompMod_v001(new GregorianCalendar());
        System.out.println("Model Date: " + udem.calendar.getTime());
        udem.workbench.buildTheModel();
        udem.runApplet();
    }

    private void runApplet() {
        DemApplet1 demAp = new DemApplet1(this);
        add(demAp);
        pack();
        setLocation(100, 100);
        setSize(1200, 640);
        setMinimumSize(new Dimension(300, 400));
        setVisible(true);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public WorkBench getWorkbench() {
        return workbench;
    }

    public static String getFilePath() {
        return filePath;
    }

    public PyramidType getPyramidType() {
        return pyramidType;
    }

    public PyramidXAxisMetric getSelected_pyr_metric() {
        return SELECTED_PYR_METRIC;
    }

}
