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
import java.util.HashMap;
import java.util.List;

/**
 * servlet--分页查找客户
 */
@WebServlet(name = "PaginateCustomerServlet", urlPatterns = "/paginateCustomer")
public class PaginateCustomerServlet extends HttpServlet {
    @Autowired
    private CustomerService customerService;      //自动注入CustomerService

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Customer> customerList = customerService.findAll();
//        request.setAttribute("customerList", customerList);
//        request.getRequestDispatcher("list.jsp").forward(request,response);      //成功后转发到list页面
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageIndex=req.getParameter("pageIndex");
        HashMap pageInfo = customerService.paginateInfo(Integer.parseInt(pageIndex));
        List<Customer> customerList= (List<Customer>) pageInfo.get("customerList");

        req.setAttribute("pageInfo",pageInfo);
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("list.jsp").forward(req,resp);      //成功后转发到list页面
    }
}
