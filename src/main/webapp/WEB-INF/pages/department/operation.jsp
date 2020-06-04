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
                <label for="name" class="layui-form-label">
                    <span class="x-red">*</span>部门名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" placeholder="请输入部门名称" lay-verify="name" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="desc" class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入介绍内容" id="desc" name="desc" class="layui-textarea"></textarea>
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

            //自定义验证规则
            form.verify({
                name: function (value) {
                    if (value.length < 1) {
                        return '部门名称不能为空';
                    }
                },
            });
            let url;
            <c:choose>
            <c:when test="${type=='add'}">
            url = '<%=request.getContextPath()%>/department/add';
            </c:when>
            <c:otherwise>
            url = '<%=request.getContextPath()%>/department/up';
            $("#id").val(parent.rowData.id);
            $("#name").val(parent.rowData.name);
            $("#desc").val(parent.rowData.desc);
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
                                parent.up(data.field);
                                if (res.code = 200) {
                                    //关闭当前frame
                                    xadmin.close();

                                    // 可以对父窗口进行刷新
                                    // xadmin.father_reload();
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
