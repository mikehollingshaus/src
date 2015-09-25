/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import UtahDemEconCoCompMod_v001.PopPyramid.PyramidType;
import UtahDemEconCoCompMod_v001.PopPyramid.PyramidXAxisMetric;
import java.awt.Dimension;

import java.util.Date;
import javax.swing.JFrame;
import rules.ModelConstraints;

/**
 *
 * @author u0214256
 */
public class UtahDemEconCoCompMod_v001 extends JFrame {

    private final Date date;
    private final WorkBench workbench;
    public static final String filePath = "C:\\Users\\u0214256\\Documents\\Data\\Tables\\cohcompinp1.csv";
    private PyramidType pyramidType;
 
    public final PyramidXAxisMetric SELECTED_PYR_METRIC = PyramidXAxisMetric.HUNDRED_KS;

    public UtahDemEconCoCompMod_v001(Date d) {
        super("Cohort Component Applet");
        this.date = d;
        ModelConstraints mc1 = new ModelConstraints(2010, 2066);
        this.workbench = new WorkBench(mc1, filePath);
//        this.modelBuilt = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pyramidType = PyramidType.SINGLE_YEAR;
//        this.pyrSchema = PyramidXSchema.FIXED;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UtahDemEconCoCompMod_v001 udem = new UtahDemEconCoCompMod_v001(new Date());
        udem.workbench.buildTheModel();
        udem.runApplet();
    }

    private void runApplet() {
        DemApplet1 demAp = new DemApplet1(this);
        add(demAp);
        pack();

        setLocation(100, 100);
        setSize(1200, 640);
//        setResizable(false);
        setMinimumSize(new Dimension(300,400));
        setVisible(true);
    }

    public Date getDate() {
        return date;
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
    /*
     public boolean isModelBuilt() {
     return modelBuilt;
     }
     */

//    public PyramidXSchema getPyrSchema() {
//        return pyrSchema;
//    }
    public PyramidXAxisMetric getSelected_pyr_metric() {
        return SELECTED_PYR_METRIC;
    }

}
