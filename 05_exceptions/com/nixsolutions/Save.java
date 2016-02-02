import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by sobolenko on 2/2/2016.
 */
public class Save {
    public static void main(String[] args) {
        /*ClassLoader classLoader = Save.class.getClassLoader();
        try {
            classLoader.loadClass("learn-1.0-SNAPSHOT.jar");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        File file = new File("learn-1.0-SNAPSHOT.jar");
        URLClassLoader classLoader = null;
        try {
            classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, Save.class.getClassLoader());
            Class classToLoad = Class.forName("Save", true, classLoader);
            Method method = classToLoad.getDeclaredMethod("Save");
            Object instance = classToLoad.newInstance();
            Object result = method.invoke(instance);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
