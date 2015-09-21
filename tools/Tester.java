/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import UtahDemEconCoCompMod_v001.WorkBench;

/**
 *
 * @author u0214256
 */
public class Tester {

    private final WorkBench workBench;
    public Tester(WorkBench wb) {
        this.workBench=wb;
    }
    
    public void test(){
        System.out.println("test");
        // Create a population of given structure. Specifically, One Region (Utah), at one time (April 1, 2010)
        // There is a ragged array representing the dimensions of the population I desire to build;
        // Dimension one is time, 2 is space, three is age, four is sex, five is whether a resident, six is hometype, and seven is special pop
        // For simplicity, start with one time and one space (Utah on April 1, 2010). I should be able to get the population of Utah into a tree
        
                
    }
    
}
