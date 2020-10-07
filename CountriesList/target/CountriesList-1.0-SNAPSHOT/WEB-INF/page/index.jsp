<%--
  Created by IntelliJ IDEA.
  User: yuanmengfan
  Date: 2020/9/29
  Time: 12:05 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  isErrorPage="true" %>
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
        .margin0 {
            margin: 0px;
        }

        table td, table th {
            text-align: center;
        }

        #topTable tr td {
            line-height: 33px;
        }

        #selectBtnParent button{
            margin-top: 20px ;
        }

        strong {
            color: #ff9cb0;
        }
        #tableInfo table tr td:first-child,th{
            background-color: #dcedf6;
        }
        #listTable table tr td{
            text-align: left;
        }
        #listTable table tr td button a{
            color: white;
        }
        #listTable table tr td button a:hover
        {
            color: rgba(44,55,255,0.74);
        }

    </style>
    <script type="text/javascript">
        function showData(pageNum,pageSize){
            $.ajax({
                url:"${APP_PATH}/init",
                data:("pageNum="+pageNum+"&pageSize="+pageSize),
                type:"GET",
                success: function(result) {
                    var pageInfo = result.data.pageInfo;

                    var array = new Array(pageInfo.pageNum,pageInfo.pageSize,pageInfo.startRow,pageInfo.endRow,pageInfo.total,pageInfo.pages,1,pageInfo.prePage,pageInfo.nextPage,pageInfo.pages,pageInfo.isFirstPage,pageInfo.isLastPage,pageInfo.hasPreviousPage,pageInfo.hasNextPage);
                    $.each($("#tableInfo tr td:last-child"),function (index,item) {
                        $(this).empty().append(array[index]+"");
                    });

                    $("#listTable tbody").empty();
                    $.each(pageInfo.list,function(index,item){
                        var id = $("<td></td>").append(item.id);
                        var countryname = $("<td></td>").append(item.countryname);
                        var countrycode = $("<td></td>").append(item.countrycode);
                        var option = $("<td></td>").append(123);
                        $("<tr></tr>").append(id).append(countryname).append(countrycode).append(option).appendTo($("#listTable tbody"));
                    });

                    $("#pageMsg").empty().append("总记录<strong>"+pageInfo.total+"</strong>条,当前第<strong>"+pageInfo.pageNum+"</strong>页,总共<strong>"+pageInfo.pages+"</strong>页")


                    $("#pageNav ul").empty();
                    $("<li></li>").addClass(pageInfo.isFirstPage?"disabled":"").append($("<a></a>").attr("onclick",pageInfo.hasPreviousPage?"showData("+pageInfo.prePage+","+pageSize+")":"void(0)").append("<span aria-hidden='true'>&laquo;</span>")).appendTo($("#pageNav ul"))
                    $.each(pageInfo.navigatepageNums,function(index,item){
                        var li = $("<li></li>").append($("<a></a>").append(item).attr("onclick","showData("+item+","+pageSize+")"));
                        if(item == pageInfo.pageNum){
                            li.addClass("active");
                        }
                        li.appendTo($("#pageNav ul"));
                    });
                    $("<li></li>").addClass(pageInfo.isLastPage?"disabled":"").append($("<a></a>").attr("onclick",pageInfo.hasNextPage?"showData("+pageInfo.nextPage+","+pageSize+")":"void(0)").append("<span aria-hidden='true'>&raquo;</span>")).appendTo($("#pageNav ul"))
                }
            });
        }

        function sendAjaxToResult(url,countryname,countrycode,pageNum,pageSize){
            $.ajax({
                url: url,
                data:"countryname="+countryname+"&countrycode="+countrycode+"&pageNum="+pageNum+"&pageSize="+pageSize,
                success:function(result){
                    var pageInfo = result.data.pageInfo;
                    $("#inputPageNum").val(pageInfo.pageNum);

                    var array = new Array(pageInfo.pageNum,pageInfo.pageSize,pageInfo.startRow,pageInfo.endRow,pageInfo.total,pageInfo.pages,1,pageInfo.prePage,pageInfo.nextPage,pageInfo.pages,pageInfo.isFirstPage,pageInfo.isLastPage,pageInfo.hasPreviousPage,pageInfo.hasNextPage);
                    $.each($("#tableInfo tr td:last-child"),function (index,item) {
                        $(this).empty().append(array[index]+"");
                    });

                    $("#listTable tbody").empty();
                    $.each(pageInfo.list,function(index,item){
                        var id = $("<td></td>").append(item.id);
                        var countryname = $("<td></td>").append(item.countryname);
                        var countrycode = $("<td></td>").append(item.countrycode);
                        var option = $("<td></td>").attr("style","text-align:center;").append($("<button></button>").addClass("btn btn-info btn-xs").append("<a href='${APP_PATH}/toUpdate/"+item.id+"'>修改</a>")).append("&nbsp;&nbsp;&nbsp;").append($("<button class='deleteBtn'>删除</button>").addClass("btn btn-info btn-xs").attr("countryID",item.id));
                        $("<tr></tr>").append(id).append(countryname).append(countrycode).append(option).appendTo($("#listTable tbody"));
                    });

                    $("#pageMsg").empty().append("总记录<strong>"+pageInfo.total+"</strong>条,当前第<strong>"+pageInfo.pageNum+"</strong>页,总共<strong>"+pageInfo.pages+"</strong>页")

                    $("#pageNav ul").empty();
                    $("<li></li>").addClass(pageInfo.isFirstPage?"disabled":"").append($("<a></a>").on("click",function(){if(pageInfo.hasPreviousPage){sendAjaxToResult(url,countryname,countrycode,pageInfo.prePage,pageSize)}}).append("<span aria-hidden='true'>&laquo;</span>")).appendTo($("#pageNav ul"))
                    $.each(pageInfo.navigatepageNums,function(index,item){
                        var li = $("<li></li>").append($("<a></a>").append(item).on("click",function (){
                            sendAjaxToResult(url,countryname,countrycode,item,pageSize)
                        }));
                        if(item == pageInfo.pageNum){
                            li.addClass("active");
                        }
                        li.appendTo($("#pageNav ul"));
                    });
                    $("<li></li>").addClass(pageInfo.isLastPage?"disabled":"").append($("<a></a>").on("click",function(){if(pageInfo.hasNextPage){sendAjaxToResult(url,countryname,countrycode,pageInfo.nextPage,pageSize)}}).append("<span aria-hidden='true'>&raquo;</span>")).appendTo($("#pageNav ul"))
                }
            })
        }


        $(function () {
            $("#tableInfo tbody").hide();
            $("#showBody").click(function () {
                $("#tableInfo tbody").toggle()
            });
            sendAjaxToResult("${APP_PATH}/init","","",1,10);
            //设置提交按钮发送ajax请求
            $(document).on("click","#selectBtnParent button",function () {
                sendAjaxToResult("${APP_PATH}/init",$("#inputCountryName").val(),$("#inputCountryCode").val(),$("#inputPageNum").val(),$("#inputPageSize").val())
                // showData($("#inputPageNum").val(),$("#inputPageSize").val());
            });

            $(document).on("click","#listTable table thead tr th a[tempname]",function () {
                sendAjaxToResult("${APP_PATH}/sort/"+$(this).attr("tempname")+"/"+$(this).attr("sort"),$("#inputCountryName").val(),$("#inputCountryCode").val(),1,10);
                if($(this).attr("sort") == "asc"){
                    $(this).attr("sort","desc");
                }else{
                    $(this).attr("sort","asc");
                }
            })
           $(document).on("click",".deleteBtn",function () {
               var b = confirm("是否删除");
               if(b == true){
                  window.location.href="${APP_PATH}/delete/"+$(this).attr("countryID")
               }
           })

        });
    </script>
