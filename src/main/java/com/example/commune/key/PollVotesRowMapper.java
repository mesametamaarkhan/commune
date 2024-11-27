package com.example.commune.key;

import com.example.commune.model.PollVotes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollVotesRowMapper implements RowMapper<PollVotes> {
    @Override
    public PollVotes mapRow(ResultSet rs, int rowNum) throws SQLException {
        PollVotes pollVotes = new PollVotes();
        PollVotesKey id = new PollVotesKey();
        id.setUserId(rs.getInt("userId"));
        id.setPollId(rs.getInt("pollId"));
        pollVotes.setId(id);
        pollVotes.setOptionId(rs.getInt("optionId"));
        return pollVotes;
    }
}
