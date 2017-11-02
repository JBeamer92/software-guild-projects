/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jared
 */
public class App {
    
    public static void main(String[] args) throws DVDLibraryPersistenceException {
        
//        UserIO myIO = new UserIOConsoleImpl();
//        DVDLibraryView myView = new DVDLibraryView(myIO);
//        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
//        DVDLibraryAuditDao myAuditDao = new DVDLibraryAuditDaoImpl();
//        DVDLibraryServiceLayer mySL = new DVDLibraryServiceLayerImpl(myDao, myAuditDao);
//        DVDLibraryController controller = new DVDLibraryController(myView, mySL);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        
        DVDLibraryController controller = ctx.getBean(DVDLibraryController.class);

        controller.run();
        
    }
    
}
