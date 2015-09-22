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
public class Time {

    public final int year, month, day;

    public Time(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Time(Time oldTime, int monthOffset) {
        if (monthOffset < 0) {
            throw new RuntimeException("Tried to project backward in time, which this application does not presently allow");
        }
        this.day = oldTime.day;
        if (monthOffset == 0) {
            this.year = oldTime.year;
            this.month = oldTime.month;
            return;
        }

        int yearsToAdd = (int) monthOffset / 12;
        int monthsLeft = monthOffset - 12 * yearsToAdd;
        int newMonth;
        if (oldTime.month + monthsLeft > 12) {
            yearsToAdd += 1;
            newMonth = oldTime.month + monthsLeft - 12;
        } else {
            newMonth = oldTime.month + monthsLeft;
        }
        this.year = oldTime.year + yearsToAdd;
        this.month = newMonth;
    }

}
