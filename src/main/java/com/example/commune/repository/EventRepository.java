package com.example.commune.repository;

import com.example.commune.dto.EventDTO;
import com.example.commune.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public Event save(Event event) {
        String query = "INSERT INTO event (EventTitle, Description, OrganizerID, EventDate, Location) VALUES (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(query,event.getEventTitle(), event.getDescription(), event.getOrganizerID(), event.getEventDate(), event.getLocation());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return event;
    }

    public List<EventDTO> findAll(Integer userId) {
        String query = "SELECT e.EventID, e.EventTitle, e.Description, CONCAT(u.FirstName, ' ', u.LastName) AS organizerName, e.EventDate, e.Location FROM event e JOIN Users u ON e.OrganizerID = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(EventDTO.class), userId);
    }
}
