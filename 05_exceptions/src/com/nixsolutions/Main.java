package com.nixsolutions;

/**
 * Main class that execute 'save' method from 'SaveTextToFile' class
 */
public class Main {
    /**
     * Main executable method
     * @param args
     */
    public static void main(String[] args) {

        String toWrite = "text for write to file";
        //String path = "E:\\JavaPPP\\savefile.txt";
        String path = "c:\\JavaPPP\\savefile.txt";
        System.out.println("Try to write text ");
        SaveTextToFile sttf = new SaveTextToFile();
        sttf.save(toWrite, path);
    }
}
