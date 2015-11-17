/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 * Base writing tool
 *
 * @author mednorcom
 */
public abstract class AbstractWriter {

    /**
     * remaining resource value
     */
    private double remainingResource;
    /**
     * Color of the ink/graphite
     */
    private String color;
    /**
     * usage of the resource per sign
     */
    private double resourceUsage;

    /**
     * Constructs Abstract writer with specified color
     *
     * @param color - text color
     */
    public AbstractWriter(String color) {
        this.remainingResource = 1;
        this.color = color;
    }

    /**
     * Returns the remaining resource
     *
     * @return - the resource remaining at the moment as decimal from 0 to 1
     */
    public Double getRemainingResource() {
        return (Double) remainingResource;
    }

    /**
     * Returns the remaining resource in percentage view
     *
     * @return - the remaining resource in percentage view as decimal from 0 to 100
     */
    public Double getRemainingResourcePercentage() {
        return ((Double) (remainingResource * 100));
    }

    /**
     * Sets remaining resource
     *
     * @param remainingResource - decimal value from 0 to 1. Values above 1 will be rounded to 1
     * values below 0 will be rounded to 0
     */
    protected void setRemainingResource(double remainingResource) {
        if (remainingResource > 1) {
            this.remainingResource = 1;
        } else if (remainingResource < 0) {
            this.remainingResource = 0;
        } else {
            this.remainingResource = remainingResource;
        }
    }

    /**
     * Returns color of the ink/graphite
     *
     * @return - color value as text
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns resource usage
     *
     * @return - resource usage as decimal
     */
    protected final double getResourceUsage() {
        return resourceUsage;
    }

    /**
     * Sets resource usage
     *
     * @param resourceUsage resource usage as decimal; Note: values above 1 will make your tool
     * unable to write even a single sign.
     */
    protected final void setResourceUsage(double resourceUsage) {
        this.resourceUsage = resourceUsage;
    }

    /**
     * Writes given text to the StringBuilder
     *
     * @param inputText - input text
     * @return - returns a string builder instance which contains given text
     */
    public StringBuilder write(StringBuilder inputText) {
        char[] text = new char[inputText.length()];
        StringBuilder output = new StringBuilder("|" + this.getClass().getSimpleName() + ":" + this.getColor() + "::");
        inputText.getChars(0, inputText.length(), text, 0);
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

    /**
     * Prepare your tool to writing
     */
    public abstract void prepare();

}
