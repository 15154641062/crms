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
@WebServlet(name = "PaginateCustomerServlet", urlPatterns = "/paginateQuery")
public class PaginateCustomerServlet extends HttpServlet {
    @Autowired
    private CustomerService customerService;      //自动注入CustomerService

    private static Customer customer = new Customer();        //存储customer信息
    private static boolean isFirstQuery=true;       //是否首次查询的标志

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
        String pageIndex = req.getParameter("pageIndex");     //获取页码
        String type = req.getParameter("type");       //获取查询类型
        HashMap pageInfo = null;

        if (type.equals("0")) {
            pageInfo = customerService.paginateInfo(Integer.parseInt(pageIndex));
        } else {
            isFirstQuery=(req.getParameter("isFirst")!=null&&req.getParameter("isFirst").equals("true"))?true:isFirstQuery;
            if (isFirstQuery){
                customer.setCname(req.getParameter("cname"));
                customer.setGender(req.getParameter("gender"));
                customer.setCellphone(req.getParameter("cellphone"));
                customer.setEmail(req.getParameter("email"));
                isFirstQuery=false;
            }
            pageInfo= customerService.paginateInfo(customer,Integer.parseInt(pageIndex));       //调用service层方法执行查询
        }

        List<Customer> customerList = (List<Customer>) pageInfo.get("customerList");

        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("list.jsp").forward(req, resp);      //成功后转发到list页面
    }
}
