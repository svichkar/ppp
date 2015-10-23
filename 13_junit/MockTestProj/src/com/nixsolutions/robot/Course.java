package com.nixsolutions.robot;

public enum Course {
	UP(1), LEFT(2), DOWN(3), RIGHT(0);
	
	private int intCoord;

	private void setIntValue(int value) {
		intCoord = value;
	}

	public int getIntValue() {
		return intCoord;
	}

	private Course(int coordVal) {
		setIntValue(coordVal);
	}

	public static Course getCourseByInt(int value) {
		switch (value) {
			case 0:
				return Course.RIGHT;
			case 1:
			case -3:
				return Course.UP;
			case 2:
			case -2:
				return Course.LEFT;
			case 3:
			case -1:
				return Course.DOWN;
			default:
				return null;
		}
	}

}
