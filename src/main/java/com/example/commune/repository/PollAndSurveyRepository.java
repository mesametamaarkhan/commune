package com.example.commune.repository;

import com.example.commune.dto.PollAndSurveyDTO;
import com.example.commune.model.PollAndSurvey;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PollAndSurveyRepository {

    private final JdbcTemplate jdbcTemplate;

    public PollAndSurvey save(PollAndSurvey poll) {
        String query = "INSERT INTO PollAndSurvey (Title, Description, CreatedBy, CreatedDate) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, poll.getTitle());
                ps.setString(2, poll.getDescription());
                ps.setInt(3, poll.getCreatedBy());
                ps.setTimestamp(4, new Timestamp(poll.getCreatedDate().getTime()));
                return ps;
            }, keyHolder);

            Integer generatedId = keyHolder.getKey().intValue();
            poll.setPollID(generatedId);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return poll;
    }

    public List<PollAndSurveyDTO> findAll(Integer userId) {
        String query = "SELECT p.PollID, p.Title, p.Description, CONCAT(u.FirstName, ' ', u.LastName) AS createdBy, p.CreatedDate FROM PollAndSurvey p JOIN Users u ON p.CreatedBy = u.UserID WHERE u.PostalCode = (SELECT PostalCode FROM Users WHERE UserID = ?);\n";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PollAndSurveyDTO.class), userId);
    }
}
