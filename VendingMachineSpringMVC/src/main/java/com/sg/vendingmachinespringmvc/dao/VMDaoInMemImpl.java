/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jared
 */
public class VMDaoInMemImpl implements VMDao {
    
    private Map<Integer, Item> items = new TreeMap<>();
    
    private static int itemIdCounter = 1;
    
    public VMDaoInMemImpl() {
        Item item1 = new Item();
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQuantity(9);
        Item item2 = new Item();
        item2.setName("M & Ms");
        item2.setPrice(new BigDecimal("1.50"));
        item2.setQuantity(2);
        Item item3 = new Item();
        item3.setName("Pringles");
        item3.setPrice(new BigDecimal("2.10"));
        item3.setQuantity(5);
        Item item4 = new Item();
        item4.setName("Reese's");
        item4.setPrice(new BigDecimal("1.85"));
        item4.setQuantity(4);
        Item item5 = new Item();
        item5.setName("Pretzels");
        item5.setPrice(new BigDecimal("1.25"));
        item5.setQuantity(9);
        Item item6 = new Item();
        item6.setName("Twinkies");
        item6.setPrice(new BigDecimal("1.95"));
        item6.setQuantity(3);
        Item item7 = new Item();
        item7.setName("Doritos");
        item7.setPrice(new BigDecimal("1.75"));
        item7.setQuantity(11);
        Item item8 = new Item();
        item8.setName("Almond Joy");
        item8.setPrice(new BigDecimal("1.85"));
        item8.setQuantity(0);        
        Item item9 = new Item();
        item9.setName("Trident");
        item9.setPrice(new BigDecimal("1.05"));
        item9.setQuantity(6);
        
        this.addItem(item1);
        this.addItem(item2);
        this.addItem(item3);
        this.addItem(item4);
        this.addItem(item5);
        this.addItem(item6);
        this.addItem(item7);
        this.addItem(item8);
        this.addItem(item9);
        
    }

    @Override
    public List<Item> listItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(int id) {
        return items.get(id);
    }

    @Override
    public Item addItem(Item itemToAdd) {
        if (itemToAdd.getId() == null) {
            itemToAdd.setId(itemIdCounter);
            itemIdCounter++;
            return items.put(itemToAdd.getId(), itemToAdd);
            
        }
        return itemToAdd;
    }

    @Override
    public void removeItem(Item itemToRemove) {
        items.remove(itemToRemove.getId());
    }

    @Override
    public Item updateItem(Item item) {
        return items.put(item.getId(), item);
    }
    
}
