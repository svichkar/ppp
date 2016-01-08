package com.nixsolutions.reflections;

/**
 * this class was created only for testing reflections task
 * @author kryzhanovskiy
 *
 */
public class SimpleCar {
	
	@ToString
	private String color;
	@ToString
	private String brand;
	@ToString
	private int speed;
	
	private Double price;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
