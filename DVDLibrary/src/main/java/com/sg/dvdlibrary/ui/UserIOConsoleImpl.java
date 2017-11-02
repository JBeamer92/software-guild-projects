/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author jared
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        double userDouble = 0;
        boolean isInvalid = true;
        while (isInvalid) {
            try {
                userDouble = Double.parseDouble(sc.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                print("Invalid input.");
                print(prompt);
            }
        }

        return userDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userDouble = readDouble(prompt);
        while (userDouble < min || userDouble > max) {
            System.err.println("Please enter a number between "
                    + min + " and " + max);
            userDouble = readDouble(prompt);
        }

        return userDouble;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        float userFloat = 0;
        boolean isInvalid = true;
        while (isInvalid) {
            try {
                userFloat = Float.parseFloat(sc.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                print("Invalid input.");
                print(prompt);
            }
        }
        return userFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userFloat = readFloat(prompt);
        while (userFloat < min || userFloat > max) {
            System.err.println("Please enter a number between "
                    + min + " and " + max);
            userFloat = readFloat(prompt);
        }

        return userFloat;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        int userInt = 0;
        boolean isInvalid = true;
        while (isInvalid) {
            try {
                userInt = Integer.parseInt(sc.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                print("Invalid input.");
                print(prompt);
            }
        }
        return userInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int userInt = readInt(prompt);
        while (userInt < min || userInt > max) {
            System.err.println("Please enter a number between "
                    + min + " and " + max);
            userInt = readInt(prompt);
        }

        return userInt;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        long userLong = 0;
        boolean isInvalid = true;
        while (isInvalid) {
            try {
                userLong = Long.parseLong(sc.nextLine());
                isInvalid = false;
            } catch (NumberFormatException e) {
                print("Invalid input.");
                print(prompt);
            }
        }
        return userLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userLong = readLong(prompt);
        while (userLong < min || userLong > max) {
            System.err.println("Please enter a number between "
                    + min + " and " + max);
            userLong = readLong(prompt);
        }

        return userLong;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        prompt = sc.nextLine();
        return prompt;
    }

}