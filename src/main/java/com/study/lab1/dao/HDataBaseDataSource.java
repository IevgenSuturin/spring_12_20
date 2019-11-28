package com.study.lab1.dao;

import com.study.lab1.model.Account;
import com.study.lab1.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class HDataBaseDataSource implements IDataSource {
    private static final String GET_ACCOUNT_BY_ID = "select * from Accounts where id=?";
    private static final String GET_USER_BY_ID = "select * from Users where id=?";

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private AccountRowMapper accountRowMapper = new AccountRowMapper();
    private UserRowMapper userRowMapper = new UserRowMapper();

    public HDataBaseDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public Account getAccount(Long id) {
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ID, new Object[]{id}, accountRowMapper);
    }

    @Override
    public User getUser(Long id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[]{id}, userRowMapper);
    }
}

class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setBalance(resultSet.getLong("balance"));
        return account;
    }
}

class UserRowMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        return user;
    }
}