package com.nixsolutions;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
for (int i = 0; i < 5; i++) {
	System.out.println(random(1, 5));
}
		// создание массива рандомных транспортных средств размером в 10
		// элементов.
		Machine[] autopark = generateArr();

		// далее 5 циклов движения, каждый цикл это 1 шаг влево или 1 шаг вправо
		// или 1-5 шагов вперед.
		for (int i = 0; i < 5; i++) {
		/*	switch (random(1, 3)) {
			case 1:
				for (Machine machine : autopark) {
					machine.turnLeft();
				}
				break;
			case 2:
				for (Machine machine : autopark) {
					machine.turnRight();
				}
				break;
			case 3:
				for (int y = 0; y < random(1, 5); y++) {
					
					for (Machine machine : autopark) {
						machine.move();
						System.out.println(y);
					}
					
				}
				break;
			default:
				break;
			}
		}*/
			
			for (int j = 0; j < autopark.length; j++) {
				mooveCycle(autopark[j]);
			}
		}
		// сортировка массива по пройденной дистанции.
		Arrays.sort(autopark);

		for (Machine machine : autopark) {
			System.out.println(machine.getName() + " " + machine.getDistance());
		}
		
}
	public static Machine[] generateArr() {

		Machine[] autopark = new Machine[10];
		for (int i = 0; i < autopark.length; i++) {

			switch (random(1, 3)) {
			case 1:
				autopark[i] = new Car();
				break;
			case 2:
				autopark[i] = new Bus();
				break;
			case 3:
				autopark[i] = new Bicycle();
				break;
			default:
				break;
			}

		}
		return autopark;
	}

	public static void mooveCycle(Machine auto) {
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

	public static int random(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;

	}
}
