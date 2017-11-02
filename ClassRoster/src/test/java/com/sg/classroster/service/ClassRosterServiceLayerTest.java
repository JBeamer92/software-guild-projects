/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dto.Student;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jared
 */
public class ClassRosterServiceLayerTest {
    
    private ClassRosterServiceLayer service;
    
    // just like in main method, need to instantiate with
    // injected dependencies on each stub Implementation
    // of the Dao and AuditDao
    public ClassRosterServiceLayerTest() {
        // dependency injection here!
//        ClassRosterDao dao = new ClassRosterDaoStubImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
//        
//        service = new ClassRosterServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayer.class);
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("0002");
        
        student.setFirstName("Jane");
        student.setLastName("Doob");
        student.setCohort("Java");
        service.createStudent(student);
    }
    
    @Test
    public void testCreateStudentDuplicateId() throws Exception {
        Student student = new Student("0001");
        
        student.setFirstName("Jane");
        student.setLastName("Doob");
        student.setCohort("Java");
        
        try {
            service.createStudent(student);
            // should not reach this point
            fail("Expected ClassRosterDuplicateIdException was not thrown.");
        } catch (ClassRosterDuplicateIdException e) {
            return;
            // can also leave blank
        }
        
    }
    
    @Test
    public void testCreateStudentInvalidData() throws Exception {
        Student student = new Student("0002");
        
        student.setFirstName("");
        student.setLastName("Doob");
        student.setCohort("Java");
        
        try {
            service.createStudent(student);
            // should not reach this point
            fail("Expected ClassRosterDataValidationException was not thrown.");
        } catch (ClassRosterDataValidationException e) {
            return;
            // can also leave blank
        }
    }

    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        // service layer doesn't actually do anything
        // just want to test to make sure correct size it returned
        assertEquals(1, service.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetStudent() throws Exception {
        // this should NOT return null because
        // student id 0001 DOES exist (we made it when we created
        // the ClassRosterDaoStubImpl)
        Student student = service.getStudent("0001");
        assertNotNull(student);
        
        student = service.getStudent("9999");
        assertNull(student);
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        Student student = service.removeStudent("0001");
        assertNotNull(student);
        
        student = service.removeStudent("9999");
        assertNull(student);
    }
    
}
