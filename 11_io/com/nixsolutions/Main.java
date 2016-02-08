package com.nixsolutions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.Scanner;

public class Main extends SimpleFileVisitor<Path> {
    static String[] list;
    static int i;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out
                .println("Please enter full path to source directory to copy:");
        String s = scan.nextLine();
        System.out.println("Please enter full path to destination directory:");
        System.out.println(
                "WARNING: destination files are overwritten if conflicts occur!");
        String d = scan.nextLine();

        System.out.println(
                "\nCopying using java.io (\"_java.io\" appended to destination path)...");
        File srcFile = new File(s);
        File destFile = new File(d + "_java.io");
        JavaIoCopy.copy(srcFile, destFile);
        System.out.println("Completed.");

        System.out.println(
                "\nCopying using java.nio (\"_java.nio\" appended to destination path)...");
        //d += "java.nio";
        JavaNioCopy.copy(s, d + "_java.nio");
        System.out.println("Completed.");

        System.out.println(
                "\nCopying using org.apache.commons.io (\"_org.apache.commons.io\" appended to destination path)...");
        destFile = new File(d + "_org.apache.commons.io");
        ApacheCommonsIoCopy.copy(srcFile, destFile);
        System.out.println("Completed.");
    }

}
