package com.example.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射的执行类
 */
public class ReflexUtil {

    public static Class getClass(String className) throws ClassNotFoundException {
        Class aClass;
        aClass = Class.forName(className);
        return aClass;
    }

    public static Class getClass(Object className) {
        Class aClass = className.getClass();
        return aClass;
    }

    /**
     * 获取实例
     *
     * @param className
     * @return
     */
    public static Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object obj;

        obj = getClass(className).newInstance();

        return obj;
    }


    /**
     * 获取实例
     *
     * @param object
     * @return
     */
    public Object createObject(Object object) {
        Object obj = null;
        try {
            obj = getClass(object).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 获取实例
     *
     * @param aClass
     * @return
     */
    public static Object createObject(Class aClass) throws InstantiationException, IllegalAccessException {
        Object obj;

        obj = aClass.newInstance();

        return obj;
    }

    /**
     * 执行普通函数
     *
     * @param className
     * @param methodName
     * @param vales
     * @param parameterTypes
     */
    public static Object invokeMethod(String className, String methodName, Object[] vales, Class<?>... parameterTypes) throws InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object object;

        Method method = getClass(className).getDeclaredMethod(methodName, parameterTypes);
        Object instance = createObject(className);
        method.setAccessible(true);
        /**
         * 执行对应的函数a!
         */
        object = method.invoke(instance, vales);

        return object;
    }

    /**
     * 执行静态普通函数
     *
     * @param className
     * @param methodName
     * @param vales
     * @param parameterTypes
     */
    public static Object invokeStaticMethod(String className, String methodName, Object[] vales, Class[] parameterTypes) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object obj = null;

        Class aClass = getClass(className);
        Method method = aClass.getDeclaredMethod(methodName, parameterTypes);
        Object instance = createObject(aClass);
        method.setAccessible(true);
        /**
         * 执行对应的函数
         */
        obj = method.invoke(instance, vales);

        return obj;
    }

    /**
     * 获取字段
     *
     * @param className
     * @param fieldName
     * @return
     */
    public static Field getField(String className, String fieldName) throws ClassNotFoundException, NoSuchFieldException {
        Field field;
        Class aClass = getClass(className);

        field = aClass.getField(fieldName);

        return field;
    }

    /**
     * 获取字段的值
     *
     * @param className
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(String className, String fieldName) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException {
        Field field;

        Class aClass = getClass(className);

        Object object = createObject(aClass);

        field = aClass.getField(fieldName);

        return field.get(object);
    }

    /**
     * 获取字段的值
     *
     * @param className
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(String className, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Class aClass = getClass(className);
        Field field = aClass.getField(fieldName);
        return field.get(object);
    }

    /**
     * 给字段赋值
     *
     * @param className
     * @param fieldName
     * @param value
     */
    public static void setField(String className, String fieldName, String value) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException {
        Class aClass = getClass(className);
        Object object = createObject(className);
        Field field = aClass.getField(fieldName);
        field.set(object, value);
    }

    /**
     * 给字段赋值
     *
     * @param className
     * @param fieldName
     * @param value
     */
    public static void setField(String className, Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Class aClass = getClass(className);

        Field field = aClass.getField(fieldName);
        field.set(object, value);
    }
}
