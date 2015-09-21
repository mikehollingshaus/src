/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author u0214256
 */
public class PopValue {

    private double totalSize, femSize, maleSize;

    private final Time date;
    private final Region region;
    private final Status status;
    private final Home home;

    private AgeDistribution totDistribution, maleDistribution, femDistribution;

    private Mortality maleMort, femMort;
    private Fertility fert;
    private Migration maleNonLaborMig, maleLaborMig, femNonLaborMig, femLaborMig;

    
    public PopValue(Time date, Region region, Status status, Home home) {
        this.date = date;
        this.region = region;
        this.status = status;
        this.home = home;
    }

    public PopValue(Time date, Region region, Status status, Home home, AgeDistribution maleStruct, AgeDistribution femStruct) {
        this.date = date;
        this.region = region;
        this.status = status;
        this.home = home;
        updateDistribution(maleStruct, femStruct);
    }

    public void updateDistribution(AgeDistribution mDist, AgeDistribution fDist) {
        maleDistribution = mDist;
        femDistribution = fDist;
        totDistribution = mDist.add(fDist);
        updateSizes();
    }

    private void updateSizes() {
        maleSize = maleDistribution.sumData();
        femSize = femDistribution.sumData();
        totalSize = totDistribution.sumData();
    }

    public Time getDate() {
        return date;
    }

    public Region getRegion() {
        return region;
    }

    public Status getStatus() {
        return status;
    }

    public Home getHome() {
        return home;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(double totalSize) {
        this.totalSize = totalSize;
    }

    public double getFemSize() {
        return femSize;
    }

    public void setFemSize(double femSize) {
        this.femSize = femSize;
    }

    public double getMaleSize() {
        return maleSize;
    }

    public void setMaleSize(double maleSize) {
        this.maleSize = maleSize;
    }

    public AgeDistribution getTotDistribution() {
        return totDistribution;
    }

    public void setTotDistribution(AgeDistribution totDistribution) {
        this.totDistribution = totDistribution;
    }

    public AgeDistribution getMaleDistribution() {
        return maleDistribution;
    }

    public void setMaleDistribution(AgeDistribution maleDistribution) {
        this.maleDistribution = maleDistribution;
    }

    public AgeDistribution getFemDistribution() {
        return femDistribution;
    }

    public void setFemDistribution(AgeDistribution femDistribution) {
        this.femDistribution = femDistribution;
    }

    public Mortality getMaleMort() {
        return maleMort;
    }

    public void setMaleMort(Mortality maleMort) {
        this.maleMort = maleMort;
    }

    public Mortality getFemMort() {
        return femMort;
    }

    public void setFemMort(Mortality femMort) {
        this.femMort = femMort;
    }

    public Fertility getFert() {
        return fert;
    }

    public void setFert(Fertility fert) {
        this.fert = fert;
    }

    public Migration getMaleNonLaborMig() {
        return maleNonLaborMig;
    }

    public void setMaleNonLaborMig(Migration maleNonLaborMig) {
        this.maleNonLaborMig = maleNonLaborMig;
    }

    public Migration getMaleLaborMig() {
        return maleLaborMig;
    }

    public void setMaleLaborMig(Migration maleLaborMig) {
        this.maleLaborMig = maleLaborMig;
    }

    public Migration getFemNonLaborMig() {
        return femNonLaborMig;
    }

    public void setFemNonLaborMig(Migration femNonLaborMig) {
        this.femNonLaborMig = femNonLaborMig;
    }

    public Migration getFemLaborMig() {
        return femLaborMig;
    }

    public void setFemLaborMig(Migration femLaborMig) {
        this.femLaborMig = femLaborMig;
    }

}
