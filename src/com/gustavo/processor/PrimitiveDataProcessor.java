package com.gustavo.processor;

import static com.gustavo.constants.ConstantValues.COLLECTION_END;
import static com.gustavo.constants.ConstantValues.COLLECTION_START;
import static com.gustavo.constants.ConstantValues.COMMA;
import static com.gustavo.constants.ConstantValues.EMPTY_STRING;
import static com.gustavo.constants.ConstantValues.QUOTE;
import static com.gustavo.converter.Converter.convertValueToType;
import com.gustavo.converter.JsonFactory;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.StringTokenizer;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class PrimitiveDataProcessor extends JsonFactory {

    public static short[] processShortArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        short[] array;
        if (f != null) {
            array = (short[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (short[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static int[] processIntArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        int[] array;
        if (f != null) {
            array = (int[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (int[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static float[] processFloatArrayData(Object valObj, Field f, Class cls){
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        float[] array;
        if (f != null) {
            array = (float[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (float[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static double[] processDoubleArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        double[] array;
        if (f != null) {
            array = (double[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (double[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static long[] processLongArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        long[] array;
        if (f != null) {
            array = (long[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (long[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static char[] processCharArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        char[] array;
        if (f != null) {
            array = (char[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (char[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static byte[] processByteArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        byte[] array;
        if (f != null) {
            array = (byte[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (byte[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

    public static boolean[] processBooleanArrayData(Object valObj, Field f, Class cls) {
        valObj = valObj.toString().replace(COLLECTION_START, EMPTY_STRING);
        valObj = valObj.toString().replace(COLLECTION_END, EMPTY_STRING);

        StringTokenizer tokenizer = new StringTokenizer(valObj.toString(), COMMA);
        int count = 0;
        boolean[] array;
        if (f != null) {
            array = (boolean[]) Array.newInstance(f.getType().getComponentType(), tokenizer.countTokens());
        } else {
            array = (boolean[]) Array.newInstance(cls.getComponentType(), tokenizer.countTokens());
        }

        while (tokenizer.hasMoreTokens()) {
            String elem = tokenizer.nextElement().toString().replace(QUOTE, EMPTY_STRING);
            if (f != null) {
                array[count++] = convertValueToType(f.getName(), elem.trim(), f.getType().getComponentType());
            } else {
                array[count++] = convertValueToType(null, elem.trim(), cls.getComponentType());
            }
        }
        return array;
    }

}
