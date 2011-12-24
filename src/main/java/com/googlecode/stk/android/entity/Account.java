package com.googlecode.stk.android.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Account {

	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField
	public String name;
}
