/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.ArrayList;

/**
 *
 * @author jared
 */
public interface DVDLibraryServiceLayer {
    
    // ADD DVD
    void addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException,
            DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException;
    
    // LIST ALL DVDs
    ArrayList<DVD> viewAllDVDs() throws DVDLibraryPersistenceException;
    
    // VIEW DVD BY TITLE
    DVD getDVD(String title) throws DVDLibraryPersistenceException;
    
    // REMOVE DVD
    DVD removeDVD(String title) throws DVDLibraryPersistenceException;
    
    // EDIT DVD
    DVD editDVD(DVD dvd, int editProperty, String changeTo) 
            throws DVDLibraryPersistenceException;
    
    //boolean checkValidDVD(DVD dvd);
    
    // SEARCH BY KEYWORD
    ArrayList<DVD> searchDVDsByKeyword(String keyword) 
            throws DVDLibraryPersistenceException;
    
}
