package com.nixsolutions.robot.main;

import java.io.IOException;

public class RobotClass {
	private Integer coordX;
	public Integer getCoordX() {
		return coordX;
	}

	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}

	public Integer getCoordY() {
		return coordY;
	}

	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	private Integer coordY;
	private Course course;
	private RobotLogger robotMoves;

	/**
	 * @param coordX
	 * @param coordY
	 * @param course
	 */
	public RobotClass() {
		this.coordX = 0;
		this.coordY = 0;
		this.course = Course.RIGHT;
		robotMoves = new RobotLogger();
	}

	public RobotClass(RobotLogger logger) {
		this.coordX = 0;
		this.coordY = 0;
		this.course = Course.RIGHT;
		robotMoves = logger;

	}

	public void turnLeft() throws IOException {
		this.course = Course.getCourseByInt((this.course.getIntValue() + 1) % 4);
		robotMoves.writeToLog(this);
	}

	public void turnRight() throws IOException {
		this.course = Course.getCourseByInt((this.course.getIntValue() - 1) % 4);
		robotMoves.writeToLog(this);
	}

	public void stepForward() throws IOException {
		switch (course) {
		case UP:
			coordY += 1;
			break;
		case DOWN:
			coordY -= 1;
			break;
		case RIGHT:
			coordX += 1;
			break;
		case LEFT:
			coordX -= 1;
		default:
			break;
		}
		robotMoves.writeToLog(this);
	}

}
