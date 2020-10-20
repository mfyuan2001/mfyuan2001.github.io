<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    pageContext.setAttribute("APP_PATH", path);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <%--必须先映入Jquery文件--%>
    <script src="${APP_PATH}/static/js/jquery-1.11.0.js" type="text/javascript"></script>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>

    <script type="text/javascript">
        var pages;  //用于添加完数据之后跳转到最后一页
        var pageNum;    //用于修改完数据之后跳转到当前页
        $(function () {
            $.ajax({
                url: "${APP_PATH}/emps",
                data: "pn=1",
                type: "GET",
                success: function (result) {
                    //解析并显示表格信息
                    build_emp_table(result);
                    //解析并显示分页信息
                    build_page_info(result);
                    //解析并显示分页条数据
                    build_page_nav(result);
                }
            });

            //每次点击新增按钮都会清空modal中的数据再显示modal
            $("#btn_add").click(function () {
                $("#add_from")[0].reset();
                $("#add_Name").parent().parent().removeClass("has-error").removeClass("has-success");
                $("#name_span_glyphicon").removeClass("glyphicon-ok").removeClass("glyphicon-remove");
                $("#nameHelpBlock").text("");
                $("#add_Email").parent().parent().removeClass("has-error").removeClass("has-success");
                $("#email_span_glyphicon").removeClass("glyphicon-ok").removeClass("glyphicon-remove");
                $("#emailHelpBlock").text("");
                $("#add_Name").removeAttr("validate");
                $("#add_Email").removeAttr("validate");
                $("#add_Name").removeAttr("nameValue");
                /*
                 * 给modal中的保存按钮加上点击事件
                 * 先将其验证，
                 * 再把modal隐藏
                 * 再发送ajax请求，添加请求之后再发送一次查询请求
                 * */
                // $("#btn_save").on("click", add());
                $("#labelTitle").text("新建员工");
                $("#btn_commit").text("保存");
                $("#btn_commit").attr("onclick", "add()");
                $("#add_modal").modal({
                    backdrop: "static"
                });
            });

            //给输入name的输入框架添加keyup事件
            $("#add_Name").keyup(function () {
                var name = $("#add_Name").val();
                var regName = /^[a-z0-9\u2E80-\u9FFF_]{2,10}$/;
                if (regName.test(name)) {
                    $.ajax({
                        url: "${APP_PATH}/validate",
                        type: "GET",
                        data: "empName=" + $(this).val(),
                        success: function (result) {
                            if (result.code == 100) {
                                $("#add_Name").parent().parent().removeClass("has-error");
                                $("#add_Name").parent().parent().addClass("has-success");
                                $("#name_span_glyphicon").removeClass("glyphicon-remove");
                                $("#name_span_glyphicon").addClass("glyphicon-ok");
                                $("#nameHelpBlock").text("用户名可用");
                                $("#add_Name").attr({validate: "true"});
                            } else {
                                if ($("#add_Name").attr("nameValue") == name) {
                                    $("#add_Name").parent().parent().removeClass("has-error");
                                    $("#add_Name").parent().parent().addClass("has-success");
                                    $("#name_span_glyphicon").removeClass("glyphicon-remove");
                                    $("#name_span_glyphicon").addClass("glyphicon-ok");
                                    $("#nameHelpBlock").text("用户名可用");
                                    $("#add_Name").attr({validate: "true"});
                                    return;
                                }
                                $("#add_Name").parent().parent().removeClass("has-success");
                                $("#add_Name").parent().parent().addClass("has-error");
                                $("#name_span_glyphicon").removeClass("glyphicon-ok");
                                $("#name_span_glyphicon").addClass("glyphicon-remove");
                                $("#nameHelpBlock").text("用户名已存在")
                                $("#add_Name").removeAttr("validate");
                            }
                        }
                    })
                } else {
                    $("#add_Name").parent().parent().removeClass("has-success");
                    $("#add_Name").parent().parent().addClass("has-error");
                    $("#name_span_glyphicon").removeClass("glyphicon-ok");
                    $("#name_span_glyphicon").addClass("glyphicon-remove");
                    $("#nameHelpBlock").text("用户名格式不正确");
                    $("#add_Name").removeAttr("validate");
                }
            });
            //给输入email的输入框架添加keyup事件
            $("#add_Email").keyup(function () {
                var email = $("#add_Email").val();
                var regEmail = /^([0-9a-zA-Z_]{2,16})@([0-9a-zA-Z_]{2,10})((\.[0-9a-zA-Z_]+){1,3})$/;
                if (regEmail.test(email)) {
                    $("#add_Email").parent().parent().removeClass("has-error");
                    $("#add_Email").parent().parent().addClass("has-success");
                    $("#email_span_glyphicon").removeClass("glyphicon-remove");
                    $("#email_span_glyphicon").addClass("glyphicon-ok");
                    $("#emailHelpBlock").text("邮箱合法");
                    $("#add_Email").attr({validate: "true"});
                } else {
                    $("#add_Email").parent().parent().removeClass("has-success");
                    $("#add_Email").parent().parent().addClass("has-error");
                    $("#email_span_glyphicon").removeClass("glyphicon-ok");
                    $("#email_span_glyphicon").addClass("glyphicon-remove");
                    $("#emailHelpBlock").text("邮箱格式不正确");
                    $("#add_Email").removeAttr("validate");
                }
            });
            //显示下拉列表中的数据
            select_dept();

            $("#delete_modal_yes").click(function () {
                console.log($(this).attr("empId"));
                $("#delete_modal").modal('hide');
                $.ajax({
                    url: "${APP_PATH}/emps/" + $(this).attr("empId"),
                    type: "POST",
                    data: "_method=DELETE",
                    success: function () {
                        to_page(1);
                    }
                });
            });

        });


        //验证数据
        function validate_add_from() {
            if ($("#add_Name").attr("validate") == undefined) {
                return false;
            }
            if ($("#add_Email").attr("validate") == undefined) {
                return false;
            }
            return true;
        }

        //用ajax发送emps这个请求并重页面，
        function to_page(pn) {
            $.ajax({
                url: "${APP_PATH}/emps",
                data: "pn=" + pn,
                type: "GET",
                success: function (result) {
                    //解析并显示表格信息
                    build_emp_table(result);
                    //解析并显示分页信息
                    build_page_info(result);
                    //解析并显示分页条数据
                    build_page_nav(result);
                }
            })
        }


        //解析并显示表格信息
        function build_emp_table(result) {
            $("#host_table tbody").empty();
            var emps = result.extend.pageInfo.list;
            $.each(emps, function (index, item) {
                // empId":1,"empName":"c34bd0","gender":"F","email":"c34bd0@qq.com","dId":2,"department":{"id":2,"deptName":"测试部"
                var empId = $("<td></td>").append(item.empId);
                var empName = $("<td></td>").append(item.empName);
                var email = $("<td></td>").append(item.email);
                var gender = $("<td></td>").append(item.gender == "M" ? "男" : "女");
                var deptName = $("<td></td>").append(item.department.deptName);
                $("<tr></tr>")
                    .append("<td><input type='checkbox' class='checks'></td>")
                    .append(empId)      //id
                    .append(empName)    //name
                    .append(email)      //email
                    .append(gender)     //gender
                    .append(deptName)   //deptName
                    .append($("<td></td>")  //操作
                        .append($("<button></button>")          //编辑按钮
                            .attr("class", "btn btn-info btn-sm")
                            .append($("<span></span>")
                                .attr("class", "glyphicon glyphicon-pencil")
                                .attr("aria-hidden", "true")
                            )
                            .append("编辑")
                            .click(function () {
                                $("#add_Name").parent().parent().removeClass("has-error").removeClass("has-success");
                                $("#name_span_glyphicon").removeClass("glyphicon-ok").removeClass("glyphicon-remove");
                                $("#nameHelpBlock").text("");
                                $("#add_Email").parent().parent().removeClass("has-error").removeClass("has-success");
                                $("#email_span_glyphicon").removeClass("glyphicon-ok").removeClass("glyphicon-remove");
                                $("#emailHelpBlock").text("");
                                $("#add_Name").attr("validate", "true");
                                $("#add_Email").attr("validate", "true");
                                $("#btn_commit").attr("onclick", "update(" + item.empId + ")");
                                $("#labelTitle").text("修改员工");
                                $("#btn_commit").text("修改");
                                $.ajax({
                                    url: "${APP_PATH}/empById/" + item.empId,
                                    type: "GET",
                                    success: function (result) {
                                        //点击了编辑这个按钮之后立刻给到modal中的save点击事件设置成update这个函数
                                        var emp = result.extend.emp;
                                        $("#add_Name").val(emp.empName);
                                        $("#add_Name").attr("nameValue", emp.empName);
                                        $("#add_Email").val(emp.email);
                                        $("#add_from input[type=radio]").val([emp.gender]);
                                        $("#add_from select").val(item.dId)
                                        // if (emp.gender == "M") {
                                        //     $("#add1_Gender").prop("checked", "checked");
                                        // } else {
                                        //     $("#add2_Gender").prop("checked", "checked");
                                        // }
                                        // $("#add_deptName option[value=" + item.dId + "]").prop("selected", "selected");
                                        $("#add_modal").modal({
                                            backdrop: "static"
                                        });
                                    }
                                })
                            })
                        )
                        .append(" ")
                        .append($("<button></button>")         //删除按钮
                            .attr("class", "btn btn-danger btn-sm")
                            .append($("<span></span>")
                                .attr("class", "glyphicon glyphicon-pencil")
                                .attr("aria-hidden", "true")
                            )
                            .append("删除")
                            .click(function () {
                                $("#delete_modal p").text("是否删除名为< " + item.empName + " >员工");
                                $("#delete_modal").modal({
                                    backdrop: "static"
                                });
                                $("#delete_modal_yes").attr("empId", item.empId);
                            })
                        )
                    )
                    .appendTo($("#host_table tbody"));
            });
        }

        //解析并显示分页条数据
        //保存相关的页面信息
        function build_page_nav(result) {
            this.pages = result.extend.pageInfo.pages;

            this.pageNum = result.extend.pageInfo.pageNum;

            $("#page_info").empty();
            var s = "当前第" + result.extend.pageInfo.pageNum +
                "页,共有" + result.extend.pageInfo.pages +
                "页,总计" + result.extend.pageInfo.total + "条记录";
            $("#page_info").append(s);
        }

        //解析并显示分页信息
        function build_page_info(result) {
            $("#page_nav").empty();
            var ul = $("<ul></ul>").attr("class", "pagination");
            //是否有上一页
            var first_class = result.extend.pageInfo.isFirstPage ? "disabled" : "";
            //是否有下一页
            var last_class = result.extend.pageInfo.isLastPage ? "disabled" : "";
            //最后一页
            var pages = result.extend.pageInfo.pages;
            //当前页
            var pageNum = result.extend.pageInfo.pageNum;

            //上一页这个按钮
            var previousPage = result.extend.pageInfo.hasPreviousPage ? $("<li></li>")
                .append($("<a></a>")
                    .append($("<span></span>")
                        .attr("aria-label", "true")
                        .append("&laquo;")
                    )
                    .attr("onclick", "to_page(" + (pageNum - 1) + ")")
                    .attr("aria-label", "Previous")
                ) : "";
            //下一页这个按钮
            var nextPage = result.extend.pageInfo.hasNextPage ? $("<li></li>")
                .append($("<a></a>")
                    .append($("<span></span>")
                        .attr("aria-label", "true")
                        .append("&raquo;")
                    )
                    .attr("onclick", "to_page(" + (pageNum + 1) + ")")
                    .attr("aria-label", "Next")
                ) : "";
            //首页按钮
            var firstPage = $("<li></li>")
                .append($("<a></a>")
                    .attr("onclick", "to_page(1)")
                    .append("首页")
                )
                .attr("class", first_class);
            //尾页按钮
            var lastPage = $("<li></li>")
                .append($("<a></a>")
                    .attr("onclick", "to_page(" + pages + ")")
                    .append("尾页")
                )
                .attr("class", last_class);
            ul.append(firstPage).append(previousPage);
            $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
                //是否是按钮显示的当前页，档期页提示高亮
                var btn_class = item == result.extend.pageInfo.pageNum ? "active" : "";
                var li = $("<li></li>")
                    .append($("<a></a>")
                        .attr("onclick", "to_page(" + item + ")")
                        .append(item)
                    )
                    .attr("class", btn_class);
                ul.append(li);
            });
            $("<nav></nav>")
                .attr("aria-label", "Page navigation")
                .append(ul.append(nextPage).append(lastPage))
                .appendTo($("#page_nav"));
        }

        //发送ajax请求访问depts这个映射，并重载modal中的下拉列表
        function select_dept() {
            $.ajax({
                url: "${APP_PATH}/depts",
                type: "GET",
                success: function (result) {
                    // console.log(result)
                    $.each(result.extend.depts, function (index, item) {
                        $("<option></option>")
                            .append(item.deptName)
                            .attr("value", item.id).appendTo($("#add_deptName"));
                    })
                }
            });
        }


        /**
         * add()添加员工操作
         * 验证数据
         * 隐藏modal
         * 发送ajax请求
         * 请求拿到返回后再次发送ajax请求到最后一页
         */
        function add() {
            if (!validate_add_from()) {
                return;
            }
            $.ajax({
                url: "${APP_PATH}/emps",
                type: "POST",
                //serialize() 把from表单中间的数据里字符串的形式返回
                data: $("#add_from").serialize(),
                success: function (result) {
                    //如果是错误的我们先判断错误信息
                    if (result.code == 100) {
                        $("#add_modal").modal('hide');
                        to_page(pages + 1);
                    } else {
                        if ($.each(result.extend.errors.empName != undefined)) {
                            // alert(result.extend.errors.empName)
                            $("#nameHelpBlock").text(result.extend.errors.empName);
                        }
                        if ($.each(result.extend.errors.empName1 != undefined)) {
                            // alert(result.extend.errors.empName1)
                            $("#nameHelpBlock").text(result.extend.errors.empName1);
                        }
                        if ($.each(result.extend.errors.email != undefined)) {
                            // alert(result.extend.errors.email)
                            $("#emailHelpBlock").text(result.extend.errors.email);
                        }
                    }
                }
            });
        }

        /**
         * update(empId)修改员工操作
         * 验证数据
         * 隐藏modal
         * 发送ajax请求 参数带上_method 跟传入的empId 便于服务器能够识别是put请求跟哪个id修改员工数据
         * 请求拿到返回后再次发送ajax请求到最后一页
         */
        function update(empId) {
            if (!validate_add_from()) {
                return;
            }
            console.log("_method=PUT&empId=" + empId + "&" + $("#add_from").serialize());
            console.log(pageNum);
            $.ajax({
                url: "${APP_PATH}/emps",
                type: "PUT",
                data: "empId=" + empId + "&" + $("#add_from").serialize(),
                // type: "POST",
                // data: "_method=PUT&empId=" + empId + "&" + $("#add_from").serialize(),
                success: function (result) {
                    if (result.code == 100) {
                        $("#add_modal").modal('hide');
                        to_page(pageNum);
                    } else {
                        if ($.each(result.extend.errors.empName != undefined)) {
                            // alert(result.extend.errors.empName)
                            $("#nameHelpBlock").text(result.extend.errors.empName);
                        }
                        if ($.each(result.extend.errors.email != undefined)) {
                            // alert(result.extend.errors.email)
                            $("#emailHelpBlock").text(result.extend.errors.email);
                        }
                    }
                }
            });

        }

        $(document).on("click", "#check_id", function () {
            $(".checks").prop("checked", $(this).prop("checked"));

        });
        $(document).on("click", ".checks", function () {
            $("#check_id").prop("checked", $(".checks:checked").length == $(".checks").length);

        });

        $(document).on("click", "#btn_delete", function () {
            if ($(".checks:checked").length > 0) {
                var empName = "";
                var empId = ""
                $.each($(".checks:checked"), function () {
                    empId += $(this).parents("tr").find("td:eq(1)").text() + "-";
                    empName += $(this).parents("tr").find("td:eq(2)").text() + ",";
                });

                if (confirm("是否删除< " + empName.substring(0, empName.length - 1) + " >员工")) {
                    $.ajax({
                        url: "${APP_PATH}/emps/" + empId.substring(0, empId.length - 1),
                        type: "POST",
                        data: "_method=DELETE",
                        success: function (result) {
                            to_page(1);
                        }
                    })
                }
                ;
            }

        })
    </script>
