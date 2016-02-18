package com.nixsolutions.reflectv2task1;

import java.lang.reflect.Field;

public class PublicValuesStandard implements PublicValues {

    @Override
    public Object getPublicValue(Object object, String fieldName)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Object value = null;
        Field field = null;

        field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        if (field.getAnnotation(Public.class) != null) {
            value = field.get(object);
        } else {
            throw new IllegalAccessException();
        }

        field.setAccessible(false);
        return value;
    }

}