</head>
<body>
<div class="container">
    <div class="row" id="topTable">
        <form action="javascript:void(0)">
            <table class="col-sm-7 col-xs-8  table table-bordered table-hover">
                <thead>
                <tr>
                    <th colspan="5">国家(地区)列表</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td  class="col-sm-1">国家(地区)名称:</td>
                    <td class="col-sm-2">
                        <input type="text" name="countryname" id="inputCountryName" class="form-control" p>
                    </td>
                    <td class="col-sm-1">国家(地区)代码:</td>
                    <td class="col-sm-2">
                        <input type="text" name="countrycode" id="inputCountryCode"  class="form-control">
                    </td>
                    <td class="col-sm-1" id="selectBtnParent"  rowspan="2">
                        <button   class="btn btn-info btn-lg">查询</button>
                    </td>
                </tr>
                <tr>
                    <td class="col-sm-1">页码：</td>
                    <td class="col-sm-2"><input class="form-control" id="inputPageNum" name="pageNum" value="1">
                    </td>
                    <td class="col-sm-1">页面大小：</td>
                    <td class="col-sm-2"><input class="form-control" id="inputPageSize" name="pageSize" value="10"></td>
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
            <tbody>
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
                <td></td>
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

    <div class="row" id="listTable">
        <table class="table table-hover table-bordered">
            <thead>
            <tr>
                <th colspan="4">查询结果 - <a href="${APP_PATH}/toSave">[新增国家(地区)]</a></th>
            </tr>
            <tr>
                <th><a href="javascript:void(0)" tempname="id" sort="desc">id</a></th>
                <th><a href="javascript:void(0)" tempname="countryname" sort="asc">国家(地区)名</a></th>
                <th><a href="javascript:void(0)" tempname="countrycode" sort="asc">国家(地区)代码</a></th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>

    <div class="row">
        <div class="col-sm-offset-2">
            <span class="col-sm-6 h3 margin0" style="line-height: 46px" id="pageMsg">总记录<strong>${pageInfo.total}</strong>条,当前第<strong>${pageInfo.pageNum}</strong>页,总共<strong>${pageInfo.pages}</strong>页</span>
            <nav aria-label="Page navigation" id="pageNav">
                <ul class="pagination pagination-lg col-sm-6 margin0">

                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>