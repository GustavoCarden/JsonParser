package com.gustavo.utils;


import static com.gustavo.constants.ConstantValues.ARRAY;
import static com.gustavo.constants.ConstantValues.COLLECTION;
import static com.gustavo.constants.ConstantValues.EXCEPTION;
import static com.gustavo.constants.ConstantValues.MAP;
import static com.gustavo.constants.ConstantValues.NATIVE;
import static com.gustavo.constants.ConstantValues.OBJECT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class Utils {

    public static String typeInstance(Class type) {
        String instance = null;

        try {
            if (type.isArray()) {
                instance = ARRAY;
            } else if (Collection.class.isAssignableFrom(type)) {
                instance = COLLECTION;
            } else if (Map.class.isAssignableFrom(type)) {
                instance = MAP;
            } else if (Throwable.class.isAssignableFrom(type)) {
                instance = EXCEPTION;
            } else if (type.isPrimitive() || isObjectDataType(type)) {
                instance = NATIVE;
            } else {
                instance = OBJECT;
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        return instance;
    }

    public static boolean isObjectDataType(Class type) {
        /*Package p = type.getPackage();
        /*Si el package es null signifca que esta en el package default para las clases del usuario.
        Si la clase no tiene titulo no es una clase de Oracle.*
        return p != null && p.getImplementationTitle() != null;*/

        boolean isObjectDataType = false;
        while (type.getSuperclass() != null) {
            if (Comparable.class.isAssignableFrom(type)) {
                isObjectDataType = true;
                break;
            }
            type = type.getSuperclass();
        }
        return isObjectDataType;
    }

    public static boolean isEmpty(Object obj, String type) {
        boolean isEmpty = false;
        if (obj != null) {
            switch (type) {
                case ARRAY:
                    Object[] objs = (Object[]) obj;
                    isEmpty = objs.length <= 0;
                    break;
                case COLLECTION:
                    Collection list = (Collection) obj;
                    isEmpty = list.isEmpty();
                    break;
                case MAP:
                    Map map = (Map) obj;
                    isEmpty = map.isEmpty();
                    break;
                default:
                    break;
            }
        }

        return isEmpty;
    }

    public static <T> T getInstanceForClass(Class type) {
        Object objInstance = null;
        try {
            if (Collection.class.isAssignableFrom(type)) {
                objInstance = new ArrayList<>();
            } else if (Map.class.isAssignableFrom(type)) {
                objInstance = new LinkedHashMap();
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return (T) objInstance;
    }

}
