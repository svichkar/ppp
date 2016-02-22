package com.nixsolutions;

import java.io.File;
import java.io.IOException;

/**
 * Created by sobolenko on 2/4/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileCopy fileCopy = new FileCopy();
        fileCopy.createDirectories();
        //fileCopy.makeCopyWithJavaIo("D:\\Labs", "D:\\Labs1");
        fileCopy.makeCopyWithJavaNio(System.getProperty("user.dir") + File.separator + "testDir", System.getProperty("user.dir") + File.separator + "CopyByNio");
        //fileCopy.makeCopyWithApacheCommonsIo("testDir", "CopyByApache");
    }
}
