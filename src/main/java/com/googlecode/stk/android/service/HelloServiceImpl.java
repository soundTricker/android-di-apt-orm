package com.googlecode.stk.android.service;

import android.util.Log;

import com.googlecode.stk.android.R;

import roboguice.inject.InjectResource;


public class HelloServiceImpl implements HelloService {
	
	@InjectResource(R.string.hello)
	String hello;

	@Override
	public String hello() {
		Log.i("HelloServiceImpl", hello);
		return hello + " call";
	}

}
