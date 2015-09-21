/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author u0214256
 */
public class SexAgeStructure {

    public static final int NUM_AGES = 101;

    private final int[] ageRanges;
    private final Sex male;
    private final Sex female;

    private double data;
    
    
    
    public SexAgeStructure() {
        int[] tempAgeRanges = new int[NUM_AGES];
        for (int i = 0; i < tempAgeRanges.length; i++) {
            tempAgeRanges[i] = i;
        }
        this.ageRanges = tempAgeRanges;
        this.male = Sex.MALE;
        this.female = Sex.FEMALE;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
    
    

}
