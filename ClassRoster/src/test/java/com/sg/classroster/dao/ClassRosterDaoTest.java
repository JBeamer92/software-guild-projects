/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jared
 */
public class ClassRosterDaoTest {
    
    // create an instance to use!
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();
    
    
    public ClassRosterDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        
        // set dao into known good state
        // use dao's method to get list
        // but don't know how many students/what state...
        List<Student> studentList = dao.getAllStudents();
        // ... so go ahead and remove them all so we DO!
        for (Student student : studentList) {
            dao.removeStudent(student.getStudentId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addStudent AND getStudent method, of class ClassRosterDao.
     */
    @Test
    public void testAddGetStudent() throws Exception {
        //create student
        Student student = new Student("0001");
        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java-May-2017");
        
        // add into dao
        dao.addStudent(student.getStudentId(), student);
        
        // get it back from dao
        Student fromDao = dao.getStudent(student.getStudentId());
        
        // check that they are idendical
        // remember to set Override equals in student DTO
        assertEquals(student, fromDao);
    }

    /**
     * Test of getAllStudents method, of class ClassRosterDao.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java");
        
        dao.addStudent(student1.getStudentId(), student1);
        
        Student student2 = new Student("0002");
        student2.setFirstName("Joe");
        student2.setLastName("Smith");
        student2.setCohort(".NET");
        
        dao.addStudent(student2.getStudentId(), student2);
        
        assertEquals(2, dao.getAllStudents().size());
    }

    /**
     * Test of removeStudent method, of class ClassRosterDao.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        // add 2, remove 1, check the size
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java");
        
        dao.addStudent(student1.getStudentId(), student1);
        
        Student student2 = new Student("0002");
        student2.setFirstName("Joe");
        student2.setLastName("Smith");
        student2.setCohort(".NET");
        
        dao.addStudent(student2.getStudentId(), student2);
        
        dao.removeStudent(student1.getStudentId());
        
        assertEquals(1, dao.getAllStudents().size());
        
        // if we remove student, trying to get removed student
        // should return null
        assertNull(dao.getStudent(student1.getStudentId()));
        
        // try it again with second student
        dao.removeStudent(student2.getStudentId());
        
        assertEquals(0, dao.getAllStudents().size());
        
        assertNull(dao.getStudent(student2.getStudentId()));
        
        
    }

}
