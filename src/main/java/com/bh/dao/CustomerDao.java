package com.bh.dao;

import com.bh.pojo.Customer;
import org.springframework.stereotype.Repository;

public interface CustomerDao {

    public int addCustomer(Customer customer);     //添加一个客户
}
