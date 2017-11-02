/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author jared
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMainMenuGetSelection() {
        io.print("\n=============== Main Menu ===============");
        io.print(" 1. List DVDs          5. Remove DVD    "
                + "\n 2. Add DVD            6. Search keyword"
                + "\n 3. View by Title      7. Exit          "
                + "\n 4. Edit DVD"
                + "\n=========================================");
        return io.readInt("\nPlease select from the above choices.");
    }

    public void displayAddDVDBanner() {
        io.print("\n==== Add DVD ====");
    }

    public DVD getAddDVDInfo() {
        String title = io.readString("Please enter Title");

        LocalDate releaseDate = LocalDate.parse(editReleaseDate());

        String mpaaRating = io.readString("Please enter MPAA Rating");
        String director = io.readString("Please enter Director");
        String studio = io.readString("Please enter Studio");
        String userNote = io.readString("Please enter a Personal Review or Note");

        DVD newDVD = new DVD(title);

        newDVD.setReleaseDate(releaseDate);
        newDVD.setMpaaRating(mpaaRating);
        newDVD.setDirector(director);
        newDVD.setStudio(studio);
        newDVD.setUserNote(userNote);

        return newDVD;
    }

    public void displayAddDVDSuccess() {
        io.readString("\nDVD successfully created."
                + "\nPlease hit enter to continue.");
    }

    public void displayAllBanner() {
        io.print("\n==== List All DVDs ====");
    }

    public void displayAllDVDs(ArrayList<DVD> dvdList) {
        io.print("\n");
        for (DVD dvd : dvdList) {
            io.print(dvd.getTitle());
        }
        io.readString("\nPlease hit enter to continue.");
    }

    public void displaySearchByTitleBanner() {
        io.print("\n==== Search By Title ====");
    }

    public String getTitleChoice() {
        return io.readString("Please enter Title");
    }

    public void displayDVDInfo(DVD dvd) {
        if (dvd != null) {
            io.print("\n" + dvd.getTitle()
                    + "\nReleased: " + dvd.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                    + "\nRated: " + dvd.getMpaaRating()
                    + "\nDirected by: " + dvd.getDirector()
                    + "\nStudio: " + dvd.getStudio()
                    + "\nPersonal review: " + dvd.getUserNote() + "\n");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayInvalidTitle() {
        io.print("Invalid title.");
    }

    public int printEditMenuGetSelection() {
        io.print("\n=============== Edit Menu ===============");
        io.print(" 1. Title             5. Studio          "
                + "\n 2. Release Date      6. Personal Review "
                + "\n 3. MPAA Rating       7. Exit            "
                + "\n 4. Director"
                + "\n=========================================");
        return io.readInt("\nWhat would you like to edit?");
    }

    public String editReleaseDate() {
        Integer releaseYear;
        String yearString = "";
        String stringDate = "";
        do {
            do {
                releaseYear = io.readInt("\nPlease enter Release Year (yyyy)");
                yearString = releaseYear.toString();
                if (yearString.length() != 4) {
                    io.print("Invalid Year format.");
                }
            } while (yearString.length() != 4);

            Integer releaseMonth = io.readInt("\nPlease enter Release Month (number 1-12)", 1, 12);
            String monthString = releaseMonth.toString();
            if (monthString.length() == 1) {
                monthString = "0" + monthString;
            }

            Integer releaseDay;
            switch (releaseMonth) {
                case 4:
                case 6:
                case 9:
                case 11:
                    releaseDay = io.readInt("\nPlease enter Release Day (number 1-30)", 1, 30);
                    break;
                case 2:
                    if (releaseYear % 4 == 0) {
                        releaseDay = io.readInt("\nPlease enter Release Day (number 1-29)", 1, 29);
                    } else {
                        releaseDay = io.readInt("\nPlease enter Release Day (number 1-28)", 1, 28);
                    }
                    break;
                default:
                    releaseDay = io.readInt("\nPlease enter Release Day (number 1-31)", 1, 31);
            }

            String dayString = releaseDay.toString();
            if (dayString.length() == 1) {
                dayString = "0" + dayString;
            }

            stringDate = yearString + "-"
                    + monthString + "-"
                    + dayString;

            if (LocalDate.parse(stringDate).compareTo(LocalDate.now()) > 0) {
                io.print("\nTHAT'S IMPOSSIBLE! Who are you, Marty McFly?");
            }
        } while (LocalDate.parse(stringDate).compareTo(LocalDate.now()) > 0);
        return stringDate;
    }

    public String editMenuChangeTo() {
        return io.readString("What would you like to change it to?");
    }

    public void displayRemoveDVDBanner() {
        io.print("\n==== Remove DVD ====");
    }

    public void displayRemoveDVDSuccess() {
        io.readString("\nDVD successfully removed."
                + "\nPlease hit enter to continue.");
    }

    public String getKeywordToSearch() {
        return io.readString("Please enter the keyword to search.");
    }

    public void displayUnknownCommand() {
        io.print("Unknown Command.");
    }

    public void displayExitMessage() {
        io.print("Good Bye!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("\n==== ERROR ====");
        io.print(errorMsg);
    }

}
