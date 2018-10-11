
package com.sg.vendingspringmvc.dao;

import com.sg.vendingspringmvc.dto.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tylerbates
 */
public class ItemDaoJdbcTemplateImp implements ItemDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_SELECT_ALL_ITEMS 
            = "select * from items";

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new ItemMapper());
    }

    private static final String SQL_SELECT_ITEM_BY_ID
            = "select * from items where id = ?";
    
    @Override
    public Item getItem(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM_BY_ID, 
                    new ItemMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not choose item by ID.");
            return null;
        }
    }
    
    private static final String SQL_DELETE_ITEM
            = "delete from items where id = ?";
    
    @Override
    public void deleteItem(int id) {
        jdbcTemplate.update(SQL_DELETE_ITEM, id);
    }
    
    private static final String SQL_REDUCE_STOCK
            = "insert into items (ID, name, price, stock) values (?, ?, ?, ?)";
    
    @Override
    public void reduceItemStock(Item item) {
        jdbcTemplate.update(SQL_REDUCE_STOCK,
                item.getId(),
                item.getName(),
                item.getCost(),
                item.getStock() - 1);
    }
    
    @Override
    public void execute(String statement) {
        jdbcTemplate.execute(statement);
    }
    
    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setCost(rs.getDouble("price"));
            item.setStock(rs.getInt("stock"));
            return item;
        }
            
        
    }
}
