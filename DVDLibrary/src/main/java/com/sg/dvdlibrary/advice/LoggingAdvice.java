/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.advice;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jared
 */
public class LoggingAdvice {
    
    DVDLibraryAuditDao audit;
    
    public LoggingAdvice(DVDLibraryAuditDao audit) {
        this.audit = audit;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        
        String auditEntry = jp.getSignature().getName() + ": ";
        
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        
        try {
            audit.writeAuditEntry(auditEntry);
        } catch (DVDLibraryPersistenceException e) {
            System.err.println("ERROR: Unable to create audit entry.");
        }
    }
    
}
