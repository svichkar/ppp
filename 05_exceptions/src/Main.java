/**
 * Created by konstantin on 16.11.2015.
 */
public class Main {

    public static void main(String args[]) {

        SaveFile saveFile = new SaveFile();

        try {
            saveFile.save("Path is not absolute!!! Exception should be thrown.", "/directory/doesNotExistFile.txt");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            saveFile.save("Write this text into file which doesn't exist.", "d:/directory/doesNotExistFile.txt");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        try {
            saveFile.save("Overwrite existing file with current text.", "d:/directory/doesNotExistFile.txt");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }
}