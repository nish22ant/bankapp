package com.bankapp.controller;

import com.bankapp.model.dao.AccountQueries;


public class Main {
	public static void main(String[] args) {
//		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(BankappConfig.class);
//		System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
		AccountQueries aq = new AccountQueries();
		System.out.println(aq.getFindById());
	}
}
