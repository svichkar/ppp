package com.nixsolutions.reflectv2task1;

public interface PublicValues {
    public Object getPublicValue(Object object, String fieldName)
            throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException;
}
