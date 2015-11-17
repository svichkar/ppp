/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 * A marker class
 *
 * @author mednorcom
 */
public class Marker extends AbstractWriter {

    /**
     * Is cape on marker or not. True if cape is on.
     */
    private boolean capeOn;

    /**
     * Constructs Marker with specified color
     *
     * @param color - text color
     */
    public Marker(String color) {
        super(color);
        this.capeOn = true;
        this.setResourceUsage(0.0085);
    }

    /**
     *  Returns status is cape on marker or not. True if cape is on.
     */
    public boolean isCapeOn() {
        // System.out.println("Pop!");
        return capeOn;
    }

    /**
     * Sets cape on or off
     * @param capeOn true - cape is on, false - cape is off
     */
    public void setCapeOn(boolean capeOn) {
        this.capeOn = capeOn;
    }

    
    @Override
    public void prepare() {
        if (this.isCapeOn()) {
            this.setCapeOn(false);
        }
        System.out.println("Marker is ready!");
    }

    @Override
    public StringBuilder write(StringBuilder inputText) {
        if (!this.isCapeOn()) {
            return super.write(inputText);
        } else {
            System.out.println("Marker cannot write with cape on");
            return new StringBuilder();
        }
    }
}
