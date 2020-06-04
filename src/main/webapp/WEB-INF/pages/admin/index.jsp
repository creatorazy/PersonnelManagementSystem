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
            <a href="">用户管理</a>
            <a>
              <cite>用户管理</cite></a>
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
                        <input type="text" id="loginName" name="loginName" placeholder="用户名称" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="layui-inline layui-show-xs-block">
                        <button class="layui-btn" onclick="search()"><i class="layui-icon">&#xe615</i>搜索</button>
                        <shiro:hasPermission name="admin:addPage">
                            <button class="layui-btn"
                                    onclick="xadmin.open('添加用户','<%=request.getContextPath()%>/pages/admin/add',450,430)">
                                <i class="layui-icon"></i>添加
                            </button>
                        </shiro:hasPermission>
                    </div>

                </div>
                <shiro:hasPermission name="admin:del">
                    <div class="layui-card-header">
                        <button class="layui-btn layui-btn-danger mb-2" onclick="delCheck()" style="margin-bottom: 5px"><i
                                class="layui-icon"></i>批量删除
                        </button>
                    </div>
                </shiro:hasPermission>

                <table class="layui-hide" id="admin" lay-filter="barDemo"></table>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/html" id="barDemo">
    <shiro:hasPermission name="admin:adminRolePage">
        <a class="layui-btn layui-btn-xs" lay-event="roleEdit">角色管理</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="admin:upPage">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="admin:del">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>

</script>
<script id="parseDate" type="text/html">
    {{#
    var fn = function(){
    return  new Date(d.cdate).format("yyyy-MM-dd");
    };
    }}
    {{ fn() }}
</script>
<script id="parseState" type="text/html">
    <input type="checkbox" name="lock" value="{{d.id}}" title="锁定" lay-filter="lockDemo" {{ d.state== 0
           ? 'checked' : '' }}>
</script>
<script>

    Date.prototype.format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "h+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }


    var search;
    var up;
    var delCheck;
    var rowData;
    var rowObj;
    layui.use(['table', "laytpl"], function () {
        let table = layui.table;
        let form = layui.form;
        let laytpl = layui.laytpl;
        table.render({
            id: "admin"
            , elem: '#admin'
            , url: '<%=request.getContextPath()%>/admin/query'
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 10 //只显示 1 个连续页码
            }
            , cols: [[
                {type: 'checkbox', align: "center", width: 80}
                , {title: '序号', type: 'numbers', align: "center", width: 80}
                , {field: 'loginName', title: '登陆名', align: "center"}
                , {field: 'userName', title: '用户名', align: "center"}
                , {field: 'password', title: '登陆密码', align: "center"}
                , {field: 'cDate', title: '用户创建时间', align: "center", templet: '#parseDate'}
                , {field: 'state', title: '用户状态', align: "center", templet: '#parseState'}
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

        //用户状态修改
        form.on('checkbox(lockDemo)', function (obj) {
            let zt;
            //获取input value
            const id = this.value;
            if (obj.elem.checked) {
                zt = 0;
            } else {
                zt = 1;
            }
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/admin/up",
                data: {
                    id: id,
                    state: zt
                },
                success: function (data) {
                    if (data.code == 200) {
                        layer.msg("用户状态修改成功!")
                    } else if (data.code == 400) {
                        obj.elem.checked = !obj.elem.checked;
                        obj.othis.toggleClass("layui-form-checked");
                        layer.msg("用户状态修改失败!")
                    } else {
                        obj.elem.checked = !obj.elem.checked;
                        obj.othis.toggleClass("layui-form-checked");
                        layer.msg("无权操作");
                    }
                },
                error: function () {
                    obj.elem.checked = !obj.elem.checked;
                    obj.othis.toggleClass("layui-form-checked");
                    layer.msg("用户状态修改失败!")
                }
            });

        });

        delCheck = function delCheck() {
            let checkStatus = table.checkStatus('admin'); //idTest 即为基础参数 id 对应的值
            let arr = [];
            $.each(checkStatus.data, function (index, obj) {
                arr.push(obj.id);
            })
            if (arr.length > 0) {
                del(arr);
            }
        }

        //监听行工具事件
        table.on('tool(barDemo)', function (obj) {
            rowObj = obj;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    layer.load(2);
                    del(obj.data.id);
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                rowData = obj.data;
                let p = $("input[value=" + rowData.id + "]")[0].checked;
                if(p){
                    rowData.state = '0';
                }else{
                    rowData.state = '1';
                }
                xadmin.open('编辑用户信息', '<%=request.getContextPath()%>/pages/admin/up', 450, 430);
            }else if(obj.event==='roleEdit'){
                rowData = obj.data;
                xadmin.open('编辑角色信息', '<%=request.getContextPath()%>/pages/admin/role', 450, 430);
            }
        });

        search = function search() {
            table.reload('admin', {
                where: { //设定异步数据接口的额外参数，任意设
                    loginName: $("#loginName").val()
                    //…
                }
            }); //只重载数据
        }

        up = function (data) {
            let obj = Object.assign({}, data);
            let p = $("input[value=" + obj.id + "]").parent();
            let strNode;
            laytpl($("#parseState").text()).render(obj, function (str) {
                strNode = str;
            });
            if (typeof (obj.state) == "undefined") {
                obj.state = '1';
            }
            rowObj.update(obj);
            p.text("");
            p.append($(strNode))
            form.render("checkbox");
        }
    });


    function del(data) {
        $.ajax({
            url: '<%=request.getContextPath()%>/admin/del',
            type: 'post',
            data: {ids: data},
            traditional: true,
            success: function (res) {
                layer.alert(res.msg, {icon: 6}, function (index1) {
                    if (res.code = 200) {
                        layer.close(index1);
                        search();
                    }
                    layer.closeAll('loading');
                });
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
