/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author jared
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private static final String DVD_FILE = "dvd_library.txt";
    private static final String DELIMITER = "::";

    private HashMap<String, DVD> dvdLibrary = new HashMap<>();

    // LOAD FILE
    private void loadDVDLibrary() throws DVDLibraryPersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException(
                    "-_- That's embarrassing, could not load library into memory", e);
        }

        String currentString;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentString = sc.nextLine();
            currentTokens = currentString.split(DELIMITER);

            DVD currentDVD = new DVD(currentTokens[0]);
            
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate releaseDate = LocalDate.parse(currentTokens[1], format);
            currentDVD.setReleaseDate(releaseDate);
            
//            currentDVD.setReleaseDate(currentTokens[1]);
            currentDVD.setMpaaRating(currentTokens[2]);
            currentDVD.setDirector(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            currentDVD.setUserNote(currentTokens[5]);

            // put DVD into Map
            dvdLibrary.put(currentDVD.getTitle(), currentDVD);

        }
        sc.close();
    }

    // WRITE FILE
    private void writeDVDLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;

        
        
        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not save DVD data.", e);
        }

        ArrayList<DVD> dvdList = viewAllDVDs();

        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate().toString() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirector() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getUserNote());
            out.flush();
        }
        out.close();

    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        DVD newDVD = dvdLibrary.put(title, dvd);
        writeDVDLibrary();
        return newDVD;
    }

    @Override
    public ArrayList<DVD> viewAllDVDs() throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        return new ArrayList<>(dvdLibrary.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        return dvdLibrary.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        DVD removedDVD = dvdLibrary.remove(title);
        writeDVDLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(DVD dvd, int editProperty, String changeTo) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        switch (editProperty) {
            case 1:
                dvd = dvdLibrary.remove(dvd.getTitle());
                dvd.setTitle(changeTo);
                break;
            case 2:
                LocalDate releaseDate = LocalDate.parse(changeTo);
                dvd.setReleaseDate(releaseDate);
                break;
            case 3:
                dvd.setMpaaRating(changeTo);
                break;
            case 4:
                dvd.setDirector(changeTo);
                break;
            case 5:
                dvd.setStudio(changeTo);
                break;
            case 6:
                dvd.setUserNote(changeTo);
                break;
            default:
                break;
        }
        dvdLibrary.put(dvd.getTitle(), dvd);
        writeDVDLibrary();
        return dvd;
    }
    
    // SEARCH BY KEYWORD
    @Override
    public ArrayList<DVD> searchDVDsByKeyword(String keyword) throws DVDLibraryPersistenceException {
        loadDVDLibrary();
        ArrayList<DVD> allDVDs = viewAllDVDs();
        ArrayList<DVD> dvdsWithKeyword = new ArrayList<>();
        
        for (DVD currentDVD : allDVDs) {
            if (currentDVD.getTitle().contains(keyword)) {
                dvdsWithKeyword.add(currentDVD);
            }
        }
        return dvdsWithKeyword;
    }

}
