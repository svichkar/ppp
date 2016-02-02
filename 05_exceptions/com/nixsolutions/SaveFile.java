import exception.Save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sobolenko on 2/2/2016.
 */
public class SaveFile implements Save {
    @Override
    public void save(String data, String fileName) {
        String pathName = System.getProperty("user.dir");
        File outputFile = new File(pathName + "\\Test.txt");
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(outputFile);
            if (!outputFile.exists()) {
                fwriter.write("");
            } else {
                fwriter.close();
                throw new CustomExeption();
            }
            fwriter.write(data);
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CustomExeption customExeption) {
            customExeption.printStackTrace();
        }
    }
}
