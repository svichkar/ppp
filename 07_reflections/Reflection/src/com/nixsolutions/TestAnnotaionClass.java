/**
 * 
 */
package com.nixsolutions;

import java.util.Date;

/**
 * @author mixeyes
 *
 */
public class TestAnnotaionClass {
	
	@ToString
	public Integer intVAlue = 100;
	
	@ToString
	public String strValue = "String value";
	
	@ToString
	public Date actualDate = new Date();
	
	public String notAnnoValue;

}
