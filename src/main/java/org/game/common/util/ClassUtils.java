package org.game.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class ClassUtils {
    private ClassUtils() {
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getGenericOfSuperclass(Class<?> clazz, int argsIndex) {
        ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
        return (Class<?>) pt.getActualTypeArguments()[argsIndex];
    }

    public static Class<?> getGenericOfInterface(Class<?> clazz, int interfaceIndex, int argsIndex) {
        ParameterizedType pt = (ParameterizedType) clazz.getGenericInterfaces()[interfaceIndex];
        return (Class<?>) pt.getActualTypeArguments()[argsIndex];
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invoke(Method method, Object obj, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
