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
public abstract class DemographicComponent {
    protected final AgeSchedule ageSchedule;
    
    /*
    Constructs a Demographic Component (mortality, fertility, or migration), from the given age schedule
    */
    public DemographicComponent(AgeSchedule aSch){
        this.ageSchedule = aSch;
    }

    public AgeSchedule getAgeSchedule() {
        return ageSchedule;
    }
       
}
