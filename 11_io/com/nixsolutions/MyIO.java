package com.nixsolutions;

import java.io.*;

/**
 * Created by kozlovskij on 11/18/2015.
 */
public class MyIO {
    public static void MyCopy(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            File[] files = source.listFiles();
            for (File f : files) {
                MyCopy(f, new File(dest, f.getName()));
            }
        } else {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static void main(String[] args) {
        String inputDir = "D:\\test";
        String outputDir = "D:\\atest";
        File in = new File(inputDir);
        File out = new File(outputDir);

        try {
            MyCopy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
