package com.bh.servlet;

import com.bh.pojo.Customer;
import com.bh.service.CustomerService;
import com.bh.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import xin.xihc.utils.common.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "AddCustomerServlet", urlPatterns = "/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    @Autowired
    private CustomerService customerService;      //自动注入CustomerService

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加参数
        Customer customer = new Customer();
        customer.setCname(request.getParameter("cname"));
        customer.setGender(request.getParameter("gender"));
        customer.setBirthday(DateFormat.toSqlDate(request.getParameter("birthday")));
        customer.setCellphone(request.getParameter("cellphone"));
        customer.setEmail(request.getParameter("email"));
        customer.setDescription(request.getParameter("description"));
        int flag = customerService.addCustomer(customer);       //调用业务层执行添加
        if (flag != -1) {
            String msg = "新增成功";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("msg.jsp").forward(request,response);      //成功后转发到msg页面
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
