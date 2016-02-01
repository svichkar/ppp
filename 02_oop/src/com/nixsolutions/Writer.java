package com.nixsolutions;
/**
 * Created  by pantiukhin on 2/1/2016
 */

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Writer {
    private WritingInstrument[] instruments = new WritingInstrument[10];
    private Random random = new Random();

    public static void main(String[] args) {
        Writer writer = new Writer();
        writer.fillInstrumentsArray();
        writer.writeWithInstruments();
        writer.displayRating();
    }

    /**
     * Fills the instruments array with WritingInstruments objects (Pen, Pencil, and Marker) at
     * random
     */
    public void fillInstrumentsArray() {
        for (int nextIndex = 0; nextIndex < this.instruments.length; nextIndex++) {
            int num = random.nextInt(3) + 1;
            switch (num) {
                case 1:
                    this.instruments[nextIndex] = new Pen();
                    break;
                case 2:
                    this.instruments[nextIndex] = new Pencil();
                    break;
                case 3:
                    this.instruments[nextIndex] = new Marker();
            }
        }
    }

    /**
     * Initiates the process of writing and calculating the remaining writing material for each
     * element of the instruments array
     */
    public void writeWithInstruments() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < instruments.length; i++) {
            sb.setLength(0);
            for (int y = 0; y < 10; ++y) {
                instruments[i].write(sb);
            }
        }
    }

    /**
     * Calculates the rating of the writing instruments based on the amount of the remaining writing material
     */
    public void displayRating() {
        float remainderPen = 0f;
        float remainderPencil = 0f;
        float remainderMarker = 0f;

        for (int i = 0; i < instruments.length; i++) {
            if (instruments[i].getClass().getName().equals("com.nixsolutions.Pen")) {
                remainderPen = instruments[i].getPercentage();
            } else if (instruments[i].getClass().getName().equals("com.nixsolutions.Pencil")) {
                remainderPencil = instruments[i].getPercentage();
            } else {
                remainderMarker = this.instruments[i].getPercentage();
            }
        }

        Map<Float, String> remainders = new TreeMap<Float, String>();
        remainders.put(remainderMarker, "Marker:");
        remainders.put(remainderPen, "Pen:");
        remainders.put(remainderPencil, "Pencil:");
        for (Map.Entry<Float, String> entry : remainders.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey() + "% left");
        }
    }
}
