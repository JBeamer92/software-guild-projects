/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;

/**
 *
 * @author jared
 */
public interface VMDao {
    
    List<Item> listItems();
    
    Item getItem(int id);
    
    Item addItem(Item itemToAdd);
    
    void removeItem(Item itemToRemove);
    
    Item updateItem(Item item);
}
