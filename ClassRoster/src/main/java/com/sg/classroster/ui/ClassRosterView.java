/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author jared
 */
public class ClassRosterView {
    
    private UserIO io;
    
    // PRINT MENU
    public int printMenuAndGetSelection() {
        io.print("\nMain Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
            
        return io.readInt("Please select from the above choices.");
    }
    
    // CONSTRUCTOR
    public ClassRosterView(UserIO io) {
        this.io = io;
    }
    
    // ADD NEW STUDENT
    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }
    
    public void displayCreateStudentBanner() {
        io.print("\n=== Create Student ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("Student successfully created."
                + " Please hit enter to continue.");
    }
    
    // LIST ALL STUDENTS
    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            io.print(currentStudent.getStudentId() + ": "
                    + currentStudent.getFirstName() + " "
                    + currentStudent.getLastName());
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("\n=== Display All Students ===");
    }
    
    // GET STUDENT
    public void displayDisplayStudentBanner() {
        io.print("\n=== Display Student ===");
    }
    
    // get seperately, then given to controller
    // because view & dao == NO TALKIE!!!
    // also used for remove student
    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID");
    }
    
    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    // REMOVE STUDENT
    public void displayRemoveStudentBanner() {
        io.print("\n=== Remove Student ===");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readString("Student successfully removed. Please hit enter to continue.");
    }
    
    // UNKNOWN COMMAND
    public void displayUnknownCommandBanner() {
        io.print("\nUnknown Command!!!");
    }
    
    // EXIT
    public void displayExitBanner() {
        io.print("\nGood Bye!!!");
    }
    
    // ERROR
    public void displayErrorMessage(String errorMsg) {
        io.print("\n=== ERROR ===");
        io.print(errorMsg);
    }
    
}
