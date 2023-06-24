package com.bankapp.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.bankapp.configuration.BankappConfig;
import com.bankapp.model.entity.Account;
import com.bankapp.model.service.AccountService;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(BankappConfig.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for(String bean: beanDefinitionNames) {
			System.out.println(bean);
			
		}
		Account account = new Account(1, 1, "Charizard", 1000);
		AccountService serviceBean = applicationContext.getBean("accountService", AccountService.class);
		System.out.println(serviceBean.transfer(1, 2, 50, "password"));

	}

}
