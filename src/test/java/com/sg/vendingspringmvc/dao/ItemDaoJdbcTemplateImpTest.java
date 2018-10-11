/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingspringmvc.dao;

import com.sg.vendingspringmvc.dto.Item;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class ItemDaoJdbcTemplateImpTest {
    
    public ItemDao dao;
    
    public ItemDaoJdbcTemplateImpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("itemDao", ItemDao.class);
        
        List<Item> items = dao.getAllItems();
        for (Item currentItem: items) {
            dao.deleteItem(currentItem.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of getAllItems method, of class ItemDaoJdbcTemplateImp.
     */
    /*@Test
    public void testGetAllItems() {
    }

    /**
     * Test of getItem method, of class ItemDaoJdbcTemplateImp.
     */
    /*@Test
    public void testGetItem() {
    }
    */
}
