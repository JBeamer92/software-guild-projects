/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

/**
 *
 * @author jared
 */
public class ClassRosterPersistenceException extends Exception {
    
    // for when something goes wrong in our application
    // that isn't cause by another exception
    public ClassRosterPersistenceException(String message) {
        super(message);
    }
    
    // for when something goes wrong in our application
    // that IS cause by another exception
    public ClassRosterPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
