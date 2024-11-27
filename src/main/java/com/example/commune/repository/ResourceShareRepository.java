package com.example.commune.repository;

import com.example.commune.dto.LostAndFoundDTO;
import com.example.commune.dto.ResourceShareDTO;
import com.example.commune.model.LostAndFoundItem;
import com.example.commune.model.ResourceShare;
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
public class ResourceShareRepository {
    private final JdbcTemplate jdbcTemplate;

    public ResourceShare save(ResourceShare resourceShare) {
        String query = "INSERT INTO resourceshare (ResourceName, SharedBy, Status, SharedDate) VALUES (?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query, resourceShare.getResourceName(), resourceShare.getSharedBy(), resourceShare.getStatus(), resourceShare.getSharedDate());
            System.out.println(resourceShare);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
        return resourceShare;
    }

    public List<ResourceShareDTO> findAll(Integer userId) {
        String query = "SELECT r.ShareID, r.SharedBy AS sharerId, r.ResourceName, r.Status, CONCAT(u.FirstName, ' ', u.LastName) AS sharerName, r.sharedDate FROM ResourceShare r JOIN Users u ON r.SharedBy = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ResourceShareDTO.class), userId);
    }

    public Map<String, String> getSharerContactInfo(Integer sharerId) {
        Map<String, String> contactInfo = new HashMap<>();
        String query = "SELECT u.Email, u.PhoneNumber FROM ResourceShare r JOIN Users u ON r.SharedBy = u.UserID WHERE r.SharedBy = ? LIMIT 1";
        try {
            contactInfo = jdbcTemplate.queryForObject(query, new Object[]{sharerId}, (rs, rowNum) -> {
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
