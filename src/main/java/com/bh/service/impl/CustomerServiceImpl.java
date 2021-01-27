package com.bh.service.impl;

import com.bh.dao.CustomerDao;
import com.bh.pojo.Customer;
import com.bh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    /**
     * 添加一个客户
     * @param customer
     * @return
     */
    @Override
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }
}
