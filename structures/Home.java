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
public class Home {

    public enum HomeType {

        GROUP_QUARTERS, HOUSEHOLD, NONE;
    }

    public final HomeType homeType;

    public Home(HomeType homeType) {
        this.homeType = homeType;
    }

}
