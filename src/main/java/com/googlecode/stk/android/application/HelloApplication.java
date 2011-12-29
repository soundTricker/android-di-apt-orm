package com.googlecode.stk.android.application;

import java.util.List;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;

import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.googlecode.stk.android.db.DaoProvider;
import com.googlecode.stk.android.db.DatabaseHelper;
import com.googlecode.stk.android.db.entity.Account;
import com.googlecode.stk.android.service.HelloService;
import com.googlecode.stk.android.service.HelloServiceImpl;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;


public class HelloApplication extends RoboApplication {

	private OrmLiteSqliteOpenHelper helper;

	@Override
	public void onCreate() {
		OpenHelperManager.setOpenHelperClass(DatabaseHelper.class);
		
		helper = OpenHelperManager.getHelper(this , DatabaseHelper.class);
		
		getInjector().injectMembers(helper);
		super.onCreate();
	}
	
	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new HelloModule(helper));
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		OpenHelperManager.releaseHelper();
	}
	
	static class HelloModule extends AbstractAndroidModule {

		private OrmLiteSqliteOpenHelper helper;

		public HelloModule(OrmLiteSqliteOpenHelper helper) {
			super();
			this.helper = helper;
		}

		@Override
		protected void configure() {
			bind(OrmLiteSqliteOpenHelper.class).toInstance(helper);
			bind(new TypeLiteral<Dao<Account,Integer>>() {
			}).toProvider(new DaoProvider<Account>(helper.getConnectionSource(), Account.class)).in(Singleton.class);
			
			bind(HelloService.class).to(HelloServiceImpl.class);
		}
		
	}
	
}
