package com.nixsolutions;

import java.lang.reflect.*;

/**
 * @author Sirotkin Mikhail
 */
public class ReflectionTaskFirstCustom {

    /**
     * main() method where we try to print all values of object's fields
     * First (String) and second (int) have to be print out because they have annotation '@Public'
     * Third field have to throw exception because it doesn't have annotation
     * @param args
     */
    public static void main(String[] args) {
        MyTestClass testObj = new MyTestClass("test class", 10, false);
        Field[] fields = testObj.getClass().getDeclaredFields();
        for(Field field : fields){
            try {
                System.out.println("Field '" + field.getName() + "' have value '" + getFieldValue(testObj, field.getName()).toString() + "'");
            }catch (NoSuchFieldException ex){
                System.out.println("Class of current object doesn't contain field " + field.getName());
            }catch (IllegalAccessException ex){
                System.out.println("Field '" + field.getName() + "' can't be called because of access exception.");
            }
        }
    }

    /**
     * Static method that return field value of object
     * Implementing without using standart libraries
     * @param object
     * @param fieldName
     * @return object that contains in requested field
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Class cl = object.getClass();
        try {
            Field field = cl.getDeclaredField(fieldName);
            if (field.isAnnotationPresent(Public.class)) {
                field.setAccessible(true);
                return field.get(object);
            } else {
                throw new IllegalAccessException("Current class hasn't annotation '@Public'!");
            }
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw ex;
        }
    }
}
