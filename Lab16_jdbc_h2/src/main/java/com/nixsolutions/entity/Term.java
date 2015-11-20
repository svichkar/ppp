package com.nixsolutions.entity;

public class Term extends BaseEntity {

	private int termId;
	private String alias;
	
	public Term(int termId, String alias){
		this.termId = termId;
		this.alias = alias;
	}

	@Override
	public int getId() {		
		return termId;
	}
	
	public String getAlias(){
		return alias;
	}
	
	public void setId(int termId) {		
		this.termId = termId;
	}
	
	public void setAlias(String alias){
		this.alias = alias;
	}
}
