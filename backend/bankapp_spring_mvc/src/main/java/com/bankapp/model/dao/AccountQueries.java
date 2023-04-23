package com.bankapp.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:queriesMySQL.properties")
public class AccountQueries {
	
	@Autowired
	private Environment environment;
	private String findById = environment.getProperty("accounts.query.find.by.id");
	private String deleteById;
	private String update;
	private String findAll;
	private String insert;
	public String getFindById() {
		return findById;
	}
	public void setFindById(String findById) {
		this.findById = findById;
	}
	public String getDeleteById() {
		return deleteById;
	}
	public void setDeleteById(String deleteById) {
		this.deleteById = deleteById;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getFindAll() {
		return findAll;
	}
	public void setFindAll(String findAll) {
		this.findAll = findAll;
	}
	public String getInsert() {
		return insert;
	}
	public void setInsert(String insert) {
		this.insert = insert;
	}
	
	
}
