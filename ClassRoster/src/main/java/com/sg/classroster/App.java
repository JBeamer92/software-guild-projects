/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jared
 */
public class App {
    
    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        ClassRosterView myView = new ClassRosterView(myIo);
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//        ClassRosterAuditDao myAudit = new ClassRosterAuditDaoFileImpl();
//        ClassRosterServiceLayer mySL = new ClassRosterServiceLayerImpl(myDao, myAudit);
//        ClassRosterController controller = new ClassRosterController(mySL, myView);
//        
//        controller.run();

        // instantiate context
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        
        // get controller from context
        ClassRosterController controller = ctx.getBean(
                "controller", ClassRosterController.class);
        
        controller.run();
    }
    
}
