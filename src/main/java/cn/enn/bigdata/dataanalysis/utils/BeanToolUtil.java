package cn.enn.bigdata.dataanalysis.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

@Component
public class BeanToolUtil {
    public static void copyProperties(Object source, Object target) throws IllegalAccessException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        if (targetFields.length > 0) {
            for (int i = 0; i < targetFields.length; i++) {
                Field field = targetFields[i];
                Field sourceField = findSameField(sourceFields, field);
                if (sourceField != null) {
                    System.out.println(field.getName());
                    field.setAccessible(true);
                    sourceField.setAccessible(true);
                    field.set(target, sourceField.get(source));
                }
            }
        }


    }

    private static Field findSameField(Field[] sourceFields, Field field) {
        for (int i = 0; i < sourceFields.length; i++) {
            if (field.getName().equals(sourceFields[i].getName())) {
                return sourceFields[i];
            }
        }
        return null;
    }
}
