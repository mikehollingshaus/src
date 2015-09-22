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
public abstract class DemographicForce {

    protected final AgeDistribution ageDistribution;
    protected final DemographicForceType forceType;

    /*
     Constructs a Demographic Component (mortality, fertility, or migration), from the given age schedule
     */
    public DemographicForce(AgeDistribution sexAgeDistribution, DemographicForceType dft) {
        this.ageDistribution = sexAgeDistribution;
        this.forceType = dft;
    }

    public DemographicForce(DemographicForce d, DemographicForceType dft) {
        this.ageDistribution = d.ageDistribution;
        this.forceType = dft;
    }

    public AgeDistribution getSexAgeDistribution() {
        return ageDistribution;
    }

    public DemographicForceType getForceType() {
        return forceType;
    }

    public enum DemographicForceType {

        MORTALITY, FERTILITY, MIGRATION;
    }

}
