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
public class Status {

    public enum StatusType {

        SPECIAL_POP, NORMAL_POP, NONE;
    }
    public final StatusType statusType;

    public Status(StatusType statusType) {
        this.statusType = statusType;
    }

}
