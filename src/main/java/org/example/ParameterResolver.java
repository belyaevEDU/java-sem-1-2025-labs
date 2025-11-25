package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ParameterResolver {
    public static Object[] resolveParameters(Class<?>[] parameterClasses) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Object> objects = new ArrayList<>();
        for (Class<?> clazz : parameterClasses) {
            objects.add(resolveInstance(clazz));
        }
        return objects.toArray(new Object[0]);
    }

    private static Object resolveInstance(Class<?> clazz) throws NoSuchMethodException {
        if (clazz.isPrimitive()) {
            return getDefaultPrimitiveValue(clazz);
        } else if (clazz == String.class) {
            return "";
        }

        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            try {
                constructor.setAccessible(true);
                Object[] constructorArgs = resolveParameters(constructor.getParameterTypes());
                return constructor.newInstance(constructorArgs);
            } catch (Exception e) {
            }
        }
        throw new NoSuchMethodException("ERROR: Couldn't find resolvable constructor. Somehow");
    }

    private static Object getDefaultPrimitiveValue(Class<?> type) {
        if (type == int.class) return 0;
        if (type == boolean.class) return false;
        if (type == long.class) return 0L;
        if (type == double.class) return 0.0;
        if (type == float.class) return 0.0f;
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;
        if (type == char.class) return '\u0000';
        return null;
    }
}
