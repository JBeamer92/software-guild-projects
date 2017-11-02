/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author jared
 */
public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao {

    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        
        // similar to dao writer
        PrintWriter out;
        
        try {
            // true allows to append to file
            // don't want to replace the file, want to add to it!
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            
        } catch (IOException e) {
            throw new ClassRosterPersistenceException(
                    "Could not persist audit information.", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        out.close();
    }
}
