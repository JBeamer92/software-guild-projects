/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryDuplicateTitleException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import java.util.ArrayList;

/**
 *
 * @author jared
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryServiceLayer service;
    
    // CONSTRUCTOR
    public DVDLibraryController(DVDLibraryView view, DVDLibraryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    // RUNTIME
    public void run() throws DVDLibraryPersistenceException {

        boolean keepGoing = true;
        int mainMenuSelection = 0;

        try {
            while (keepGoing) {
                mainMenuSelection = getMainMenuSelection();
                switch (mainMenuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        addDVD();
                        break;
                    case 3:
                        searchByTitle();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        removeDVD();
                        break;
                    case 6:
                        searchByKeyword();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }
            }

            exitMessage();
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    // NAVIGATE MENU
    private int getMainMenuSelection() {
        return view.printMainMenuGetSelection();
    }

    // ADD DVD
    private void addDVD() throws DVDLibraryPersistenceException {
        view.displayAddDVDBanner();
        boolean hasErrors = false;

        do {
            DVD newDVD = view.getAddDVDInfo();
            try {
                service.addDVD(newDVD.getTitle(), newDVD);
                view.displayAddDVDSuccess();
                hasErrors = false;
            } catch (DVDLibraryDuplicateTitleException |
                    DVDLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    // LIST ALL DVDs
    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayAllBanner();
        ArrayList<DVD> dvdList = service.viewAllDVDs();
        view.displayAllDVDs(dvdList);
    }

    // SEARCH BY TITLE
    private void searchByTitle() throws DVDLibraryPersistenceException {
        view.displaySearchByTitleBanner();
        String title = view.getTitleChoice();
        DVD dvd = service.getDVD(title);
        view.displayDVDInfo(dvd);
    }

    // EDIT DVD
    private String getTitleFromUser() {
        return view.getTitleChoice();
    }

    private boolean checkForValidDVD(String title) throws DVDLibraryPersistenceException {
        DVD dvd = service.getDVD(title);
        boolean isValidTitle;
        if (dvd == null) {
            isValidTitle = false;
        } else {
            isValidTitle = true;
        }
        return isValidTitle;
    }

    private int getEditMenuSelection() {
        return view.printEditMenuGetSelection();
    }

    private void makeChangesToDVD(String title, int selection) throws DVDLibraryPersistenceException {
        DVD dvd = service.getDVD(title);
        
        String changeTo;
        if (selection == 2) {
            changeTo = view.editReleaseDate();
        } else {
            changeTo = view.editMenuChangeTo();
        }
        
        service.editDVD(dvd, selection, changeTo);
    }

    private void editDVD() throws DVDLibraryPersistenceException {
        String dvdTitle = getTitleFromUser();
        boolean validTitle = checkForValidDVD(dvdTitle);

        if (validTitle) {
            int editMenuSelection = 0;
            boolean keepEditing = true;

            while (keepEditing) {
                editMenuSelection = getEditMenuSelection();
                switch (editMenuSelection) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        makeChangesToDVD(dvdTitle, editMenuSelection);
                        break;
                    case 7:
                        keepEditing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } else {
            view.displayInvalidTitle();
        }
    }

    // REMOVE DVD
    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        String title = view.getTitleChoice();
        boolean isValid = checkForValidDVD(title);
        if (isValid) {
            view.displayRemoveDVDSuccess();
        } else {
            view.displayInvalidTitle();
        }
        service.removeDVD(title);
    }

    // SEARCH BY KEYWORD
    private void searchByKeyword() throws DVDLibraryPersistenceException {
        String keyword = view.getKeywordToSearch();
        ArrayList<DVD> dvdsWithKeyword = service.searchDVDsByKeyword(keyword);
        view.displayAllDVDs(dvdsWithKeyword);
    }

    // UNKNOWN COMMAND
    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    // EXIT
    private void exitMessage() {
        view.displayExitMessage();
    }

}
