package com.nixsolutions;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Sirotkin Mikhail
 */
public class ReflectionTaskFirstWithUtils {

    /**
     * main() method where we try to print all values of object's fields
     * First (String) and second (int) have to be print out because they have annotation '@Public'
     * Third field have to throw exception because it doesn't have annotation
     * @param args
     */
    public static void main(String[] args) {
        MyTestClass testObj = new MyTestClass("test class 2", 10, false);
        Field[] fields = testObj.getClass().getDeclaredFields();
        for(Field field : fields){
            try {
                System.out.println("Field '" + field.getName() + "' have value '" + getFieldValueUsingBeanUtils(testObj, field.getName()).toString() + "'");
            }catch (NoSuchFieldException ex){
                System.out.println("Class of current object doesn't contain field " + field.getName());
            }catch (IllegalAccessException ex){
                System.out.println("Field '" + field.getName() + "' can't be called because of access exception.");
            }catch (NoSuchMethodException | InvocationTargetException ex){
                System.out.println("Field '" + field.getName() + "' can't be called because of next exception:" + ex.getMessage());
            }
        }
    }

    public static Object getFieldValueUsingBeanUtils(Object object, String fieldName) throws IllegalAccessException,
            NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field.isAnnotationPresent(Public.class)) {
            return BeanUtils.getProperty(object, fieldName);
        } else throw new IllegalAccessException("Field '" + fieldName + "' is not annotated as @Public.");
    }
}
