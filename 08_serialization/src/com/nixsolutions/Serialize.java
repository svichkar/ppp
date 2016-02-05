package com.nixsolutions;

import java.io.*;

/**
 * Created by Lexx on 21.11.2015.
 */
public class Serialize {
    /**
     * @param obj
     */
    public void serializing(Account obj) {
        try {
            FileOutputStream foStream = new FileOutputStream(obj.getName() + ".dat");
            ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
            ooStream.writeObject(obj);
            foStream.flush();
            foStream.close();
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    public Account deserializing(Account obj) {
        try {
            FileInputStream fiStream = new FileInputStream(obj.getName() + ".dat");
            ObjectInputStream oiStream = new ObjectInputStream(fiStream);
            obj = (Account) oiStream.readObject();
            fiStream.close();
            return obj;
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
