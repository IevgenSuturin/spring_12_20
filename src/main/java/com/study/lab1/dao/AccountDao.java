package com.study.lab1.dao;

import com.study.lab1.model.Account;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class AccountDao {
    private IDataSource dataSource;

    public Account get(long id) {
        Account account = dataSource.getAccount(id);
        return account;
    }

    public void setDataSource(IDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
