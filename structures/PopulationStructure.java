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
public class PopulationStructure {

    public final Time time;
    public final Region region;
    public final int age;
    public final boolean isMale;
    public final int numChildren;
    public final boolean birthOrder;

    public PopulationStructure(Time date, Region geography, int age, boolean isMale, int numChildren, boolean birthOrder) {
        this.time = date;
        this.region = geography;
        this.age = age;
        this.isMale = isMale;
        this.numChildren = numChildren;
        this.birthOrder = birthOrder;
    }
}
