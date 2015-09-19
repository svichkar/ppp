package com.nixsolutions;

public abstract class Shape {
	
	private int coordX;
	private int coordY;
	private float size;
	
	protected void setCoordX(int value){
		coordX = value;
	}
	
	protected void setCoordY(int value){
		coordY = value;
	}
	
	protected void setSize(float value){
		if (value<0){
			size = 0;
			System.out.println("Size cannot be less than zero. Setting shape size to 0.");
		} else {
			size = value;
		}
	}
	
	public int getCoordX(){
		return coordX;
	}
	
	public int getCoordY(){
		return coordY;
	}
	
	public float getSize(){
		return size;
	}
	
	public void changeSize(float modifier){
		setSize(getSize()*modifier);
	}
	
	public void move(int diffX, int diffY){
		setCoordX(getCoordX()+diffX);
		setCoordY(getCoordY()+diffY);
	}
	
	public abstract float area();
}
