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
public abstract class RVector {

    public final int length;
    public final VectorType type;
    String[] stringArray;

    public RVector(int l, VectorType vt) {
        this.length = l;
        this.type = vt;
    }

    public RVector(double a, double b, double c) {
        this.type = VectorType.NUMERIC;
        this.length = (int) Math.floor(((b - a) / c) + 1);
    }

    public int getLength(){
        return length;
    };

    public abstract String toString();

    public abstract String getStringValueAtIndex(int i);

}
