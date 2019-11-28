package com.study.lab1.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountCache {
    MockDataSource dataSource;

    public AccountCache(MockDataSource dataSource) {
        this.dataSource = dataSource;

    }
}
