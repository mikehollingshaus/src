/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import mathematicalAttributes.AgeSpecificNumber;
import mathematicalAttributes.NumericObject;
import mathematicalAttributes.NumericObjectVector;
import mathematicalAttributes.Ratio;
import rules.ModelConstraints;

/**
 *
 * @author u0214256
 */
public class CohortComponentModel {

    private ModelConstraints modelConstraints;
    private DataFrame dataFrame;

    private Fertility fertilityObject;
    private Mortality mortalityObject;
//    Migration nonLaborMigrationObject, laborMigrationObject;
    private Migration netMigObject;
    private PointPopulation basePop;
    private Ratio secondarySexRatio;

    public double[] ages;

    private double[] yearArray;

    private PointPopulation[] periodPopPop;

    public CohortComponentModel(ModelConstraints mc, DataFrame datf) {
        this.modelConstraints = mc;
        this.dataFrame = datf;
        this.yearArray = new NumericObjectVector(new NumericObject(modelConstraints.getBegYear(), false), new NumericObject(modelConstraints.getEndYear(), false), new NumericObject(1, false)).getValues();
        createComponents();
        createPop0();
        this.secondarySexRatio = new Ratio(1.05);
    }

    public void build1() {

        PointPopulation launchPop = basePop;
        PointPopulation postMort = basePop.applyAgeMortalitySchedule(mortalityObject);

        System.out.println(dataFrame);
        System.out.println("END");

        /*        for (int i = 0; i < yearArray.length; i++) {

         double launchyear = yearArray[i];
         PointPopulation targetPop // Run the model

         launchPop = 
         }
         */
    }

    private void createComponents() {
        NumericObjectVector ageVect = (NumericObjectVector) dataFrame.var("age");
        this.ages = ageVect.getValues();
        AgeSpecificNumber[] mortRates = getRatesFromVect((NumericObjectVector) dataFrame.var("t.m.qx"), ages);
        AgeSpecificNumber[] fertRates = getRatesFromVect((NumericObjectVector) dataFrame.var("asrb"), ages);
        AgeSpecificNumber[] migRates = getRatesFromVect((NumericObjectVector) dataFrame.var("t.m.mig"), ages);
        this.mortalityObject = new Mortality(new AgeSchedule(mortRates));
        this.fertilityObject = new Fertility(new AgeSchedule(fertRates));
        this.netMigObject = new Migration(new AgeSchedule(migRates));
    }

    private void createPop0() {
        AgeSpecificNumber[] agePops = getCountsFromVect((NumericObjectVector) dataFrame.var("t.m.pop"), ages);

        this.basePop = new PointPopulation(new AgeSchedule(agePops), modelConstraints.getBegYear(), 7, 1);
        this.periodPopPop = new PointPopulation[yearArray.length];
        periodPopPop[0] = basePop;
    }

    public AgeSpecificNumber[] getRatesFromVect(NumericObjectVector nv, double[] tage) {
        AgeSpecificNumber[] temp = new AgeSpecificNumber[nv.getLength()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new AgeSpecificNumber(nv.getValueAtIndex(i), tage[i]);
        }
        return temp;
    }

    public AgeSpecificNumber[] getCountsFromVect(NumericObjectVector nv, double[] tage) {
        AgeSpecificNumber[] temp = new AgeSpecificNumber[nv.getLength()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new AgeSpecificNumber(nv.getValueAtIndex(i), tage[i]);
        }
        return temp;
    }

    public ModelConstraints getModelConstraints() {
        return modelConstraints;
    }

    public Fertility getFertilityObject() {
        return fertilityObject;
    }

    public Mortality getMortalityObject() {
        return mortalityObject;
    }

    public Migration getNetMigObject() {
        return netMigObject;
    }

    /*public Migration getNonLaborMigrationObject() {
     return nonLaborMigrationObject;
     }

     public Migration getLaborMigrationObject() {
     return laborMigrationObject;
     }*/
}
