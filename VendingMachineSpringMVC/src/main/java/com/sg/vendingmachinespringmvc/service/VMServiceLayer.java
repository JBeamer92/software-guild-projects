/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jared
 */
public interface VMServiceLayer {
    
    List<Item> listItems();
    
    void setBalance(BigDecimal balance);
    
    BigDecimal returnBalance();
            
    String vendItemGetMessage(int itemId);
    
    String returnChangeString();
}
