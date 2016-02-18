package com.fieldvalues;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.*;


/**
 * Created by pantiukhin on 2/17/2016.
 */
public class UtilityClass {
    public Object getPublicValue(Object inputObject, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object fieldValue = null;
        Field obtainedField = null;
        obtainedField = inputObject.getClass().getDeclaredField(fieldName);
        if (obtainedField.isAnnotationPresent(Public.class)) {
            setFieldPublic(obtainedField);
            fieldValue = obtainedField.get(inputObject);
        } else {
            throw new IllegalAccessException();
        }
        return fieldValue;
    }

    public Object getPublicValueBeanUtils(Object inputObject, String fieldName) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object fieldValue = null;
        Field obtainedField = null;
        obtainedField = inputObject.getClass().getDeclaredField(fieldName);
        if (obtainedField.isAnnotationPresent(Public.class)) {
            setFieldPublic(obtainedField);
            fieldValue = PropertyUtils.getSimpleProperty(inputObject, fieldName);
        } else {
            throw new IllegalAccessException();
        }
        return fieldValue;
    }

    public static void main(String[] args) {
        UtilityClass utilityClass = new UtilityClass();
        ClassWithFields classWithFields = new ClassWithFields();
        String[] fields = {"longField", "stringField", "intField", "doubleField"};
        classWithFields.setIntField(2);
        classWithFields.setLongField(1L);
        classWithFields.setStringField("Nice");
        classWithFields.setDoubleField(1.2);
        for (String fieldName : fields) {
            try {
                System.out.println("Reflexion - " + fieldName + ": " + utilityClass.getPublicValue(classWithFields, fieldName));
                System.out.println("BeanUtils - " + fieldName + ": " + utilityClass.getPublicValueBeanUtils(classWithFields, fieldName));
                System.out.println();
            } catch (NoSuchFieldException e) {
                System.out.println(fieldName + ": No such Field");
                System.out.println();
            } catch (IllegalAccessException e) {
                System.out.println(fieldName + ": Illegal Access Exception");
                System.out.println();
            } catch (NoSuchMethodException e) {
                System.out.println(fieldName + ": No Such Method Exception");
                System.out.println();
            } catch (InvocationTargetException e) {
                System.out.println(fieldName + ": Invocation Target Exception");
                System.out.println();
            }
        }
    }

    public void setFieldPublic(Field obtainedField) {
        if (!obtainedField.isAccessible()) {
            obtainedField.setAccessible(true);
        }
    }
}

