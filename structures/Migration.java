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
public class Migration extends DemographicForce {

    public Migration(AgeDistribution ageDistribution) {
        super(ageDistribution, DemographicForceType.MIGRATION);
    }

    public Migration(Migration m) {
        super(m, DemographicForceType.MIGRATION);
    }
}
