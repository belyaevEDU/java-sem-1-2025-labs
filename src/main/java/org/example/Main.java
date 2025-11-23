package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        AnnotatedClass annotatedObject = new AnnotatedClass();

        Class<AnnotatedClass> annotatedClass = AnnotatedClass.class;
        for (Method method : annotatedClass.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())) {
                continue;
            }
            if (method.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation annotation = method.getAnnotation(CustomAnnotation.class);
                for (int i = 0; i < annotation.parameter(); i++) {
                    try {
                        method.setAccessible(true);
                        method.invoke(annotatedObject);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}