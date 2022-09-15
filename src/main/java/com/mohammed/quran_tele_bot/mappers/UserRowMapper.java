package com.mohammed.quran_tele_bot.mappers;

import com.mohammed.quran_tele_bot.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setChatId(rs.getString("chat_id"));
        return user;
    }
}
