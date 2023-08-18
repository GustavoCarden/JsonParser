package com.gustavo.converter;

import com.gustavo.processor.DataProcessor;
import java.util.List;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class JsonFactory {

    public static String datePattern;
    public static boolean mapWithShowAnnotation;
    public static List<String> selectedFields;

    /*
    * Constructor por default
     */
    public JsonFactory() {
    }

    /*
    * Constructor de la clase que nos permite asignar un formato a las propiedades tipo Date de los objetos a transformar.
    * @param datePattern: Parametro tipo String que contiene el formato.
     */
    public JsonFactory(String datePattern) {
        JsonFactory.datePattern = datePattern;
    }

    /*
    * Metodo que nos permite obtener una respuesta unicamente con las propiedades del objeto que contengan la etiqueta Show.
    * @return Este metodo nos da como resultado una instancia de la clase configurada.
     */
    public JsonFactory mapOnlyWithShowAnnotation() {
        JsonFactory.mapWithShowAnnotation = true;
        selectedFields = null;
        return this;
    }

    /*
    * Metodo que nos permite obtener una respuesta unicamente con las propiedades del objeto que esten en el parametro de entrada.
    * @param selectedFields: Parametro tipo lista que contiene el nombre de los campos a mostrar.
    * @return Este metodo nos da como resultado una instancia de la clase configurada.
     */
    public JsonFactory mapOnlySelectedFields(List<String> selectedFields) {
        for (int i = 0; i < selectedFields.size(); i++) {
            selectedFields.set(i, selectedFields.get(i).toLowerCase());
        }
        JsonFactory.selectedFields = selectedFields;
        mapWithShowAnnotation = false;
        return this;
    }

    /*
    * Metodo que convierte objectos y arreglos de texto JSON a objectos.
    * @param data: Parametro tipo cadena que contiene el JSON a convertir.
    * @param cls: Clase a la cual se convertirá el objeto.
    * @return El metodo retorna una instancia de la clase enviada en los parametros.
     */
    public <T> T convertToObject(String data, Class<T> cls) {
        return DataProcessor.processObject(data, cls, null, null, null);
    }

    /*
    * Metodo que convierte listas y mapas de texto JSON a colleciones de objetos.
    * @param data: Parametro que contiene el JSON a convertir.
    * @param instanceClass: Clase de la coleccion.
    * @param cls: Clase a la cual se convertirá los objeto.
    * @return El metodo retorna una instancia de la clase enviada en los parametros.
     */
    public <T> T convertToObject(String data, Class instanceClass, Class<T> objClass) {
        return DataProcessor.processObject(data, instanceClass, objClass, null, null);
    }

    /*
    * Metodo que convierte un objeto en su representacion JSON.
    * @param obj: Objeto a representar en JSON.
    * @param cls: Clase del objeto a representar.
     */
    public String convertToJson(Object obj) {
        return DataProcessor.processObjectToData(obj);
    }
}
