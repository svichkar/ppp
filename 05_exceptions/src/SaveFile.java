import exception.Save;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by konstantin on 16.11.2015.
 */
public class SaveFile implements Save {
    @Override
    public void save(String text, String filePath) {

        Path path = Paths.get(filePath);

        try {
            if (path.isAbsolute() == false) {
                throw new PathNotAbsoluteException("Specified path is not absolute");
            }

            Files.createFile(path);
            Files.write(path, text.getBytes());
        } catch (NoSuchFileException e) {

            System.out.println("The specified file doesn't exist. It will be created.");

            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                Files.write(path, text.getBytes());
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (FileAlreadyExistsException ex) {

            System.out.println("Specified file already exists. It will be overwritten");
            try {

                Files.write(path, text.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (PathNotAbsoluteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
