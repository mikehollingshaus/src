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

    private double totalCount;
    private int year, month, day;
    private AgeSchedule ageCounts;

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
        checkDateValues(year, month, day);
        this.ageCounts = ac;
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalCount = ageCounts.getTotalValue();
    }

    public void checkDateValues(int year, int month, int day) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("Month must be an integer in the interval [1,12]");
        }
        if (day < 1 || day > 31) {
            throw new RuntimeException("Day must be an integer in the interval [1,31]");
        }
        switch (month) {
            case 9:
            case 4:
            case 6:
            case 11:
                if (day > 30) {
                    throw new RuntimeException("For the months Sept, Apr, June, and Nov, day must be an integer in the interval [1,30]");
                }
                break;
            case 2:
                if (day > 29) {
                    throw new RuntimeException("For the month of February, day must be an integer in the interval [1,29]");
                }

                if (day > 28 && !((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
                    throw new RuntimeException("For the month of February in non-leap years, day must be an integer in the interval [1,28]");
                }
                break;
            default:
                break;

        }
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
        NumericObject tempNum = new NumericObject(calcCounts.getValueAtIndex(calcCounts.length-1), false);
        newAgeNums[newAgeNums.length - 1] = new AgeSpecificNumber(newAgeNums[newAgeNums.length - 1].add(tempNum), ageCounts.getValueAtIndex(0));

        System.out.println("Test");

        AgeSchedule newSchedule = new AgeSchedule(newAgeNums);
        return new PointPopulation(newSchedule, year + 1, month, day);
    }

    private void checkScheduleLength(DemographicComponent dc) {
        if (dc.getAgeSchedule().length != ageCounts.length) {
            throw new RuntimeException("Tried to apply an age-schedule to a population with a different number of age groupings");
        }
    }

}
