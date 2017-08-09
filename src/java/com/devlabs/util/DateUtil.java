package com.devlabs.util;

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
        
        // Se obtiene un calendario
        Calendar calendar = Calendar.getInstance();
        
        // Se le establece una fecha
        calendar.setTime(date);
        
        /*
            Si el tipo es por tiempo, se retornará un String
            en forma de tiempo separado por dos puntos.
        */
        if (type.equals("time")) {
            
            return calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE) + ":"
                    + calendar.get(Calendar.SECOND);
        
        /*
            Si el tipo es por fecha, se retornará un String
            en forma de fecha separado por guiones medios.
        */
        } else {
            
            if (type.equals("simpleTime")) {
                
                return calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
                
            } else {
                
                if (type.equals("date")) {
                
                    return calendar.get(Calendar.DAY_OF_MONTH) + "-"
                            + calendar.get(Calendar.MONTH) + "-"
                            + calendar.get(Calendar.YEAR);

                }
                
            }
            
        }
        
        return null;
        
    }
    
}
