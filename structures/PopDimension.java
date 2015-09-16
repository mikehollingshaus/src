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
public enum PopDimension {

    AGE(0, 100), SEX(1, 2);
    private int lowerBound, upperBound;

    PopDimension(int lower, int upper) {
        this.lowerBound = lower;
        this.upperBound = upper;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
    
}
