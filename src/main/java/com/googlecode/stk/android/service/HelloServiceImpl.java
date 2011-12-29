package com.googlecode.stk.android.service;

import java.sql.SQLException;
import java.util.List;

import roboguice.inject.InjectResource;
import roboguice.util.Ln;
import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.googlecode.stk.android.R;
import com.googlecode.stk.android.db.entity.Account;
import com.j256.ormlite.dao.Dao;


public class HelloServiceImpl implements HelloService{
	
	Context context;
	
	@InjectResource(R.string.hello)
	String hello;

	@Inject
	private Dao<Account, Integer> accountDao;
	
	@Inject
	public HelloServiceImpl(Context context) {
		this.context = context;
	}

	@Override
	public String hello() {
		Log.i("HelloServiceImpl", hello);
		return hello + " call";
	}

	@Override
	public int createAccount(String name) {
		
		Account account = new Account(name);
		
		try {
			accountDao.create(account);
		} catch (SQLException e) {
			Ln.e(e);
		}
		return -1;
	}
	
	@Override
	public void deleteAccount(int id) {
		
		try {
			accountDao.deleteById(id);
			Ln.d("delete account id:%d", id);
		} catch (SQLException e) {
			Ln.e(e);
		}
	}

	@Override
	public List<Account> findAllAccount() {
		try {
			return accountDao.queryForAll();
		} catch (SQLException e) {
			Ln.e(e);
			throw new RuntimeException(e);
		}
	}
}
