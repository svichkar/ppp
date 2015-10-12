package com.nixsolutions.task1;

public class ReflectionTask1 {
    public static void main (String[] args) throws NoSuchFieldException, SecurityException, IllegalAccessException{
	Human person = new Human("John", "Smith");
	UtilsReflection.getValue(person, "firstName");
	UtilsReflection.getValue(person, "lastName");
    }
}
