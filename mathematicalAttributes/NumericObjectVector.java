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
public class NumericObjectVector extends RVector {

    protected NumericObject[] numericObjects;
    double[] values;

    public NumericObjectVector(int l) {
        super(l, VectorType.NUMERIC);
        this.values = new double[length];
        this.numericObjects = new NumericObject[l];
    }

    public NumericObjectVector(int l, NumericObject newNO) {
        super(l, VectorType.NUMERIC);
        this.values = new double[length];
        this.numericObjects = new NumericObject[length];
        for (int i = 0; i < l; i++) {
            numericObjects[i] = newNO;
        }

        initializeValues();
    }

    public NumericObjectVector(NumericObject[] nOA) {
        super(nOA.length, VectorType.NUMERIC);
        this.values = new double[length];
        this.numericObjects = nOA;
        initializeValues();
    }

    public NumericObjectVector(double[] dA) {
        super(dA.length, VectorType.NUMERIC);
        this.values = new double[length];
        this.numericObjects = new NumericObject[dA.length];
        for (int i = 0; i < dA.length; i++) {
            numericObjects[i] = new NumericObject(dA[i], false);
        }
        initializeValues();
    }

    /*
     Create a vector going from the a value to the b value, by increments of c
     */
    public NumericObjectVector(NumericObject a, NumericObject b, NumericObject c) {
        super(a.getValue(), b.getValue(), c.getValue());
        this.values = new double[length];
        numericObjects = new NumericObject[length];
        for (int i = 0; i < length; i++) {
            numericObjects[i] = new NumericObject(a.getValue() + c.getValue() * i, false);
        }
        initializeValues();
    }

    private void initializeValues() {
        for (int i = 0; i < length; i++) {
            values[i] = numericObjects[i].value;
        }

    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < numericObjects.length; i++) {
            double v = numericObjects[i].getValue();
            temp += v + ",\t";
        }

        return temp;
    }

    @Override
    public String getStringValueAtIndex(int i) {
        return values[i] + "";
    }

    public double[] getValues() {
        return values;
    }

    public double getValueAtIndex(int i) {
        if (i < 0 || i > length - 1) {
            throw new RuntimeException("Tried to retrieve a value from a Numeric Vector that is out of bounds");
        }
        return (values[i]);
    }

    public double[] add(NumericObjectVector v2) {
        checkVLengths(v2);
        double[] tempV = new double[length];
        for (int i = 0; i < length; i++) {
            tempV[i] = values[i] + v2.getValueAtIndex(i);
        }
        return tempV;
    }

    public double[] subtract(NumericObjectVector v2) {
        checkVLengths(v2);
        double[] tempV = new double[length];
        for (int i = 0; i < length; i++) {
            tempV[i] = values[i] - v2.getValueAtIndex(i);
        }
        return tempV;
    }

    public double[] multiply(NumericObjectVector v2) {
        checkVLengths(v2);
        double[] tempV = new double[length];
        for (int i = 0; i < length; i++) {
            tempV[i] = values[i] * v2.getValueAtIndex(i);
        }
        return tempV;
    }

    public double[] divide(NumericObjectVector v2) {
        checkVLengths(v2);
        if (v2.hasZeros()) {
            throw new RuntimeException("Tried to divide a vector by another vector with zeros in it");
        }
        double[] tempV = new double[length];
        for (int i = 0; i < length; i++) {
            tempV[i] = values[i] / v2.getValueAtIndex(i);
        }
        return tempV;
    }

    public NumericObjectVector addO(NumericObjectVector v2) {
        return new NumericObjectVector(add(v2));
    }

    public NumericObjectVector subtractO(NumericObjectVector v2) {
        return new NumericObjectVector(subtract(v2));
    }

    public NumericObjectVector multiplyO(NumericObjectVector v2) {
        return new NumericObjectVector(multiply(v2));
    }

    public NumericObjectVector divideO(NumericObjectVector v2) {
        return new NumericObjectVector(divide(v2));
    }

    private void checkVLengths(NumericObjectVector v2) {
        if (length != v2.getLength()) {
            throw new RuntimeException("Tried to perform arithmetic on two numeric vectors of different lengths");
        }
    }

    public boolean hasZeros() {
        for (double v : values) {
            if (v == 0) {
                return true;
            }
        }
        return false;
    }

    public void setValues(double[] v) {
        for (int i = 0; i < v.length; i++) {
            setValue(i, v[i]);
        }
    }

    public void setValue(int index, double v) {
        values[index] = v;
        numericObjects[index] = new NumericObject(v, false);
    }

    public double getTotalValue() {
        double tempTot = 0;
        for (double v : values) {
            tempTot += v;
        }
        return tempTot;
    }

}
