package com.mohammed.quran_tele_bot.repositories;

import com.mohammed.quran_tele_bot.mappers.UserRowMapper;
import com.mohammed.quran_tele_bot.models.User;
import com.mohammed.quran_tele_bot.services.TelegramBotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void storeUser(User user) {
        String sql = "INSERT INTO user (chat_id) VALUES (?)";
        try
        {
            jdbcTemplate.update(sql, user.getChatId());
        } catch (Exception e) {
            logger.warn("This user Already exists");
        }
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

}
