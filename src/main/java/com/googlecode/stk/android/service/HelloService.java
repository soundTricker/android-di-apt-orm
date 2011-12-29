package com.googlecode.stk.android.service;

import java.util.List;

import com.googlecode.stk.android.db.entity.Account;

public interface HelloService {

	public String hello();

	public int createAccount(String name);
	
	public void deleteAccount(int id);
	
	public List<Account> findAllAccount();
}
