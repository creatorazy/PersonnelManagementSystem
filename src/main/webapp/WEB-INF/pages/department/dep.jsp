<%--
  Created by IntelliJ IDEA.
  User: azy
  Date: 2020/5/20
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
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
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">部门管理</a>
            <a>
              <cite>部门管理</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-inline layui-show-xs-block">
                        <input type="text" id="name" name="name" placeholder="部门名称" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-inline layui-show-xs-block">
                        <button class="layui-btn" onclick="search()"><i class="layui-icon">&#xe615</i>搜索</button>
                        <shiro:hasPermission name="department:addPage">
                            <button class="layui-btn"
                                    onclick="xadmin.open('添加部门','<%=request.getContextPath()%>/pages/department/add',450,310)">
                                <i class="layui-icon"></i>添加
                            </button>
                        </shiro:hasPermission>
                    </div>

                </div>
                <shiro:hasPermission name="department:del">
                    <div class="layui-card-header">
                        <button class="layui-btn layui-btn-danger mb-2" onclick="delCheck()" style="margin-bottom: 5px"><i
                                class="layui-icon"></i>批量删除
                        </button>
                    </div>
                </shiro:hasPermission>

                <table class="layui-hide" id="dep" lay-filter="barDemo"></table>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/html" id="barDemo">
    <shiro:hasPermission name="department:upPage">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>

    <shiro:hasPermission name="department:del">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
</script>
<script>

    var search;
    var delCheck;
    var rowData;
    var rowObj;
    layui.use('table', function () {
        let table = layui.table;
        table.render({
            id: "dep"
            , elem: '#dep'
            , url: '<%=request.getContextPath()%>/department/query'
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 10 //只显示 1 个连续页码
            }
            , cols: [[
                {type: 'checkbox', align: "center", width: 80}
                , {title: '序号', type: 'numbers', align: "center", width: 80}
                , {field: 'name', title: '部门名', align: "center", width: 300}
                , {field: 'desc', title: '详情介绍', align: "center"}
                , {title: '操作', toolbar: '#barDemo', align: "center", width: 200}
            ]]
            , response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.rows //解析数据列表
                };
            }
        });

        delCheck = function delCheck() {
            let checkStatus = table.checkStatus('dep'); //idTest 即为基础参数 id 对应的值
            let arr = [];
            $.each(checkStatus.data, function (index, obj) {
                arr.push(obj.id);
            })
            console.log(arr);
            del(arr);
        }

        //监听行工具事件
        table.on('tool(barDemo)', function (obj) {
            rowObj = obj;
            console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    layer.load(2);
                    del(obj.data.id);
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                rowData = obj.data;
                xadmin.open('编辑部门信息', '<%=request.getContextPath()%>/pages/department/up', 450, 310);
            }
        });

        search = function search() {
            table.reload('dep', {
                where: { //设定异步数据接口的额外参数，任意设
                    name: $("#name").val()
                    //…
                }
            }); //只重载数据
        }
    });

    function up(data) {
        rowObj.update(Object.assign({}, data));
    }

    function del(data) {
        $.ajax({
            url: '<%=request.getContextPath()%>/department/del',
            type: 'post',
            data: {ids: data},
            traditional: true,
            success: function (res) {
                if (res.code == 200) {
                    layer.alert(res.msg, {icon: 6}, function (index1) {
                        layer.close(index1);
                        search();
                        layer.closeAll('loading');
                    });
                } else if (res.code == 400) {
                    layer.alert('删除出错了.....', {icon: 6}, function (index1) {
                        layer.close(index1);
                        layer.closeAll('loading');
                    });
                } else {
                    layer.alert('你没有权限删除', {icon: 6}, function (index1) {
                        layer.close(index1);
                        layer.closeAll('loading');
                    });
                }

            },
            error: function (res) {
                layer.msg("请检查网络是否连接")
                layer.closeAll('loading');
            }
        });
    }

</script>
</body>
</html>
