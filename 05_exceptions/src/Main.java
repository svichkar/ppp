/**
 * Created by konstantin on 16.11.2015.
 */
public class Main {

    public static void main (String args[]) throws Exception{

        SaveFile saveFile = new SaveFile();

        saveFile.save("Write this text into file which doesn't exist.", "d:/directory/doesNotExistFile.txt");
        saveFile.save("Overwrite existing file with current text.", "d:/directory/doesNotExistFile.txt");
        saveFile.save("Path is not absolute!!! Exception should be thrown.", "/directory/doesNotExistFile.txt");
    }
}