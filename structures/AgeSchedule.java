/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import mathematicalAttributes.AgeSpecificNumber;
import mathematicalAttributes.NumericObject;
import mathematicalAttributes.NumericObjectVector;
import tools.R;

/**
 *
 * @author u0214256
 */
public class AgeSchedule extends NumericObjectVector {

    private final NumericObject[] ages;

    public AgeSchedule(AgeSpecificNumber[] r) {
        super(r);
        this.ages = findAllAges(r);
        if (R.thereAreDuplicates(ages)) {
            throw new RuntimeException("Cannot have an age schedule where the same age appears more than once");
        }
    }

    public AgeSchedule(NumericObject v, NumericObject[] a) {
        super(a.length, v);
        this.ages = a;
        if (R.thereAreDuplicates(ages)) {
            throw new RuntimeException("Cannot have an age schedule where the same age appears more than once");
        }
    }
    

    private NumericObject[] findAllAges(AgeSpecificNumber[] r) {
        NumericObject[] temp = new NumericObject[r.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = r[i].getAge();
        }
        return temp;
    }

    public NumericObject[] getAges() {
        return ages;
    }

    public AgeSpecificNumber[] getRates() {
        return (AgeSpecificNumber[]) numericObjects;
    }

}
