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
public class PopulationStructure_Detailed {

    public final Time time;
    public final Region region;
    public final boolean isResident, isGroupQuarters, isSpecialPop;
    public final int numChildren;
    public final boolean birthOrder;

    public PopulationStructure_Detailed(Time date, Region geography, boolean isResident, boolean isGroupQuarters, boolean isSpecialPop, int numChildren, boolean birthOrder) {
        this.time = date;
        this.region = geography;
   
        this.isResident = isResident;
        this.isGroupQuarters = isGroupQuarters;
        this.isSpecialPop = isSpecialPop;
        this.numChildren = numChildren;
        this.birthOrder = birthOrder;
    }

}
