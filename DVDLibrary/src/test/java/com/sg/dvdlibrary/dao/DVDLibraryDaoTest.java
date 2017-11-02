/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
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
public class DVDLibraryDaoTest {
    
    private DVDLibraryDao dao = new DVDLibraryDaoStubImpl();
    
    public DVDLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<DVD> dvdList = dao.viewAllDVDs();
        
        for (DVD dvd : dvdList) {
            dao.removeDVD(dvd.getTitle());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testAddGetDVD() throws Exception {
        // create new dvd
        DVD dvd = new DVD("Hoosiers");
//        dvd.setReleaseDate("1986-11-14");
        dvd.setMpaaRating("PG");
        dvd.setDirector("David Anspaugh");
        dvd.setStudio("Hemdale Pictures");
        dvd.setUserNote("Milan wins!");
        
        dao.addDVD(dvd.getTitle(), dvd);
        
        DVD fromDao = dao.getDVD(dvd.getTitle());
        
        assertEquals(dvd, fromDao);
    }

    /**
     * Test of viewAllDVDs method, of class DVDLibraryDao.
     */
    @Test
    public void testViewAllDVDs() throws Exception {
    }

    /**
     * Test of removeDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testRemoveDVD() throws Exception {
        // create new dvd
        DVD dvd1 = new DVD("Hoosiers");
        dvd1.setReleaseDate(LocalDate.parse("1986-11-14"));
        dvd1.setMpaaRating("PG");
        dvd1.setDirector("David Anspaugh");
        dvd1.setStudio("Hemdale Pictures");
        dvd1.setUserNote("Milan wins!");
        
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("The Shining");
        dvd2.setReleaseDate(LocalDate.parse("1986-11-14"));
        dvd2.setMpaaRating("R");
        dvd2.setDirector("Abbie Doobie");
        dvd2.setStudio("asedf");
        dvd2.setUserNote("Here's Johnny!");
        
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        dao.removeDVD(dvd1.getTitle());
        
        // check length
        assertEquals(1, dao.viewAllDVDs().size());
        
        // see that dvd is null
        assertNull(dao.getDVD(dvd1.getTitle()));
        
        dao.removeDVD(dvd2.getTitle());
        
        assertEquals(0, dao.viewAllDVDs().size());
        
        assertNull(dao.getDVD(dvd2.getTitle()));
        
        
    }

    /**
     * Test of editDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testEditDVD() throws Exception {
    }

    /**
     * Test of searchDVDsByKeyword method, of class DVDLibraryDao.
     */
    @Test
    public void testSearchDVDsByKeyword() throws Exception {
    }
    
}
