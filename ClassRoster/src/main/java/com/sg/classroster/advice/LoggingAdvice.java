/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.advice;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jared
 */
public class LoggingAdvice {
    // use this to write to the audit log file
    ClassRosterAuditDao auditDao;
    
    public LoggingAdvice(ClassRosterAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    // JoinPoint is a type from the AspectJ Tools library
    // we can get the signature of the method and the params
    // that are being passed to the method to which
    // the advice is being applied
    public void createAuditEntry(JoinPoint jp) {
        // gets all of the arguments that are passed to method
        Object[] args = jp.getArgs();
        // gets the signature of the method that is being called
        String auditEntry = jp.getSignature().getName() + ": ";
        
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        
        // if writing to the log causes an exception, simply
        // print errer to error console, as opposed to causing
        // a system failure as a result of a faulty audit logger
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (ClassRosterPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice");
        }
    }
}
