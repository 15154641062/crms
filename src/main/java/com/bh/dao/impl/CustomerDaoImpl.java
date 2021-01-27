package com.bh.dao.impl;

import com.bh.dao.CustomerDao;
import com.bh.pojo.Customer;
import com.bh.utils.GetQueryRunnerByC3p0;
import com.bh.utils.GetUUID;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private QueryRunner queryRunner= GetQueryRunnerByC3p0.GetQueryRunner();     //通过自定义工具类获取QueryRunner

    @Override
    public List<Customer> findAll() {
        String findAllSql="select * from tb_customer";
        try {
            List<Customer> customerList=queryRunner.query(findAllSql, new BeanListHandler<Customer>(Customer.class));
            return customerList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 新增客户
     * @param customer
     * @return
     */
    @Override
    public int addCustomer(Customer customer) {
        String insertSql="insert into tb_customer values(?,?,?,?,?,?,?)";       //需执行的SQL语句
        Object[] params={GetUUID.getUUID32(),customer.getCname(),customer.getGender(),
                customer.getBirthday(),customer.getCellphone(),customer.getEmail(),customer.getDescription()};      //拼装所需参数
        try {
            return queryRunner.update(insertSql,params);        //执行SQL
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;      //若发生异常则返回-1,表示失败
        }
    }
}
