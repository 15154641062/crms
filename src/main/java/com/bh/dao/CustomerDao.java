package com.bh.dao;

import com.bh.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerDao {

    public int addCustomer(Customer customer);     //添加一个客户

    public List<Customer> findAll();       //查找所有客户
}
