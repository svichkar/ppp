package laba.io;

import java.io.IOException;
import java.nio.file.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Path path1 = Paths.get("D:\\all");
		Path path2 = Paths.get("D:\\app\\all");

		try {
			Files.walkFileTree(path1, new MyCopy(path1, path2));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
