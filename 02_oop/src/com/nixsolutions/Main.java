package com.nixsolutions;

import java.util.Random;

/**
 * The Class Main.
 */
public class Main {
	/**
	 * The main method.
	 *
	 */
	public static void main(String[] args) {
		int countOfFigure = 10;
		int typeFigure;
		double koef;
		FeometricFigure[] figure = new FeometricFigure[countOfFigure];
		Random random = new Random();
		for (int i = 0; i < countOfFigure; i++) {
			typeFigure = random.nextInt(3);
			switch (typeFigure) {
			case 0:
				figure[i] = new Square();
				break;
			case 1:
				figure[i] = new Triangle();
				break;
			case 2:
				figure[i] = new Circle();
			}

		}
		System.out.println("Areas of all figures before sorting:");

		for (int i = 0; i < countOfFigure; i++) {

			System.out.println("Area of " + figure[i].nameOfFigure() + "=" + figure[i].area());

		}
		System.out.println("");

		sortFiguresByArea(figure);

		System.out.println("Areas of all figures after sorting:");
		for (int i = 0; i < countOfFigure; i++) {

			System.out.println("Area of " + figure[i].nameOfFigure() + "=" + figure[i].area());

		}
		System.out.println("");

		for (int i = 0; i < countOfFigure; i++) {
			koef = random.nextDouble();
			System.out.println("Change size of figures with " + koef + " coefficient");
			System.out.println("New size area for " + figure[i].nameOfFigure() + "=" + figure[i].changeSize(koef));
		}

	}

	/**
	 * Sort figures by area.
	 *
	 * @param massive
	 *            of figures
	 */
	private static void sortFiguresByArea(FeometricFigure[] figure) {
		for (int i = 0; i < figure.length; i++) {
			for (int j = 0; j < figure.length - i - 1; j++) {
				if (figure[j].area() > figure[j + 1].area()) {
					FeometricFigure temp = figure[j];
					figure[j] = figure[j + 1];
					figure[j + 1] = temp;
				}
			}
		}

	}

}
