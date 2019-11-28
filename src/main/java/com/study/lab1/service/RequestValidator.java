package com.study.lab1.service;

import com.study.lab1.dao.UserDao;
import com.study.lab1.model.Request;
import com.study.lab1.model.User;

public class RequestValidator {
    private UserDao userDao;

    public void validate(Request request) {
        long userId = request.getUserId();
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new SecurityException("User with id = " + userId + " not found");
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
