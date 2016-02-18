package com.nixsolutions.reflectv2task1;

/** Gets the value of object's field annotated as Public */
public interface PublicValues {

    /**
     * Based on specified name, gets the value of object's field annotated as
     * Public, otherwise throws an exception.
     * 
     * @param object
     *            Object to get the field value from.
     * @param fieldName
     *            Name of the field to get the value from.
     * @return Returns the value of a specified field.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public Object getPublicValue(Object object, String fieldName)
            throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException;
}
