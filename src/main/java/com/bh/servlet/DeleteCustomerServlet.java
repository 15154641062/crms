package com.bh.servlet;

import com.bh.pojo.Customer;
import com.bh.service.CustomerService;
import com.bh.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet--删除一个客户(形式删除)
 */
@WebServlet(name = "DeleteCustomerServlet", urlPatterns = "/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
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
        String cid = req.getParameter("cid");
        int flag = customerService.deleteById(cid);       //调用service层方法执行删除
        if (flag != -1) {
            String msg = "删除成功";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("msg.jsp").forward(req, resp);      //成功后转发到msg页面
        }
    }
}
