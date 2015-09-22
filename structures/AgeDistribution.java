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
public class AgeDistribution {

    
    /**
     * This object is immutable. And, therefore, it does not need a twin method
     */
    
    public static final int NUM_AGES = 101;

    private final int[] ageRanges;
    private final double[] data;

    public AgeDistribution(AgeDistribution oldAgeDist){
        this.ageRanges = oldAgeDist.ageRanges;
        this.data = oldAgeDist.data;
    }
    
    public AgeDistribution(double[] data) {
        int[] tempAgeRanges = new int[NUM_AGES];
        for (int i = 0; i < tempAgeRanges.length; i++) {
            tempAgeRanges[i] = i;
        }
        this.ageRanges = tempAgeRanges;
        this.data = data;
    }
    
    public AgeDistribution(double dat) {
        int[] tempAgeRanges = new int[NUM_AGES];
        double[] tempData = new double[NUM_AGES];
        for (int i = 0; i < tempAgeRanges.length; i++) {
            tempAgeRanges[i] = i;
            tempData[i] = dat;
        }
        this.ageRanges = tempAgeRanges;
        this.data=tempData;
    }

    public double[] getData() {
        return data;
    }
 
    public AgeDistribution add(AgeDistribution sas) {
        double[] sumDat = R.sumArrays(data, sas.data);
        return new AgeDistribution(sumDat);
    }

    public AgeDistribution subtract(AgeDistribution sas) {
        double[] sumDat = R.differenceArrays(data, sas.data);
        return new AgeDistribution(sumDat);
    }
    
    public AgeDistribution multiply(AgeDistribution sas){
        double[] sumDat = R.multiplyArrays(data, sas.data);
        return new AgeDistribution(sumDat);
    }
    
    
    public double sumData(){
        double sum = 0;
        for (int i = 0; i < data.length; i ++){
            sum += data[i];
        }
        return sum;
    }

   
    
    
    
    /* public boolean equals(AgeDistribution sas) {
     if (maleData.length != sas.maleData.length) {
     return false;
     }
     return true;
     }
     */
}
