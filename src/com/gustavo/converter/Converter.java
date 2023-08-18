package com.gustavo.converter;



import static com.gustavo.constants.ConstantValues.BYTE_DEFAULT_VALUE;
import static com.gustavo.constants.ConstantValues.CHAR_DEFAULT_VALUE;
import static com.gustavo.constants.ConstantValues.DOUBLE_DEFAULT_VALUE;
import static com.gustavo.constants.ConstantValues.FLOAT_DEFAULT_VALUE;
import static com.gustavo.constants.ConstantValues.INT_DEFAULT_VALUE;
import static com.gustavo.constants.ConstantValues.LONG_DEFAULT_VALUE;
import static com.gustavo.constants.ConstantValues.SHORT_DEFAULT_VALUE;
import com.gustavo.exceptions.FormatException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class Converter {

    /*Metodo que convierte un valor en un tipo de dato especifico
    * @param fieldName: Parametro que contiene el nombre del campo en el cual se situara el resultado, 
    *  solo para enviarlo de informacion en caso de ocurrir alguna exepcion de conversion.
    * @param value: Parametro tipo cadena que contiene el valor a convertir.
    * @param clsType: Parametro que contiene la clase a convertir.
     */
    public static <T> T convertValueToType(String fieldName, String value, Class clsType) {
        Object o = null;
        try {
            if (value != null) {
                switch (clsType.getSimpleName()) {
                    case "String":
                        o = value;
                        break;
                    case "Integer":
                    case "int":
                        o = Integer.valueOf(value);
                        break;
                    case "Double":
                    case "double":
                        o = Double.valueOf(value);
                        break;
                    case "BigInteger":
                        o = BigInteger.valueOf(Long.valueOf(value));
                        break;
                    case "Float":
                    case "float":
                        o = Float.valueOf(value);
                        break;
                    case "Date":
                        try {
                            SimpleDateFormat formater = null;
                            /*if (datePattern != null) {
                                formater = new SimpleDateFormat(datePattern);
                            } else {
                                formater = new SimpleDateFormat("dd/MM/yyyy");
                            }*/

                            if (clsType.getName().equals("java.util.Date")) {
                                o = formater.parse(value);
                            } else {
                                o = new java.sql.Date(formater.parse(value).getTime());
                            }
                        } catch (ParseException e) {
                            throw new FormatException("Se ha producido un error al convertir el campo [" + fieldName + "]");
                        } catch (IllegalArgumentException e) {
                            throw new FormatException("El formato proporcionado no es valido.");
                        }
                        break;
                    case "Byte":
                    case "byte":
                        o = Byte.parseByte(value);
                        break;
                    case "Short":
                    case "short":
                        o = Short.valueOf(value);
                        break;
                    case "Long":
                    case "long":
                        o = Long.valueOf(value);
                        break;
                    case "Boolean":
                    case "boolean":
                        o = Boolean.valueOf(value);
                        break;
                    case "Timestamp":
                        o = Timestamp.valueOf(value);
                        break;
                    case "Character":
                    case "char":
                        o = value.charAt(0);
                        break;
                    case "BigDecimal":
                        o = BigDecimal.valueOf(Double.valueOf(value));
                        break;
                    case "Object":
                        o = value;
                        break;
                }
            } else {
                switch (clsType.getSimpleName()) {
                    case "short":
                        o = SHORT_DEFAULT_VALUE;
                        break;
                    case "int":
                        o = INT_DEFAULT_VALUE;
                        break;
                    case "float":
                        o = FLOAT_DEFAULT_VALUE;
                        break;
                    case "double":
                        o = DOUBLE_DEFAULT_VALUE;
                        break;
                    case "long":
                        o = LONG_DEFAULT_VALUE;
                        break;
                    case "char":
                        o = CHAR_DEFAULT_VALUE;
                        break;
                    case "byte":
                        o = BYTE_DEFAULT_VALUE;
                        break;
                    case "boolean":
                        o = Boolean.FALSE;
                        break;
                }
            }
        } catch (NumberFormatException e) {
            String errorMsg;
            if (fieldName != null) {
                errorMsg = "El valor del campo " + fieldName + " no corresponde con el tipo de dato.";
            } else {
                errorMsg = "Los datos a convertir no corresponde con el tipo de dato de salida.";
            }
            System.err.println(errorMsg);
        } catch (FormatException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return (T) o;
    }
}
