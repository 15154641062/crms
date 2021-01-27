package com.bh.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormat {

    /**
     * 转化String格式的日期为数据库中的日期格式
     * @param date
     * @return
     */
    public static Date toSqlDate(String date){
        try {
        long time=new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();

            return new Date(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
