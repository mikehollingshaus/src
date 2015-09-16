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
public class StringVector extends RVector {

    private String[] values;

    public StringVector(int l) {
        super(l, VectorType.STRING);
        this.values = new String[l];
    }

    public StringVector(String[] strs) {
        super(strs.length, VectorType.STRING);
        this.values = strs;
    }

    public void setValues(String[] v) {
        this.values = v;
    }

    public void setValue(int index, String v) {
        values[index] = v;
    }
    
    public String[] getValues(){
        return values;
    }
    
    @Override public String toString(){
        String temp = "";
        for (String v: values){
            temp += v + ",\t";
        }
        return temp;
    }
    
     @Override
    public String getStringValueAtIndex(int i) {
        return values[i];
    }

}
