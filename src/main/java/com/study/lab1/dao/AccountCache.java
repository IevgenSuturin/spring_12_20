package com.study.lab1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountCache {
    IDataSource dataSource;

    public AccountCache(IDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
