package com.nixsolutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
/**
 * Created by pantiukhin on 2/8/2016. Using the org.apache.commons.io library
 */
public class ApacheCopy {

    public static void main(String[] args) {
        String sourceDirectoryName = "Foder_Structure";
        File sourceDirectory = new File(sourceDirectoryName);
        String destDirectoryName = "Foder_Structure_copy";
        File destDirectory = new File(destDirectoryName);
        try {
            FileUtils.copyDirectory(sourceDirectory, destDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
