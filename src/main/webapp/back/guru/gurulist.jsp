<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#tabguru").datagrid({
            url:'${pageContext.request.contextPath}/guru/findAll',
            height:500,
            striped:true,//显示斑马线效果
            pagination:true,//显示分页效果栏
            fitColumns:true,
            rownumbers:true,
            remoteSort:false,
            columns:[[
                {title:'',field:'',checkbox:true,height:100},
                {title:'id',field:'id',width:10},
                {title:'名字',field:'name',width:10},
                {title:'状态',field:'stauts',width:10},
                {title:'性别',field:'sex',width:10},
                {title:'照片',field:'image',width:20,formatter:function (value,row,index) {
                   var imgages='<img src="${pageContext.request.contextPath}/back/guru'+row.headPic+'" width="100px">';
                   return imgages;
                }}
            ]],
            toolbar:'#btnguru',
        })
    })
    function guruadd() {
        $("#saveguruDialog").dialog({
            buttons: [{
                iconCls:'icon-save',
                text:'保存',
                handler:function () {
                    //将用户信息存储到数据库
                    $("#addbannerInput").form('submit', {
                        url: '/banner/addbanner',
                        success: function (result) {
                            var resultObj = $.parseJSON(result);
                            if (resultObj.success) {
                                $.messager.show({title: '提示', msg: '轮播图添加成功！！！'});
                            } else {
                                $.messager.show({title: '提示', msg: resultObj.messager});
                            }
                            //关闭对话框
                            $("#savebannerDialog").dialog('close');
                            //刷新
                            $("#tabguru").datagrid('reload');
                        }
                    })
                },
            }],
        });
    }
</script>
<table id="tabguru"></table>
<div id="btnguru">
    <a href="#" class="easyui-linkbutton" onclick="guruadd();" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="#" class="easyui-linkbutton" onclick="guruupdate();" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="#" class="easyui-linkbutton" onclick="gurudelete();" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a href="#" class="easyui-linkbutton" onclick="gurusave();" data-options="iconCls:'icon-save',plain:true">保存</a>
</div>
<!--添加轮播图对话框-->
<div id="saveguruDialog" data-options="href:'${pageContext.request.contextPath}/back/guru/addguru.jsp',iconCls:'icon-save',draggable:false,width:600,height:400,title:'添加上师',"></div>
<!--更新轮播图对话框-->
<div id="updateguruDialog" data-options="iconCls:'icon-edit',draggable:false,width:600,height:400,title:'更新轮播图',"></div>