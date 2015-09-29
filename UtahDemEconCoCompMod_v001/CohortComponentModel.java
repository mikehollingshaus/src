/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import mathematicalAttributes.NumericObjectVector;
import rules.ModelConstraints;
import structures.AgeDistribution;
import structures.DemographicForce.DemographicForceType;
import structures.Fertility;
import structures.Home;
import structures.Home.HomeType;
import structures.Migration;
import structures.Mortality;
import structures.PopValue;
import structures.Population;
import structures.Region;
import structures.Status;
import structures.Status.StatusType;
import structures.Time;
import tools.DataFrame;

/**
 *
 * @author u0214256
 */
public class CohortComponentModel {

    public static final double SEX_RATIO = 1.05;
    private final ModelConstraints modelConstraints;
    private final DataFrame dataFrame;
//    private final Population pop0;
    Population basePop;
    private Population[] stockPops, deathPops, migrantPops, birthPops;
    private Population treeOfStockPops, treeOfDeathPops, treeOfMigrantPops, treeOfBirthPops;
    private Population allCCPops;

    public CohortComponentModel(ModelConstraints mc, DataFrame datf) {
        this.modelConstraints = mc;
        this.dataFrame = datf;
        this.stockPops = new Population[modelConstraints.getNumYears()];
        this.deathPops = new Population[modelConstraints.getNumYears() - 1];
        this.migrantPops = new Population[modelConstraints.getNumYears() - 1];
        this.birthPops = new Population[modelConstraints.getNumYears() - 1];
    }

    public void build1() {

        // Build up the population;
        // First, create the necessary age distributions from the data
        NumericObjectVector maleMortV = (NumericObjectVector) dataFrame.var("t.m.qx");
        NumericObjectVector femMortV = (NumericObjectVector) dataFrame.var("t.f.qx");
        NumericObjectVector fertV = (NumericObjectVector) dataFrame.var("asrb");
        NumericObjectVector maleMigV = (NumericObjectVector) dataFrame.var("t.m.mig");
        NumericObjectVector femMigV = (NumericObjectVector) dataFrame.var("t.f.mig");
        NumericObjectVector malePopV = (NumericObjectVector) dataFrame.var("t.m.pop");
        NumericObjectVector femPopV = (NumericObjectVector) dataFrame.var("t.f.pop");

        AgeDistribution maleMort = new AgeDistribution(maleMortV.getValues());
        AgeDistribution femMort = new AgeDistribution(femMortV.getValues());
        AgeDistribution fert = new AgeDistribution(fertV.getValues());
        AgeDistribution maleMig = new AgeDistribution(maleMigV.getValues());
        AgeDistribution femMig = new AgeDistribution(femMigV.getValues());
        AgeDistribution malePop = new AgeDistribution(malePopV.getValues());
        AgeDistribution femPop = new AgeDistribution(femPopV.getValues());

        // Create the population
        // First, create the population value
        this.basePop = new Population(new PopValue(new Time(2010, 4, 1), new Region("Utah State"), new Status(StatusType.NORMAL_POP), new Home(HomeType.HOUSEHOLD), malePop, femPop));
        basePop.setName("Utah State Population 2010");
        // Set the Demographic forces

        basePop.setMaleMort(new Mortality(maleMort));
        basePop.setFemMort(new Mortality(femMort));
        basePop.setFert(new Fertility(fert));
        // For now, just use the one migration value
        basePop.setMaleMig(new Migration(maleMig));
        basePop.setFemMig(new Migration(femMig));

        project();

    }

    public void project() {
        stockPops[0] = basePop;
        Population targetPop = stockPops[0];

        int yr0 = modelConstraints.getBegYear();
        int yrk = modelConstraints.getEndYear();

        for (int i = modelConstraints.getBegYear(); i < yrk; i++) {
            Population launchPop = targetPop;
            Population deaths = launchPop.applyForce(DemographicForceType.MORTALITY);
            deaths.setName("Utah State Deaths " + i);
            Population postMort = launchPop.subtractPopulation(deaths);
            Population migrants = postMort.applyForce(DemographicForceType.MIGRATION);
            migrants.setName("Utah State Migrants " + i);
            Population postMig = postMort.addPopulation(migrants);
            Population halfDeaths = deaths.multiplyByConstant(0.5);
            Population fertPop = postMig.addPopulation(halfDeaths);
            Population births = fertPop.applyForce(DemographicForceType.FERTILITY);
            births.setName("Utah State Births " + i);
            targetPop = postMig.agePop(births, 12);
            targetPop.setName("Utah State Projected Pop " + (i + 1));

            stockPops[i - yr0 + 1] = targetPop;
            deathPops[i - yr0] = deaths;
            migrantPops[i - yr0] = migrants;
            birthPops[i - yr0] = births;
        }

        treeOfStockPops = new Population(new PopValue(new Time(2010, 4, 1), new Region("Utah State"), new Status(StatusType.NONE), new Home(HomeType.NONE)), stockPops);
        treeOfDeathPops = new Population(new PopValue(new Time(2010, 4, 1), new Region("Utah State"), new Status(StatusType.NONE), new Home(HomeType.NONE)), deathPops);
        treeOfMigrantPops = new Population(new PopValue(new Time(2010, 4, 1), new Region("Utah State"), new Status(StatusType.NONE), new Home(HomeType.NONE)), migrantPops);
        treeOfBirthPops = new Population(new PopValue(new Time(2010, 4, 1), new Region("Utah State"), new Status(StatusType.NONE), new Home(HomeType.NONE)), birthPops);

        treeOfStockPops.setName("All Stock Pop");
        treeOfDeathPops.setName("All Death Pops");
        treeOfMigrantPops.setName("All Migrant Pops");
        treeOfBirthPops.setName("All Birth Pops");
//
        Population[] bigChildrenPops = {treeOfStockPops, treeOfDeathPops, treeOfMigrantPops, treeOfBirthPops};
//
        allCCPops = new Population(new PopValue(new Time(2010, 4, 1), new Region("Utah State"), new Status(StatusType.NONE), new Home(HomeType.NONE)), bigChildrenPops);
        allCCPops.setName("All population stocks, deaths, migrations, and births");

    }

    public Population[] getStockPops() {
        return stockPops;
    }

    public ModelConstraints getModelConstraints() {
        return modelConstraints;
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }

    public Population getBasePop() {
        return basePop;
    }

    public Population[] getDeathPops() {
        return deathPops;
    }

    public Population[] getMigrantPops() {
        return migrantPops;
    }

    public Population[] getBirthPops() {
        return birthPops;
    }

    public void setAllPops(Population[] allPops) {
        this.stockPops = allPops;
    }

    public Population getTreeOfStockPops() {
        return treeOfStockPops;
    }

    public Population getAllCCPops() {
        return allCCPops;
    }

}
