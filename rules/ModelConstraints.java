/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

/**
 *
 * @author u0214256
 */
public class ModelConstraints {

    private int begYear, endYear, numYears;

    public ModelConstraints(int begy, int endy) {
        this.begYear = begy;
        this.endYear = endy;
        this.numYears = endYear-begYear+1;
    }

    public int getBegYear() {
        return begYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getNumYears() {
        return numYears;
    }
    

}
