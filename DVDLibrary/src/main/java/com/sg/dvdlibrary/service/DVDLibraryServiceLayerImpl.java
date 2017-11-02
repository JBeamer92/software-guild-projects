/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.ArrayList;

/**
 *
 * @author jared
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {
    
    private DVDLibraryAuditDao auditDao;
    private DVDLibraryDao dao;
    
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    // ADD DVD
    @Override
    public void addDVD(String title, DVD dvd) throws 
            DVDLibraryPersistenceException, 
            DVDLibraryDuplicateTitleException, 
            DVDLibraryDataValidationException {
        if (dao.getDVD(dvd.getTitle()) != null) {
            throw new DVDLibraryDuplicateTitleException(
                "ERROR: NO REHASHING! Unable to add DVD. Title " + dvd.getTitle() +
                " already exists.");
        }
        
        validateDVDData(dvd);
        
        dao.addDVD(dvd.getTitle(), dvd);
    }
    
    // VALIDATE DVD DATA
    private void validateDVDData(DVD dvd) throws 
            DVDLibraryDataValidationException {
        if (dvd.getTitle() == null || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getMpaaRating() == null || dvd.getMpaaRating().trim().length() == 0
                || dvd.getDirector() == null || dvd.getDirector().trim().length() == 0
                || dvd.getStudio() == null || dvd.getStudio().trim().length() == 0
                || dvd.getUserNote() == null || dvd.getUserNote().trim().length() == 0) {
            throw new DVDLibraryDataValidationException(
            "ERROR: All fields are required.");
        }
    }

    // VIEW ALL DVDS
    @Override
    public ArrayList<DVD> viewAllDVDs() throws 
            DVDLibraryPersistenceException {
        return dao.viewAllDVDs();
    }

    // GET DVD BY TITLE
    @Override
    public DVD getDVD(String title) throws 
            DVDLibraryPersistenceException {
        return dao.getDVD(title);
    }

    // REMOVE DVD
    @Override
    public DVD removeDVD(String title) throws 
            DVDLibraryPersistenceException {
        DVD removedDVD = dao.removeDVD(title);
        return dao.removeDVD(title);
    }

    // EDIT DVD
    @Override
    public DVD editDVD(DVD dvd, int editProperty, String changeTo) throws 
            DVDLibraryPersistenceException {
        return dao.editDVD(dvd, editProperty, changeTo);
    }

    // SEARCH DVD LIBRARY BY KEYWORD
    @Override
    public ArrayList<DVD> searchDVDsByKeyword(String keyword) throws 
            DVDLibraryPersistenceException {
        return dao.searchDVDsByKeyword(keyword);
    }
    
}
