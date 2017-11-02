/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VMServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

/**
 *
 * @author jared
 */
@Controller
public class VMController {

    private VMServiceLayer service;

    @Inject
    public VMController(VMServiceLayer service) {
        this.service = service;
    }

    // LOAD
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayVendingPage(Model model) {
        List<Item> items = service.listItems();
        String balance = service.returnBalance().toString();

        model.addAttribute("balance", balance);
        model.addAttribute("items", items);
        return "index";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase(HttpServletRequest request, Model model) {

        String currentMessage = "";
        Integer currentItem = null;
        String changeReturn = "";

        String balanceString = request.getParameter("balance");
        BigDecimal balance = BigDecimal.ZERO.setScale(2);
        
        String itemIdString = request.getParameter("item");

        int itemId;
        String message;

        try {
            balance = new BigDecimal(balanceString);
            service.setBalance(balance);
            itemId = Integer.parseInt(itemIdString);
            currentItem = itemId;
            message = service.vendItemGetMessage(itemId);
            currentMessage = message;

        } catch (NumberFormatException | NullPointerException ex) {
            currentMessage = "Please select valid item.";
        }

        if (currentMessage.equals(
                "Thank you!")) {
            currentItem = null;
            changeReturn = service.returnChangeString();
            balance = service.returnBalance();
        }

        List<Item> items = service.listItems();

        model.addAttribute("currentItem", currentItem);
        model.addAttribute("balance", balance.toString());
        model.addAttribute("items", items);
        model.addAttribute("currentMessage", currentMessage);
        model.addAttribute("changeReturn", changeReturn);

        currentMessage = "";

        return "index";
    }
}
