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
public class NumericObject {

    protected boolean hasNumAndDenom;
    protected Double value, numerator, denominator;

    public NumericObject(double v, boolean hND) {

        this.value = v;
        this.hasNumAndDenom = hND;
    }

    public NumericObject addO(NumericObject asno) {

        double x = value.doubleValue();
        double y = asno.getValue().doubleValue();
        return new NumericObject(x + y, false);
    }

    public NumericObject subtractO(NumericObject asno) {

        double x = value.doubleValue();
        double y = asno.getValue().doubleValue();
        return new NumericObject(x - y, false);
    }

    public NumericObject multiplyO(NumericObject asno) {

        double x = value.doubleValue();
        double y = asno.getValue().doubleValue();
        return new NumericObject(x * y, false);
    }

    public NumericObject divideO(NumericObject asno) {

        double x = value.doubleValue();
        double y = asno.getValue().doubleValue();
        if (y == 0) {
            throw new RuntimeException("Tried to divide an age specific number object by another that equals 0");
        }
        return new NumericObject(x / y, false);
    }

    public double add(NumericObject asno) {
        return addO(asno).getValue().doubleValue();
    }

    public double subtract(NumericObject asno) {
        return subtractO(asno).getValue().doubleValue();
    }

    public double multiply(NumericObject asno) {
        return multiplyO(asno).getValue().doubleValue();
    }

    public double divide(NumericObject asno) {
        return divideO(asno).getValue().doubleValue();
    }

    public boolean hasNumAndDenom() {
        return hasNumAndDenom;
    }

    public Double getValue() {
        return value;
    }

    public Double getNumerator() {
        return numerator;
    }

    public Double getDenominator() {
        return denominator;
    }

}
