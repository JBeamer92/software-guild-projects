/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.service.ClassRosterDuplicateIdException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author jared
 */
public class ClassRosterController {
    
    private ClassRosterView view;
    private ClassRosterServiceLayer sl;
    
    public void run() {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default: // never makes it here bc .nextInt() doesn't allow it
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    // CONSTRUCTOR
    public ClassRosterController(ClassRosterServiceLayer sl, ClassRosterView view) {
        this.sl = sl;
        this.view = view;
    }

    // NAVIGATE MENU
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    // ADD STUDENT
    // doesn't do any work!
    // ...but knows who does!
    private void createStudent() throws ClassRosterPersistenceException {        
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        
        // semantic exception
        // throws exception, then goes back to allow the user to correct
        do {
           Student newStudent = view.getNewStudentInfo();
           
           try {
               sl.createStudent(newStudent);
               view.displayCreateSuccessBanner();
               // can't assueme this is the first time through!
               hasErrors = false;
           } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
               hasErrors = true;
               // handles either error message
               // then gets and displays that error's message
               view.displayErrorMessage(e.getMessage());
           }
            
        } while (hasErrors);
        
    }

    // LIST ALL STUDENTS
    private void listStudents() throws ClassRosterPersistenceException {
        // let user know we're starting
        view.displayDisplayAllBanner();
        // hey service layer, I need a list!
        List<Student> studentList = sl.getAllStudents();
        // hey view, display that list!
        view.displayStudentList(studentList);
    }

    // GET STUDENT
    private void viewStudent() throws ClassRosterPersistenceException {
        // let user we're starting
        view.displayDisplayStudentBanner();
        // get and store student id
        String studentId = view.getStudentIdChoice();
        // use that and give to sl (then to dao) for Student retrieval
        Student student = sl.getStudent(studentId);
        // display proper message
        view.displayStudent(student);
    }

    // REMOVE STUDENT
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        sl.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }

    // UNKNOWN COMMAND
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    // EXIT
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
