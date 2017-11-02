package com.sg.vendingmachinespringmvc.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VMServiceLayer;
import java.math.BigDecimal;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jared
 */
public class VMServiceLayerTest {
    
    private VMServiceLayer service;
    
    public VMServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("service", VMServiceLayer.class);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testListItems() {
        Assert.assertEquals(9, service.listItems().size());
    }

    @Test
    public void testSetBalance() {
        service.setBalance(new BigDecimal("2.50"));
        Assert.assertEquals(new BigDecimal("2.50"), service.returnBalance());
    }
    
    @Test
    public void testReturnBalance() {
        service.setBalance(new BigDecimal("2.50"));
        Assert.assertEquals(new BigDecimal("2.50"), service.returnBalance());
    }
    
//    @Test
//    public void testVendItemGetMessage() {
//        // test for happy path message
//        service.setBalance(new BigDecimal("2.50"));
//        Assert.assertEquals("Thank you!", service.vendItemGetMessage(1));
//        
//    }
//    
//    @Test
//    public void testVendItemInsufficientFunds() {
//        //test for insufficient funds message
//        service.setBalance(new BigDecimal("1.00"));
//        Assert.assertEquals("Please insert $0.85", service.vendItemGetMessage(1));
//    }
//    
//    @Test
//    public void testVendItemNoStock() {
//        // test for out of stock message
//        service.setBalance(new BigDecimal("1.00"));
//        Assert.assertEquals("SOLD OUT!", service.vendItemGetMessage(8));
//    }
    
    @Test
    public void testReturnChangeString() {
        // test change to expected string
        service.setBalance(new BigDecimal("0.40"));
        Assert.assertEquals("1 Quarters 1 Dimes 1 Nickels", service.returnChangeString());
    }
    
}
