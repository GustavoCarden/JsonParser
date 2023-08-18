/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.gustavo.converter.JsonFactory;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author gustavo
 */
public class Main {

    private static final String example = "{"
            + "\"nombre\":\"Gustavo\","
            + "\"a\": 10,"
            + "\"b\": 15.2,"
            + "\"c\": 20.2,"
            + "\"d\": 1,"
            + "\"dateUtil\": 12/01/2020,"
            + "\"dateSql\": 2020/01/12,"
            + "\"l\": 10"
            + "}";

    private static final String example2 = "{"
            + "\"a\":\"Gustavo\","
            + "\"b\": 15.2,"
            + "\"cars\":[Prueba1,Prueba2],"
            + "\"names\": [Gustavo,Marina,Sebas]"
            + "}";

    private static final String example3 = "{"
            + "\"c\":[Prueba1,Prueba2],"
            + "\"ed\": { \"c\": [\"a\",\"b\",\"c\"]}"
            + "}";

    private static final String example4 = "["
            + "{"
            + "\"nombre\":\"Gustavo\","
            + "\"a\": 10,"
            + "\"b\": 15.2,"
            + "\"c\": 20.2,"
            + "\"d\": 1,"
            + "\"l\": 10"
            + "},"
            + "{"
            + "\"nombre\":\"Gustavo Cardenas\","
            + "\"a\": 11,"
            + "\"b\": 12.2,"
            + "\"c\": 13.2,"
            + "\"d\": 0,"
            + "\"l\": 14"
            + "}"
            + "]";

    private static final String example5 = "[1,0,1,0,0]";

    private static final String example6 = "{"
            + "\"ed\":["
            + " {\"d\": [a,b,d] },"
            + " {\"c\": [e,f,g] }"
            + "]"
            + "}";

    private static final String example7 = "["
            + "{"
            + "\"ed\":["
            + " {\"c\": [a,b,d] },"
            + " {\"c\": [e,f,g] }"
            + "]"
            + "},"
            + "{"
            + "\"ed\":["
            + " {\"c\": [h,i,j] },"
            + " {\"c\": [k,l,m] }"
            + "]"
            + "}"
            + "]";

    private static final String example8 = "[8.5,7,6,4,3]";

    private static final String example9 = "[\"a\",\"a\",\"a\",\"a\",\"a\"]";

