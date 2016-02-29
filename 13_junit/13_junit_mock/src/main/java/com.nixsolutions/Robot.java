package com.nixsolutions;

//import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Реализовать класс Robot, умеющий выполнять команды turnLeft, turnRight, stepForward. Начальные координаты 0,0, смотрит в направлении
 *  оси X; команда шаг вперед изменяет координаты в соответствии с направлением и записывает их в файл. Реализовать класс Program, 
 *  выполняющий цепочку команд над роботом, заданным кодом (lffrflfrrfff).
*/

public class Robot {
	private int x = 0;
	private int y = 0;
	private int angle = 0;
	//private FileWriter fileWriter;
	File file = new File("DataPoints.txt");

		
	public void turnLeft(){
		if(this.angle == 360)
		{
			this.angle = 0;
		}
		angle = angle+90;
	}
	
	public void turnRight(){
		if(this.angle == 0)
		{
			this.angle = 360;
		}
		angle = angle-90;
		
	}
	
	public void stepForward() throws IOException{
		switch(angle){
		case 0:
			x = x + 1;
			break;
		case 90:
			y = y + 1;
			break;
		case 180:
			x = x - 1;
			break;
		case 270:
			y = y - 1;
			break;
		case 360:
			x = x + 1;
			break;
		}
		writePointsToFile();
		
	}
	
	public void writePointsToFile() throws IOException, FileNotFoundException {
		PrintWriter out = new PrintWriter(file);
		out.println("X: "+x+" and Y: "+y);
		out.close();
	}
	

		
	}

	


