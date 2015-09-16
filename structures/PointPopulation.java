/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import mathematicalAttributes.AgeSpecificNumber;
import mathematicalAttributes.NumericObject;
import mathematicalAttributes.NumericObjectVector;

/**
 *
 * @author u0214256
 */
public class PointPopulation {

    private String name;
    private PopDate date;
    private double size;
    Mortality mort;
    Fertility fert;
    Migration nonLMig, lMig;
    PointPopulation[] subPops;
    PopDefinition definition;
    
    
    // A way to get the appropriate populations out of this arrayint[][][] 
    

    /*private Fertility fertilityObject;
     private Mortality mortalityObject;
     private Migration migrationObject;*/
    public double getTotalCount() {
        return totalCount;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public AgeSchedule getAgeCounts() {
        return ageCounts;
    }

    public PointPopulation(AgeSchedule ac, int year, int month, int day) {

        this.ageCounts = ac;
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalCount = ageCounts.getTotalValue();
    }

    public PointPopulation applyAgeMortalitySchedule(Mortality m) {
        checkScheduleLength(m);
        // Get the survived population
        NumericObjectVector msched = m.getAgeSchedule();
        NumericObjectVector oneSched = new NumericObjectVector(ageCounts.length, new NumericObject(1, false));
        NumericObjectVector survRates = oneSched.subtractO(msched);
        NumericObjectVector calcCounts = ageCounts.multiplyO(survRates);
        AgeSpecificNumber[] newAgeNums = new AgeSpecificNumber[calcCounts.length];

        // "Survive" it to the next year. The top group simply has the new numbers added in, the first group becomes 0, and the intermediate groups just move up one spot
        newAgeNums[0] = new AgeSpecificNumber(0, ageCounts.getValueAtIndex(0));
        for (int i = 0; i < newAgeNums.length - 1; i++) {
            newAgeNums[i + 1] = new AgeSpecificNumber(calcCounts.getValueAtIndex(i), ageCounts.getValueAtIndex(i));
        }
        NumericObject tempNum = new NumericObject(calcCounts.getValueAtIndex(calcCounts.length - 1), false);
        newAgeNums[newAgeNums.length - 1] = new AgeSpecificNumber(newAgeNums[newAgeNums.length - 1].add(tempNum), ageCounts.getValueAtIndex(0));

        System.out.println("Test");

        AgeSchedule newSchedule = new AgeSchedule(newAgeNums);
        return new PointPopulation(newSchedule, year + 1, month, day);
    }

    private void checkScheduleLength(DemographicForce dc) {
        if (dc.getAgeSchedule().length != ageCounts.length) {
            throw new RuntimeException("Tried to apply an age-schedule to a population with a different number of age groupings");
        }
    }

}
