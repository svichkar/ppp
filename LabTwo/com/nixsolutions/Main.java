package com.nixsolutions;

import java.util.Random;

/** Main class is created to hold the main method for the "OOP" quiz. */
public class Main {
	
	public static void main(String[] args){
		Shape[] shapes = new Shape[10];
		generateShapes(shapes);
		modifySize(shapes);
		
	}
	
	private static void generateShapes(Shape[] shapes){
		for (int i=0;i<shapes.length;i++){
			Random rand = new Random();
			switch (rand.nextInt(3)){
			case 0:	{
				shapes[i] = new Circle();
				break;
			}
			case 1:{
				shapes[i] = new Square();
				break;
			}
			case 2:{
				shapes[i] = new Triangle();
				break;
			}
			}
		}
	}
	
	private static void modifySize(Shape[] shapes){
		for (int i=0;i<shapes.length;i++){
			Random rand = new Random();
			shapes[i].changeSize(rand.nextFloat());
		}
	}
	
	private static void sortByArea(Shape[] shapes){
		
	}
}
