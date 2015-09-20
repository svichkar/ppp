package com.nixsolutions;

import java.util.Random;

public class Triangle extends Shape {

	@Override
	public float area() {
		return (float) (Math.sqrt(3) * Math.pow(getSize(), 2) / 4d);
	}
	
	public Triangle(){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
	
	public Triangle(int coordX, int coordY, float size){
		setSize(size);
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	public Triangle(int coordX, int coordY){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	public Triangle(float size){
		Random rand = new Random();
		setSize(size);
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
}
