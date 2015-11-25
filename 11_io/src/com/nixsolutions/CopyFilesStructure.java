package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by svichkar on 11/18/2015.
 */

public class CopyFilesStructure
{
    public static void main(String[] args)
    {
        createFileStructure(1,1);

        File srcFolder = new File("c:\\123");
        File destFolder = new File("c:\\321");

        //make sure source exists
        if(!srcFolder.exists()){

            System.out.println("Directory does not exist.");
            //just exit
            System.exit(0);

        }else{

            try{
                copyFolder(srcFolder,destFolder);
            }catch(IOException e){
                e.printStackTrace();
                //error, just exit
                System.exit(0);
            }
        }

        System.out.println("Done");
    }

    private static void createFileStructure(int depth, int count) {

        File root = new File("test");
        root.mkdir();

        File file = new File(root.getPath() + "/file");
        try {
            //file.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void copyFolder(File src, File dest)
            throws IOException{

        if(src.isDirectory()){

            //if directory not exists, create it
            if(!dest.exists()){
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile,destFile);
            }

        }else{
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }
}
