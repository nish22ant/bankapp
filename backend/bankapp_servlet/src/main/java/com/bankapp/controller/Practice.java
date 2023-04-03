package com.bankapp.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bankapp.configuration.BankAppConfig;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.dao.AccountDaoJDBC;
import com.bankapp.model.service.AccountService;

public class Practice {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BankAppConfig.class);
		AccountService accountService = context.getBean("acc", AccountService.class);
		System.out.println(accountService.getAccount(1));
	}
}
