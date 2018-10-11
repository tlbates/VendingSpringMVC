/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingspringmvc.dao;

import com.sg.vendingspringmvc.dto.Item;
import java.util.List;

/**
 *
 * @author tylerbates
 */
public interface ItemDao {
    
    //SQL get all items to be displayed
    public List<Item> getAllItems();
    
    //SQL select specific item by id
    public Item getItem(int id);
    
    //SQL delete item
    public void deleteItem(int id);
    
    //SQL execute 
    public void execute(String statement);
    
    //SQL reduce item stock
    public void reduceItemStock(Item item);
}
