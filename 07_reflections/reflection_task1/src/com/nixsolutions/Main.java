package com.nixsolutions;

import java.lang.reflect.*;

/**
 * Main class for checking get field value methods of Utils class
 */
public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException {
        //Create instance of MyTestClass
        MyTestClass testObj = new MyTestClass("test class", 10, false);
        String noFieldName = "noFieldName";
        Field[] fields = testObj.getClass().getDeclaredFields();

        //First part: check getFieldValue method that don't use external libraries
        System.out.println("Check reflection method that use java.lang.reflect.* library:");
        //Try to get values of all class fields
        for(Field field : fields){
            try {
                System.out.println("Field '" + field.getName() + "' have value '" + Utils.getFieldValue(testObj, field.getName()).toString() + "'");
            }catch (NoSuchFieldException ex){
                System.out.println("Class of current object doesn't contain field " + field.getName());
            }catch (IllegalAccessException ex){
                System.out.println("Field '" + field.getName() + "' can't be called because of access exception.");
            }
        }

        //Try to get value using wrong field name that absent in MyTestClass (NoSuchFieldException is expected)
        try {
            System.out.println("Field '" + noFieldName + "' have value '" + Utils.getFieldValue(testObj, noFieldName) + "'");
        }catch (NoSuchFieldException ex){
            System.out.println("Class of current object doesn't contain field " + noFieldName);
        }catch (IllegalAccessException ex){
            System.out.println("Field '" + noFieldName + "' can't be called because of access exception.");
        }

        //Second part: check getFieldValue method that  uses external library (Apache Commons BeanUtils)
        System.out.println("\nCheck reflection method that use Apache Commons BeanUtils library:");
        //Try to get values of all class fields
        for(Field field : fields){
            try {
                System.out.println("Field '" + field.getName() + "' have value '"
                        + Utils.getFieldValuewWithBeanUtils(testObj, field.getName()).toString() + "'");
            }catch (NoSuchFieldException ex){
                System.out.println("Class of current object doesn't contain field " + field.getName());
            }catch (IllegalAccessException ex){
                System.out.println("Field '" + field.getName() + "' can't be called because of access exception.");
            }
        }

        //Try to get value using wrong field name that absent in MyTestClass (NoSuchFieldException is expected)
        try {
            System.out.println("Field '" + noFieldName + "' have value '"
                    + Utils.getFieldValuewWithBeanUtils(testObj, noFieldName) + "'");
        }catch (NoSuchFieldException ex){
            System.out.println("Class of current object doesn't contain field '" + noFieldName + "'");
        }catch (IllegalAccessException ex){
            System.out.println("Field '" + noFieldName + "' can't be called because of access exception.");
        }

    }
}
