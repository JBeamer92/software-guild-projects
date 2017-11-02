/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author jared
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    
    private ClassRosterDao dao;
    private ClassRosterAuditDao audit;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao audit) {
        this.dao = dao;
        this.audit = audit;
    }

    // check for duplicate student id
    @Override
    public void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {
        // if the student id exists already, throw exception
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException("ERROR: Could not create student. Student Id "
                    + student.getStudentId() + " already exists.");
        }
        // otherwise, check to make sure that everything else is good
        validateStudentData(student);
        
        dao.addStudent(student.getStudentId(), student);
//        audit.writeAuditEntry("Student " + student.getStudentId() + " CREATED");
    }

    // pass-through method
    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    // pass-through method
    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    // pass-through method
    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
//        audit.writeAuditEntry("Student " + studentId + " REMOVED");
        return removedStudent;
    }
    
    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        // check for null or empty fields
        if (student.getFirstName() == null || student.getFirstName().trim().length() == 0
            || student.getLastName() == null || student.getLastName().trim().length() == 0
            || student.getCohort() == null || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException("ERROR: All fields are required.");
        }
    }
    
}
