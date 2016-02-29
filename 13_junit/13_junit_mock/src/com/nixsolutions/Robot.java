package com.nixsolutions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/*Реализовать класс Robot, умеющий выполнять команды turnLeft, turnRight, stepForward. Начальные координаты 0,0, смотрит в
направлении оси X; команда шаг вперед изменяет координаты в соответствии с направлением и записывает их в поток. 
Реализовать класс Program, выполняющий цепочку команд над роботом, заданным кодом (lffrflfrrfff) и генерирующий файл
с маршрутом робота.
*/

/**
 * The Class Robot
 */
public class Robot {
	
	/** The value for axis x */
	public int x = 0;
	
	/** The value for axis y */
	private int y = 0;
	
	/** The angle */
	private int angle = 0;
	
	/** The movements points. */
	ByteArrayOutputStream movementsPoints;

	/**
	 * Instantiates a new robot.
	 *
	 * @param movementsPoints the movements points
	 */
	public Robot(ByteArrayOutputStream movementsPoints) {
		this.movementsPoints = movementsPoints;
	}

	/**
	 * Turn left
	 */
	public void turnLeft() {
		if (this.angle == 360) {
			this.angle = 0;
		}
		angle = angle + 90;
	}

	/**
	 * Turn right
	 */
	public void turnRight() {
		if (this.angle == 0) {
			this.angle = 360;
		}
		angle = angle - 90;

	}

	/**
	 * Step forward
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void stepForward() throws IOException {
		switch (angle) {
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
		writeToStream();

	}

	/**
	 * Write to stream.
	 *
	 * @return the byte array output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ByteArrayOutputStream writeToStream() throws IOException {
		movementsPoints.reset();
		movementsPoints.write(("X: " + x + " and Y: " + y + " ||| ").getBytes());
		return movementsPoints;
	}

	/**
	 * Gets the current point x.
	 *
	 * @return the int
	 */
	public int GetCurrentPointX() {
		return x;
	}

	/**
	 * Gets the current point y.
	 *
	 * @return the int
	 */
	public int GetCurrentPointY() {
		return y;
	}

}
