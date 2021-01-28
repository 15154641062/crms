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

/**
 * servlet--更新一个客户信息
 */
@WebServlet(name = "EditSubmitServlet", urlPatterns = "/updateCustomer")
public class EditSubmitServlet extends HttpServlet {
    @Autowired
    private CustomerService customerService;      //自动注入CustomerService

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加参数
        Customer customer = new Customer();
        customer.setCid(request.getParameter("cid"));
        customer.setCname(request.getParameter("cname"));
        customer.setGender(request.getParameter("gender"));
        customer.setBirthday(DateFormat.toSqlDate(request.getParameter("birthday")));
        customer.setCellphone(request.getParameter("cellphone"));
        customer.setEmail(request.getParameter("email"));
        customer.setDescription(request.getParameter("description"));

        int flag = customerService.updateCustomer(customer);       //调用业务层执行添加
        if (flag != -1) {
            String msg = "更新成功";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("msg.jsp").forward(request,response);      //成功后转发到msg页面
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
