<%--
  Created by IntelliJ IDEA.
  User: azy
  Date: 2020/5/30
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/lib/layui/css/layui.css" media="all">
</head>
<body>
<div id="role"></div>
<script src="<%=request.getContextPath()%>/lib/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var flag = false;
    layui.use(['tree', 'jquery'], function () {
        let tree = layui.tree;
        let $ = layui.jquery;
        layer.load(2);
        $.ajax({
            url: '<%=request.getContextPath()%>/role/all',
            success: function (res) {
                let data = [];
                $.each(res, function (index, obj) {
                    let children = [];
                    $.each(obj.permissions, function (index, o) {
                        children.push({title: o.details + "(" + o.name + ")", disabled: true})
                    })
                    data.push({id: obj.id, title: obj.name, children: children})
                })
                tree.render({
                    id: 'roleTree',
                    elem: '#role'  //绑定元素
                    , data: data
                    , showCheckbox: true,
                    oncheck: function (obj) {
                        if (flag) {
                            if (obj.checked) {
                                o('<%=request.getContextPath()%>/adminRole/add', {
                                    adminId: parent.rowData.id,
                                    roleId: obj.data.id
                                });
                            } else {
                                o('<%=request.getContextPath()%>/adminRole/del', {
                                    roleIds: obj.data.id,
                                    adminId: parent.rowData.id
                                });
                            }
                        }
                    }
                });
                $.ajax({
                    url: '<%=request.getContextPath()%>/adminRole/role',
                    data: {id: parent.rowData.id},
                    success: function (res) {
                        tree.setChecked('roleTree', res);
                        flag = true;
                        layer.closeAll('loading');
                    },
                    error: function (res) {
                        layer.msg("获取用户拥有角色失败");
                        layer.closeAll('loading');
                    }
                });
            },
            error: function (res) {
                layer.msg("获取所有角色失败");
                layer.closeAll('loading');
            }
        });

        function o(url, data) {
            $.ajax({
                url: url,
                type: 'post',
                data: data,
                success: function (res) {
                    layer.msg(res.msg);
                    return res.code;
                },
                error: function (res) {
                    layer.msg("操作用户角色失败");
                    return 400;
                }
            })
        }
    });
</script>
</body>
</html>

