/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import java.util.Date;
import rules.ModelConstraints;

/**
 *
 * @author u0214256
 */
public class UtahDemEconCoCompMod_v001 {

    private Date date;
    private WorkBench workbench;
    public static final String filePath = "C:\\Users\\u0214256\\Documents\\Data\\Tables\\cohcompinp1.csv";
    
    public UtahDemEconCoCompMod_v001(Date d){
        this.date = d;
        ModelConstraints mc1 = new ModelConstraints(2010,2066);
        this.workbench = new WorkBench(mc1, filePath);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UtahDemEconCoCompMod_v001 udem = new UtahDemEconCoCompMod_v001(new Date());
        System.out.println("Hello World!");
        udem.workbench.buildTheModel();
         
    }
    
    
 }
