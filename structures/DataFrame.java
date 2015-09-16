/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import mathematicalAttributes.RVector;
import mathematicalAttributes.VectorType;
import tools.R;

/**
 *
 * @author u0214256
 */
public class DataFrame {

    private String[] varnames;
    private VectorType[] types;
    private RVector[] variables;
    int obs, cols;

    public DataFrame(String[] vn, RVector[] vcts, VectorType[] t) {

        checkInitialDataInput(vn, vcts);
        this.varnames = vn;
        this.types = t;
        this.variables = vcts;
        this.obs = vcts[0].getLength();
        this.cols = varnames.length;
    }

    public DataFrame(String[] vn) {
        this.varnames = vn;
        this.obs = 0;
        this.cols = varnames.length;
    }

    public String[] getVarnames() {
        return varnames;
    }

    public RVector[] getVariables() {
        return variables;
    }

    public int getObs() {
        return obs;
    }

    public int getCols() {
        return cols;
    }

    public RVector var(String name) {
        for (int i = 0; i < varnames.length; i++) {
            if (name.equals(varnames[i])) {
                return variables[i];
            }
        }
        throw new RuntimeException("Cannot find a variable named'" + name + "' in the dataset");
    }

    public void addVariable(String name, RVector v) {
        String[] newnames = R.appendArray(varnames, name);
        if (R.thereAreDuplicates(newnames)) {
            throw new RuntimeException("Cannot create add a variable to a dataset another variable of the same name");
        }
        if (v.getLength() == variables[0].getLength()) {
            throw new RuntimeException("Cannot add a variable to a dataset if it has a different number of observations");
        }

        this.varnames = newnames;
        this.variables = R.appendArray(variables, v);
    }

    /*public void removeVariable(String name) {
        
     }
     */
    private void checkInitialDataInput(String[] vn, RVector[] vcts) {
        if (vn.length != vcts.length) {
            throw new RuntimeException("Cannot create a dataset where the number of variable names differs from the number of vectors");
        }
        if (R.thereAreDuplicates(vn)) {
            throw new RuntimeException("Cannot create a dataset where there are duplicate variable names");
        }
        for (int i = 0; i < vn.length - 1; i++) {
            for (int j = i + 1; j < vn.length; j++) {
                if (vcts[i].getLength() != vcts[j].getLength()) {
                    throw new RuntimeException("Cannot create a dataset where variables have different numbers of observations");
                }
            }
        }

    }

    @Override
    public String toString() {
        String temp = "Var:\t";
        for (String v : varnames) {
            temp += v + "\t\t";
        }
        String[][] varStr = new String[cols][obs];

        for (int c = 0; c < cols; c++) {
            RVector tv = variables[c];
            for (int r = 0; r < obs; r++) {
                varStr[c][r] = tv.getStringValueAtIndex(r);
            }
        }

        for (int r = 0; r < obs; r++) {
            temp += "\n[" + r + "]\t";
            for (int c = 0; c < cols; c++) {
                temp += varStr[c][r] + "\t\t";
            }
        }
        return temp;
    }

}
