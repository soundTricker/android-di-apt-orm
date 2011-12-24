package com.googlecode.stk.android.db;

import java.sql.SQLException;
import java.util.List;

import roboguice.util.Ln;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.common.collect.Lists;
import com.googlecode.stk.android.entity.Account;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "helloAndroid.db";
	
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null , DATABASE_VERSION);
	}
	
	private List<Class<?>> findEntityClass() {
		List<Class<?>> tables = Lists.newArrayList();
		
		tables.add(Account.class);
		
		return tables;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		
		try {
			
			for (Class<?> clazz : findEntityClass()) {
				TableUtils.createTable(connectionSource, clazz);
				Ln.d("create %s table", clazz.getSimpleName());
			}
			
		} catch (SQLException e) {
			Ln.e(e, "Can't create Database Table");
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		
		try {
			for (Class<?> clazz : findEntityClass()) {
				
				TableUtils.dropTable(connectionSource, clazz, true);
				Ln.d("drop %s table", clazz.getSimpleName());
			}
			onCreate(db , connectionSource);
		} catch (SQLException e) {
			Ln.e(e , "Can't update Database Table");
			throw new RuntimeException(e);
		}
	}

}
