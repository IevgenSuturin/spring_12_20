package com.study.lab1.dao;

import com.study.lab1.model.User;

public class UserDao {
    private IDataSource dataSource;

    public User getUser(long id) {
        User user = dataSource.getUser(id);
        return user;

    }

     public void setDataSource(IDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
