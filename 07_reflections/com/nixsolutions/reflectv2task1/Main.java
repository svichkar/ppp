package com.nixsolutions.reflectv2task1;

import java.lang.reflect.Field;

/**
 * Implements task 1 of variant 2 in 07_reflections lab. Creates and initializes
 * the bean class, then, prints either its fields annotated as Public (using
 * different APIs), or exceptions for non-Public fields.
 */
public class Main {
    public static void main(String[] args) {
        Bean bean = new Bean();

        initializeBean(bean);

        System.out.println("Java SE API:\n");
        printPublicValues(new PublicValuesStandard(), bean);

        System.out.println();

        System.out.println("org.apache.commons.lang3.reflect:\n");
        printPublicValues(new PublicValuesThirdParty(), bean);
        
        System.out.println("Finished, exiting.");
    }

    private static void initializeBean(Bean bean) {
        bean.setGenus("Coffea");
        bean.setSpecies("Arabica");
        bean.setOrigin("Indonesia");
        bean.setLand("Java");
        bean.setCaffeinePercent(1.2);
        bean.setProteinsPercent(10.8);
        bean.setRoasted(true);
        bean.setDryProcessed(false);
    }

    private static void printPublicValues(PublicValues pv, Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            String name = field.getName();
            String value;

            try {
                value = pv.getPublicValue(object, name).toString();
                System.out.println(name + ": " + value);
            } catch (IllegalArgumentException | NoSuchFieldException
                    | SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
