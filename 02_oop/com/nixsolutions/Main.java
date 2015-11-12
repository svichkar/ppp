package com.nixsolutions;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		Transport[] transport = new Transport[10];
		int[] distance = new int[10];
		for (int i = 0; i < 10; i++) {
			int number = rand.nextInt(3);
			switch (number) {
			case 0:
				transport[i] = new Car(11);
				break;
			case 1:
				transport[i] = new Bus(9);
				break;
			case 2:
				transport[i] = new Bicycle(8);
				break;
			}

			for (int j = 0; j < 5; j++) {
				number = rand.nextInt(3);
				switch (number) {
				case 0:
					transport[i].left();
					break;
				case 1:
					transport[i].right();
					break;
				case 2:
					int stepRandom = 1 + rand.nextInt(5);
					transport[i].move(stepRandom);
					break;
				}
			}
			distance[i] = transport[i].getDistance();
		}
		System.out.println("Before:");
		for (int i = 0; i < 10; i++) {
			System.out.println("Transport name: " + transport[i].getClass().getName() + ", distance: "
					+ transport[i].getDistance());
		}

		System.out.println("After sorting:");
		Transport[] sortedArr = sortTransport(transport);
		for (int i = 0; i < 10; i++) {
			System.out.println("Transport name: " + sortedArr[i].getClass().getName() + ", distance: "
					+ sortedArr[i].getDistance());
		}

	}

	public static Transport[] sortTransport(Transport[] transportArr) {
		Transport[] newSortedArr = transportArr;
		for (int i = newSortedArr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {

				if (newSortedArr[j].getDistance() > newSortedArr[j + 1].getDistance()) {
					Transport tmp = newSortedArr[j];
					newSortedArr[j] = newSortedArr[j + 1];
					newSortedArr[j + 1] = tmp;
				}
			}
		}
		return newSortedArr;
	}

}
