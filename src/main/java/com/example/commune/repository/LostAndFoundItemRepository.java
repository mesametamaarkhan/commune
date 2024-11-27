package com.example.commune.repository;

import com.example.commune.dto.LostAndFoundDTO;
import com.example.commune.model.LostAndFoundItem;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LostAndFoundItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public LostAndFoundItem save(LostAndFoundItem lostAndFoundItem) {
        String query = "INSERT INTO lostandfounditem (ItemName, Description, UserID, ReportDate) VALUES (?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query, lostAndFoundItem.getItemName(), lostAndFoundItem.getDescription(), lostAndFoundItem.getUserID(), lostAndFoundItem.getReportDate());
            System.out.println(lostAndFoundItem);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        return lostAndFoundItem;
    }

    public List<LostAndFoundDTO> findAll(Integer userId) {
        String query = "SELECT l.LostFoundID, l.ItemName, l.Description, CONCAT(u.FirstName, ' ', u.LastName) AS FullName, l.ReportDate FROM LostAndFoundItem l JOIN Users u ON l.UserID = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(LostAndFoundDTO.class), userId);
    }

    public Map<String, String> getPosterContactInfo(Integer posterId) {
        Map<String, String> contactInfo = new HashMap<>();
        String query = "SELECT u.Email, u.PhoneNumber FROM LostAndFoundItem l JOIN Users u ON l.UserID = u.UserID WHERE l.UserID = ? LIMIT 1";
        try {
            contactInfo = jdbcTemplate.queryForObject(query, new Object[]{posterId}, (rs, rowNum) -> {
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
