package main.java.com.nixsolutions;

import java.io.*;

/**
 * Created by Lexx on 21.11.2015.
 */
public class Serialize {
    /**
     * @param obj
     */
    public void serializing(Account obj) throws IOException {
        FileOutputStream foStream = new FileOutputStream(obj.getName() + ".dat");
        ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
        ooStream.writeObject(obj);
        foStream.flush();
        foStream.close();
    }

    /**
     * @param obj
     * @return
     */
    public Account deserializing(Account obj) throws IOException, ClassNotFoundException {
        FileInputStream fiStream = new FileInputStream(obj.getName() + ".dat");
        ObjectInputStream oiStream = new ObjectInputStream(fiStream);
        obj = (Account) oiStream.readObject();
        fiStream.close();
        oiStream.close();
        return obj;
    }
}
