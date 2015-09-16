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
public class PopDate {

    private final int year, month, day;

    public PopDate(int year, int month, int day) {
        validate(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private void validate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("Month must be an integer in the interval [1,12]");
        }
        if (day < 1 || day > 31) {
            throw new RuntimeException("Day must be an integer in the interval [1,31]");
        }
        switch (month) {
            case 9:
            case 4:
            case 6:
            case 11:
                if (day > 30) {
                    throw new RuntimeException("For the months Sept, Apr, June, and Nov, day must be an integer in the interval [1,30]");
                }
                break;
            case 2:
                if (day > 29) {
                    throw new RuntimeException("For the month of February, day must be an integer in the interval [1,29]");
                }

                if (day > 28 && !((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
                    throw new RuntimeException("For the month of February in non-leap years, day must be an integer in the interval [1,28]");
                }
                break;
            default:
                break;

        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

}
