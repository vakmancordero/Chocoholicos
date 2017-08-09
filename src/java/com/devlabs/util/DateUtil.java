package com.devlabs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author VakSF
 */
public class DateUtil {
    
    /**
     * Retorna una fecha simple en base a un tipo y una fecha definida.
     * El tipo puede ser "time" o "date"
     * 
     * @param date es una fecha completa
     * @param type el tipo a convertir
     * @return      la fecha esperada en base al tipo
     */
    public static String getSimpleDateTime(Date date, String type) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    
}
