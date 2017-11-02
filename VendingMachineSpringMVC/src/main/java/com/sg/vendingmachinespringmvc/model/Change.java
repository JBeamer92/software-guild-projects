/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author jared
 */
public class Change {

    private int quarters = 0;
    private int dimes = 0;
    private int nickels = 0;
    private int pennies;

    public Change(int pennies) {
        this.pennies = pennies;

        // get quarters
        while ((this.pennies - 25) >= 0) {
            this.quarters++;
            this.pennies -= 25;
        }
        // get dimes
        while ((this.pennies - 10) >= 0) {
            this.dimes++;
            this.pennies -= 10;
        }
        // get nickels
        while ((this.pennies - 5) >= 0) {
            this.nickels++;
            this.pennies -= 5;
        }
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
}
