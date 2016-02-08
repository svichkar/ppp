package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JavaIoCopy {
    public static void copy(File src, File dest) {
        try {
            if (src.isDirectory()) {
                if (!dest.exists()) {
                    dest.mkdir();
                }

                String[] sa = src.list();

                for (String s : sa) {
                    File newSrc = new File(src, s);
                    File newDest = new File(dest, s);
                    copy(newSrc, newDest);
                }
            } else {
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);

                byte[] b = new byte[4096];
                int len;

                while ((len = in.read(b)) > -1) {
                    out.write(b, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
