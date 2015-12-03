/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectionsworkshop;

import java.lang.reflect.Field;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
public class ReflectionsWorkshop {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnnotatedClass testClass = new AnnotatedClass();
        LOGGER.info("Task1 - native:");
        for (Field field : testClass.getClass().getDeclaredFields()) {
            try {
                System.out.println(UtilityClass.getPublicValue(testClass, field.getName()));
                // TODO code application logic here
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.info("Task1 - Google:");
        for (Field field : testClass.getClass().getDeclaredFields()) {
            try {
                System.out.println(UtilityClass.getPublicValueFieldUtils(testClass,
                        field.getName()));
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("Task2:");
        CustomPathClassLoader testLoader = new CustomPathClassLoader();
        testLoader.setPath("C:\\Users\\mednorcom\\Documents\\NetBeansProjects\\GIT\\javappp\\"
                + "07_reflections\\ReflectionsWorkshop\\customClassFolder");
        try {
            System.out.println(testLoader.loadClass("genericsworkshop.FloatToDouble")
                    .getName());
        } catch (ClassNotFoundException ex) {
            LOGGER.error(ex);
        }
        
    }

}
