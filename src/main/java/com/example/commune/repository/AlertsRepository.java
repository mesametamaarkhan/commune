package com.example.commune.repository;

import com.example.commune.dto.AlertDTO;
import com.example.commune.model.Alerts;
import com.example.commune.model.BabySitting;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlertsRepository {
    private final JdbcTemplate jdbcTemplate;

    public Alerts save(Alerts alerts) {
        String query = "INSERT INTO alerts (Description, IssuedBy, IssuedDate) VALUES (?, ?, ?)";
        try{
            jdbcTemplate.update(query,alerts.getDescription(), alerts.getIssuedBy(), alerts.getIssuedDate());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return alerts;
    }

    public List<AlertDTO> findAll(Integer userId) {
        String query = "SELECT a.AlertID, a.Description, CONCAT(u.FirstName, ' ', u.LastName) AS name, a.IssuedDate AS date FROM alerts a JOIN Users u ON a.IssuedBy = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AlertDTO.class), userId);
    }

}
