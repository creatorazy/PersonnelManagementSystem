<%--
  Created by IntelliJ IDEA.
  User: azy
  Date: 2020/5/20
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
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
                    <span class="x-red">*</span>角色名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" placeholder="请输入角色名称" lay-verify="name"
                           autocomplete="off"
                           class="layui-input">
                    <br>
                    <span id="msg" class="x-red"></span>
                </div>

            </div>

            <div class="layui-form-item">
                <label for="details" class="layui-form-label">
                    <span class="x-red">*</span>角色描述
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="details" name="details" placeholder="请输入角色描述"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item" pane="">
                <label class="layui-form-label">角色权限</label>
                <div class="layui-input-block" id="permission">

                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="" id="btn">增加</button>
            </div>

        </form>
    </div>
</div>
<script id="getPermission" type="text/html">
    <input type="checkbox" name="perms" value="{{d.id}}" lay-skin="primary" title="{{d.details}}">
</script>
<script>

    layui.use(['form', 'layer', 'jquery', 'laytpl'],
        function () {
            $ = layui.jquery;
            var form = layui.form, layer = layui.layer;
            let laytpl = layui.laytpl;
            layer.load(2);
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/permission/all',
                success: function (res) {
                    let html = "";
                    $.each(res.rows, function (index, obj) {
                        //直接解析字符
                        laytpl($('#getPermission').html()).render(obj
                            , function (string) {
                                html += string;
                            });
                    });
                    $("#permission").html(html);
                    $("input[name='perms']").each(function (i, obj) {
                        let $inp = $(obj);
                        if (typeof parent.rowData != 'undefined') {
                            $.each(parent.rowData.permissions, function () {
                                if (this.id == $inp.val()) {
                                    $inp.prop("checked", true);
                                }
                            });
                        }
                    });
                    form.render('checkbox');
                    layer.closeAll('loading');
                }
            })

            $("#name").blur(function () {
                if ($("#name").val() != "" && typeof (parent.rowData) != 'undefined' && $("#name").val() != parent.rowData.name) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/role/isExist',
                        type: 'get',
                        data: {
                            name: $("#name").val()
                        },
                        success: function (res) {
                            if (res) {
                                $("#msg").text("角色名称已经被使用");
                            } else {
                                $("#msg").text("");
                            }
                            layer.closeAll('loading');
                        },
                        error: function (res) {
                            layer.msg("请检查网络是否连接")
                            layer.closeAll('loading');
                        }
                    });
                }
            })
            //自定义验证规则
            form.verify({
                name: function (value) {
                    if (value.length < 1) {
                        return '角色名称不能为空';
                    }
                    if ($("#msg").text() != "") {
                        return '角色名称已经被使用';
                    }
                }
            });
            let url;
            <c:choose>
            <c:when test="${type=='add'}">
            url = '<%=request.getContextPath()%>/role/add';
            </c:when>
            <c:otherwise>
            url = '<%=request.getContextPath()%>/role/up';
            $("#id").val(parent.rowData.id);
            $("#name").val(parent.rowData.name);
            $("#details").val(parent.rowData.details);
            $("#btn").text("更新信息");
            </c:otherwise>
            </c:choose>
            //监听提交
            form.on('submit(add)',
                function (data) {
                    layer.load(2);
                    let checkID = [];
                    $("input[name='perms']:checked").each(function (i) {
                        checkID[i] = $(this).val();
                    });
                    data.field.perms = checkID;
                    $.ajax({
                        type: 'post',
                        url: url,
                        data: data.field,
                        traditional: true,
                        success: function (res) {
                            layer.alert(res.msg, {icon: 6}, function () {
                                if (res.code = 200) {
                                    <c:if test="${type=='up'}">
                                    parent.up(data.field);
                                    //关闭当前frame
                                    </c:if>
                                    xadmin.close();
                                    xadmin.father_reload();
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
