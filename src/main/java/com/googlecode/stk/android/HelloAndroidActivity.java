package com.googlecode.stk.android;

import android.util.Log;
import android.widget.TextView;

import com.google.inject.Inject;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.RoboGuice;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.stk.android.db.DatabaseHelper;
import com.googlecode.stk.android.service.HelloService;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

@EActivity(R.layout.main)
@RoboGuice
public class HelloAndroidActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	private static String TAG = "maven-test";
	
	@ViewById
	TextView text;
	
	@Inject
	HelloService helloService;
	
	@Click
	void button() {
		
		Log.i(TAG, "click");
		text.setText(helloService.hello());
	}
	
}
