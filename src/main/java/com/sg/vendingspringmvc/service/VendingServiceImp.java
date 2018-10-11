package com.sg.vendingspringmvc.service;


import com.sg.vendingspringmvc.dao.ItemDao;
import com.sg.vendingspringmvc.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author tylerbates
 */
public class VendingServiceImp implements VendingService {

    private ItemDao dao;

    @Inject
    public VendingServiceImp(ItemDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Integer> returnChange(BigDecimal money, int id) {
        Item pickedItem = dao.getItem(id);
        List<Integer> changeList = new ArrayList<>();
        BigDecimal cost;
        if (pickedItem == null) {
            cost = new BigDecimal("0");
        } else {
            cost = new BigDecimal(String.valueOf(pickedItem.getCost()));
        }
        money = money.subtract(cost);

        BigDecimal quarter = new BigDecimal(".25");
        BigDecimal dime = new BigDecimal(".10");
        BigDecimal nickel = new BigDecimal(".05");
        BigDecimal penny = new BigDecimal(".01");

        BigDecimal quarterNum[] = money.divideAndRemainder(quarter);
        money = quarterNum[1];
        BigDecimal dimeNum[] = money.divideAndRemainder(dime);
        money = dimeNum[1];
        BigDecimal nickelNum[] = money.divideAndRemainder(nickel);
        money = nickelNum[1];
        BigDecimal pennyNum[] = money.divideAndRemainder(penny);

        changeList.add(quarterNum[0].intValue());
        changeList.add(dimeNum[0].intValue());
        changeList.add(nickelNum[0].intValue());
        changeList.add(pennyNum[0].intValue());

        return changeList;

    }

    //DAO METHODS
    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(BigDecimal money, int id) throws
            InsufficientFundsException,
            OutOfStockException,
            InvalidChoiceException {
        Item checkItem = dao.getItem(id);
        if (checkItem == null) {
            throw new InvalidChoiceException("This is not a valid choice.");
        } else if (checkItem.getStock() <= 0) {
            throw new OutOfStockException("This item is out of stock.");
        } else if (money.compareTo(new BigDecimal(checkItem.getCost())) == -1) {
            throw new InsufficientFundsException("Please insert " + new BigDecimal(checkItem.getCost()).subtract(money).setScale(2,RoundingMode.DOWN));
        }

        return checkItem;
    }
    
    @Override
    public void reduceItemStock(Item item) {
        dao.deleteItem(item.getId());
        dao.reduceItemStock(item);
    }
    

}
