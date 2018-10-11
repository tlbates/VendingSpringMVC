package com.sg.vendingspringmvc.controller;

import com.sg.vendingspringmvc.dto.Item;
import com.sg.vendingspringmvc.service.InsufficientFundsException;
import com.sg.vendingspringmvc.service.InvalidChoiceException;
import com.sg.vendingspringmvc.service.InvalidMoneyException;
import com.sg.vendingspringmvc.service.OutOfStockException;
import com.sg.vendingspringmvc.service.VendingService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingController {
        
    VendingService service;
    List<Item> allItems;
    String currentTotal = "0";
    String message;
    int id;
    List<Integer> changeList;
    BigDecimal total = new BigDecimal("0");
    Item currentItem;
    
    @Inject
    public VendingController(VendingService service) {
        this.service = service;
    }
    
    //main method to load everything and render the
    //snacks onto the page
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Map<String, Object> model) {
        allItems = service.getAllItems();
        model.put("itemList", allItems);
        model.put("total", currentTotal);
        model.put("message", message);
        model.put("pickedId", id);
        model.put("changeList", changeList);
        return "index";
    }
    
    //here is the method for the adding money buttons
    @RequestMapping(value="/addMoney", method=RequestMethod.POST)
    public String addMoney(HttpServletRequest request ,Map<String, Object> model) {
        message = "";
        changeList = null;
        String inputTotal = request.getParameter("total");
        String money = request.getParameter("money");
        
        if (inputTotal.equals("")) {
            total = new BigDecimal("0");
        } else {
            total = new BigDecimal(inputTotal);
        }
        
        BigDecimal addedMoney = new BigDecimal(money);
        
        total = total.add(addedMoney);
        currentTotal = total.setScale(2).toString();
        
        return "redirect:/";
    }
    
    //here is the method for when you would like to select an item
    @RequestMapping(value="/select", method=RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Map<String, Object> model) {
        id = Integer.parseInt(request.getParameter("item"));
        return "redirect:/";
    }
    
    //here is the method for when you would like to make a purchase
    //it also returns messages based on your entered information
    //it also returns change back
    @RequestMapping(value="/makePurchase", method=RequestMethod.POST)
    public String makePurchase(HttpServletRequest request, Map<String, Object> model){
        
        try {
        currentItem = service.getItem(total, id);
        } catch (InvalidChoiceException|OutOfStockException|InsufficientFundsException e){
           message = e.getMessage();
           model.put("message", message);
           id = 0;
           currentTotal = "0";
           return "redirect:/";
        }
        
        message = "Thank you!!";
        changeList = service.returnChange(total, id);
        service.reduceItemStock(currentItem);
        
        currentTotal= "0";
        id = 0;
        model.put("message", message);
        model.put("changeList", changeList);
        return "redirect:/";
    }
    
    //here is when you want to enter money and choose not to buy anything
    @RequestMapping(value="/getChange", method=RequestMethod.POST)
    public String getChange(HttpServletRequest request, Map<String, Object> model){
        message = "Here is your change!";
        changeList = service.returnChange(total, id);
        currentTotal = "";
        return "redirect:/";
    }
}
