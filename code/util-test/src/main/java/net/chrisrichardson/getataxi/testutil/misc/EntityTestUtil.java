package net.chrisrichardson.getataxi.testutil.misc;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class EntityTestUtil {
    public static void setId(Object entity, String entityId) {
        try {
            Field idField = ReflectionUtils.findField(entity.getClass(), "id");
            idField.setAccessible(true);
            idField.set(entity, entityId);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
