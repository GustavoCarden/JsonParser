package com.gustavo.utils;

import com.gustavo.constants.ConstantValues;
import static com.gustavo.constants.ConstantValues.COLLECTION_END_CHAR;
import static com.gustavo.constants.ConstantValues.COLLECTION_START_CHAR;
import static com.gustavo.constants.ConstantValues.OBJECT_END_CHAR;
import static com.gustavo.constants.ConstantValues.OBJECT_START_CHAR;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo Cardenas Alba
 */
public class StringUtils {

    public static String removeFirst(String originalText, String regex) {
        int index = originalText.indexOf(regex);
        return originalText.substring(index + 1);
    }

    public static String removeLast(String originalText, String regex) {
        int index = originalText.lastIndexOf(regex);
        return originalText.substring(0, index);
    }

    public static String substringForFirst(String originalText, String regex) {
        String result = "";
        int index = originalText.indexOf(regex);
        if (index >= 0) {
            result = originalText.substring(0, index);
        }
        return result;
    }

    public static int getNextIndexOf(String text, String regex, int indexOf) {
        int index = -1;
        for (int i = indexOf; i < text.length(); i++) {
            if (text.charAt(i) == regex.charAt(0)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public static List getValuesForRow(String text, String regex) {
        List list = new ArrayList();
        int openObjects = 0;

        int index;
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case COLLECTION_START_CHAR:
                case OBJECT_START_CHAR:
                    openObjects++;
                    break;
                case OBJECT_END_CHAR:
                case COLLECTION_END_CHAR:
                    openObjects--;
                    break;
            }

            index = text.indexOf(regex);
            if (i == index) {
                if (openObjects == 0) {
                    list.add(substringForFirst(text, regex));
                    text = text.substring(index + 1);
                    i = 0;
                    index = text.indexOf(regex);

                    if (index < 0 && !text.isEmpty()) {
                        list.add(text);
                        text = "";
                    }
                } else {
                    index = getNextIndexOf(text, regex, index);
                    if (index == -1) {
                        list.add(text);
                    }
                }
            } else if ((text.charAt(i) == regex.charAt(0) && openObjects == 0)) {
                list.add(text.substring(0, i));
                list.addAll(getValuesForRow(text.substring(i + 1, text.length()), regex));
                text = "";
            } else if (i == text.length() - 1) {
                list.add(text.substring(0, i + 1));
                text = "";
            }

        }

        return list;
    }

    public static List getKeyValue(String text, String regex) {
        List list = new ArrayList();
        int openObjects = 0;
        int index = text.indexOf(regex);

        OUTER:
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case COLLECTION_START_CHAR:
                case OBJECT_START_CHAR:
                    openObjects++;
                    break;
                case COLLECTION_END_CHAR:
                    openObjects--;
                    break;
                case OBJECT_END_CHAR:
                    if (openObjects == 1) {
                        break OUTER;
                    } else {
                        openObjects--;
                    }
                    break;
                default:
                    break;
            }

            if (i == index && openObjects == 0) {
                list.add(text.substring(0, index).replaceAll(ConstantValues.QUOTE, ConstantValues.EMPTY_STRING));
                list.add(text.substring(index + 1, text.length()));
            }
        }

        return list;
    }

    public static String trimTextExcludeStringsValues(String text) {
        boolean isQuotesOpen = false;
        String trimText = null;
        if (text != null) {
            trimText = "";
            for (int i = 0; i < text.length(); i++) {
                if ((text.charAt(i) == ' ' || text.charAt(i) == '\n') && !isQuotesOpen) {
                    trimText += trimTextExcludeStringsValues(text.substring(i + 1));
                    break;
                } else {
                    if (text.charAt(i) == '\"') {
                        isQuotesOpen = !isQuotesOpen;
                    }
                    trimText += text.charAt(i);
                }
            }
        }
        
        return trimText;
    }
}
