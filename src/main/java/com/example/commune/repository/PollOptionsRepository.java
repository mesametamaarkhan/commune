package com.example.commune.repository;

import com.example.commune.key.PollOptionsKey;
import com.example.commune.key.PollOptionsRowMapper;
import com.example.commune.model.PollOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PollOptionsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PollOptionsRowMapper pollOptionsRowMapper = new PollOptionsRowMapper();

    public PollOptions save(PollOptions pollOptions) {
        String query = "INSERT INTO polloptions (OptionID, PollID, OptionText) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, pollOptions.getId().getOptionID(), pollOptions.getId().getPollID(), pollOptions.getOptionText());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }

        return pollOptions;
    }

    public void incrementOptionSelectCount(Integer optionID, Integer pollID) {
        String query = "UPDATE PollOptions SET OptionSelectCount = OptionSelectCount + 1 WHERE OptionID = ? AND PollID = ?";
        jdbcTemplate.update(query, optionID, pollID);
    }

    public List<PollOptions> findByPollId(Integer pollId) {
        String query = "SELECT * FROM polloptions WHERE PollID = ?";
        return jdbcTemplate.query(query, pollOptionsRowMapper, pollId);
    }

    public Optional<PollOptions> findById(PollOptionsKey pollOptionsKey) {
        String query = "SELECT * FROM polloptions WHERE PollID = ? AND OptionID = ?";
        return jdbcTemplate.query(query, pollOptionsRowMapper, pollOptionsKey.getOptionID(), pollOptionsKey.getPollID()).stream().findFirst();
    }


}
