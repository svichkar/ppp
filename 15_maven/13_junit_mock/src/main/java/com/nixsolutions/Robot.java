package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.IOException;

public class Robot {
	private int angle;
	private int x;
	private int y;
	private BufferedWriter writer = null;

	public Robot(BufferedWriter bw) {
		writer = bw;
		angle = 0;
		x = 0;
		y = 0;
	}

	public void turnLeft() {
		angle = angle + 90;
		if (angle >= 360)
			angle = angle % 360;
	}

	public void turnRight() {
		angle = angle - 90;
		if (Math.abs(angle) > 360)
			angle = 360 - Math.abs(angle) % 360;
		if (angle < 0)
			angle = 360 + angle;
	}

	public void stepForward() throws IOException {
		switch (angle) {
		case 0:
			x = x + 1;
			break;
		case 270:
			y = y - 1;
			break;
		case 180:
			x = x - 1;
			break;
		case 90:
			y = y + 1;
			break;
		}

		writer.write("angle=" + angle + " x=" + x + " y=" + y + "\n");
		writer.flush();
	}

}
