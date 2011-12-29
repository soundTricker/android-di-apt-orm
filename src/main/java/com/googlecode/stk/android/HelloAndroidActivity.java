package com.googlecode.stk.android;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.RoboGuice;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.stk.android.db.entity.Account;
import com.googlecode.stk.android.service.HelloService;

@EActivity(R.layout.main)
@RoboGuice
public class HelloAndroidActivity extends Activity {

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
		String name = accountName.getText().toString();
		
		if(Strings.isNullOrEmpty(name)) {
			Toast.makeText(this, "account name is empty", Toast.LENGTH_SHORT).show();
			return;
		}
		
		helloService.createAccount(accountName.getText().toString());
		reloadAccountIds();
	}

	@Click
	void deleteAccount() {
		Integer accountId = (Integer) accountIds.getSelectedItem();
		
		if(accountId == null) {
			Toast.makeText(this, "id is nothing", Toast.LENGTH_SHORT).show();
			return;
		}

		helloService.deleteAccount(accountId);
		reloadAccountIds();
	}
}
