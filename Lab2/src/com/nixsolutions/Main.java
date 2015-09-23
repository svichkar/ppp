package com.nixsolutions;

import java.util.Random;

public class Main {
	
	/**
	 * Creates array of 10 random vehicles.
	 * Simulates move of vehicles.
	 * Sorts array of vehicles by passed distance.
	 */
	public static void main(String[] args){
		Transport[] transportArray = new Transport[10];
		Random rdm = new Random();
		for (int i = 0; i < transportArray.length; i++){
			switch (rdm.nextInt(3)){
			case 0: transportArray[i] = new Car();
					break;
			case 1: transportArray[i] = new Bus();
					break;
			case 2: transportArray[i] = new Bicycle();
					break;
			default: break;
			}
		}
		for (int i = 0; i < 5; i++){
			switch (rdm.nextInt(3)){
				case 0: for (int j = 0; j < transportArray.length; j++){
							transportArray[j].leftTurn();
						}
						break;
				case 1: for (int j = 0; j < transportArray.length; j++){
							transportArray[j].rightTurn();
						}
						break;
				case 2: int stepsCount = rdm.nextInt(5);
						for (int j = 0; j < transportArray.length; j++){
							for (int sc = 0; sc <= stepsCount; sc++){
								transportArray[j].rightTurn();
							}
						}
						break;
				default: break;
			}
		}
		for (int i = 0; i < transportArray.length - 1; i++){
			for(int j = 0; j < transportArray.length - 1 - i; j++){
				if (transportArray[j].getDistance()>transportArray[j+1].getDistance()){
					Transport b = transportArray[j];
					transportArray[j] = transportArray[j+1];
					transportArray[j+1] = b;
				}
			}
		}
	}
}
