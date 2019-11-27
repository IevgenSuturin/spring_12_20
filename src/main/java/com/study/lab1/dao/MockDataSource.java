package com.study.lab1.dao;

import com.study.lab1.model.Account;
import com.study.lab1.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MockDataSource {
    private List<Account> accounts;
    private List<User> users;

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // create some mock data in data source.
    public MockDataSource() {
    }

    public void updateAccount(Account account) {
        Account accountToUpdate = accounts.stream().filter(i -> i.getId() == account.getId()).findAny().orElse(null);
        if (accountToUpdate != null) {
            accountToUpdate.setBalance(account.getBalance());
        }
    }

    public Account getAccount(long id) {
        try {
            // call to datasource take some time :) maybe we should optimize?
            Thread.sleep(generateTimeout());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Account account = accounts.stream().filter(i -> i.getId() == id).findAny().orElse(null);
        return copy(account);
    }

    public User getUser(long id) {
        try {
            // call to datasource take some time :) maybe we should optimize?
            Thread.sleep(generateTimeout());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (User user : users) {
            if (user.getId() == id) {
                return copy(user);
            }
        }
        return null;
    }

    // to prevent direct data source changes
    private Account copy(Account accountToCopy) {
        if (accountToCopy == null) {
            return null;
        }
        Account account = new Account();
        account.setId(accountToCopy.getId());
        account.setBalance(accountToCopy.getBalance());
        return account;
    }

    // to prevent direct data source changes
    private User copy(User userToCopy) {
        if (userToCopy == null) {
            return null;
        }

        return new User(userToCopy.getId());
    }

    private int generateTimeout(){
        return (int) (1000 * Math.random());
    }
}
