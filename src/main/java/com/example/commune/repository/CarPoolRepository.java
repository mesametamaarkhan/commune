package com.example.commune.repository;

import com.example.commune.dto.CarPoolDTO;
import com.example.commune.model.CarPool;
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
public class CarPoolRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarPool save(CarPool carPool) {
        String query = "INSERT INTO carpool (DriverID, AvailableSeats, StartLocation, Destination, DepartureTime) VALUES (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query,carPool.getDriverID(), carPool.getAvailableSeats(), carPool.getStartLocation(), carPool.getDestination(), carPool.getDepartureTime());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return carPool;
    }

    public List<CarPoolDTO> findAll(Integer userId) {
        String query = "SELECT c.CarpoolID, c.DriverID, CONCAT(u.FirstName, ' ', u.LastName) AS driverName, c.AvailableSeats, c.StartLocation, c.Destination, c.DepartureTime FROM carpool c JOIN Users u ON c.DriverID = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CarPoolDTO.class), userId);
    }

    public Map<String, String> getDriverContactInfo(Integer driverId) {
        Map<String, String> contactInfo = new HashMap<>();
        String query = "SELECT u.Email, u.PhoneNumber FROM carpool c JOIN Users u ON c.DriverID = u.UserID WHERE c.DriverID = ? LIMIT 1";
        try {
            contactInfo = jdbcTemplate.queryForObject(query, new Object[]{driverId}, (rs, rowNum) -> {
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
