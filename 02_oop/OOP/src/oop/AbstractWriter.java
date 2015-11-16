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
public abstract class AbstractWriter {

    private double remainingResource;
    private String color;
    private double resourceUsage;

    public AbstractWriter(String color) {
        this.remainingResource = 1;
        this.color = color;
    }

    public double getRemainingResource() {
        return remainingResource;
    }

    protected void setRemainingResource(double remainingResource) {
        if (remainingResource > 1) {
            this.remainingResource = 1;
        } else if (remainingResource < 0) {
            this.remainingResource = 0;
        } else {
            this.remainingResource = remainingResource;
        }
    }

    public String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
    }

    protected final double getResourceUsage() {
        return resourceUsage;
    }

    protected final void setResourceUsage(double resourceUsage) {
        this.resourceUsage = resourceUsage;
    }

    public StringBuilder write(StringBuilder inputText) {
        char[] text = null;
        StringBuilder output = new StringBuilder();
        inputText.getChars(0, inputText.length() - 1, text, 0);
        for (char symbol : text) {
            this.setRemainingResource(this.getRemainingResource() - this.getResourceUsage());
            if (this.getRemainingResource() > 0) {
                output.append(symbol);
            } else {
                System.out.println("Cannot write, it's empty");
                return output;
            }
        }
        return output;
    }

    public abstract void prepare();

}