</head>
<body>
<!-- 动态模态框 1  -->
<div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="labelTitle">新建员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="add_from">
                    <div class="form-group  has-feedback">
                        <label for="add_Name" class="col-sm-2 control-label ">Name</label>
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" name="empName" class="form-control" id="add_Name"
                                       placeholder="xiaochen">
                            </div>
                            <span class="glyphicon <%--glyphicon-ok--%> form-control-feedback" aria-hidden="true"
                                  id="name_span_glyphicon"></span>
                            <%--                            <span id="inputGroupSuccessNameStatus" class="sr-only">(success)</span>--%>
                            <span id="nameHelpBlock" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group  has-feedback">
                        <label for="add_Email" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-envelope"></span></span>
                                <input type="text" name="email" class="form-control" id="add_Email"
                                       placeholder="xiaochen@qq.com">
                            </div>
                            <span class="glyphicon <%--glyphicon-ok--%> form-control-feedback" aria-hidden="true"
                                  id="email_span_glyphicon"></span>
                            <%--                            <span id="inputGroupSuccessEmailStatus" class="sr-only">1111</span>--%>
                            <span id="emailHelpBlock" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="add1_Gender" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="add2_Gender" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-briefcase"></span></span>
                                <select class="form-control" id="add_deptName" name="dId">
                                </select>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn_commit">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 动态模态框 2-->
<div class="modal fade" id="delete_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="deleteLabel">删除员工</h4>
            </div>
            <div class="modal-body">
                <p>是否删除员工</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">否</button>
                <button type="button" class="btn btn-danger" id="delete_modal_yes">是</button>
            </div>
        </div>
    </div>
</div>
<%--搭建网页--%>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h2>SSM-CRUD<small>Simple test</small></h2>
        </div>
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="btn_add">新增</button>
            <button class="btn btn-danger" id="btn_delete">删除</button>
        </div>
    </div>
    <%--显示表格--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped" id="host_table">
                <thead>
                <tr>
                    <th><input type="checkbox" id="check_id"></th>
                    <th>id</th>
                    <th>lastName</th>
                    <th>email</th>
                    <th>gender</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%--显示分页文字信息--%>
        <div class="col-md-6" id="page_info">
        </div>
        <%--显示分页条--%>
        <div class="col-md-6" id="page_nav">

        </div>
    </div>
</div>
</body>
</html>