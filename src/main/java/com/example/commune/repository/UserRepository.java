package com.example.commune.repository;

import com.example.commune.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE Email = ?";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User save(User user) {
        String query = "INSERT INTO users (FirstName, LastName, Email, Role, Password, PhoneNumber, PostalCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole(), user.getPassword(), user.getPhoneNumber(), user.getPostalCode());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    public Iterable<User> findAll(User user) {
        String query = "SELECT * FROM users WHERE UserID != ? AND PostalCode = ?";
        return jdbcTemplate.query(query, new Object[]{user.getUserID(), user.getPostalCode()}, new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(Integer id) {
        String query = "SELECT * FROM users WHERE UserID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }


}
