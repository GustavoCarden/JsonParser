package com.gustavo.processor;

import com.gustavo.annotations.Show;
import static com.gustavo.constants.ConstantValues.ARRAY;
import static com.gustavo.constants.ConstantValues.COLLECTION;
import com.gustavo.converter.JsonFactory;
import static com.gustavo.converter.JsonFactory.selectedFields;
import com.gustavo.exceptions.FormatException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static com.gustavo.constants.ConstantValues.COLLECTION_END;
import static com.gustavo.constants.ConstantValues.COLLECTION_START;
import static com.gustavo.constants.ConstantValues.COLON;
import static com.gustavo.constants.ConstantValues.COMMA;
import static com.gustavo.constants.ConstantValues.EMPTY_STRING;
import static com.gustavo.constants.ConstantValues.EXCEPTION;
import static com.gustavo.constants.ConstantValues.MAP;
import static com.gustavo.constants.ConstantValues.NATIVE;
import static com.gustavo.constants.ConstantValues.OBJECT;
import static com.gustavo.constants.ConstantValues.OBJECT_END;
import static com.gustavo.constants.ConstantValues.OBJECT_START;
import static com.gustavo.constants.ConstantValues.QUOTE;
import static com.gustavo.converter.Converter.convertValueToType;
import java.util.HashMap;
import com.gustavo.utils.StringUtils;
import com.gustavo.utils.Utils;
import static com.gustavo.converter.JsonFactory.mapWithShowAnnotation;
import java.lang.reflect.Modifier;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class DataProcessor extends JsonFactory {

    //Procesadores para convertir JSON a Objeto ======================================================================
    /*
    * Metodo encargado de convertir un texto en formato json en un objeto de la clase enviada.
    * @param data: Parametro cadena que contiene el texto a convertir.
    * @param instanceClass: Parametro tipo class que nos proporciona la clase a la que va a convertir la data.
    * @param objClass: Parametro tipo class utilizado para cuando el objeto a convertir es un mapa o una lista, 
    * determina el tipo de objeto que contienen.
    * @param preInstanciatedObject: Objeto en el cual se mapearan las propiedades de sus superClases en caso de tener.
    * @param calculatedValues: Parametro tipo mapa que sirve para asignar valores a super clases de las que extienda el objeto (Ej. La clase Exception).
     */
    public static <T> T processObject(String data, Class instanceClass, Class<T> objClass, Object preInstanciatedObject, Map<Object, String> calculatedValues) {
        Object obj = null;
        Map<Object, String> values = null;
        try {
            if (calculatedValues == null) {
                data = StringUtils.trimTextExcludeStringsValues(data);
                values = mapValues(data);
            } else {
                values = calculatedValues;
            }

            String typeInstance = Utils.typeInstance(instanceClass);
            switch (typeInstance) {
                case MAP:
                    Map<Object, Object> mapValues = new HashMap<>();
                    for (int i = 0; i < values.size(); i++) {
                        mapValues.put(i, processObject(values.get(i), objClass, null, null, null));
                    }
                    obj = mapValues;
                    break;
                case COLLECTION:
                    List<T> lstValues = new ArrayList();
                    for (int i = 0; i < values.size(); i++) {
                        lstValues.add(processObject(values.get(i), objClass, null, null, null));
                    }
                    obj = lstValues;
                    break;
                case ARRAY:
                    obj = selectProcessor(data, null, instanceClass);
                    break;
                case EXCEPTION:
                case OBJECT:
                    Field[] fields = instanceClass.getDeclaredFields();
                    obj = preInstanciatedObject == null ? instanceClass.newInstance() : preInstanciatedObject;

                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object valObj = values.remove(field.getName());

                        if (valObj != null) {
                            switch (Utils.typeInstance(field.getType())) {
                                case ARRAY:
                                    field.set(obj, processArrayData(valObj, field));
                                    break;
                                case COLLECTION:
                                    field.set(obj, processCollectionData(valObj, field));
                                    break;
                                case MAP:
                                    field.set(obj, processMapData(valObj, field));
                                    break;
                                case NATIVE:
                                    field.set(obj, convertValueToType(field.getName(), valObj.toString().trim(), field.getType()));
                                    break;
                                default:
                                    field.set(obj, processObject(valObj.toString(), field.getType(), null, null, null));
                                    break;
                            }
                        }
                    }

                    Class objSuperClass = instanceClass.getSuperclass();
                    if (objSuperClass != null && !objSuperClass.equals(Object.class) && !values.isEmpty()) {
                        processObject(null, objSuperClass, null, obj, values);
                    }

                    break;
                default:
                    return convertValueToType(null, data, instanceClass);
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        return obj != null ? (T) obj : null;
    }

    public static Map<Object, String> mapValues(String data) {
        Map<Object, String> mapData = new HashMap<>();
        try {
            if (data != null) {
                if (data.startsWith(COLLECTION_START) && data.endsWith(COLLECTION_END)) {
                    data = StringUtils.removeFirst(data, COLLECTION_START);
                    data = StringUtils.removeLast(data, COLLECTION_END);

                    List<String> lst = StringUtils.getValuesForRow(data, COMMA);
                    int count = 0;
                    for (String rowData : lst) {
                        if ((rowData.contains(OBJECT_START) && rowData.contains(OBJECT_END))) {
                            mapData.put(count++, rowData);
                        } else {
                            mapData.put(count++, rowData);
                        }
                    }
                } else if (data.startsWith(OBJECT_START) && data.endsWith(OBJECT_END)) {
                    data = StringUtils.removeFirst(data, OBJECT_START);
                    data = StringUtils.removeLast(data, OBJECT_END);

                    List<String> lst = StringUtils.getValuesForRow(data, COMMA);
                    for (String rowData : lst) {
                        if ((rowData.contains(OBJECT_START) && rowData.contains(OBJECT_END)) || (rowData.contains(COLLECTION_START) && rowData.contains(COLLECTION_END))) {
                            List aux = StringUtils.getKeyValue(rowData, COLON);
                            if (aux != null) {
                                mapData.put(aux.get(0), aux.get(1).toString());
                            }
                        } else {
                            List<String> lstKeyVal = StringUtils.getKeyValue(rowData, COLON);
                            mapData.put(lstKeyVal.get(0), lstKeyVal.get(1).replace(QUOTE, EMPTY_STRING));
                        }
                    }
                } else {
                    List<String> lst = StringUtils.getValuesForRow(data, COMMA);
                    int count = 0;
                    for (String rowData : lst) {
                        mapData.put(count++, rowData);
                    }
                }
            } else {
                throw new FormatException("No se ha podido realizar la conversion debido a que los datos recibidos estan vacios.");
            }
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
        return mapData;
    }

    public static <T> T[] processArrayData(Object valObj, Field f) {
        valObj = StringUtils.removeFirst(valObj.toString(), COLLECTION_START);
        valObj = StringUtils.removeLast(valObj.toString(), COLLECTION_END);

        List lstData = StringUtils.getValuesForRow(valObj.toString(), COMMA);
        Class<?> clsListType = f.getType().getComponentType();
        if (clsListType == null) {
            ParameterizedType listType = (ParameterizedType) f.getGenericType();
            if (f.getType().equals(Map.class)) {
                clsListType = (Class<?>) listType.getActualTypeArguments()[1];
            } else {
                clsListType = (Class<?>) listType.getActualTypeArguments()[0];
            }

            if (clsListType.isArray()) {
                clsListType = clsListType.getComponentType();
            }
        }
        T[] array = (T[]) Array.newInstance(clsListType, lstData.size());
        int count = 0;

        for (Object o : lstData) {
            String elem = o.toString().replace(QUOTE, EMPTY_STRING);
            if (elem.startsWith(COLLECTION_START) && elem.endsWith(COLLECTION_END)) {
                List auxData = StringUtils.getKeyValue(elem, COMMA);
                for (Object data : auxData) {
                    array[count++] = (T) processObject(data.toString(), f.getType().getComponentType(), null, null, null);
                }
            } else if (elem.startsWith(OBJECT_START) && elem.endsWith(OBJECT_END)) {
                array[count++] = (T) processObject(elem, clsListType, null, null, null);
            } else {
                array[count++] = (T) convertValueToType(f.getName(), elem.trim(), clsListType);
            }
        }
        return array;
    }

    public static Collection processCollectionData(Object valObj, Field f){
        valObj = StringUtils.removeFirst(valObj.toString(), COLLECTION_START);
        valObj = StringUtils.removeLast(valObj.toString(), COLLECTION_END);

        List lstData = StringUtils.getValuesForRow(valObj.toString(), COMMA);
        Collection collection = Utils.getInstanceForClass(f.getType());
        for (Object o : lstData) {
            String elem = o.toString();
            elem = elem.trim();

            /*Se intenta obtener el tipo de dato de la coleccion, en caso de no tenerlo se comportara como Objeto.*/
            Class<?> clsListType = null;
            try {
                ParameterizedType listType = (ParameterizedType) f.getGenericType();
                clsListType = (Class<?>) listType.getActualTypeArguments()[0];
            } catch (Exception e) {
                clsListType = Object.class;
            }

            if (elem.startsWith(COLLECTION_START) && elem.endsWith(COLLECTION_END)) {
                List val = StringUtils.getValuesForRow(elem, COMMA);
                for (Object obj : val) {
                    switch (Utils.typeInstance(clsListType)) {
                        case ARRAY:
                            collection.add(processArrayData(obj.toString(), f));
                            break;
                        case COLLECTION:
                            collection.add(processCollectionData(obj.toString(), f));
                            break;
                        case MAP:
                            collection.add(processMapData(valObj, f));
                            break;
                    }
                }
            } else if (elem.startsWith(OBJECT_START) && elem.endsWith(OBJECT_END)) {
                collection.add(processObject(elem, clsListType, null, null, null));
            } else {
                collection.add(convertValueToType(f.getName(), elem.replace(QUOTE, EMPTY_STRING), clsListType));
            }
        }
        return collection;
    }

    public static Map processMapData(Object valObj, Field f) {
        valObj = StringUtils.removeFirst(valObj.toString(), COLLECTION_START);
        valObj = StringUtils.removeLast(valObj.toString(), COLLECTION_END);

        List lstData = StringUtils.getValuesForRow(valObj.toString(), COMMA);
        Map map = Utils.getInstanceForClass(f.getType());
        int count = 0;
        for (Object o : lstData) {
            String elem = o.toString();
            if (elem.startsWith(COLLECTION_START) && elem.endsWith(COLLECTION_END)) {
                List val = StringUtils.getValuesForRow(elem, COMMA);
                for (Object obj : val) {
                    /*ParameterizedType listType = (ParameterizedType) f.getGenericType();
                    Class<?> clsListType = (Class<?>) listType.getActualTypeArguments()[0];*/
                    map.put(count++, processArrayData(obj.toString(), f));
                }
            } else if (elem.contains(OBJECT_START) && elem.contains(OBJECT_END)) {
                ParameterizedType type = (ParameterizedType) f.getGenericType();
                map.put(count++, processObject(elem, (Class) type.getActualTypeArguments()[1], null, null, null));
            } else {
                map.put(count++, elem.replace(QUOTE, EMPTY_STRING));
            }
        }
        return map;
    }

    //Metodos para arreglos que no pertenecen a un campo------------------------------------------------------------
    public static Object selectProcessor(Object data, Field f, Class cls){
        Object obj;
        switch (cls.getComponentType().getSimpleName()) {
            case "short":
                obj = PrimitiveDataProcessor.processShortArrayData(data, f, f == null ? cls : null);
                break;
            case "int":
                obj = PrimitiveDataProcessor.processIntArrayData(data, f, f == null ? cls : null);
                break;
            case "float":
                obj = PrimitiveDataProcessor.processFloatArrayData(data, f, f == null ? cls : null);
                break;
            case "double":
                obj = PrimitiveDataProcessor.processDoubleArrayData(data, f, f == null ? cls : null);
                break;
            case "long":
                obj = PrimitiveDataProcessor.processLongArrayData(data, f, f == null ? cls : null);
                break;
            case "char":
                obj = PrimitiveDataProcessor.processCharArrayData(data, f, f == null ? cls : null);
                break;
            case "byte":
                obj = PrimitiveDataProcessor.processByteArrayData(data, f, f == null ? cls : null);
                break;
            case "boolean":
                obj = PrimitiveDataProcessor.processBooleanArrayData(data, f, f == null ? cls : null);
                break;
            default:
                obj = DataProcessor.processArrayDataWithClass(data, cls);
                break;
        }
        return obj;
    }

    public static <T> T[] processArrayDataWithClass(Object valObj, Class cls) {
        valObj = StringUtils.removeFirst(valObj.toString(), COLLECTION_START);
        valObj = StringUtils.removeLast(valObj.toString(), COLLECTION_END);

        List lstData = StringUtils.getValuesForRow(valObj.toString(), COMMA);
        T[] array = null;
        if (cls != null) {
            if (cls.isArray()) {
                cls = cls.getComponentType();
            }

            array = (T[]) Array.newInstance(cls, lstData.size());
            int count = 0;

            for (Object o : lstData) {
                String elem = o.toString();
                if (elem.startsWith(COLLECTION_START) && elem.endsWith(COLLECTION_END)) {
                    List auxData = StringUtils.getValuesForRow(elem, COMMA);
                    for (Object data : auxData) {
                        array[count++] = (T) processObject(data.toString(), cls.getComponentType(), null, null, null);
                    }
                } else if (elem.startsWith(OBJECT_START) && elem.endsWith(OBJECT_END)) {
                    array[count++] = (T) processObject(elem, cls, null, null, null);
                } else {
                    elem = elem.replace(QUOTE, EMPTY_STRING);
                    array[count++] = (T) convertValueToType(null, elem.trim(), cls);
                }
            }
        }
        return array;
    }

    //Procesadores para convertir de Objeto a JSON=====================================================================.   
    private static String processObjectToJson(Object obj, Class cls) {
        StringBuilder builder = new StringBuilder();
        Object value;
        try {
            Field[] fields = cls.getDeclaredFields();
            Field f;
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                if (Modifier.isStatic(f.getModifiers()) || f.getType().equals(cls)) {
                    continue;
                }

                f.setAccessible(true);
                value = null;
                if (selectedFields != null) {
                    if (selectedFields.contains(f.getName().toLowerCase())) {
                        value = f.get(obj);
                    }
                } else if (mapWithShowAnnotation) {
                    if (f.isAnnotationPresent(Show.class)) {
                        value = f.get(obj);
                    }
                } else {
                    value = f.get(obj);
                }

                if (value != null) {
                    Class typeClass = f.getType().equals(Object.class) ? value.getClass() : f.getType();
                    String typeIns = Utils.typeInstance(typeClass);

                    if (!Utils.isEmpty(value, typeIns)) {
                        if (i > 0 && !builder.toString().isEmpty()) {
                            builder.append(",");
                        }
                        builder.append("\"");
                        builder.append(f.getName());
                        builder.append("\"");
                        builder.append(":");

                        if (typeIns.equals(ARRAY) || typeIns.equals(COLLECTION) || typeIns.equals(MAP)
                                || typeIns.equals(OBJECT) || typeIns.equals(EXCEPTION)) {
                            builder.append(processObjectToData(value));
                        } else {
                            if (f.getType().equals(String.class)
                                    || f.getType().equals(Date.class)
                                    || f.getType().equals(java.sql.Date.class)
                                    || f.getType().equals(java.sql.Timestamp.class)) {
                                builder.append("\"");
                                if (f.getType().equals(Date.class)
                                        || f.getType().equals(java.sql.Date.class)
                                        || f.getType().equals(java.sql.Timestamp.class)) {
                                    SimpleDateFormat formater = new SimpleDateFormat(JsonFactory.datePattern == null ? "dd/MM/yyyy" : JsonFactory.datePattern);
                                    value = formater.format(value);
                                }
                                builder.append(value);
                                builder.append("\"");
                            } else {
                                builder.append(value);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        return builder.toString();
    }

    public static String processObjectToData(Object o) {
        StringBuilder json = new StringBuilder();
        Class cls = o.getClass();
        switch (Utils.typeInstance(cls)) {
            case ARRAY:
                json.append(COLLECTION_START);
                switch (cls.getComponentType().getSimpleName()) {
                    case "short":
                        short[] shorts = (short[]) o;
                        for (int i = 0; i < shorts.length; i++) {
                            json.append(processObjectToData(shorts[i]));
                            if (i != shorts.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "int":
                        int[] ints = (int[]) o;
                        for (int i = 0; i < ints.length; i++) {
                            json.append(processObjectToData(ints[i]));
                            if (i != ints.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "float":
                        float[] floats = (float[]) o;
                        for (int i = 0; i < floats.length; i++) {
                            json.append(processObjectToData(floats[i]));
                            if (i != floats.length - 1) {
                                json.append(",");
                            }
                        }
                    case "double":
                        double[] doubles = (double[]) o;
                        for (int i = 0; i < doubles.length; i++) {
                            json.append(processObjectToData(doubles[i]));
                            if (i != doubles.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "long":
                        long[] longs = (long[]) o;
                        for (int i = 0; i < longs.length; i++) {
                            json.append(processObjectToData(longs[i]));
                            if (i != longs.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "char":
                        char[] chars = (char[]) o;
                        for (int i = 0; i < chars.length; i++) {
                            json.append(processObjectToData(chars[i]));
                            if (i != chars.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "byte":
                        byte[] bytes = (byte[]) o;
                        for (int i = 0; i < bytes.length; i++) {
                            json.append(processObjectToData(bytes[i]));
                            if (i != bytes.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    case "boolean":
                        boolean[] bools = (boolean[]) o;
                        for (int i = 0; i < bools.length; i++) {
                            json.append(processObjectToData(bools[i]));
                            if (i != bools.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                    default:
                        Object[] objects = (Object[]) o;
                        for (int i = 0; i < objects.length; i++) {
                            json.append(processObjectToData(objects[i]));
                            if (i != objects.length - 1) {
                                json.append(",");
                            }
                        }
                        break;
                }

                json.append(COLLECTION_END);
                break;

            case COLLECTION:
                json.append(COLLECTION_START);
                ArrayList list = (ArrayList) o;
                for (int i = 0; i < list.size(); i++) {
                    json.append(processObjectToData(list.get(i)));
                    if (i != list.size() - 1) {
                        json.append(",");
                    }
                }

                json.append(COLLECTION_END);
                break;

            case MAP:
                json.append(COLLECTION_START);
                LinkedHashMap<Object, Object> map = (LinkedHashMap) o;
                Object[] objects = map.entrySet().toArray();
                for (int i = 0; i < objects.length; i++) {
                    Map.Entry entry = (Map.Entry) objects[i];
                    json.append(processObjectToData(entry.getValue()));
                    if (i != objects.length - 1) {
                        json.append(",");
                    }
                }
                json.append(COLLECTION_END);
                break;

            case NATIVE:
                json.append(OBJECT_START);
                json.append("\"value\":");
                json.append(o);
                json.append(OBJECT_END);
                break;

            case EXCEPTION:
            case OBJECT:
                json.append(OBJECT_START);
                json.append(processObjectToJson(o, cls));
                while (cls.getSuperclass() != null && !cls.getSuperclass().equals(Object.class)) {
                    String jsonAux = processObjectToJson(o, cls.getSuperclass());
                    if (!jsonAux.isEmpty() && json.length() > 1) {
                        json.append(",");
                    }
                    json.append(jsonAux);
                    cls = cls.getSuperclass();
                }
                json.append(OBJECT_END);
                break;

            default:
                System.err.println("Clase no reconocida...");
        }

        return json.toString();
    }

}
