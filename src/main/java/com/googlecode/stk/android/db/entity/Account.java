package com.googlecode.stk.android.db.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Account extends BaseEntity {
	
	public Account() {
	}

	public Account(String name) {
		this.name = name;
	}

	@DatabaseField
	public String name;
}
