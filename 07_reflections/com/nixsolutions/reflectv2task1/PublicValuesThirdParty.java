package com.nixsolutions.reflectv2task1;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

public class PublicValuesThirdParty implements PublicValues {
    private List<Field> list;
    private boolean listPopulatedOnce;

    @Override
    public Object getPublicValue(Object object, String fieldName) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> objClass = object.getClass();

        if (!listPopulatedOnce) {
            list = FieldUtils.getFieldsListWithAnnotation(objClass, Public.class);
            listPopulatedOnce = true;
        }

        Field field = FieldUtils.getDeclaredField(objClass, fieldName, true);
        Object value = null;

        if (list.contains(field)) {
            value = FieldUtils.readDeclaredField(object, fieldName, true);
        } else {
            throw new IllegalAccessException();
        }

        return value;
    }

}