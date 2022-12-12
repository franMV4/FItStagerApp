package com.svalero.fitstagerapplication.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String MY_DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Convierte mi formato de fecha personalizado (dd/MM/yyyy) a formato
     * LocalDate de la BBDD (yyyy-MM-dd)
     *
     * @param dateString fecha con formato (dd/MM/yyyy)
     * @return LocalDate fecha con formato (yyyy-MM-dd)
     */
    public static LocalDate fromMyDateFormatStringToLocalDate(String dateString) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(MY_DATE_FORMAT);
        return LocalDate.parse(dateString.trim(), formato);
    }

    /**
     * Convierte un LocalDate en un String formato de fecha personalizado (dd/MM/yyyy)
     *
     * @param localDate LocalDate de la BBDD (yyyy-MM-dd)
     * @return String con formato (dd/MM/yyyy)
     */
    public static String fromLocalDateToMyDateFormatString(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MY_DATE_FORMAT);
        return String.valueOf(localDate.format(dateTimeFormatter));
    }
}
