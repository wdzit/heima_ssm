package com.itheima.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

public class DateStringEditor extends PropertiesEditor {


    @Override
    public  void setAsText(String text) throws IllegalArgumentException {
     Date date =  DateUtils.string2Date(text,"yyyy-MM-dd HH:mm:ss");
     super.setValue(date);

    }
}
