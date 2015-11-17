/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 * A pen class
 *
 * @author mednorcom
 */
public class Pen extends AbstractWriter {

    /**
     * is pen clicked on. True if pen is clicked and able to write
     */
    private boolean clicked;

    /**
     * Constructs Pen with specified color
     *
     * @param color - text color
     */
    public Pen(String color) {
        super(color);
        clicked = false;
        this.setResourceUsage(0.008);
    }

    /**
     * Returns pen clicked status
     *
     * @return if the pen clicked and able to write - true
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * sets clicked status
     *
     * @param isClicked if pen is clicked and able to write then true, else - false
     */
    private void setClicked(boolean isClicked) {
        this.clicked = isClicked;
    }

    @Override
    public void prepare() {
        if (!this.isClicked()) {
            this.click();
        }
        System.out.println("Pen is ready!");

    }

    @Override
    public StringBuilder write(StringBuilder inputText) {
        if (this.isClicked()) {
            return super.write(inputText);
        } else {
            System.out.println("Pen cannot write without clicking");
            return new StringBuilder();
        }
    }

    /**
     *Toggle pen on/off with clicking it
     */
    public void click() {
        if (this.isClicked()) {
            this.setClicked(false);
        } else {
            this.setClicked(true);
        }

    }
}
