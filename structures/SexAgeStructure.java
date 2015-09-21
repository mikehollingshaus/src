/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import tools.R;

/**
 *
 * @author u0214256
 */
public class SexAgeStructure {

    public static final int NUM_AGES = 101;

    private final int[] ageRanges;
    private final Sex male;
    private final Sex female;

    private double[] maleData;
    private double[] femaleData;

    public SexAgeStructure() {
        int[] tempAgeRanges = new int[NUM_AGES];
        for (int i = 0; i < tempAgeRanges.length; i++) {
            tempAgeRanges[i] = i;
        }
        this.ageRanges = tempAgeRanges;
        this.male = Sex.MALE;
        this.female = Sex.FEMALE;
    }

    public double[] getMaleData() {
        return maleData;
    }

    public void setMaleData(double[] maleData) {
        this.maleData = maleData;
    }

    public double[] getFemaleData() {
        return femaleData;
    }

    public void setFemaleData(double[] femaleData) {
        this.femaleData = femaleData;
    }

    public SexAgeStructure add(SexAgeStructure sas) {
        SexAgeStructure tempSas = new SexAgeStructure();
        tempSas.setMaleData(R.sumArrays(maleData, sas.maleData));
        tempSas.setFemaleData(R.sumArrays(femaleData, sas.femaleData));
        return tempSas;
    }

    public SexAgeStructure subtract(SexAgeStructure sas) {
        SexAgeStructure tempSas = new SexAgeStructure();
        tempSas.setMaleData(R.differenceArrays(maleData, sas.maleData));
        tempSas.setFemaleData(R.differenceArrays(femaleData, sas.femaleData));
        return tempSas;
    }

   /* public boolean equals(SexAgeStructure sas) {
        if (maleData.length != sas.maleData.length) {
            return false;
        }
        return true;
    }
*/
}
