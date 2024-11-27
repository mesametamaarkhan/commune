package com.example.commune.repository;

import com.example.commune.dto.ItemDTO;
import com.example.commune.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public Item save(Item item) {
        String query = "INSERT INTO item (ItemName, Description, Price, Status, Seller_ID, DatePosted) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query, item.getItemName(), item.getDescription(), item.getPrice(), item.getStatus(), item.getSellerId(), item.getDatePosted());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return item;
    }

    public List<ItemDTO> findAll(Integer userId) {
        String query = "SELECT i.ItemID, i.ItemName, CONCAT(u.FirstName, ' ', u.LastName) AS SellerName, i.Seller_ID, i.Description, i.Price, i.DatePosted AS postedDate FROM item i JOIN Users u ON i.Seller_ID = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ItemDTO.class), userId);
    }

    public Map<String, String> getSellerContactInfo(Integer userId) {
        Map<String, String> contactInfo = new HashMap<>();
        String query = "SELECT u.Email, u.PhoneNumber FROM Item i JOIN Users u ON i.Seller_ID = u.UserID WHERE i.Seller_ID = ? LIMIT 1";
        try {
            contactInfo = jdbcTemplate.queryForObject(query, new Object[]{userId}, (rs, rowNum) -> {
                Map<String, String> result = new HashMap<>();
                result.put("email", rs.getString("Email"));
                result.put("phoneNumber", rs.getString("PhoneNumber"));
                return result;
            });
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return contactInfo;
    }
}
