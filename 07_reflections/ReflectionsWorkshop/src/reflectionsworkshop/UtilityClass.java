/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectionsworkshop;

import java.lang.reflect.Field;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 *
 * @author mednorcom
 */
public class UtilityClass {

    public static Object getPublicValue(Object targetObject, String fieldName)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = targetObject.getClass().getDeclaredField(fieldName);
        if (field.isAnnotationPresent(Public.class)) {
            field.setAccessible(true);
            return field.get(targetObject);
        }
        throw new IllegalAccessException("Field does not have @Public annotation");
    }

    public static Object getPublicValueFieldUtils(Object targetObject, String fieldName)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Field field = FieldUtils.getDeclaredField(targetObject.getClass(), fieldName, true);
        if (field.isAnnotationPresent(Public.class)) {
            return FieldUtils.readField(field, targetObject, true);
        }
        throw new IllegalAccessException("Field does not have @Public annotation");
    }
}
