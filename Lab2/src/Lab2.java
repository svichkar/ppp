import java.util.Random;

public class Lab2 {
	/**
	 * The main method - start point of program
	 */
	public static void main(String[] args) {
		final int countOfFigures = 10;
		Figure[] figures = new Figure[countOfFigures];

		for (int i = 0; i < figures.length; i++) {
			int typeOfFigure = new Random().nextInt(3);
			switch (typeOfFigure) {
			case 0:
				figures[i] = new Circle();
				break;
			case 1:
				figures[i] = new Square();
				break;
			case 2:
				figures[i] = new Triangle();
				break;
			}
		}
		for (int i = 0; i < figures.length; i++) {
			float coef = (float) Math.random();
			figures[i].changeSize(coef);
		}
		sortFiguresByArea(figures);
	}

	/**
	 * The method will sort the input array of Figures by area
	 * 
	 * @param mas
	 *            - array of Figures
	 */
	public static void sortFiguresByArea(Figure[] mas) {
		for (int i = 0; i < mas.length; i++) {
			for (int j = 0; j < mas.length - i - 1; j++) {
				if (mas[j].area() > mas[j + 1].area()) {
					Figure temp = mas[j];
					mas[j] = mas[j + 1];
					mas[j + 1] = temp;
				}
			}
		}
	}
}
