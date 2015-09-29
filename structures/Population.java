/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import structures.DemographicForce.DemographicForceType;
import UtahDemEconCoCompMod_v001.CohortComponentModel;

/**
 *
 * @author Mike Hollingshaus The population, as a data structure, may be
 * represented by a tree. Each population is a node, consisting of a value
 * (Time, Region, signature, etc...) and a list of references to subpopulations
 * (children) If a greater population is found, each population has a parent
 * population.
 *
 */
public class Population extends DefaultMutableTreeNode {

    private String name;

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

        updateChildren();
        // Update the age structures and population sizes appropriately
        buildAgeDistributionFromSubPops();
    }
    /*
     Constructs a leaf population with the supplied value
     */

    private void updateChildren() {
        this.children = new Vector(Arrays.asList(subPopulations));
      
    }

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

    public void setMaleMig(Migration mig) {
        value.setMaleMig(mig);
    }

    public void setFemMig(Migration mig) {
        value.setFemMig(mig);
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
    public Population addPopulation(Population p) {
        if (!hasSameStructure(p)) {
            throw new RuntimeException("Tried to add two populations with different structures");
        }
        PopValue pv = value.twin();
        // If this is a leaf population, then return a new population that adds the two sizes;
        AgeDistribution amale, afem;
        if (isLeaf()) {
            amale = new AgeDistribution(value.getMaleDistribution().add(p.value.getMaleDistribution()));
            afem = new AgeDistribution(value.getFemDistribution().add(p.value.getFemDistribution()));

            // Set the popvalue, with default demographic forces being from this object (not the one passed along in the parameter)
            pv.updateDistribution(amale, afem);
            pv.setDefaultDemForces(this);

            return new Population(pv);
        }
        // Otherwise, return a new population that is the sum of the subpopulations

        // First, create a new popvalue
        pv.setDefaultDemForces(this);

        Population[] newSubPops = new Population[subPopulations.length];
        // add each subpopulation
        for (int i = 0; i < subPopulations.length; i++) {
            newSubPops[i] = subPopulations[i].addPopulation(p);
        }
        // Pass along the subpopulations
        return new Population(pv, newSubPops);
    }

    /*
     Subtracts a population, returning a new population with the same structure, Time d, Region d, and given mortality, fertility, and migration forces. The population must have the same compositional structure. 
     Details: Populations are added by summing the male and female age structures.
     Note, if this is not a leaf population, then its the sum of the children components (recursive)
     */
    public Population subtractPopulation(Population p) {
        if (!hasSameStructure(p)) {
            throw new RuntimeException("Tried to subtract two populations with different structures");
        }
        PopValue pv = value.twin();
        // If this is a leaf population, then return a new population that subtracts the two sizes;
        AgeDistribution amale, afem;
        if (isLeaf()) {
            amale = new AgeDistribution(value.getMaleDistribution().subtract(p.value.getMaleDistribution()));
            afem = new AgeDistribution(value.getFemDistribution().subtract(p.value.getFemDistribution()));

            // Set the popvalue, with default demographic forces being from this object (not the one passed along in the parameter)
            pv.updateDistribution(amale, afem);
            pv.setDefaultDemForces(this);

            return new Population(pv);
        }
        // Otherwise, return a new population that is the sum of the subpopulations

        // First, create a new popvalue
        pv.setDefaultDemForces(this);

        Population[] newSubPops = new Population[subPopulations.length];
        // add each subpopulation
        for (int i = 0; i < subPopulations.length; i++) {
            newSubPops[i] = subPopulations[i].subtractPopulation(p);
        }
        // Pass along the subpopulations
        return new Population(pv, newSubPops);
    }
    /*
     Returns whether there are subpopulations.
     If there are no subpopulations, then it is a "leaf" population
     */

    /*
     Multiplies a population by a constant. This is important when, 
     for example, wishing to subtract half the population of deaths before applying birth rates
     */
    public Population multiplyByConstant(double val) {
        PopValue pv = value.twin();
        AgeDistribution amale, afem;
        if (isLeaf()) {
            amale = new AgeDistribution(value.getMaleDistribution().multiply(new AgeDistribution(val)));
            afem = new AgeDistribution(value.getFemDistribution().multiply(new AgeDistribution(val)));
            // Set the popvalue, with default demographic forces being from this object (not the one passed along in the parameter)
            pv.updateDistribution(amale, afem);
            pv.setDefaultDemForces(this);
            return new Population(pv);
        }
        pv.setDefaultDemForces(this);
        Population[] newSubPops = new Population[subPopulations.length];
        // add each subpopulation
        for (int i = 0; i < subPopulations.length; i++) {
            newSubPops[i] = subPopulations[i].multiplyByConstant(val);
        }
        // Pass along the subpopulations
        return new Population(pv, newSubPops);
    }

    /*
     Two populations have the same structure if their signatures are equal, their respective children have the same structure;
     Dates can be different, geographies can be different, 
     age and sex structures for now can be different, but they are currently typed so they must be be male and female with ages 0-101, so not an issue for now
     */
    public boolean hasSameStructure(Population p) {
        // Check they have the same number of children;
        // If they are both leafs, then they do
        if (isLeaf() && p.isLeaf()) {
            return true;
        }
        // if they were not both leafs, but one alone is, then they do not have the same number of children 
        if (isLeaf() || p.isLeaf()) {
            return false;
        } // If neither was a leaf, then check they have the same number of children
        else if (subPopulations.length != p.subPopulations.length) {
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

    public Population twin() {
        PopValue pv = value.twin();
        if (isLeaf()) {
            return new Population(pv);
        }
        Population[] tempSubPops = new Population[subPopulations.length];

        for (int i = 0; i < subPopulations.length; i++) {
            tempSubPops[i] = subPopulations[i].twin();
        }
        return new Population(pv, tempSubPops);
    }

    public PopValue getPopValue() {
        return value;
    }

    public Population applyForce(DemographicForceType dft) {
        // If it's a leaf node, apply the force

        // if it's a leaf node, simply apply a new population that is the multiplication of the force by this pop
        if (isLeaf()) {
            Population p = new Population(value.twin());
            AgeDistribution newMalePop;
            AgeDistribution newFemPop;
            // multiply the male mortality schedule or migration schedule by the male and female populations
            switch (dft) {

                case MORTALITY:
                    newMalePop = value.getMaleDistribution().multiply(value.getMaleMort().ageDistribution);
                    newFemPop = value.getFemDistribution().multiply(value.getFemMort().ageDistribution);
                    p.value.updateDistribution(newMalePop, newFemPop);
                    return p;
                case FERTILITY:
                    // Fertility is a special case. Multiply the fertility schedule by the female distribution
                    AgeDistribution births = value.getFemDistribution().multiply(value.getFert().ageDistribution);
                    // The proportion that will be male is this function of the Secondary sex ratio
                    double propMale = CohortComponentModel.SEX_RATIO / (1 + CohortComponentModel.SEX_RATIO);
                    // Now, get the distribution (by age of mother) of the new male and female births
                    newMalePop = births.multiply(new AgeDistribution(propMale));
                    newFemPop = births.subtract(newMalePop);
                    p.value.updateDistribution(newMalePop, newFemPop);
                    return p;
                case MIGRATION:
                    newMalePop = value.getMaleDistribution().multiply(value.getMaleMig().ageDistribution);
                    newFemPop = value.getFemDistribution().multiply(value.getFemMig().ageDistribution);
                    p.value.updateDistribution(newMalePop, newFemPop);
                    return p;
            }
        } else {
            // not a leaf
            // apply the force for the subchildren
            Population[] newSubPops = new Population[subPopulations.length];
            switch (dft) {
                case MORTALITY:
                    for (int i = 0; i < subPopulations.length; i++) {
                        newSubPops[i] = subPopulations[i].applyForce(DemographicForceType.MORTALITY);
                    }
                    return new Population(value.twin(), newSubPops);
                case FERTILITY:
                    for (int i = 0; i < subPopulations.length; i++) {
                        newSubPops[i] = subPopulations[i].applyForce(DemographicForceType.FERTILITY);
                    }
                    return new Population(value.twin(), newSubPops);
                case MIGRATION:
                    for (int i = 0; i < subPopulations.length; i++) {
                        newSubPops[i] = subPopulations[i].applyForce(DemographicForceType.MIGRATION);
                    }
                    return new Population(value.twin(), newSubPops);
            }
        }
        return null;
    }

    /*
     This takes a population, and a population of births, and "ages" it. They must have the same structure
     The returned population is the target population.
     For each sex, age 0 is the total number of births, age 100 (which is 100+) is the old age 99 + 100
     And, ages 1-99 are the old ages 0-98
     */
    public Population agePop(Population births, int months) {
        if (!hasSameStructure(births)) {
            throw new RuntimeException("Tried to age a population with births of a different structure");
        }
        PopValue pv = new PopValue(new Time(value.getDate(), 12), value.getRegion(), value.getStatus(), value.getHome());

        AgeDistribution amale, afem;
        if (isLeaf()) {
            double[] oldMale = value.getMaleDistribution().getData();
            double[] oldFem = value.getFemDistribution().getData();
            int topAge = AgeDistribution.NUM_AGES - 1;
            double[] maleVal = new double[topAge + 1];
            double[] femVal = new double[topAge + 1];
            maleVal[0] = births.value.getMaleSize();
            femVal[0] = births.value.getFemSize();
            maleVal[topAge] = oldMale[topAge] + oldMale[topAge - 1];
            femVal[topAge] = oldFem[topAge] + oldFem[topAge - 1];
            for (int i = 1; i < topAge; i++) {
                maleVal[i] = oldMale[i - 1];
                femVal[i] = oldFem[i - 1];
            }

            amale = new AgeDistribution(maleVal);
            afem = new AgeDistribution(femVal);

            // Set the popvalue, with default demographic forces being from this object (not the one passed along in the parameter)
            pv.updateDistribution(amale, afem);
            pv.setDefaultDemForces(this);

            return new Population(pv);
        }
        pv.setDefaultDemForces(this);
        Population[] newSubPops = new Population[subPopulations.length];
        // add each subpopulation
        for (int i = 0; i < subPopulations.length; i++) {
            newSubPops[i] = subPopulations[i].agePop(births.subPopulations[i], months);
        }
        // Pass along the subpopulations
        return new Population(pv, newSubPops);
    }

    public String getName() {
        if (name == null) {
            return "Unnamed Population";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public PopValue getValue() {
        return value;
    }

    public Population[] getSubPopulations() {
        return subPopulations;
    }
    /*
     public DefaultMutableTreeNode getTreeNode() {
     return new DefaultMutableTreeNode(name);
     }
     */

    public String toString() {
        return name;
    }

    public double getTotMedianAge() {
//        double medianAge = 0;
        double count = 0;
        double medPoint = value.getTotalSize() / 2;
        double[] totDist = value.getTotDistribution().getData();
        int stopIndex = 0;
        for (int i = 0; i < totDist.length; i++) {
            count += totDist[i];
            if (count > medPoint) {
                stopIndex = i;
                break;
            }
        }
//        double lowMed = totDist[stopIndex];
//        double highMed = totDist[stopIndex + 1];
                
        return stopIndex;
    }
    
    public double getDependencyRatio(){
        double totUnder15 = 0;
        double tot65Plus = 0;
        double totInBetween = 0;
        double[] totDist = value.getTotDistribution().getData();
        for (int i = 0; i < 15; i++){
            totUnder15+=totDist[i];
        }
        for (int i = 65; i < totDist.length; i++){
            tot65Plus+=totDist[i];
        }
        double dependent = totUnder15+tot65Plus;
        totInBetween = value.getTotalSize()-dependent;
        
        return dependent/totInBetween*100;
    
    }

}
