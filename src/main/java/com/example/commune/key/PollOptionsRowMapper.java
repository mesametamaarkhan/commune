package com.example.commune.key;

import com.example.commune.model.PollOptions;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PollOptionsRowMapper implements RowMapper<PollOptions> {

    @Override
    public PollOptions mapRow(ResultSet rs, int rowNum) throws SQLException {
        PollOptionsKey key = new PollOptionsKey(rs.getInt("OptionID"), rs.getInt("PollID"));
        PollOptions pollOptions = new PollOptions();
        pollOptions.setId(key);
        pollOptions.setOptionText(rs.getString("OptionText"));
        pollOptions.setOptionSelectCount(rs.getInt("OptionSelectCount"));
        return pollOptions;
    }
}
