/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingspringmvc.service;

import com.sg.vendingspringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public interface VendingService {

    //return change
    public List<Integer> returnChange(BigDecimal money, int id);
    
    
    
    //DAO PASSTHROUGHS
    //Get all items to be displayed
    public List<Item> getAllItems();

    //Get a specific item
    public Item getItem(BigDecimal money, int id) throws
            InsufficientFundsException, 
            OutOfStockException, 
            InvalidChoiceException;

    //reduce stock
    public void reduceItemStock(Item item);
}