    public static void main(String[] args) throws Exception {
        JsonFactory factory = new JsonFactory("dd/MM/yyyy");
        try {
            /*Example 1*/
            //Object obj = factory.convertToObject(example, Example.class);
            /*Example 2*/
            //Object obj2 = factory.convertToObject(example2, Example2.class);
            /*Example 3*/
            //Object obj3 = factory.convertToObject(example3, Example3.class);
            /*Example 4*/
            //Object obj4Arr = factory.convertToObject(example4, Example[].class);
            //Object obj4Map = factory.convertToObject(example4, Map.class, Example.class);
            //Object obj4List = factory.convertToObject(example4, List.class, Example.class);
            /*Example 5*/
            //Object obj5 = factory.convertToObject(example5, byte[].class);
            /*Example 6*/
            //Object obj6 = factory.convertToObject(example6, Example6.class);
            /*Example 7*/
            //Object obj7 = factory.convertToObject(example7, Example6[].class);
            /*Example 8*/
            //Object obj8 = factory.convertToObject(example8, Map.class, Float.class);
            /*Example 9*/
            //Object obj9 = factory.convertToObject(example9, String[].class);
            //System.out.println();

            //Ejemplos object to JSON==========================================================
            //Convert object to json.
            /*//Ejemplo 1 =======================================================
            /*long[] l = new long[]{1,2,3,4};
            System.err.println(factory.format(l, long[].class));*/
            //Ejemplo 2 ======================================================
            List<String> fields = new ArrayList<>();
            fields.add("nombre");
            fields.add("num");
            fields.add("fecNac");
            fields.add("fecha");
            fields.add("detail");
            fields.add("a");
            fields.add("b");

            //factory.mapOnlySelectedFields(fields);
            /*ExampleJson ej = new ExampleJson();
            ej.setNombre("Gustavo");
            ej.setNum(10);
            ej.setFecNac(new Date());
            ej.setFecha(new java.sql.Date(System.currentTimeMillis()));
            ej.setFecTime(new Timestamp(System.currentTimeMillis()));
            
            ExampleJsonDetail detail = new ExampleJsonDetail(null, 10);
            ej.setDetail(detail);*/
 /*List<ExampleJsonDetail> lst = new ArrayList<>();
            lst.add(new ExampleJsonDetail("A", INT_DEFAULT_VALUE));
            lst.add(new ExampleJsonDetail("B", INT_DEFAULT_VALUE));
            //ej.setLst(lst);
            ReturnObject ro = new ReturnObject();
            ro.setToken("TOKEN");
            ro.setData(lst);*/
            //System.err.println(new Gson().toJson(ro));
            /* LinkedHashMap<Integer, ExampleJsonDetail> map = new LinkedHashMap<>();
            //map.put(1, new ExampleJsonDetail("A", INT_DEFAULT_VALUE, mapArray));
            map.put(1, new ExampleJsonDetail("C", INT_DEFAULT_VALUE));
            map.put(2, new ExampleJsonDetail("D", INT_DEFAULT_VALUE));
            ej.setMap(map);*

            System.err.println(factory.convertToJson(ej, ExampleJson.class));*/
 /*LinkedHashMap<Object, LinkedHashMap<Integer, Integer>> mapArray = new LinkedHashMap<Object, LinkedHashMap<Integer, Integer>>();
            LinkedHashMap<Integer, Integer> map2 = new LinkedHashMap<>();
            map2.put(1, 1);
            mapArray.put(1, map2);

            LinkedHashMap<Integer, ExampleJsonDetail> map = new LinkedHashMap<>();
            map.put(1, new ExampleJsonDetail("A", INT_DEFAULT_VALUE, mapArray));
            //map.put(1, new ExampleJsonDetail("A", INT_DEFAULT_VALUE, map2));
            map.put(2, new ExampleJsonDetail("B", INT_DEFAULT_VALUE));
            ej.setMap(map);

            //ej.setArr(new ExampleJsonDetail[]{new ExampleJsonDetail("A", INT_DEFAULT_VALUE), new ExampleJsonDetail("B", 2)});
            //ej.setIntArr(new int[]{1,2,3,4});
            /*Example 1*/
 /*List<String> selectedFields = new ArrayList<>();
            selectedFields.add("nombre");
            /*Example 2*
            JsonFactory factory = new JsonFactory().mapOnlySelectedFields(selectedFields);*/
            /*Pruebas con exepciones========================================= */
            String sqlEx = "{\"SQLState\":\"ORA_5000\",\"vendorCode\":10,\"detailMessage\":\"Razon de prueba\"}";
            SQLException ex = factory.convertToObject(sqlEx, SQLException.class);
            System.err.println("");
            
 
            /*String ex = "{\n"
                    + "  \"detailMessage\": \"No se ha podido realizar la conversion debido a que los datos recibidos estan vacios.\",\n"
                    + "  \"stackTrace\": [\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 0\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 1\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 2\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 3\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 4\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 5\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 6\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 7\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 8\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"declaringClass\": \"com.gustavo.converter.JsonFactory\",\n"
                    + "      \"methodName\": \"MET_PRUEBA\",\n"
                    + "      \"fileName\": \"CAMPO_PRUEBA\",\n"
                    + "      \"lineNumber\": 9\n"
                    + "    }\n"
                    + "  ]\n"
                    + "}";
            Exception e = factory.convertToObject(ex, Exception.class);*/
            //Object o = new Object();
            //Object obj = StackTraceElement.class.cast(o);
            
            String classNotFound = "";
            ClassNotFoundException clssEx = factory.convertToObject(classNotFound, ClassNotFoundException.class);
            System.err.println("");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();

            /*Ejemplo 1*/
 /*StackTraceElement[] lst = new StackTraceElement[10];
            for (int i = 0; i < 10; i++) {
                StackTraceElement ste = new StackTraceElement(JsonFactory.class.getName(), "MET_PRUEBA", "CAMPO_PRUEBA", i);
                lst[i] = ste;
            }
            e.setStackTrace(lst);
            System.err.println(factory.convertToJson(e));*/

 /*Ejemplo 2*/
 /*SQLException ex = new SQLException("Razon de prueba", "ORA_5000");
            System.err.println(factory.convertToJson(ex));*/
        }
    }
}
