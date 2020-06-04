<%--
  Created by IntelliJ IDEA.
  User: azy
  Date: 2020/5/20
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>人事管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/xadmin.css">
    <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
    <script src="<%=request.getContextPath()%>/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input type="hidden" id="id" name="id" value="">
            <div class="layui-form-item">
                <label for="loginName" class="layui-form-label">
                    <span class="x-red">*</span>登陆名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="loginName" name="loginName" placeholder="请输入登陆名" lay-verify="loginName"
                           autocomplete="off"
                           class="layui-input">
                    <br>
                    <span id="msg" class="x-red"></span>
                </div>

            </div>

            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">
                    <span class="x-red">*</span>用户名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="userName" name="userName" placeholder="请输入用户名" lay-verify="userName"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="password" class="layui-form-label">
                    <span class="x-red">*</span>密码
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="password" name="password" placeholder="请输入密码" lay-verify="password"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="state" class="layui-form-label">
                    用户状态
                </label>
                <div class="layui-input-inline">
                    <input type="checkbox" id="state" name="state" value="0" title="锁定">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="" id="btn">增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer', 'jquery'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;
            $("#loginName").blur(function () {
                if ($("#loginName").val() != "" && (typeof(parent.rowData)=='undefined' || $("#loginName").val() != parent.rowData.loginName)) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/admin/isExist',
                        type: 'get',
                        data: {
                            name: $("#loginName").val()
                        },
                        success: function (res) {
                            if (res) {
                                $("#msg").text("登陆名称已经被使用");
                            } else {
                                $("#msg").text("");
                            }
                        },
                        error: function (res) {
                            layer.msg("请检查网络是否连接")
                        }
                    });
                }else{
                    $("#msg").text("");
                }
            })
            //自定义验证规则
            form.verify({
                loginName: function (value) {
                    if (value.length < 1) {
                        return '登陆名不能为空';
                    }
                    if ($("#msg").text() != "") {
                        return '登陆名已经被使用';
                    }
                },
                userName: function (value) {
                    if (value.length < 1) {
                        return '用户名不能为空';
                    }
                },
                password: function (value) {
                    if (value.length < 6) {
                        return '密码长度不能小于6位';
                    }
                },
            });
            let url;
            <c:choose>
            <c:when test="${type=='add'}">
            url = '<%=request.getContextPath()%>/admin/add';
            </c:when>
            <c:otherwise>
            url = '<%=request.getContextPath()%>/admin/up';
            $("#id").val(parent.rowData.id);
            $("#loginName").val(parent.rowData.loginName);
            $("#userName").val(parent.rowData.userName);
            $("#password").val(parent.rowData.password);
            if (parent.rowData.state == 0) {
                $("#state").next().click();
            }
            $("#btn").text("更新信息");
            </c:otherwise>
            </c:choose>
            //监听提交
            form.on('submit(add)',
                function (data) {
                    layer.load(2);
                    $.ajax({
                        url: url,
                        data: data.field,
                        success: function (res) {
                            layer.alert(res.msg, {icon: 6}, function () {
                                if (res.code = 200) {
                                    <c:if test="${type=='up'}">
                                    parent.up(data.field);
                                    //关闭当前frame
                                    </c:if>
                                    xadmin.close();
                                    // 可以对父窗口进行刷新
                                    <c:if test="${type=='add'}">
                                    xadmin.father_reload();
                                    </c:if>
                                }
                                layer.closeAll('loading');
                            });
                        },
                        error: function (res) {
                            layer.msg("请检查网络是否连接")
                            layer.closeAll('loading');
                        }
                    });
                    return false;
                });

        });
</script>
</body>
</html>
