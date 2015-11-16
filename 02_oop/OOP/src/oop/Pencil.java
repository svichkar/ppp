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
public class Pencil extends AbstractWriter {

    private boolean sharpen;

    public Pencil(String color) {
        super(color);
        this.sharpen = true;
        this.setResourceUsage(0.009);
    }

    public boolean isSharpen() {
        return sharpen;
    }

    public void setSharpen(boolean sharpen) {
        this.sharpen = sharpen;
    }

    @Override
    public void prepare() {
        if (!this.isSharpen()) {
            this.setSharpen(sharpen);
        }
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

    public StringBuilder erase(StringBuilder inputText) {
        char[] text = null;
        if (inputText.length() > 0) {
            inputText.getChars(0, 2 - inputText.length(), text, 0);
            return new StringBuilder(text.toString());

        } else {
            System.out.println("Nothing to erase");
            return new StringBuilder(inputText);
        }
    }
}
