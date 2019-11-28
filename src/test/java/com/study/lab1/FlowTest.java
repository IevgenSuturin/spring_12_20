package com.study.lab1;

import com.study.lab1.handler.RequestHandler;
import com.study.lab1.model.Account;
import com.study.lab1.model.Request;
import com.study.lab1.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FlowTest {
    @Test
    public void testSuccessWidthdrawRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        RequestHandler requestHandler = applicationContext.getBean(RequestHandler.class);
        AccountService accountService = requestHandler.getAccountService();

        // prepare
        Account accountFromBeforeRequest = accountService.getAccount(1);
        Account accountToBeforeRequest = accountService.getAccount(2);
        System.out.println("<===Before widthdraw===>");
        System.out.println(accountFromBeforeRequest);
        System.out.println(accountToBeforeRequest);

        Request request = new Request();
        request.setAccountFromId(1);
        request.setAccountToId(2);
        request.setOperation("w");
        request.setUserId(1);

        // action
        requestHandler.handle(request);

        // check
        Account accountFromAfterRequest = accountService.getAccount(1);
        Account accountToAfterRequest = accountService.getAccount(2);
        System.out.println("<===After widthdraw===>");
        System.out.println(accountFromAfterRequest);
        System.out.println(accountToAfterRequest);

        double allMoneyBefore = accountFromBeforeRequest.getBalance() + accountToBeforeRequest.getBalance();
        double allMoneyAfter = accountFromAfterRequest.getBalance() + accountToAfterRequest.getBalance();

        assertFalse(accountFromBeforeRequest.getBalance() == accountFromAfterRequest.getBalance());
        assertFalse(accountToBeforeRequest.getBalance() == accountToAfterRequest.getBalance());
        assertEquals(allMoneyBefore, allMoneyAfter, 0.1);
    }

    @Test
    public void testSuccessCounterbalanceRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");

        RequestHandler requestHandler = applicationContext.getBean(RequestHandler.class);
        AccountService accountService = requestHandler.getAccountService();

        // prepare
        Account accountFromBeforeRequest = accountService.getAccount(1);
        Account accountToBeforeRequest = accountService.getAccount(2);
        System.out.println("<===Before counterbalance===>");
        System.out.println(accountFromBeforeRequest);
        System.out.println(accountToBeforeRequest);

        Request request = new Request();
        request.setAccountFromId(1);
        request.setAccountToId(2);
        request.setOperation("cb");
        request.setUserId(1);

        // action
        requestHandler.handle(request);

        // check
        Account accountFromAfterRequest = accountService.getAccount(1);
        Account accountToAfterRequest = accountService.getAccount(2);
        System.out.println("<===After counterbalance===>");
        System.out.println(accountFromAfterRequest);
        System.out.println(accountToAfterRequest);

        double allMoneyBefore = accountFromBeforeRequest.getBalance() + accountToBeforeRequest.getBalance();
        double allMoneyAfter = accountFromAfterRequest.getBalance() + accountToAfterRequest.getBalance();

        assertEquals(allMoneyBefore, allMoneyAfter, 0.1);
        assertEquals(accountFromAfterRequest.getBalance(),  accountToAfterRequest.getBalance(), 0.1);
    }
}
