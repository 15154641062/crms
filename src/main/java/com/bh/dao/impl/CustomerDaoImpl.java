package com.bh.dao.impl;

import com.bh.dao.CustomerDao;
import com.bh.pojo.Customer;
import com.bh.utils.GetQueryRunnerByC3p0;
import com.bh.utils.GetUUID;
import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private QueryRunner queryRunner = GetQueryRunnerByC3p0.GetQueryRunner();     //通过自定义工具类获取QueryRunner

    /**
     * 查找所有客户
     *
     * @return
     */
    @Override
    public List<Customer> findAll() {
        String findAllSql = "select * from tb_customer where enable='0'";
        try {
            List<Customer> customerList = queryRunner.query(findAllSql, new BeanListHandler<Customer>(Customer.class));
            return customerList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 通过id删除客户（形式删除）
     *
     * @param cid
     * @return
     */
    @Override
    public int deleteById(String cid) {
        String deleteSql = "update tb_customer set enable='1' where cid=?";     //SQL
        Object param = cid;
        try {
            return queryRunner.update(deleteSql, param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Customer> paginateCustomer(int index, int perPage) {
        String querySql="select * from tb_customer limit ?,?";
        Object[] params= {index,perPage};
        try {
            List<Customer> customerList = queryRunner.query(querySql, new BeanListHandler<>(Customer.class),params);
            return customerList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public long getPageSum() {
        String querySql="select count(*) as pageCount from tb_customer";
        try {
            Long queryList = queryRunner.query(querySql, new ScalarHandler<>());
            return queryList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1L;
        }
    }

    /**
     * 多条件组合查询
     *
     * @param customer
     * @return
     */
    @Override
    public List<Customer> seniorQuery(Customer customer) {
        String querySql = "select * from tb_customer where enable='0' ";       //需拼接的SQL
        Object[] params = new Object[4];
        int paramCount = 0;     //判断非空的参数个数
        if (!customer.getCname().trim().equals("")) {
            querySql += "and cname like concat('%',?,'%') ";       //模糊查询
            params[paramCount++] = customer.getCname();
        }
        if (!customer.getGender().equals("")) {
            querySql += "and gender = ? ";
            params[paramCount++] = customer.getGender();
        }
        if (!customer.getCellphone().equals("")) {
            querySql += "and cellphone = ? ";
            params[paramCount++] = customer.getCellphone();
        }

        if (!customer.getEmail().equals("")) {
            querySql += "and email = ? ";
            params[paramCount++] = customer.getEmail();
        }
        Object[] param=new Object[paramCount];      //添加非空的参数到新数组对象
        for (int i=0;i<paramCount;i++){
            param[i]=params[i];
        }
        try {
            List<Customer> customers=null;          //查询结果保存在list集合中
            if (paramCount>0){
                customers = queryRunner.query(querySql, new BeanListHandler<>(Customer.class), param);
            }else{
                customers = queryRunner.query(querySql, new BeanListHandler<>(Customer.class));        //没有参数则不传param
            }
            return customers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 更新客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCustomer(Customer customer) {
        String updateSql = "update tb_customer set cname=?,gender=?," +
                "birthday=?,cellphone=?,email=?,description=? where cid=?";         //SQL
        Object[] params = {customer.getCname(), customer.getGender(), customer.getBirthday(),
                customer.getCellphone(), customer.getEmail(), customer.getDescription(), customer.getCid()};       //参数
        try {
            return queryRunner.update(updateSql, params);       //执行更新并返回受影响的行数
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;      //更新失败则返回-1
        }
    }

    /**
     * 根据id查找客户
     *
     * @param cid
     * @return
     */
    @Override
    public Customer findById(String cid) {
        String querySql = "select * from tb_customer where cid=? and enable='0'";       //SQL
        Object param = cid;         //参数
        try {
            Customer customer = queryRunner.query(querySql, new BeanHandler<>(Customer.class), param);       //调用QueryRunner执行查询
            return customer;        //返回结果对象
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 新增客户
     *
     * @param customer
     * @return
     */
    @Override
    public int addCustomer(Customer customer) {
        String insertSql = "insert into tb_customer values(?,?,?,?,?,?,?,?)";       //需执行的SQL语句
        Object[] params = {GetUUID.getUUID32(), customer.getCname(), customer.getGender(),
                customer.getBirthday(), customer.getCellphone(), customer.getEmail(), customer.getDescription(),"0"};      //拼装所需参数
        try {
            return queryRunner.update(insertSql, params);        //执行SQL
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;      //若发生异常则返回-1,表示失败
        }
    }
}
