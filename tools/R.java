/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import mathematicalAttributes.NumericObject;
import mathematicalAttributes.RVector;

/**
 *
 * @author u0214256
 */
public class R {

    
      public static double[] sumArrays(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("Tried to add two arrays of different lengths");
        }
        double[] temp = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i] + b[i];
        }
        return temp;
    }
    public static double[] differenceArrays(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("Tried to add two arrays of different lengths");
        }
        double[] temp = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i] - b[i];
        }
        return temp;
    }
    
    public static String padString(String str, int length) {
        String ns = str;
        int diff = length - str.length();
        if (diff <= 0) {
            return ns;
        }
        for (int i = 0; i < diff; i++) {
            ns += " ";
        }
        return ns;
    }

    public static boolean hasNextInt(String str) {
        return hasNextDouble(str);
    }

    public static boolean hasNextDouble(String str) {
        Scanner s;
        s = new Scanner(str);

        while (s.hasNext()) {
            if (s.hasNextDouble()) {
                return true;
            }
            s.next();
        }
        return false;
    }

    public static double nextDouble(String str) {
        Scanner s;

        s = new Scanner(str);

        while (s.hasNext()) {
            if (s.hasNextDouble()) {
                return s.nextDouble();
            }
            s.next();
        }
        throw new RuntimeException("***********Error - no next double********");

    }

    public static String[] readWordRow(String line, int num) {
        String[] temp = new String[num];
        Scanner s = new Scanner(line);

        for (int i = 0; i < temp.length; i++) {
            if (!s.hasNext()) {
                break;
            }
            temp[i] = s.next();
        }
        return R.fillRemainingBlanks(temp);
    }

    public static String[] fillRemainingBlanks(String[] stra) {
        for (int i = 0; i < stra.length; i++) {
            if (stra[i] == null) {
                stra[i] = "";
            }
        }
        return stra;
    }

    public static int[] readIntRow(String line, int num) {
        int[] temp = new int[num];
        Scanner s = new Scanner(line);

        for (int i = 0; i < temp.length; i++) {
            temp[i] = s.nextInt();
        }

        return temp;
    }

    public static int nextInt(String str) {
        return (int) nextDouble(str);
    }

    public static double[] readDoubRow(String line, int num) {
        double[] temp = new double[num];
        Scanner s = new Scanner(line);

        for (int i = 0; i < temp.length; i++) {
            temp[i] = s.nextDouble();
        }

        return temp;
    }

    public static String[] replaceArray(String[] a, String[] b, int astart) {

        int offset = 0;
        for (int ia = astart; ia < (astart + b.length); ia++) {
            a[ia] = b[offset];
            offset++;
        }
        return a;
    }

    public static String[][] replaceColumn(String[][] a, String[] b, int colnum) {

        if (b.length != a.length) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            a[i][colnum] = b[i];
        }
        return a;
    }

    public static String[] getColumn(String[][] a, int colnum) {
        String[] temp = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i][colnum];
        }
        return temp;
    }

    public static String printArray(String[][] a) {

        String s = "\t";
        for (int j = 0; j < a[0].length; j++) {
            s += "[" + j + "]\t";
        }

        for (int i = 0; i < a.length; i++) {
            s += "\n[" + i + "]";
            for (int j = 0; j < a[i].length; j++) {
                s += "\t" + a[i][j];
            }

        }
        return s;
    }

    public static String printArray(String[] a) {

        String s = "\t[0]";
        for (int i = 0; i < a.length; i++) {
            s += "\n[" + i + "]\t" + a[i];
        }
        return s;
    }

    public static String[][] transpose(String[][] a) {
        String[][] b = new String[a[0].length][a.length];
        for (int c = 0; c < a[0].length; c++) {
            for (int r = 0; r < a.length; r++) {
                b[c][r] = a[r][c];
            }
        }
        return b;
    }

    public static int[] replaceArray(int[] a, int[] b, int astart) {

        int offset = 0;
        for (int ia = astart; ia < (astart + b.length); ia++) {
            a[ia] = b[offset];
            offset++;
        }
        return a;
    }

    public static int[][] replaceColumn(int[][] a, int[] b, int colnum) {
        if (b.length != a.length) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            a[i][colnum] = b[i];
        }
        return a;
    }

    public static int[] getColumn(int[][] a, int colnum) {
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i][colnum];
        }
        return temp;
    }

    public static String printArray(int[][] a) {

        String s = "\t";
        for (int j = 0; j < a[0].length; j++) {
            s += "[" + j + "]\t";
        }

        for (int i = 0; i < a.length; i++) {
            s += "\n[" + i + "]";
            for (int j = 0; j < a[i].length; j++) {
                s += "\t" + a[i][j];
            }

        }
        return s;
    }

    public static String printArray(int[] a) {

        String s = "\t[0]";
        for (int i = 0; i < a.length; i++) {
            s += "\n[" + i + "]\t" + a[i];
        }
        return s;
    }

    public static int[][] transpose(int[][] a) {
        int[][] b = new int[a[0].length][a.length];
        for (int c = 0; c < a[0].length; c++) {
            for (int r = 0; r < a.length; r++) {
                b[c][r] = a[r][c];
            }
        }
        return b;
    }

    public static String printArray(double[][] a) {

        String s = "\t";
        for (int j = 0; j < a[0].length; j++) {
            s += "[" + j + "]\t";
        }

        for (int i = 0; i < a.length; i++) {
            s += "\n[" + i + "]";
            for (int j = 0; j < a[i].length; j++) {
                s += "\t" + a[i][j];
            }

        }
        return s;
    }

    public static String printArray(double[] a) {

        String s = "\t[0]";
        for (int i = 0; i < a.length; i++) {
            s += "\n[" + i + "]\t" + a[i];
        }
        return s;
    }

    public static String[] appendArray(String[] oldArray, String str) {
        String[] newArray = new String[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        newArray[newArray.length - 1] = str;
        return newArray;
    }

    public static RVector[] appendArray(RVector[] oldArray, RVector v) {
        RVector[] newArray = new RVector[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        newArray[newArray.length - 1] = v;
        return newArray;
    }

    public static double[] appendArray(double[] oldArray, double db) {
        double[] newArray = new double[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        newArray[newArray.length - 1] = db;
        return newArray;
    }

    public static double[] appendArray(double[] oldArray, double[] dbArray) {
        double[] newArray = new double[oldArray.length + dbArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        for (int i = 0; i < dbArray.length; i++) {
            newArray[oldArray.length + i] = dbArray[i];
        }
        return newArray;
    }

    public static int[] appendArray(int[] oldArray, int intv) {
        int[] newArray = new int[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        newArray[newArray.length - 1] = intv;
        return newArray;
    }

    /*
     Removes an object at a specified index from an array. If the object is only of length 1, returns null;
     */
    public static String[] removeStringFromArray(String[] obA, int index) {
        if (obA.length == 1) {
            return null;
        }
        String[] newArray = new String[obA.length - 1];
        int j;
        for (int i = 0; i < obA.length; i++) {
            if (i < index) {
                j = i;
            } else if (i == index) {
                continue;
            } else {
                j = i - 1;
            }
            newArray[j] = obA[i];
        }
        return newArray;
    }

    /*
     If a string appears in a string array, returns the index of its first appearance. If it does not contain it, returns -1;
     * No nulls allowed, or you might get an error (will not get an error if there is a null after the first index of desired string within the array)
     */
    public static int indexOfFirstStringInArray(String[] obA, String st) {
        if (st == null) {
            throw new RuntimeException("Null String passed to indexOfFirstArray");
        }
        for (int i = 0; i < obA.length; i++) {
            String temp = obA[i];
            if (temp == null) {
                throw new RuntimeException("Null String in Array passed to indexOfFirstArray");
            }
            if (temp.equals(st)) {
                return i;
            }
        }
        return -1;
    }
    /*
     Takes an array of objects, and returns a single of array of the unique (or distinct) objects in that array
     Input array must not be empty;
     */

    public static String[] getDistinctStrings(String[] obA) {
        // Put the first element of the old array into the new array
        int timesToLoop = obA.length - 1;
        String[] newA = {obA[0]};
        // remove the first element from the old array
        obA = R.removeStringFromArray(obA, 0);
        for (int i = 1; i <= timesToLoop; i++) {
            // if the next element is in the new array, remove it and move on otherwise
            if (R.indexOfFirstStringInArray(newA, obA[0]) > -1) {
                obA = R.removeStringFromArray(obA, 0);
                continue;
            }
            // otherwise, append the new distinct element to the new array, and then remove it from the old array
            newA = R.appendArray(newA, obA[0]);
            obA = R.removeStringFromArray(obA, 0);
        }
        return newA;
    }

    /* Gets all the first words in a file.
     If they start with numbers, they will not be retured, unless it is the first word
     Also, the file must have at least two nonempty lines
     Finally, the words returned will be distinct
     */
    public static String[] getAllFirstWordsInAFile(BufferedReader tb) {
        String[] wordArray = new String[1];
        wordArray[0] = "FIRST LINE - TEST";
        String temp, firstWord;
        Scanner s = new Scanner(tb);

        temp = nextNonEmptyLine(s);
        Scanner t = new Scanner(temp);
        firstWord = t.next();
        wordArray[0] = firstWord;
        do {
            temp = nextNonEmptyLine(s);
            t = new Scanner(temp);
            firstWord = t.next();
            if (Character.isLetter(firstWord.charAt(0))) {
                wordArray = R.appendArray(wordArray, firstWord);
            }
        } while (!firstWord.equals("EOF"));

        return R.getDistinctStrings(wordArray);
    }

    public static String nextNonEmptyLine(Scanner s) {
        String temp = null;
        while (s.hasNextLine()) {
            temp = s.nextLine();
            if (!temp.trim().isEmpty()) {
                return temp;
            }
        }
        return temp;

    }

    public static int getProductOfIntArrayElements(int[] iArray) {
        int product = iArray[0];
        for (int i = 1; i < iArray.length; i++) {
            product = product * iArray[i];
        }
        return product;
    }

    public static int getSumOfIntArrayElements(int[] iArray) {
        int sum = iArray[0];
        for (int i = 1; i < iArray.length; i++) {
            sum += iArray[i];
        }
        return sum;
    }

    public static double[] doubleFiller(String line) {

        int starIndex;
        String preStar, postStar;

        String[] subExpressions = new String[1];
        int[] numArray = new int[1];
        double[] valueArray = new double[1];

        // Find the number of different values included on the line
        Scanner t = new Scanner(line);
        subExpressions[0] = t.next();
        starIndex = subExpressions[0].trim().indexOf("*");
        preStar = subExpressions[0].trim().substring(0, starIndex);
        postStar = subExpressions[0].trim().substring(starIndex + 1);
        numArray[0] = Integer.parseInt(preStar);
        valueArray[0] = Double.parseDouble(postStar);

        int count = 1;
        while (t.hasNext()) {
            subExpressions = R.appendArray(subExpressions, t.next());
            starIndex = subExpressions[count].trim().indexOf("*");
            preStar = subExpressions[count].trim().substring(0, starIndex);
            postStar = subExpressions[count].trim().substring(starIndex + 1);
            numArray = R.appendArray(numArray, Integer.parseInt(preStar));
            valueArray = R.appendArray(valueArray, Double.parseDouble(postStar));
            count++;
        }
        double[] temp = new double[R.getSumOfIntArrayElements(numArray)];
        int index = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < numArray[i]; j++) {
                temp[index] = valueArray[i];
                index++;
            }
        }
        return temp;
    }

    public static double[] fillArrayWithScalar(int numtimes, double value) {
        double[] temp = new double[numtimes];
        for (int i = 0; i < numtimes; i++) {
            temp[i] = value;
        }
        return temp;
    }

    public static boolean thereAreDuplicates(String[] sts) {
        for (int i = 0; i < sts.length; i++) {
            for (int j = i + 1; j < sts.length; j++) {
                if (sts[i].trim().equals(sts[j].trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean thereAreDuplicates(double[] dbs) {
        for (int i = 0; i < dbs.length; i++) {
            for (int j = i + 1; j < dbs.length; j++) {
                if (Math.abs(dbs[i] - dbs[j]) < .00000000001) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean thereAreDuplicates(NumericObject[] dbs) {
        for (int i = 0; i < dbs.length; i++) {
            for (int j = i + 1; j < dbs.length; j++) {
                if (Math.abs(dbs[i].getValue() - dbs[j].getValue()) < .00000000001) {
                    return true;
                }
            }
        }
        return false;
    }

  
    
}
