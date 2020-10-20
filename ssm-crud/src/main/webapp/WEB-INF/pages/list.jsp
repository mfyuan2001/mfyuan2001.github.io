<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    pageContext.setAttribute("APP_PATH", path);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>员工列表</title>
    <!-- Web路径：
    不以/开始的相对路径找资源，以当前资源的路径为基准，经常容易出问题
    以/开始的相对路径，找资源，以服务器的路径为标准 需要加上项目名
        http:localhost:8080/crud
    -->
    <%--引入样式--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${APP_PATH}/static/js/jquery-1.11.0.js" type="text/javascript"></script>
</head>
<body>
<%--搭建网页--%>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h2>SSM-CRUD<small>Simple test</small></h2>
        </div>
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--显示表格--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <tr>
                    <th>#</th>
                    <th>lastName</th>
                    <th>email</th>
                    <th>gender</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="e">
                    <tr>
                        <td>${e.empId}</td>
                        <td>${e.empName}</td>
                        <td>${e.gender eq "M"?"男":"女"}</td>

                        <td>${e.email}</td>
                        <td>${e.department.deptName}</td>
                        <td>
                            <button class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%--显示分页文字信息--%>
        <div class="col-md-6">
            当前第${pageInfo.pageNum}页,共有${pageInfo.pages}页,总计${pageInfo.total} 条记录
        </div>
        <%--显示分页条--%>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="${pageInfo.isIsFirstPage()? "disabled":""}">
                        <a href="${APP_PATH}/getEmps?pn=1">首页</a>
                    </li>
                    <c:if test="${not pageInfo.isIsFirstPage()}">
                        <li>
                            <a href="${APP_PATH}/getEmps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="i">
                        <li class="${i eq pageInfo.pageNum ?"active":""}"><a href="${APP_PATH}/getEmps?pn=${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${not pageInfo.isIsLastPage()}">
                        <li>
                            <a href="${APP_PATH}/getEmps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <li class="${pageInfo.isIsLastPage()? "disabled":""}"><a
                            href="${APP_PATH}/getEmps?pn=${pageInfo.pages}">尾页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>