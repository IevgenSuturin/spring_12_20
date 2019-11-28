package com.study.lab1.dao;

import com.study.lab1.model.Account;
import com.study.lab1.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockDataSource implements IDataSource {
    private List<Account> accounts = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    // create some mock data in data source.
    public MockDataSource() {
        for (Long i = 1L; i < 5L; i++) {
            double balance = new BigDecimal(1000 * Math.random())
                    .setScale(2, RoundingMode.UP).doubleValue();
            accounts.add(new Account(i, balance));
            users.add(new User(i));
        }
    }

    public void updateAccount(Account account) {
        Account accountToUpdate = accounts.stream().filter(i -> i.getId() == account.getId()).findAny().orElse(null);
        if (accountToUpdate != null) {
            accountToUpdate.setBalance(account.getBalance());
        }
    }

    public List<Account> createAccountCash(){
        return accounts.stream().collect(Collectors.toList());
    }

    public List<User> createUserCash(){
        return users.stream().collect(Collectors.toList());
    }

    @Override
    public Account getAccount(Long id) {
        try {
            // call to datasource take some time :) maybe we should optimize?
            Thread.sleep(generateTimeout());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Account account = accounts.stream().filter(i -> i.getId() == id).findAny().orElse(null);
        return copy(account);
    }

    @Override
    public User getUser(Long id) {
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