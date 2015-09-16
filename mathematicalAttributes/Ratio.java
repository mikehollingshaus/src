/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematicalAttributes;

/**
 *
 * @author u0214256
 */
public class Ratio extends NumericObject {

    public Ratio(double n, double d) {
        super(n / d, true);
        if (d == 0) {
            throw new RuntimeException("Denominator of an age-specific rate cannot be less than or equal to 0");
        }
        this.numerator = n;
        this.denominator = d;
        this.value = n / d;
        this.hasNumAndDenom = true;

    }
    /*
     Constructs a rate from a double value. It must be positive.
     */

    public Ratio(double v) {
        super(v,false);
        this.value = v;
        this.hasNumAndDenom = false;
    }

}
