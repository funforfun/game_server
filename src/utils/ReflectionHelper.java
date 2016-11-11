package utils;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static Object createInstance(String className)  {
        try {
            return Class.forName(className).newInstance();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, String value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                field.set(object, value);
            } else if (field.getType().equals(int.class)) {
                field.set(object, Integer.decode(value));
            }
            // TODO: else if другие типы...
            field.setAccessible(false);
        } catch (Exception ignored) {

        }
    }
}
