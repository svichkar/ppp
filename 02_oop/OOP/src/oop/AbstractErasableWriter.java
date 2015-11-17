/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 * Base writing tool with possibility to erase
 *
 * @author Mednor
 */
public abstract class AbstractErasableWriter extends AbstractWriter {

    /**
     * Constructs Abstract Erasable writer with specified color
     *
     * @param color - text color
     */
    public AbstractErasableWriter(String color) {
        super(color);
    }

    /**
     * Erase last symbol from given text
     * @param inputText - input text
     * @return returns new text string without last symbol
     */
    public StringBuilder erase(StringBuilder inputText) {
        char[] text = new char[inputText.length()];
        if (inputText.length() > 0) {
            inputText.getChars(0, inputText.length() - 1, text, 0);
            return new StringBuilder(new String(text));

        } else {
            System.out.println("Nothing to erase");
            return new StringBuilder(new String(text));
        }
    }

}
