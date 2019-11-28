package com.study.lab1.dao;

import com.study.lab1.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCache {
    MockDataSource dataSource;
    List<User> users = new ArrayList<>();

    public UserCache(MockDataSource dataSource){
        this.dataSource = dataSource;
        users = dataSource.createUserCash();
    }
}
