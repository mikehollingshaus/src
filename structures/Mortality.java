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
public class Mortality extends DemographicForce {

    public Mortality(AgeDistribution ageDistribution) {
        super(ageDistribution, DemographicForceType.MORTALITY);
    }
    
    public Mortality(Mortality m){
        super(m, DemographicForceType.MORTALITY);
    }
}
