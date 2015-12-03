/**
 * 
 */
package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mixeyes
 *
 */
@Entity
@Table(name = "part")
public class Part implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer part_id;
	
	@Column(name = "part_name", length = 50, nullable = false)
	private String part_name;
	
	@Column(name = "vendor", length = 50, nullable = false)
	private String vendor;
	
	@Column(name = "amount", columnDefinition = "int default 0")
	private Integer amount;

	public Integer getPart_id() {
		return part_id;
	}

	public void setPart_id(Integer part_id) {
		this.part_id = part_id;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
