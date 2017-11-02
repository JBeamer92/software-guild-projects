/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author jared
 */
public interface ClassRosterServiceLayer {
    
    public void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;
    
    public List<Student> getAllStudents() throws
            ClassRosterPersistenceException;
    
    public Student getStudent(String studentId) throws
            ClassRosterPersistenceException;
    
    public Student removeStudent(String studentId) throws
            ClassRosterPersistenceException;
    
}
