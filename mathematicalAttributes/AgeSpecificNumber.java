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
public class AgeSpecificNumber extends NumericObject {

    private NumericObject age;

    /*
     Constructs a rate with a numerator (number of people experiencing an event) and denominator (number of people at risk for the event).
     Numerator must not be negative, and denominator must be greater than 0.
     */
    public AgeSpecificNumber(double n, double d, double a) {
        super(n / d, true);
        if (n < 0) {
            throw new RuntimeException("Numerator of an age-specific rate cannot be less than 0");
        }
        if (d <= 0) {
            throw new RuntimeException("Denominator of an age-specific rate cannot be less than or equal to 0");
        }
        if (a < 0) {
            throw new RuntimeException("Age for an age-specific rate cannot be less than 0");
        }
        this.numerator = n;
        this.denominator = d;
        this.value = n / d;
        this.hasNumAndDenom = true;
        this.age = new NumericObject(a, false);
    }
    /*
     Constructs a rate from a double value. It must be positive.
     */

    public AgeSpecificNumber(double v, double a) {
        super(v, false);
        this.value = v;
        this.hasNumAndDenom = false;
        this.age = new NumericObject(a, false);
    }

    public NumericObject getAge() {
        return age;
    }
}
