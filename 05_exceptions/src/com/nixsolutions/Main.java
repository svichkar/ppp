package com.nixsolutions;

/**
 * Main class that execute 'save' method from 'SaveTextToFile' class
 * @autor Sirotkin Mikhail
 */
public class Main {
    /**
     * Main executable method
     * We throw exception 'up' if it happens
     * We use some static path. If
     * @param args
     * @throws
     */
    public static void main(String[] args) throws Exception{

        String toWrite = "text for write to file";
        String path1 = "E:\\JavaPPP\\savefile.txt";
        System.out.println("Try to write text ");
        SaveTextToFile sttf = new SaveTextToFile();
        sttf.save(toWrite, path1);

        //next row throws Custom exception with text (Access is Denied) if start on Win8
        sttf.save(toWrite, "c:");

        //next row throw exception with message 'Path input object is null!'
        sttf.save(toWrite, null);
    }
}
