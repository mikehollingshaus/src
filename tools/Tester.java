/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import UtahDemEconCoCompMod_v001.WorkBench;
import structures.AgeDistribution;

/**
 *
 * @author u0214256
 */
public class Tester {

    private final WorkBench workBench;

    public Tester(WorkBench wb) {
        this.workBench = wb;
    }

    public void test() {
        System.out.println("--Test Begin--");
//        testAgeStructure();
        System.out.println("--Test End--");
        // Read in the census data
    }

    public void testAgeStructure() {
        AgeDistribution fem1 = new AgeDistribution(5);
        AgeDistribution male1 = new AgeDistribution(14);
        AgeDistribution total = fem1.add(male1);
        AgeDistribution diff = fem1.subtract(male1);
        double[] dat = male1.getData();

        AgeDistribution rand = new AgeDistribution(multDoubleRand(dat));

    }

    public double[] multDoubleRand(double[] a) {
        double[] tempD = a;
        for (int i = 0; i < a.length; i++) {
            tempD[i] *= Math.round(Math.random() * 100) / Math.E;
        }
        return tempD;
    }

}
