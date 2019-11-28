package com.study.lab1.service;

import com.study.lab1.dao.AccountDao;
import com.study.lab1.model.Account;
import com.study.lab1.model.Operation;

import java.util.Random;

public class AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account getAccount(long id) {
        return accountDao.get(id);
    }

    public void operate(Account from, Account to, Operation operation) {
        System.out.println("Starting " + operation + " operation");
        double fromBalance = from.getBalance();
        double toBalance = to.getBalance();
        if (operation == Operation.COUNTERBALANCE) {
            double fairBalance = (fromBalance + toBalance) / 2;
            from.setBalance(fairBalance);
            to.setBalance(fairBalance);
        } else if (operation == Operation.WIDTHDRAW) {
            Random random = new Random();
            double amountToWidthdraw = random.nextInt((int) fromBalance);
            from.setBalance(fromBalance - amountToWidthdraw);
            to.setBalance(toBalance + amountToWidthdraw);
        }
    }
}
