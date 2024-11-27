package com.example.commune.repository;

import com.example.commune.key.PollVotesRowMapper;
import com.example.commune.model.PollVotes;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PollVotesRepository {

    private final JdbcTemplate jdbcTemplate;

    public PollVotes save(PollVotes pollVote) {
        String query = "INSERT INTO PollVotes (UserID, PollID, OptionID) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, pollVote.getId().getUserId(), pollVote.getId().getPollId(), pollVote.getOptionId());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return pollVote;
    }

    public boolean hasUserVoted(Integer userId, Integer pollId) {
        String query = "SELECT COUNT(*) FROM PollVotes WHERE UserID = ? AND PollID = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, userId, pollId);
        return (count != null && count > 0);
    }

    public PollVotes findByUserIdAndPollId(Integer userId, Integer pollId) {
        String query = "SELECT * FROM PollVotes WHERE UserID = ? AND PollID = ?";
        List<PollVotes> result = jdbcTemplate.query(query, new Object[]{userId, pollId}, new PollVotesRowMapper());
        return result.isEmpty() ? null : result.get(0);
    }
}
