package com.nixsolutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ApacheCommonsIoCopy {
    public static void copy(File src, File dest) {
        try {
            FileUtils.copyDirectory(src, dest, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
