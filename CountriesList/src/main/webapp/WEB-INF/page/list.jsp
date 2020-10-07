<%--
  Created by IntelliJ IDEA.
  User: yuanmengfan
  Date: 2020/9/29
  Time: 12:05 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        .margin0 {
            margin: 0px;
        }

        table td, table th {
            text-align: center;
        }

        #topTable tr td {
            line-height: 33px;
        }

        #selectBtn {
            line-height: 66px;
        }

        strong {
            color: #ff9cb0;
        }
        #tableInfo table tr td:first-child,th{
            background-color: #dcedf6;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#tableInfo tbody").hide();
            $("#showBody").click(function () {
                $("#tableInfo tbody").toggle()
            })
        })
    </script>
</head>
<body>
<div class="container">
    <div class="row" id="topTable">
        <form action="${APP_PATH}/getConditions">
            <table class="col-sm-7 col-xs-8  table table-bordered table-hover">
                <thead>
                <tr>
                    <th colspan="5">国家(地区)列表</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="col-sm-1">国家(地区)名称:</td>
                    <td class="col-sm-2">
                        <input type="text" name="countryname" value="${country.countryname}" class="form-control" p>
                    </td>
                    <td class="col-sm-1">国家(地区)代码:</td>
                    <td class="col-sm-2">
                        <input type="text" name="countrycode" value="${country.countrycode}" class="form-control">
                    </td>
                    <td class="col-sm-1" rowspan="2">
                        <button type="submit" id="selectBtn" class="btn btn-info btn-lg">查询</button>
                    </td>
                </tr>
                <tr>
                    <td class="col-sm-1">页码：</td>
                    <td class="col-sm-2"><input class="form-control" name="pageNum" value="${pageNum!=null?pageNum:1}">
                    </td>
                    <td class="col-sm-1">页面大小：</td>
                    <td class="col-sm-2"><input class="form-control" name="pageSize"
                                                value="${pageSize!=null?pageSize:10}"></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
    <div class="row" id="tableInfo">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th colspan="2">分页信息 - <a href="javascript:void(0)" id="showBody">[展开/收缩]</a></th>
            </tr>
            </thead>
            <tbody >
            <tr >
                <td>当前页号</td>
                <td>${pageInfo.pageNum}</td>
            </tr>
            <tr>
                <td>页面大小</td>
                <td>${pageInfo.pageSize}</td>
            </tr>
            <tr>
                <td>起始行号(>=)</td>
                <td>${pageInfo.startRow}</td>
            </tr>
            <tr>
                <td>终止行号(<=)</td>
                <td>${pageInfo.endRow}</td>
            </tr>
            <tr>
                <td>总结果数</td>
                <td>${pageInfo.total}</td>
            </tr>
            <tr>
                <td>总页数</td>
                <td>${pageInfo.pages}</td>
            </tr>
            <tr>
                <td>第一页</td>
                <td>1</td>
            </tr>
            <tr>
                <td>前一页</td>
                <td>${pageInfo.prePage}</td>
            </tr>
            <tr>
                <td>下一页</td>
                <td>${pageInfo.nextPage}</td>
            </tr>
            <tr>
                <td>最后一页</td>
                <td>${pageInfo.pages}</td>
            </tr>
            <tr>
                <td>是否为第一页</td>
                <td>${pageInfo.isFirstPage}</td>
            </tr>
            <tr>
                <td>是否为最后一页</td>
                <td>${pageInfo.isLastPage}</td>
            </tr>
            <tr>
                <td>是否有前一页</td>
                <td>${pageInfo.hasPreviousPage}</td>
            </tr>
            <tr>
                <td>是否有下一页</td>
                <td>${pageInfo.hasNextPage}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <table class="table table-hover table-bordered">
            <thead>
            <tr>
                <th><a href="${APP_PATH}/index/id" soft="asc">id</a></th>
                <th><a href="${APP_PATH}/index/countryname">国家(地区)名</a></th>
                <th><a href="${APP_PATH}/index/countrycode">国家(地区)代码</a></th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${states!=null}">
                <c:forEach items="${states}" var="state">
                    <tr>
                        <td>${state.id}</td>
                        <td>${state.countryname}</td>
                        <td>${state.countrycode}</td>
                        <td>123</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="row">
        <div class="col-sm-offset-4">
            <span class="col-sm-6 h4 margin0" style="line-height: 46px">总记录<strong>${pageInfo.total}</strong>条,当前第<strong>${pageInfo.pageNum}</strong>页,总共<strong>${pageInfo.pages}</strong>页</span>
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-lg col-sm-6 margin0">
                    <li class="${pageInfo.isFirstPage?"disabled":""}">
                        <a href="${APP_PATH}/getConditions?pageNum=${pageInfo.hasPreviousPage?pageInfo.prePage:pageNum}&pageSize=${pageSize}&countryname=${country.countryname}&countrycode=${country.countrycode}"  aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="currentPageNum">
                        <li class="${currentPageNum==pageInfo.pageNum?"active":""}">
                            <a href="${APP_PATH}/getConditions?pageNum=${currentPageNum}&pageSize=${pageSize}&countryname=${country.countryname}&countrycode=${country.countrycode}">${currentPageNum}</a>
                        </li>
                    </c:forEach>
                    <li class="${pageInfo.isLastPage?"disabled":""}">
                        <a href="${APP_PATH}/getConditions?pageNum=${pageInfo.hasNextPage?pageInfo.nextPage:pageNum}&pageSize=${pageSize}&countryname=${country.countryname}&countrycode=${country.countrycode}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>