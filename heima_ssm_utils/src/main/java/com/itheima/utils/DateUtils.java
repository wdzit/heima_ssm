package com.itheima.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 日期转换成字符串
     * @param date 需要转换的日期
     * @param patten   日期转换的格式
     * @return
     */

    public static String date2String(Date date,String patten){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(patten);
        String format=simpleDateFormat.format(date);
        return format;
    }
    /**
     * 字符串转换成日期
     *
     */
    public static Date string2Date(String str,String patten){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(patten);
        try {
            Date date= simpleDateFormat.parse(str);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("你输入的格式不正确，请重新输入");
        }


    }
}
