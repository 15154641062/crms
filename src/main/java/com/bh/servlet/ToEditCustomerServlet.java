package com.bh.servlet;

import com.bh.pojo.Customer;
import com.bh.service.CustomerService;
import com.bh.utils.DateFormat;
import org.apache.tomcat.util.json.JSONParserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet--通过id查询客户
 */
@WebServlet(name = "ToEditCustomerServlet", urlPatterns = "/edit")
public class ToEditCustomerServlet extends HttpServlet {
    @Autowired
    private CustomerService customerService;      //自动注入CustomerService

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");     //获取前端传过来的cid
        Customer customer = customerService.findById(cid);
        req.setAttribute("customer",customer);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }
}
