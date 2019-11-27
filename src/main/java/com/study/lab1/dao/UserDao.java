package com.study.lab1.dao;

import com.study.lab1.model.User;

public class UserDao {
    private MockDataSource dataSource;

    public User getUser(long id) {
        long start = System.currentTimeMillis();
        User user = dataSource.getUser(id);
        System.out.println("getting user took : " + (System.currentTimeMillis() - start));
        return user;

    }

    public void setDataSource(MockDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
