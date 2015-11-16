/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author mednorcom
 */
public class Pen extends AbstractWriter {

    private boolean clicked;

    public Pen(String color) {
        super(color);
        clicked = false;
        this.setResourceUsage(0.008);
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean isClicked) {
        this.clicked = isClicked;
    }

    @Override
    public void prepare() {
        if (!this.isClicked()) {
            this.click();
        }
        
    }

    @Override
    public StringBuilder write(StringBuilder inputText) {
        if (this.isClicked())
        {
            return super.write(inputText);
        }
        else
        {
            System.out.println("Pen cannot write without clicking");
            return new StringBuilder();
        }
    }

    public void click() {
        if (this.isClicked()) {
            this.setClicked(false);
        } else {
            this.setClicked(true);
        }
        System.out.print("Click!");

    }
}
