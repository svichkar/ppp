package com.nixsolutions;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		// creation of an array with 10 vehicles 
		Movable[] vehicles = generateArr();

		//Execution of the 5 movement cycles
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < vehicles.length; j++) {
				mooveCycle(vehicles[j]);
			}
		}

		// sort of the vehicles array by distance 
		Arrays.sort(vehicles);

		// output of the array in to the console
		for (Movable machine : vehicles) {
			System.out.println(machine.getClass().getSimpleName() + " " + ((Vehicle) machine).getDistance());
		}

	}

	/**
	 * generates an array of 10 random vehicles: Car, Bus, Bicycle
	 * 
	 * @return an array of Movable[] vehicles
	 */
	public static Movable[] generateArr() {
		Movable[] vehicles = new Movable[10];
		for (int i = 0; i < vehicles.length; i++) {
			switch (random(1, 3)) {
			case 1:
				vehicles[i] = new Car();
				break;
			case 2:
				vehicles[i] = new Bus();
				break;
			case 3:
				vehicles[i] = new Bicycle();
				break;
			default:
				break;
			}
		}
		return vehicles;
	}

	/**
	 * executes randomly move(), turnLeft, turnRight() methods of a device with
	 * implemented Movable interface
	 * 
	 * @param auto
	 *            an object with implemented Movable interface
	 */
	public static void mooveCycle(Movable auto) {
		switch (random(1, 3)) {
		case 1:
			auto.turnLeft();
			break;
		case 2:
			auto.turnLeft();
			break;
		case 3:
			for (int i = 0; i < random(2, 5); i++) {
				auto.move();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * generates a random number within the given range
	 * 
	 * @param min
	 *            minimal value of the range
	 * @param max
	 *            maximum value of the range
	 * @return random int value within the given range
	 */
	public static int random(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
