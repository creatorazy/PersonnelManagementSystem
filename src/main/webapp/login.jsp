<%--
  Created by IntelliJ IDEA.
  User: azy
  Date: 2020/5/15
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty admin}">
    <c:redirect url="index"></c:redirect>
</c:if>
<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>人事管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">人事管理系统 Personnel Management System</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form">
        <input name="loginName" placeholder="用户名" type="text" lay-verify="loginName" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="password" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
</div>

<script>
    $(function () {
        layui.use('form', function () {
            var form = layui.form;
            //表单验证
            form.verify({
                loginName: function (value, item) { //value：表单的值、item：表单的DOM对象
                    if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                        return '用户名不能为空且不能有特殊字符!';
                    }
                    if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                        return '用户名首尾不能出现下划线\'_\'';
                    }
                    if (/^\d+$/.test(value)) {
                        return '用户名不能全为数字';
                    }
                }
                //我们既支持上述函数式的方式，也支持下述数组的形式
                //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
                ,
                password: [
                    /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
                ]
            });

            //监听提交
            form.on('submit(login)', function (data) {
                //开启等待提示
                layer.load(2);
                //禁用登陆按钮
                $('input[type=submit]').prop('disabled', true);
                $.ajax({
                    url: '<%=request.getContextPath()%>/admin/login',
                    type: 'post',
                    data: data.field,
                    success: function (resp) {
                        if (resp.flag == 0) {
                            location.href = 'index';
                        } else {
                            //关闭等待提示
                            layer.closeAll('loading');
                            layer.msg(resp.msg);
                        }
                        //解除禁用登陆按钮
                        $('input[type=submit]').prop('disabled', false);
                    },
                    error: function () {
                        //关闭等待提示
                        layer.closeAll('loading');
                        layer.msg('登陆失败');
                        //解除禁用登陆按钮
                        $('input[type=submit]').prop('disabled', false);
                    }
                });

                return false;
            });
        });
    })
</script>
</body>
</html>
