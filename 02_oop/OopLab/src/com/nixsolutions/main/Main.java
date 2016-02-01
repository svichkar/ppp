package com.nixsolutions.main;

import java.util.Random;

import com.nixsolutions.figure.Circle;
import com.nixsolutions.figure.Figure;
import com.nixsolutions.figure.Square;
import com.nixsolutions.figure.Triangle;

public class Main {

	public static final int FIGURE_AMOUNT = 10;

	public static void changeSize(Figure[] arrFigures) {
		Random randNum = new Random();
		for (int i = 0; i < FIGURE_AMOUNT; i++) {
			arrFigures[i].resize(randNum.nextFloat());
		}
	}

	public static void sortByArea(Figure[] arrFigures) {
		Figure tmpFigure;
		boolean swapped = true;
		int j = 0;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < arrFigures.length - j; i++) {
				if (arrFigures[i].calculateArea() > arrFigures[i + 1]
						.calculateArea()) {
					tmpFigure = arrFigures[i];
					arrFigures[i] = arrFigures[i + 1];
					arrFigures[i + 1] = tmpFigure;
					swapped = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		Figure[] arrFigures = new Figure[FIGURE_AMOUNT];
		Random randNum = new Random();
		for (int i = 0; i < arrFigures.length; i++) {
			int numFigure = randNum.nextInt(3);
			if (numFigure == 1) {
				arrFigures[i] = new Square();
			} else if (numFigure == 2) {
				arrFigures[i] = new Triangle();
			} else {
				arrFigures[i] = new Circle();
			}
		}
		changeSize(arrFigures);
		sortByArea(arrFigures);
	}
}
