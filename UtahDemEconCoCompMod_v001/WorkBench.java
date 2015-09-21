/*
 * Author: Mike Hollingshaus
 * Version: September 11, 2015
 * Purpose: This class represents a space where a cohort component model is constructed. I.e., the model is "built on the workbench"
 */
package UtahDemEconCoCompMod_v001;

import rules.ModelConstraints;
import tools.CohortComponentModel;
import structures.DataFrame;
import tools.CSVDatasetReader;
import tools.Tester;

/**
 *
 * @author u0214256
 */
public class WorkBench {

    private CohortComponentModel model;
    private ModelConstraints modelConstraints;
    private String filePath;

    /*
     Constructor
     */
    public WorkBench(ModelConstraints mc, String filePath) {
        this.modelConstraints = mc;
        this.filePath = filePath;
    }

    /*
     Where the main program is run. This is where the main model building flow is conducted
     */
    public void buildTheModel() {
        
        // build the model here    
        // Read the data file
        DataFrame df1 = new CSVDatasetReader(filePath, true).readDataFrame();
//        this.model = new CohortComponentModel(modelConstraints, df1);
//        model.build1();
        
        Tester tester = new Tester(this);
        tester.test();
      
           
    }

}
