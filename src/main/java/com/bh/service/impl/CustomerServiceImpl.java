package com.bh.service.impl;

import com.bh.dao.CustomerDao;
import com.bh.pojo.Customer;
import com.bh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 更新客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    /**
     * 通过id删除客户（形式删除）
     *
     * @param cid
     * @return
     */
    @Override
    public int deleteById(String cid) {
        return customerDao.deleteById(cid);
    }

    /**
     * 查找所有客户
     *
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public long getPageSum() {
        return 0;
    }

    /**
     * 分页查询信息
     *
     * @param pageIndex
     * @return
     */
    @Override
    public HashMap paginateInfo(int pageIndex) {
        int perPage = 10;    //每页显示多少数据
        int pageSum = (int) customerDao.getPageSum();    //记录总数
        int pageCount;      //分页总数
        int flag = 5;        //显示的分页数
        int type = 0;     //查询类型 0：查询全部 1：高级查询
        int index = (pageIndex - 1) * perPage;
        if (pageSum % perPage == 0) {
            pageCount = pageSum / perPage;
        } else {
            pageCount = pageSum / perPage + 1;
        }
        List<Customer> customerList = customerDao.paginateCustomer(index, perPage);

        HashMap pageInfo = new HashMap();
        pageInfo.put("perPage", perPage);
        pageInfo.put("pageSum", pageSum);
        pageInfo.put("pageCount", pageCount);
        pageInfo.put("flag", flag);
        pageInfo.put("pageIndex", pageIndex);
        pageInfo.put("type", type);
        pageInfo.put("customerList", customerList);

        return pageInfo;
    }

    /**
     * 高级分页查询
     *
     * @param customer
     * @param pageIndex
     * @return
     */
    @Override
    public HashMap paginateInfo(Customer customer, int pageIndex) {
        int perPage = 10;    //每页显示多少数据
        int pageSum = (int) customerDao.getPageSum(customer);    //记录总数
        int pageCount;      //分页总数
        int flag = 5;        //显示的分页数
        int type = 1;     //查询类型 0：查询全部 1：高级查询
        int index = (pageIndex - 1) * perPage;
        if (pageSum % perPage == 0) {
            pageCount = pageSum / perPage;
        } else {
            pageCount = pageSum / perPage + 1;
        }
        List<Customer> customerList = customerDao.seniorQueryPaginate(customer, index, perPage);

        HashMap pageInfo = new HashMap();
        pageInfo.put("perPage", perPage);
        pageInfo.put("pageSum", pageSum);
        pageInfo.put("pageCount", pageCount);
        pageInfo.put("flag", flag);
        pageInfo.put("pageIndex", pageIndex);
        pageInfo.put("type", type);
        pageInfo.put("customerList", customerList);

        return pageInfo;
    }

    /**
     * 通过id查找客户
     *
     * @param cid
     * @return
     */
    @Override
    public Customer findById(String cid) {
        return customerDao.findById(cid);
    }

    /**
     * 多条件组合查询
     *
     * @param customer
     * @return
     */
    @Override
    public List<Customer> seniorQuery(Customer customer) {
        return customerDao.seniorQuery(customer);
    }

    /**
     * 添加一个客户
     *
     * @param customer
     * @return
     */
    @Override
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }
}
