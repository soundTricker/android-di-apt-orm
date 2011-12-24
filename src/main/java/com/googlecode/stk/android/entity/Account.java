package com.googlecode.stk.android.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Account {
	
	public Account() {
	}

	public Account(String name) {
		this.name = name;
	}

	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField
	public String name;
}
