<%--
  Created by IntelliJ IDEA.
  User: yuanmengfan
  Date: 2020/9/26
  Time: 4:36 下午
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Insert title here</title>
    <script src="${APP_PATH}/static/js/jquery-1.11.0.js" type="text/javascript"></script>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <style type="text/css">
        #optionTable tr td {
            line-height: 33px;
        }

        #optionTable tr th {
            background-color: #dcedf6;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <form action="${APP_PATH}/save" method="POST">

            <c:if test="${country !=null}">
                <input type="hidden" name="_method" value="PUT">
            </c:if>
            <table class="table table-bordered" id="optionTable">
                <input type="hidden" name="id" value="${country.id}"/>
                <thead>
                <tr>
                    <th colspan="5">国家(地区)信息 - <a href="${APP_PATH}/hello">[返回]</a></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>国家(地区)名称：</td>
                    <td><input class="form-control" name="countryname" value="${country.countryname}" type="text"></td>
                    <td>国家(地区)代码：</td>
                    <td><input class="form-control" name="countrycode" value="${country.countrycode}" type="text"></td>
                    <td>
                        <button class="btn btn-info" type="submit">保存</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>

    </div>
</div>
</body>
</html>