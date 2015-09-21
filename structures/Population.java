/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author Mike Hollingshaus The population, as a data structure, may be
 * represented by a tree. Each population is a node, consisting of a value
 * (Time, Region, signature, etc...) and a list of references to subpopulations
 * (children) If a greater population is found, each population has a parent
 * population.
 *
 */
public class Population {

    // By definition, a population must be bounded by space and time. In this model, time is indicated by a time (year, month, day),
    //    and space is indicated by a region
    //          Additionally, for our purposes a population can have a "signature", which is determined by a series of strata.
    //              An example stratum is Age, another is Sex
    private final double totalSize, femSize, maleSize;

    private final Time date;
    private final Region region;

    private double[] totData;
    private AgeStructure totSructure, maleStructure, femStructure;

    private final int numChildren;
    private final Mortality mort;
    private final Fertility fert;
    private final Migration nonLaborMig, laborMig;

    private Population[] subPopulations;

    /**
     * Constructor for a population without children (subpopulations). It needs
     * an age-sex structure, which will also calculate the size
     */
    public Population(Time date, Region region, AgeStructure maleStructure, AgeStructure femStructure, int numChildren, Mortality mort, Fertility fert, Migration nonLaborMig, Migration laborMig) {

        if (numChildren > 0) {
            throw new RuntimeException("Tried to create non-Leaf Population with a Leaf Population Constructor. This would compromise integrity of the population size");
        }

        this.date = date;
        this.region = region;
        this.maleStructure = maleStructure;
        this.femStructure = femStructure;

        this.mort = mort;
        this.fert = fert;
        this.nonLaborMig = nonLaborMig;
        this.laborMig = laborMig;

        this.maleSize = maleStructure.sumData();
        this.femSize = femStructure.sumData();
        this.totalSize = maleSize + femSize;
    }

    /*
     Constructor for a population with children (subpopulations). It does not take a size, but calculates it recursively from its subpopulations.
     */
    public Population(Time date, Region region, PopulationTreeStructure structure, Mortality mort, Fertility fert, Migration nonLaborMig, Migration laborMig) {
        this.popStruct = structure;
        if (structure.numChildren == 0) {
            throw new RuntimeException("Tried to create a Leaf Population with a non-Leaf Population Constructor. This would compromise integrity of the population size");
        }

        this.date = date;
        this.region = region;

        this.mort = mort;
        this.fert = fert;
        this.nonLaborMig = nonLaborMig;
        this.laborMig = laborMig;

        // The size is simply the sum of the size of the children;
        int tempMaleSize = 0;
        int tempFemSize = 0;
        for (Population p : subPopulations) {
            tempMaleSize += p.maleSize;
            tempFemSize += p.femSize;
        }
        this.maleSize = tempMaleSize;
        this.femSize = tempFemSize;
        this.totalSize = maleSize + femSize;
    }

    /*
     Construct a population from the subpopulations
     */
    public Population(Time date, Region region, Population[] subPopulations) {
        this.date = date;
        this.region = region;
        this.subPopulations = subPopulations;

        AgeStructure fSt = new AgeStructure(0);
        AgeStructure mSt = new AgeStructure(0);
        for (Population p : subPopulations) {
            fSt = new AgeStructure(fSt.add(p.femStructure).getData());
            mSt = new AgeStructure(mSt.add(p.maleStructure).getData());
        }

        this.popStruct = new PopulationTreeStructure();

        this.popStruct = structure;

        this.date = date;
        this.region = region;

        this.mort = mort;
        this.fert = fert;
        this.nonLaborMig = nonLaborMig;
        this.laborMig = laborMig;

        // The size is simply the sum of the size of the children;
        int tempMaleSize = 0;
        int tempFemSize = 0;
        for (Population p : subPopulations) {
            tempMaleSize += p.maleSize;
            tempFemSize += p.femSize;
        }
        this.maleSize = tempMaleSize;
        this.femSize = tempFemSize;
        this.totalSize = maleSize + femSize;
    }

    /*
     To simplify, cannot include age structures by sex, and overall.
     So, if tot is null, male and female cannot be.
     And, if male or female is null, tot cannot be
     */
    /*
     private void validateStructures(AgeStructure tot, AgeStructure m, AgeStructure f){
     if (tot==null && (m==null || f==null)){
     throw new RuntimeException("Tried to ")
     }
            
     }
     */
    /*
     Adds a population, returning a new population with the same structure, Time d, Region d, and given mortality, fertility, and migration forces. The population must have the same compositional structure. 
     Details: Populations are added by summing the male and female age structures.
     Note, if this is not a leaf population, then its the sum of the children components (recursive)
        
        
     */
    public Population addPopulation(Population p, Time d, Region g) {
        if (hasSameStructure(p)) {
            throw new RuntimeException("Tried to add two populations with different structures");
        }
        // If this is a leaf population, then return a new population that adds the two sizes;
        Population totPop;
        AgeStructure amale, afemale;

        if (isLeaf()) {
            amale = new AgeStructure(maleStructure.add(p.maleStructure).getData());
            afemale = new AgeStructure(femStructure.add(p.femStructure).getData());
            totPop = new Population(d, g, amale, afemale, popStruct, mort, fert, nonLaborMig, laborMig);
            return totPop;
        }

        // Otherwise, return a new population that is the sum of the subpopulations
        amale = new AgeStructure(maleStructure.getData());
        afemale = new AgeStructure(femStructure.getData());

        Population[] tempSubPops = new Population[subPopulations.length];

        for (int i = 0; i < subPopulations.length; i++) {
            Population p1 = subPopulations[i];
            Population p2 = p.subPopulations[i];
            tempSubPops[i] = p1.addPopulation(p2, d, g);

        }

        return new Population(d, g, amale, afemale, popStruct, mort, fert, nonLaborMig, laborMig);
    }

    /*
     Subtracts a population. The population must have the same compositional structure. 
     Details: this is done recursively. Each subpopulation is added
     */
    public Population substractPopulation(Population p) {
        if (hasSameStructure(p)) {
            throw new RuntimeException("Tried to subtract two populations with different structures");
        }
        return null;
    }

    public void applyForce(DemographicForce f) {
    }

    /*
     Returns whether there are subpopulations.
     If there are no subpopulations, then it is a "leaf" population
     */
    private boolean isLeaf() {
        return popStruct.numChildren == 0;
    }

    /*
     Two populations have the same structure if their signatures are equal, their respective children have the same structure;
     Dates can be different, geographies can be different, 
     age and sex structures for now can be different, but they are currently typed so they must be be male and female with ages 0-101, so not an issue for now
     */
    public boolean hasSameStructure(Population p) {
        // Check that their compositional signatures are the same;
        if (!popStruct.equals(p.popStruct)) {
            return false;
        }
        // If we got to this point, and there are no children, then the populations have the same structure
        if (isLeaf() && p.isLeaf()) {
            return true;
        }
        // Otherwise, check if the children have the same structure
        for (int i = 0; i < subPopulations.length; i++) {
            Population p1 = subPopulations[i];
            Population p2 = p.subPopulations[i];
            if (!p1.hasSameStructure(p2)) {
                return false;
            }
        }
        // If we passed all these tests, then the populations have the same structure;
        return true;
    }
}
