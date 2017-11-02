/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jared
 */
public class VMDaoTest {
    
    private VMDao dao;
    Item item1;
    Item item2;
    Item item3;
    
    public VMDaoTest() {
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
        dao = ctx.getBean("dao", VMDao.class);
        
        item1 = new Item();
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQuantity(9);
        item2 = new Item();
        item2.setName("M & Ms");
        item2.setPrice(new BigDecimal("1.50"));
        item2.setQuantity(2);
        item3 = new Item();
        item3.setName("Pringles");
        item3.setPrice(new BigDecimal("2.10"));
        item3.setQuantity(5);
        
        // this works because everything in memory
        // would not advise this method if stuff was being stored
        List<Item> items = dao.listItems();
        
        for (Item item : items) {
            dao.removeItem(item);
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddItem() {
        Assert.assertEquals(0, dao.listItems().size());
        
        dao.addItem(item1);
        dao.addItem(item2);
        
        Assert.assertEquals(2, dao.listItems().size());
    }
    
    @Test
    public void testAddDuplicateItem() {
        dao.addItem(item1);
        dao.addItem(item1);
        
        Assert.assertEquals(1, dao.listItems().size());
    }
    
    @Test
    public void testGetItem() {
        dao.addItem(item1);
        
        Item fromDao = dao.getItem(item1.getId());
        
        Assert.assertEquals(fromDao, item1);
    }
    
    @Test
    public void testGetInvalidItem() {
        Assert.assertNull(dao.getItem(99));
    }
    
    @Test
    public void testRemoveItem() {
        dao.addItem(item1);
        dao.addItem(item2);
        Assert.assertEquals(2, dao.listItems().size());
        
        dao.removeItem(item2);
        
        Assert.assertEquals(1, dao.listItems().size());
        
        Assert.assertNull(dao.getItem(item2.getId()));
    }
    
    @Test
    public void testUpdateItemName() {
        dao.addItem(item1);
        dao.addItem(item2);
        
        item2.setName("Sticky Hand");
        
        dao.updateItem(item2);
        
        String item2Name = item2.getName();
        
        Assert.assertEquals("Sticky Hand", item2Name);
    }
    
    @Test
    public void testListItems() {
        Assert.assertEquals(0, dao.listItems().size());
        
        dao.addItem(item1);
        dao.addItem(item2);
        dao.addItem(item3);
        
        List<Item> items = dao.listItems();
        
        Assert.assertEquals(3, dao.listItems().size());
        
        Assert.assertEquals(item1, dao.getItem(item1.getId()));
        Assert.assertEquals(item2, dao.getItem(item2.getId()));
        Assert.assertEquals(item3, dao.getItem(item3.getId()));
    }
}
