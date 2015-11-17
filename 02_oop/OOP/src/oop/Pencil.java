/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 * A pencil class
 *
 * @author mednorcom
 */
public class Pencil extends AbstractErasableWriter {

    /**
     * Sharpen status. if Sharpen status is true pencil is able to write
     */
    private boolean sharpen;

    /**
     * Constructs Pencil with specified color
     *
     * @param color - text color
     */
    public Pencil(String color) {
        super(color);
        this.sharpen = true;
        this.setResourceUsage(0.009);
    }

    /**
     * Returns sharpen status
     * @return sharpen status, if true pencil is harp and able to write
     */
    public boolean isSharpen() {
        return sharpen;
    }

    /**
     * Sets sharpen status. if Sharpen status is true pencil is able to write
     * @param sharpen if true pencil is able to write
     */
    public void setSharpen(boolean sharpen) {
        this.sharpen = sharpen;
    }

    @Override
    public void prepare() {
        if (!this.isSharpen()) {
            this.setSharpen(sharpen);
        }
        System.out.println("Pencil is ready!");
    }

    @Override
    public StringBuilder write(StringBuilder inputText) {
        if (this.isSharpen()) {
            return super.write(inputText);

        } else {
            System.out.println("Pencil cannot write without sharpening");
            return new StringBuilder();
        }
    }

}
