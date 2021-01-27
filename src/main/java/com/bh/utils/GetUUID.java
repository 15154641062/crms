package com.bh.utils;

import java.util.UUID;

public class GetUUID {
    /**
     * 返回32位大写字母UUID
     * @return
     */
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
