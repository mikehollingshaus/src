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
public class PopulationTreeStructure {

    public final int numChildren;
    public final boolean birthOrder;

    public PopulationTreeStructure(int numChildren, boolean birthOrder) {
        this.numChildren = numChildren;
        this.birthOrder = birthOrder;
    }
    
    public boolean equals(PopulationTreeStructure pts){
        return numChildren==pts.numChildren && birthOrder==pts.birthOrder;
    }
    
}
