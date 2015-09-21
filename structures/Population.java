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

    // The population value (i.e., the datapoints unique to this population (See object PopValue)
    PopValue value;
    // An ordered array of subpopulations
    private Population[] subPopulations;

    /*
     Constructs a population with the supplied value, from the supplied subpopulations
     */
    public Population(PopValue v, Population[] subPops) {
        // Store the value and the array of children
        this.value = v;
        this.subPopulations = subPops;

        // Update the age structures and population sizes appropriately
        buildAgeDistributionFromSubPops();
    }
    /*
     Constructs a leaf population with the supplied value
     */

    public Population(PopValue v) {
        // Store the value and the array of children
        this.value = v;
    }

    private void buildAgeDistributionFromSubPops() {
        if (isLeaf()) {
            throw new RuntimeException("Tried to get an age distribution from subpopulations for a leaf population (which does not have subpopulations");
        }

        AgeDistribution tempMaleDist = new AgeDistribution(0);
        AgeDistribution tempFemDist = new AgeDistribution(0);

        for (Population p : subPopulations) {
            tempMaleDist = tempMaleDist.add(p.value.getMaleDistribution());
            tempFemDist = tempFemDist.add(p.value.getFemDistribution());
        }
        value.updateDistribution(tempMaleDist, tempFemDist);
    }

    public void setMaleMort(Mortality m) {
        value.setMaleMort(m);
    }

    public void setFemMort(Mortality m) {
        value.setFemMort(m);
    }
    
    public void setFert(Fertility f) {
        value.setFert(f);
    }

    public void setMaleNonLaborMig(Migration nlm) {
        value.setMaleNonLaborMig(nlm);
    }
    
    public void setFemNonLaborMig(Migration nlm) {
        value.setFemNonLaborMig(nlm);
    }

    public void setMaleLaborMig(Migration lm) {
        value.setMaleLaborMig(lm);
    }
    
    public void setFemLaborMig(Migration lm) {
        value.setFemLaborMig(lm);
    }

    public void bearSubPopulation(Population p) {
        Population[] tempSubs = new Population[subPopulations.length + 1];
        for (int i = 0; i < subPopulations.length; i++) {
            tempSubs[i] = subPopulations[i];
        }
        tempSubs[tempSubs.length - 1] = p;
    }
    
    /*
     Adds a population, returning a new population with the same structure, Time d, Region d, and given mortality, fertility, and migration forces. The population must have the same compositional structure. 
     Details: Populations are added by summing the male and female age structures.
     Note, if this is not a leaf population, then its the sum of the children components (recursive)
        
        
     */
    /*public Population addPopulation(Population p, Time d, Region g) {
        if (hasSameStructure(p)) {
            throw new RuntimeException("Tried to add two populations with different structures");
        }
        // If this is a leaf population, then return a new population that adds the two sizes;
        Population totPop;
        AgeDistribution amale, afemale;

        if (isLeaf()) {
            amale = new AgeDistribution(maleStructure.add(p.maleStructure).getData());
            afemale = new AgeDistribution(femStructure.add(p.femStructure).getData());
            totPop = new Population(d, g, amale, afemale, popStruct, mort, fert, nonLaborMig, laborMig);
            return totPop;
        }

        // Otherwise, return a new population that is the sum of the subpopulations
        amale = new AgeDistribution(maleStructure.getData());
        afemale = new AgeDistribution(femStructure.getData());

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
        return subPopulations == null;
    }

    /*
     Two populations have the same structure if their signatures are equal, their respective children have the same structure;
     Dates can be different, geographies can be different, 
     age and sex structures for now can be different, but they are currently typed so they must be be male and female with ages 0-101, so not an issue for now
     */
    public boolean hasSameStructure(Population p) {
        // Check they have the same number of children;
        if (subPopulations.length!=p.subPopulations.length) {
            return false;
        }
        // Otherwise, check if the ordered children have the same structure
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
