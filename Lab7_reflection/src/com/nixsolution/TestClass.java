package com.nixsolution;

public class TestClass {

	@ToString
	private Integer anotated;
	
	private String notAnotated;

	public String getString(){
		return notAnotated;
	}
	public void setString(String stringValue){
		notAnotated = stringValue;
	}
	public Integer getInteger(){
		return anotated;
	}
	public void setInteger(Integer integerValue){
		anotated = integerValue;
	}
	
	@Override
	public String toString(){
		return "This is test class!!!";
	}
}
