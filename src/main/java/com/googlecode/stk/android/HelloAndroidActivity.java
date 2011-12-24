package com.googlecode.stk.android;

import java.util.List;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.inject.Inject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.RoboGuice;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.stk.android.db.DatabaseHelper;
import com.googlecode.stk.android.entity.Account;
import com.googlecode.stk.android.service.HelloService;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

@EActivity(R.layout.main)
@RoboGuice
public class HelloAndroidActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	private static String TAG = "maven-test";
	
	@ViewById
	TextView text;
	
	@ViewById(R.id.account_name)
	EditText accountName;

	@ViewById(R.id.account_ids)
	Spinner accountIds;
	
	@Inject
	HelloService helloService;

	@Click
	void button() {
		
		Log.i(TAG, "click");
		text.setText(helloService.hello());
	}
	
	@AfterViews
	void initViews() {
		reloadAccountIds();
	}

	private void reloadAccountIds() {

		List<Account> list = helloService.findAllAccount();
		ArrayAdapter<Integer> accountIdAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item);
		
		for (Account account : list) {
			accountIdAdapter.add(account.id);
		}
		
		accountIds.setAdapter(accountIdAdapter);
	}
	
	
	@Click
	void addAccount() {
		helloService.createAccount(accountName.getText().toString());
		reloadAccountIds();
	}
	
	@Click
	void deleteAccount() {
		Integer accountId = (Integer)accountIds.getSelectedItem();
		
			helloService.deleteAccount(accountId);
			reloadAccountIds();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		helloService.destroy();
		
	}
}
