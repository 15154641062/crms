package com.bh.service;

import com.bh.pojo.Customer;

import java.util.List;

public interface CustomerService {
    public int addCustomer(Customer customer);     //添加一个客户

    public List<Customer> findAll();       //查找所有客户
}
