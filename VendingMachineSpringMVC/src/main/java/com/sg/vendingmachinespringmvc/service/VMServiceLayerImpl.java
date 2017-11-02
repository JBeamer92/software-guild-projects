/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachinespringmvc.dao.VMDao;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jared
 */
public class VMServiceLayerImpl implements VMServiceLayer {

    private VMDao dao;
    private BigDecimal balance = BigDecimal.ZERO.setScale(2);

    @Inject
    public VMServiceLayerImpl(VMDao dao) {
        this.dao = dao;
    }

    private Item getItem(int itemId) {
        return dao.getItem(itemId);
    }

    private boolean checkStock(Item item) {
        if (item.getQuantity() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void decrementItemStock(Item item) {

        if (item.getQuantity() > 0) {
            item.setQuantity(item.getQuantity() - 1);
            dao.updateItem(item);
        }
    }

    private BigDecimal amountOwed(Item item, BigDecimal balance) {
        BigDecimal cost = item.getPrice();

        return balance.subtract(cost).setScale(2);
    }

    @Override
    public List<Item> listItems() {
        return dao.listItems();
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal returnBalance() {
        return balance;
    }

    @Override
    public String vendItemGetMessage(int itemId) {

        Item item = getItem(itemId);

        BigDecimal amountOwed = amountOwed(item, balance);

        boolean inStock = checkStock(item);

        if (!inStock) {
            return "SOLD OUT!";
        }

        if (amountOwed.compareTo(BigDecimal.ZERO) < 0) {
            return "Please insert $"
                    + (amountOwed.multiply(new BigDecimal("-1")));
        }

        if (inStock) {
            decrementItemStock(item);
            this.balance = amountOwed;
        }

        return "Thank you!";
    }

    @Override
    public String returnChangeString() {
        int pennies = this.balance
                .multiply(new BigDecimal("100")).intValue();
        Change change = new Change(pennies);

        String response = "";

        if (change.getQuarters() > 0) {
            response += change.getQuarters() + " Quarters ";
        }
        if (change.getDimes() > 0) {
            response += change.getDimes() + " Dimes ";
        }
        if (change.getNickels() > 0) {
            response += change.getNickels() + " Nickels";
        }

        this.balance = BigDecimal.ZERO.setScale(2);

        return response;
    }
}
