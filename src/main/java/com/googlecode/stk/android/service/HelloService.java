package com.googlecode.stk.android.service;

import java.util.List;

import com.googlecode.stk.android.entity.Account;

public interface HelloService {

	public String hello();

	public int createAccount(String name);
	
	public void deleteAccount(int id);
	
	void destroy();

	public List<Account> findAllAccount();
}
