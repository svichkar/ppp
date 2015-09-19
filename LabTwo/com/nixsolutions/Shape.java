package com.nixsolutions;

public abstract class Shape {
	
	private int coordX;
	private int coordY;
	
	private void setCoordX(int value){
		coordX = value;
	}
	
	private void setCoordY(int value){
		coordY = value;
	}
	
	public int getCoordX(){
		return coordX;
	}
	
	public int getCoordY(){
		return coordY;
	}
	
	public void move(int diffX, int diffY){
		setCoordX(getCoordX()+diffX);
		setCoordY(getCoordY()+diffY);
	}
	
	public abstract int area();
	public abstract int changeSize();
}
