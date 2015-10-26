package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Robot {

	private int x;
	private int y;
	private int direction;
	public int getDirection() {
		return direction;
	}

	private FileWriter fw = null; 
	
	File file;
	public Robot(File file) {
	   this.file = file;
		
		file.delete();
		
		x = 0;
		y = 0;
		direction = 2;
		System.out.println("Now robot is on x - " + x + ", y - " + y
				+ " and looks at " + direction);
		robotMovementLoger();
		 
	}
	
	public FileWriter getFw() {
		return fw;
	}
	public void setFw(FileWriter fw) {
		this.fw = fw;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveOneStepFowdard() {

		switch (direction) {
			case 1 :
				y++;
				break;
			case 2 :
				x++;
				break;
			case 3 :
				y--;
				break;
			case 4 :
				x--;
				break;
			default :
				break;
		}

		System.out.println("Now robot is on x - " + x + ", y - " + y
				+ " and looks at " + direction);
		robotMovementLoger();
	}
	public void changeDirection(String direction) {
		switch (direction) {
			case "l" :
				this.direction--;
				if (this.direction < 1)
					this.direction = this.direction + 4;
				break;
			case "r" :
				this.direction++;
				if (this.direction > 4)
					this.direction = this.direction - 4;
				break;
			default :
				break;
		}
		System.out.println("Now robot is on x - " + x + ", y - " + y
				+ " and looks at " + this.direction);
		robotMovementLoger();
	}

	private void robotMovementLoger(){
		
		try {
			fw = new FileWriter(file, true);
			fw.write("Now robot is on x - " + x + ", y - " + y + " and looks at " + this.direction+"\r\n");
		} catch (IOException e) {
			throw new RuntimeException();
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}
}
