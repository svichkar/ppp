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
public class Marker extends AbstractWriter {

    private boolean capeOn;

    public Marker(String color) {
        super(color);
        this.capeOn = true;
        this.setResourceUsage(0.0085);
    }

    public boolean isCapeOn() {
        System.out.println("Pop!");
        return capeOn;
    }

    public void setCapeOn(boolean capeOn) {
        this.capeOn = capeOn;
    }

    @Override
    public void prepare() {
        if (this.isCapeOn()) {
            this.setCapeOn(false);
        }
    }

    @Override
    public StringBuilder write(StringBuilder inputText) {
        if (this.isCapeOn()) {
            return super.write(inputText);
        } else {
            System.out.println("Marker cannot write with cape on");
            return new StringBuilder();
        }
    }
}
