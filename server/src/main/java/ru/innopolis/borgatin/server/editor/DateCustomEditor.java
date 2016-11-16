package ru.innopolis.borgatin.server.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.innopolis.borgatin.common.MainConst.CONST_DATE_FORMAT;

/**
 * Класс предназначен для преобразования из строки в дату
 * и обратно при передачи из view в контроллер
 */
public class DateCustomEditor extends PropertyEditorSupport {

    public void setAsText(String value) {
        try {
            setValue(new Date(new SimpleDateFormat(CONST_DATE_FORMAT).parse(value).getTime()));
        } catch (ParseException e) {
            setValue(null);
        }
    }

    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? new SimpleDateFormat(CONST_DATE_FORMAT).format(value) : "");
    }

}