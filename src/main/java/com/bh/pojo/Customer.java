package com.bh.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Customer {
    private String cid;     //客户id
    private String cname;       //客户姓名
    private String gender;      //客户性别
    private Date birthday;      //客户生日
    private String cellphone;       //客户电话
    private String email;       //客户邮箱
    private String description;     //客户描述
    private String enable;      //删除标志位（1-删除  0-未删除）
}
