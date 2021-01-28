package com.bh.servlet;

import com.bh.pojo.Customer;
import com.bh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * servlet--多条件组合查询
 */
@WebServlet(name = "QueryCustomerServlet", urlPatterns = "/queryCustomer")
public class QueryCustomerServlet extends HttpServlet {
    @Autowired
    private CustomerService customerService;      //自动注入CustomerService

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();         //获取参数
        customer.setCname(req.getParameter("cname"));
        customer.setGender(req.getParameter("gender"));
        customer.setCellphone(req.getParameter("cellphone"));
        customer.setEmail(req.getParameter("email"));

        List<Customer> customerList = customerService.seniorQuery(customer);       //调用service层方法执行查询
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("list.jsp").forward(req,resp);      //成功后转发到list页面
    }
}
