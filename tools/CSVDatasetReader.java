/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;

import java.io.IOException;
import java.util.Scanner;
import mathematicalAttributes.NumericObjectVector;
import mathematicalAttributes.RVector;
import mathematicalAttributes.StringVector;
import mathematicalAttributes.VectorType;

import structures.DataFrame;

/**
 *
 * @author Mike Hollingshaus
 * @version Sept 14, 2015
 * @purpose Reads a csv file. For now, must be of the following format: First
 * line with text is strings, separated by commas Second line with text is
 * integers 1, 2, or 3 (the number of integers must be the same as the number of
 * strings first read in)
 *
 * Following lines are all variables, of the types mapping to vector types
 */
public class CSVDatasetReader {

    private final boolean hasHeader;
    String filePathname;
    Scanner s, r;

    public static final String DELIMITER = ",";

    public CSVDatasetReader(String fp, boolean hasHeader) {
        this.hasHeader = hasHeader;
        this.filePathname = fp;
        try {
            this.s = new Scanner(new File(filePathname));
            this.r = new Scanner(new File(filePathname));
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePathname);
        }

    }

    /*
     Reads a data frame from a CSV file. The first row (with text) must be the list of variable names (separated by commas),
     And the remaining rows must contain the data values. They can be numeric (i.e., double), String, or boolean. That is all
     They must be separated by commas
     */
    public DataFrame readDataFrame() {
        StringVector varnames;

        String line = getNextNonEmptyLine(s);
        // get the number of columns
        int numCols = numStringElements(line);
        //Get the variable names (first line, there is a header, or just X1-XNUMCOLS if not)
        if (hasHeader) {
            varnames = new StringVector(populateStrings(line));
            line = getNextNonEmptyLine(s);
        } else {
            String[] vntemp = new String[numCols];
            for (int i = 0; i < vntemp.length; i++) {
                vntemp[i] = "X" + (i + 1);
            }
            varnames = new StringVector(vntemp);
        }

// Get the number of observations
        int numObs = getNumObs(r);
        // I no longer need Scanner r
        r = null;
        // Get the variable types

        VectorType[] types = populateVectorTypes(line);
        // Create the data vectors of the given types
        RVector[] dataVectors = new RVector[numCols];
        for (int i = 0; i < numCols; i++) {
            VectorType vt = types[i];
            if (vt == VectorType.NUMERIC) {
                dataVectors[i] = new NumericObjectVector(numObs);
                continue;
            }
            dataVectors[i] = new StringVector(numObs);
        }
        // Populate Variables
        populateVariables(s, line, dataVectors);

        return new DataFrame(varnames.getValues(), dataVectors, types);

    }

    private void populateVariables(Scanner scn, String startLine, RVector[] dv) {
        String line = startLine;
        for (int r = 0; r < dv[0].getLength(); r++) {
            Scanner t = new Scanner(line);
            t.useDelimiter(DELIMITER);
            for (int c = 0; c < dv.length; c++) {
                RVector rv = dv[c];

                String temp = t.next();
                if (rv.type == VectorType.NUMERIC) {
                    NumericObjectVector nv = (NumericObjectVector) rv;
                    nv.setValue(r, Double.parseDouble(temp));
                    continue;
                }
                StringVector sv = (StringVector) rv;
                sv.setValue(r, temp);
            }
            if (r < dv[0].getLength() - 1) {
                line = getNextNonEmptyLine(scn);
            }
        }
    }

    /*
     Returns the number of observations in the file. It is the number of lines in the file, less 1 (varnames)
     */
    private int getNumObs(Scanner scn) {
        int count = 0;
        while (scn.hasNextLine()) {
            scn.nextLine();
            count++;
        }
        return count - 1;
    }

    private String getNextNonEmptyLine(Scanner scn) {
        String nL = scn.nextLine();
        while (nL.trim().length() <= 0) {
            nL = scn.nextLine();
        }
        return nL;
    }

    private String[] readCSVLine(String line) {

        return null;
    }

    private int numStringElements(String line) {
        Scanner t = new Scanner(line);
        t.useDelimiter(DELIMITER);
        int count = 0;
        while (t.hasNext()) {
            t.next();
            count++;
        }
        return count;
    }

    private String[] populateStrings(String line) {
        String[] elements = new String[numStringElements(line)];
        Scanner t = new Scanner(line);
        t.useDelimiter(DELIMITER);
        for (int i = 0; i < elements.length; i++) {
            elements[i] = t.next();
        }
        return elements;
    }

    private VectorType[] populateVectorTypes(String line) {
        VectorType[] elements = new VectorType[numStringElements(line)];
//        int[] elements = new int[vartypes.length];
        Scanner t = new Scanner(line);
        for (int i = 0; i < elements.length; i++) {

            t.useDelimiter(DELIMITER);
            String temp = t.next();

            if (isDouble(temp)) {
                elements[i] = VectorType.NUMERIC;
                continue;
            }
            elements[i] = VectorType.STRING;
        }

        return elements;
    }

    public boolean isDouble(String temp) {
        try {
            Double.parseDouble(temp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
