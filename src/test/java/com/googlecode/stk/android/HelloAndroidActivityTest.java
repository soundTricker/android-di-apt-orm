package com.googlecode.stk.android;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.stk.android.db.entity.Account;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class HelloAndroidActivityTest {

	@Before
	public void before() {
		Robolectric.application.onCreate();
	}
	
	@Test
	public void アカウント名入力なし() {
		HelloAndroidActivity_ activity = new HelloAndroidActivity_();
		activity.onCreate(null);
		
		activity.initViews();

		activity.addAccount();
		
		List<Account> list = activity.helloService.findAllAccount();
		
		assertThat(list, is(notNullValue()));
		
		assertThat(list.size(), is(0));
	}

	@Test
	public void アカウント名入力() {
		HelloAndroidActivity_ activity = new HelloAndroidActivity_();
		activity.onCreate(null);
		
		activity.initViews();

		activity.accountName.setText("test");
		
		activity.addAccount();
		
		List<Account> list = activity.helloService.findAllAccount();
		
		assertThat(list, is(notNullValue()));
		
		assertThat(list.size(), is(1));
		
		assertThat(list.get(0).name, is("test"));
	}

}
