package com.example.commune.repository;

import com.example.commune.model.BabySitting;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BabySittingRepository {

    private final JdbcTemplate jdbcTemplate;

    public BabySitting save(BabySitting babySitting) {
        String query = "INSERT INTO babysitting (UserID, Name, Location, RatePerHour, HoursAvailable, PostDate) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query,babySitting.getUserID(), babySitting.getName(), babySitting.getLocation(), babySitting.getRatePerHour(), babySitting.getHoursAvailable(), babySitting.getPostDate());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return babySitting;
    }

    public List<BabySitting> findAll(Integer userId) {
        String query = "SELECT * FROM babysitting b JOIN Users u ON b.UserID = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(BabySitting.class), userId);
    }

    public Map<String, String> getBabysitterContact(Integer userId) {
        Map<String, String> contactInfo = new HashMap<>();
        String query = "SELECT u.Email, u.PhoneNumber FROM BabySitting b JOIN Users u ON b.UserID = u.UserID WHERE b.UserID = ? LIMIT 1";
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
