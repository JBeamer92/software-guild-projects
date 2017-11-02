/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jared
 */
public class DVDLibraryDaoStubImpl implements DVDLibraryDao {
    
    private HashMap<String, DVD> dvdLibrary = new HashMap<>();

    // ADD DVD
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        
        DVD newDVD = dvdLibrary.put(title, dvd);
        
        return newDVD;
    }

    // LIST ALL DVDs
    @Override
    public ArrayList<DVD> viewAllDVDs() throws DVDLibraryPersistenceException {
        
        return new ArrayList<>(dvdLibrary.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        
        return dvdLibrary.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException {
        
        DVD removedDVD = dvdLibrary.remove(title);
        
        return removedDVD;
    }

    @Override
    public DVD editDVD(DVD dvd, int editProperty, String changeTo) throws DVDLibraryPersistenceException {
        
        switch (editProperty) {
            case 1:
                //DVD newDVD = dvd;
                dvd = dvdLibrary.remove(dvd.getTitle());
                dvd.setTitle(changeTo);
                //dvd = newDVD;
                dvdLibrary.put(changeTo, dvd);
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
        
        return dvd;
    }
    
    // SEARCH BY KEYWORD
    @Override
    public ArrayList<DVD> searchDVDsByKeyword(String keyword) throws DVDLibraryPersistenceException {
        
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
