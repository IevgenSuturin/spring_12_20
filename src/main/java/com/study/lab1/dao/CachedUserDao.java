package com.study.lab1.dao;

import com.study.lab1.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Aspect
public class CachedUserDao {
    UserDao userDao;

    List<User> users = new ArrayList<>();

    public CachedUserDao(UserDao userDao){
        this.userDao = userDao;
   }

    @Around("execution(com.study.lab1.model.User com.study.lab1.dao.UserDao.getUser(..))")
    public User getUser(ProceedingJoinPoint thisJoinPoint)  throws Throwable{
        Object[] methodArgs = thisJoinPoint.getArgs();
        User cashedUser = users.stream().filter(user -> user.getId()==(Long) methodArgs[0]).findAny().orElse(null);
        if(cashedUser == null){
            cashedUser = (User) thisJoinPoint.proceed();
            users.add(cashedUser);
        }
        return cashedUser;
    }
}
