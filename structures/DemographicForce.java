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
    
    /*
    Constructs a Demographic Component (mortality, fertility, or migration), from the given age schedule
    */
    public DemographicForce(AgeDistribution sexAgeDistribution){
        this.ageDistribution=sexAgeDistribution;
    }

    public AgeDistribution getSexAgeDistribution() {
        return ageDistribution;
    }
       
}
