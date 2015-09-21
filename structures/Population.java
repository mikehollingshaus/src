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
    private final int totalSize, totFemSize, totMaleSize;

    private final Time date;
    private final Region region;
    private final SexAgeStructure sexAgeStructure;

    private final PopulationTreeStructure popStruct;
    private final Mortality mort;
    private final Fertility fert;
    private final Migration nonLaborMig, laborMig;

    private Population[] subPopulations;

    /**
     * Constructor for a population without children (subpopulations). It needs
     * an age-sex structure, which will also calculate the size
     */
    public Population(Time date, Region region, SexAgeStructure sexAgeStructure, PopulationTreeStructure structure, Mortality mort, Fertility fert, Migration nonLaborMig, Migration laborMig) {
        this.popStruct = structure;
        if (structure.numChildren > 0) {
            throw new RuntimeException("Tried to create non-Leaf Population with a Leaf Population Constructor. This would compromise integrity of the population size");
        }

        this.date = date;
        this.region = region;
        this.sexAgeStructure = sexAgeStructure;

        this.mort = mort;
        this.fert = fert;
        this.nonLaborMig = nonLaborMig;
        this.laborMig = laborMig;
        
        
        this.totalSize = size;
    }

    /*
     Constructor for a population with children (subpopulations). It does not take a size, but calculates it recursively from its subpopulations.
     */
    public Population(Time date, Region region, SexAgeStructure sexAgeStructure, PopulationTreeStructure compSig, Mortality mort, Fertility fert, Migration nonLaborMig, Migration laborMig) {
        this.popStruct = compSig;
        if (compSig.numChildren == 0) {
            throw new RuntimeException("Tried to create a Leaf Population with a non-Leaf Population Constructor. This would compromise integrity of the population size");
        }

        this.date = date;
        this.region = region;
        this.sexAgeStructure = sexAgeStructure;

        this.mort = mort;
        this.fert = fert;
        this.nonLaborMig = nonLaborMig;
        this.laborMig = laborMig;

        // The size is simply the sum of the size of the children;
        int tempSize = 0;
        for (Population p : subPopulations) {
            tempSize += p.totalSize;
        }
        this.totalSize = tempSize;

    }

    /*
     Adds a population, returning a new population with the same structure, Time d, Region d, and given mortality, fertility, and migration forces. The population must have the same compositional structure. 
     Details: this is done recursively.
     */
    public Population addPopulation(Population p, Time d, Region g, Mortality m, Fertility f, Migration nLM, Migration lM) {
        if (hasSameStructure(p)) {
            throw new RuntimeException("Tried to add two populations with different structures");
        }
        // If this is a leaf population, then return a new population that adds the two sizes;
        // Remember, to create a new population, create the new compositional signature and all the children;

        if (isLeaf() && p.isLeaf()) {
            
            return new Population(totalSize + p.totalSize, d, g, popStruct, m, f, nLM, lM);
        }

        return null;
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
