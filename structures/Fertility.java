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
public class Fertility extends DemographicForce {

    public Fertility(AgeDistribution ageDistribution) {
        super(ageDistribution, DemographicForceType.FERTILITY);
    }
    public Fertility(Fertility f){
        super(f, DemographicForceType.FERTILITY);
    }
}
