package com.study.lab1.dao;

import com.study.lab1.model.Account;
import com.study.lab1.model.User;

public interface IDataSource {
    User getUser(Long id);
    Account getAccount(Long id);
}
