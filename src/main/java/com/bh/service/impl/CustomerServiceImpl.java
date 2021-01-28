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

    /**
     * 更新客户信息
     * @param customer
     * @return
     */
    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    /**
     * 通过id删除客户（形式删除）
     * @param cid
     * @return
     */
    @Override
    public int deleteById(String cid) {
        return customerDao.deleteById(cid);
    }

    /**
     * 查找所有客户
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    /**
     * 通过id查找客户
     * @param cid
     * @return
     */
    @Override
    public Customer findById(String cid) {
        return customerDao.findById(cid);
    }

    /**
     * 多条件组合查询
     * @param customer
     * @return
     */
    @Override
    public List<Customer> seniorQuery(Customer customer) {
        return customerDao.seniorQuery(customer);
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
