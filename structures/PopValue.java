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

    private double[] totData;
    private AgeStructure totSructure, maleStructure, femStructure;

    private Mortality mort;
    private Fertility fert;
    private Migration nonLaborMig, laborMig;

    public PopValue(Time date, Region region, Status status, Home home) {
        this.date = date;
        this.region = region;
        this.status = status;
        this.home = home;
    }

    public PopValue(Time date, Region region, Status status, Home home, AgeStructure maleStruct, AgeStructure femStruct) {
        this.date = date;
        this.region = region;
        this.status = status;
        this.home = home;
        updateStructure(maleStruct, femStruct);
    }

    public void updateStructure(AgeStructure mStruct, AgeStructure fStruct) {
        maleStructure = mStruct;
        femStructure = fStruct;
        totSructure = mStruct.add(fStruct);
        updateSizes();
    }

    private void updateSizes() {
        maleSize = maleStructure.sumData();
        femSize = femStructure.sumData();
        totalSize = totSructure.sumData();
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

    public double[] getTotData() {
        return totData;
    }

    public void setTotData(double[] totData) {
        this.totData = totData;
    }

    public AgeStructure getTotSructure() {
        return totSructure;
    }

    public void setTotSructure(AgeStructure totSructure) {
        this.totSructure = totSructure;
    }

    public AgeStructure getMaleStructure() {
        return maleStructure;
    }

    public void setMaleStructure(AgeStructure maleStructure) {
        this.maleStructure = maleStructure;
    }

    public AgeStructure getFemStructure() {
        return femStructure;
    }

    public void setFemStructure(AgeStructure femStructure) {
        this.femStructure = femStructure;
    }

    public Mortality getMort() {
        return mort;
    }

    public void setMort(Mortality mort) {
        this.mort = mort;
    }

    public Fertility getFert() {
        return fert;
    }

    public void setFert(Fertility fert) {
        this.fert = fert;
    }

    public Migration getNonLaborMig() {
        return nonLaborMig;
    }

    public void setNonLaborMig(Migration nonLaborMig) {
        this.nonLaborMig = nonLaborMig;
    }

    public Migration getLaborMig() {
        return laborMig;
    }

    public void setLaborMig(Migration laborMig) {
        this.laborMig = laborMig;
    }

}
