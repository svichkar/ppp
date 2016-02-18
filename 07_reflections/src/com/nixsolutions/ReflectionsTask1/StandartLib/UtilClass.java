package com.nixsolutions.ReflectionsTask1.StandartLib;

import com.nixsolutions.ReflectionsTask1.Public;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.withAnnotation;

/**
 * Created by sobolenko on 2/15/2016.
 */
public class UtilClass {

    public Object getField(Object obj, String fieldName) throws IllegalAccessException {
        List<Field> fields = Arrays.stream(obj.getClass().getDeclaredFields()).filter(an -> an.getAnnotation(Public.class) != null).collect(Collectors.toList());
        Object result = null;
        for (Field fl : fields) {
            fl.setAccessible(true);
            if (fl.getName().equals(fieldName)) {
                result = fl.get(obj);
                return result;
            }
        }
        throw new IllegalAccessException();
    }
}