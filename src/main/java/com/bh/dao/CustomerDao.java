package com.bh.dao;

import com.bh.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerDao {

    public int addCustomer(Customer customer);     //添加一个客户

    public List<Customer> findAll();       //查找所有客户

    public Customer findById(String cid);       //根据cid查找客户

    public int updateCustomer(Customer customer);      //更新客户信息

    public int deleteById(String cid);      //通过id删除客户

    public List<Customer> seniorQuery(Customer customer);       //多条件组合查询

    public long getPageSum();       //获取记录总数

    public long getPageSum(Customer customer);       //获取记录总数（带参）

    public List<Customer> paginateCustomer(int index,int perPage);       //分页查询客户

    public List<Customer> seniorQueryPaginate(Customer customer,int index, int perPage);     //高级分页查询
}
