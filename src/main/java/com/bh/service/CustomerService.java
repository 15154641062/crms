package com.bh.service;

import com.bh.pojo.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    public int addCustomer(Customer customer);     //添加一个客户

    public List<Customer> findAll();       //查找所有客户

    public Customer findById(String cid);       //根据cid查找客户

    public int updateCustomer(Customer customer);      //更新客户信息

    public int deleteById(String cid);      //通过id删除客户

    public List<Customer> seniorQuery(Customer customer);       //多条件组合查询

    public long getPageSum();       //获取记录总数

    public HashMap paginateInfo(int pageIndex);     //分页信息

    public HashMap paginateInfo(Customer customer, int pageIndex);     //高级查询分页信息

}
