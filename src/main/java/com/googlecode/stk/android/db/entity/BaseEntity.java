package com.googlecode.stk.android.db.entity;

import com.j256.ormlite.field.DatabaseField;


public abstract class BaseEntity {

	@DatabaseField(generatedId = true)
	public Integer id;
	
	@Override
	public int hashCode() {
		
		final int multiplier = 37;
		
		int result = 11;
		
		return multiplier * result + ((id == null)? 0 : id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!this.getClass().isInstance(obj)) {
			return false;
		}
		
		BaseEntity entity = (BaseEntity) obj;
		
		if(entity.id == null) {
			return this.id == null;
		}
		
		if(this.id == null) {
			return entity.id == null;
		}
		
		return entity.id.equals(this.id);
	}
}
