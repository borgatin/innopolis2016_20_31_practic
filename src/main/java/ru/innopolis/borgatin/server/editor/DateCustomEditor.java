package ru.innopolis.borgatin.server.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by avborg on 31.10.2016.
 */
public class DateCustomEditor extends PropertyEditorSupport {

    public void setAsText(String value) {
        try {
            setValue(new Date(new SimpleDateFormat("dd.MM.yyyy").parse(value).getTime()));
        } catch (ParseException e) {
            setValue(null);
        }
    }

    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? new SimpleDateFormat("dd.MM.yyyy").format(value) : "");
    }

}