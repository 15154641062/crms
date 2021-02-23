<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>客户列表</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript">
        <%--var perPage=${pageInfo.perPage};	//每页显示多少数据--%>
        <%--var pageIndex=${pageInfo.pageIndex};		//当前页--%>
        <%--var pageSum=${pageInfo.pageSum};	//记录总数--%>
        <%--var pageCount=${pageInfo.pageCount};	//分页总数--%>
        <%--var flag=${pageInfo.flag<pageInfo.pageCount?pageInfo.flag:pageInfo.pageCount};		//显示的分页数--%>
    </script>
</head>

<body>
<%
    HashMap pageInfo = (HashMap) request.getAttribute("pageInfo");
    int perPage = (int) pageInfo.get("perPage");    //每页显示多少数据
    int pageIndex = (int) pageInfo.get("pageIndex");        //当前页
    int pageSum = (int) pageInfo.get("pageSum");    //记录总数
    int pageCount = (int) pageInfo.get("pageCount");    //分页总数
    int flag = (int) pageInfo.get("flag");        //最大显示的分页数
    int showPage = flag > pageCount ? pageCount : flag;        //当前分页数
%>
<h3 align="center">客户列表</h3>
<table border="1" width="70%" align="center">
    <tr>
        <th>客户姓名</th>
        <th>性别</th>
        <th>生日</th>
        <th>手机</th>
        <th>邮箱</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${customerList}" var="customer">
        <tr>
            <td>${customer.cname}</td>
            <td>${customer.gender}</td>
            <td>${customer.birthday}</td>
            <td>${customer.cellphone}</td>
            <td>${customer.email}</td>
            <td>${customer.description}</td>
            <td>
                <a href="<c:url value='/edit?cid=${customer.cid}'/>">编辑</a>
                <a href="<c:url value='/deleteCustomer?cid=${customer.cid}'/>">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h3 align="center">
    <table align="center">
        <tr>
            <td>
                <a href="/paginateCustomer?pageIndex=1">首页</a>
            </td>
            <td>
                <c:if test="<%=pageIndex>1%>">
                    <a href="/paginateCustomer?pageIndex=<%=pageIndex-1%>">上一页</a>
                </c:if>
            </td>
            <c:if test="<%=pageIndex-flag<0%>">
                <c:forEach begin="1" end="<%=flag%>" var="index">
                    <td>
                        <form action="/paginateCustomer" method="post">
                            <input type="submit" name="pageIndex" value="${index}">
                        </form>
                    </td>
                </c:forEach>
            </c:if>

            <c:if test="<%=pageCount-pageIndex<flag%>">
                <c:forEach begin="<%=pageCount-flag+1%>" end="<%=pageCount%>" var="index">
                    <td>
                        <form action="/paginateCustomer" method="post">
                            <input type="submit" name="pageIndex" value="${index}">
                        </form>
                    </td>
                </c:forEach>
            </c:if>

            <c:if test="<%=pageIndex-flag>=0&&pageCount-pageIndex>=flag%>">
                <c:forEach begin="<%=pageIndex-flag/2>1?pageIndex-flag/2:1%>"
                           end="<%=pageIndex+flag/2<pageCount?pageIndex+flag/2:pageCount%>" var="index">
                    <td>
                        <form action="/paginateCustomer" method="post">
                            <input type="submit" name="pageIndex" value="${index}">
                        </form>
                    </td>
                </c:forEach>
            </c:if>

            <td>
                <c:if test="<%=pageIndex<pageCount%>">
                    <a href="/paginateCustomer?pageIndex=<%=pageIndex+1%>">下一页</a>
                </c:if>
            </td>
            <td>
                <label>&nbsp;&nbsp;&nbsp;第<%=pageIndex%>页,共<%=pageCount%>页</label>
            </td>
        </tr>
    </table>
</h3>
</body>
</html>
