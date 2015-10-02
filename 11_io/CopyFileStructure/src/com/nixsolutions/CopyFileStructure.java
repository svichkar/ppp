/**
 * 
 */
package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author mixeyes
 *
 */
public class CopyFileStructure {
    private static final int BUFFER_SIZE = 1024;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String folderPathSRC = System.getProperty("user.dir").replace("\\CopyFileStructure", "");
		String folderPathDST = "d:\\folderPathDST";
		copyDir(folderPathSRC, folderPathDST);
		// TODO Auto-generated method stub
	}
	
	private static boolean copyDir(final String src, final String dst) {
        System.out.println("Copy dir - " + src);
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isDirectory() && !dstFile.exists()) {
            dstFile.mkdir();
            File nextSrcFile;
            String nextSrcFilename, nextDstFilename;
            for (String filename : srcFile.list()) {
                nextSrcFilename = srcFile.getAbsolutePath()
                        + File.separator + filename;
                nextDstFilename = dstFile.getAbsolutePath()
                        + File.separator + filename;
                nextSrcFile = new File(nextSrcFilename);
                if (nextSrcFile.isDirectory()) {
                    copyDir(nextSrcFilename, nextDstFilename);
                } else {
                    copyFile(nextSrcFilename, nextDstFilename);
                }
            }
            return true;
        } else {
            return false;
        }
    }
 
    private static boolean copyFile(final String src, final String dst) {
        System.out.println("Copy file - " + src);
        final File srcFile = new File(src);
        final File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isFile() && !dstFile.exists()) {
            try (InputStream in = new FileInputStream(srcFile);
                    OutputStream out = new FileOutputStream(dstFile)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytes;
                while ((bytes = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytes);
                }
            } catch (FileNotFoundException ex) {
                 return false;
            } catch (IOException ex) {
                                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}


