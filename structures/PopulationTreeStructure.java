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

    public PopulationTreeStructure(int numChildren) {
        this.numChildren = numChildren;

    }

    public boolean equals(PopulationTreeStructure pts) {
        return numChildren == pts.numChildren;
    }

}
