package com.bh.dao.test;

import com.bh.dao.CustomerDao;
import com.bh.dao.impl.CustomerDaoImpl;
import com.bh.pojo.Customer;
import com.bh.utils.DateFormat;
import com.bh.utils.GetUUID;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class GenerateDataTest {
    CustomerDaoImpl customerDao =new CustomerDaoImpl();
    @Test
    public void toGenerate(){
        Customer customer=new Customer();
        for (int i=0;i<100;i++){
            customer.setCname("长江"+i+"号");
            customer.setGender(i%2==0?"男":"女");
            customer.setBirthday(DateFormat.toSqlDate("2020-12-"+(i%30)));
            customer.setEmail((1000000000+new Random().nextInt(2000000000-1000000000+1))+"@qq.com");
            customer.setCellphone((1000000000+new Random().nextInt(2000000000-1000000000+1))+i%10+"");
            customer.setDescription(i+"");
            customerDao.addCustomer(customer);
        }
    }
}
