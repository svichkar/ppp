package com.nixsolutions;

import java.util.Random;

public class Circle extends Shape {

	@Override
	public float area() {
		return (float) (Math.PI * Math.pow(getSize(), 2));
	}
	
	public Circle(){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
	
	public Circle(int coordX, int coordY, float size){
		setSize(size);
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	public Circle(int coordX, int coordY){
		Random rand = new Random();
		setSize(rand.nextFloat());
		setCoordX(coordX);
		setCoordY(coordY);
	}
	
	public Circle(float size){
		Random rand = new Random();
		setSize(size);
		setCoordX(rand.nextInt());
		setCoordY(rand.nextInt());
	}
}
