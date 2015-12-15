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

	public Part() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="part_id")
	private Integer partId;
	
	@Column(name = "part_name", length = 50, nullable = false)
	private String partName;
	
	@Column(name = "vendor", length = 50, nullable = false)
	private String vendor;
	
	@Column(name = "amount", columnDefinition = "int default 0")
	private Integer amount;

	public Integer getPart_id() {
		return partId;
	}

	public void setPart_id(Integer part_id) {
		this.partId = part_id;
	}

	public String getPart_name() {
		return partName;
	}

	public void setPart_name(String part_name) {
		this.partName = part_name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((partId == null) ? 0 : partId.hashCode());
		result = prime * result + ((partName == null) ? 0 : partName.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (partId == null) {
			if (other.partId != null)
				return false;
		} else if (!partId.equals(other.partId))
			return false;
		if (partName == null) {
			if (other.partName != null)
				return false;
		} else if (!partName.equals(other.partName))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}

}
