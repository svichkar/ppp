package laba.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class MyCopy extends SimpleFileVisitor<Path> {

	public Path source, destination;

	public MyCopy(Path s, Path d) {
		source = s;
		destination = d;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

		Path newPath = destination.resolve(source.relativize(file));
		try {
			Files.copy(file, newPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes fileAttributes) {

		Path newPath = destination.resolve(source.relativize(dir));
		try {
			Files.copy(dir, newPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println(e.getMessage()+ " Folder was replaced");
		}
		
		return FileVisitResult.CONTINUE;
	}
	
	 @Override
	    public FileVisitResult visitFileFailed(Path file, IOException exc)
	            throws IOException {
	        System.err.println(exc.getMessage() + " is not a valid Directory");
	        return FileVisitResult.CONTINUE;
	    }

}
