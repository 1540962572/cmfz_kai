<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#tabcourse").datagrid({
            url:'${pageContext.request.contextPath}/course/findAll',
            height:500,
            striped:true,//显示斑马线效果
            pagination:true,//显示分页效果栏
            fitColumns:true,
            rownumbers:true,
            remoteSort:false,
            columns:[[
                {title:'',field:'',checkbox:true,height:100},
                {title:'id',field:'id',width:10},
                {title:'课程名字',field:'title',width:10},
                {title:'marking',field:'marking',width:10},
                {title:'时间',field:'creatTime',width:20}
            ]],
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/back' + rowData.imgPath + '"  style="height:100px;"></td>' +
                    '<td style="border:0">' +
                    '<p>date: ' + rowData.date + '</p>' +
                    '<p>description: ' + rowData.desc + '</p>' +
                    '<p>path: ' + rowData.imgPath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            toolbar:'#btncourse',
        })
    })
    function courseadd() {
        $("#savecourseDialog").dialog({
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function () {
                    //将用户信息存储到数据库
                    $("#addcourseInput").form('submit',{
                        url:'${pageContext.request.contextPath}/course/add',
                        success:function (result) {
                            var resultObj = $.parseJSON(result);
                            if(resultObj.success){
                                $.messager.show({title:'提示',msg:'轮播图添加成功！！！'});
                            }else{
                                $.messager.show({title:'提示',msg:resultObj.messager});
                            }
                            //关闭对话框
                            $("#savecourseDialog").dialog('close');
                            //刷新
                            $("#tabcourse").datagrid('reload');
                        }
                    })
                },
            }],
        });
    }
    function courseupdate(){
        var rows=$("#tabcourse").datagrid('getSelections');
        if(rows.length==1){
            var id=null;
            for (var i=0;i<rows.length;i++){
                id=rows[i].id;
            };
            $("#updatecourseDialog").dialog({
                href:'${pageContext.request.contextPath}/back/course/updatecourse.jsp?id='+id,
                buttons:[{
                    iconCls:'icon-edit',
                    text:'修改',
                    handler:function () {
                        $("#updatecourseInput").form('submit',{
                            url:'${pageContext.request.contextPath}/course/update',
                            success:function (result) {
                                var resultObj = $.parseJSON(result);
                                //关闭对话框
                                $("#updatecourseDialog").dialog('close');
                                //刷新
                                $("#tabcourse").datagrid('reload');
                                if(resultObj.success){
                                    $.messager.show({title:'提示',msg:'轮播图修改成功！！！'});
                                }else{
                                    $.messager.show({title:'提示',msg:resultObj.messager});
                                }
                            }
                        });
                    }
                }]
            });

        }else{
            $.messager.show({title:'提示',msg:'请选择一行数据'});
        }
    }
    function coursedelete() {
        var rows=$("#tabcourse").datagrid('getSelections');
        if(rows.length<=0){
            $.messager.show({title:'提醒',msg:'至少选择一行数据'});
        }else{
            var ids=[];
            for(var i=0;i<rows.length;i++){
                ids.push(rows[i].id);
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/course/deleteAll",
                type:"POST",
                traditional:true,
                data:{id:ids},
                success:function (result) {
                    $.messager.show({title:'提示',msg:'删除成功'});
                    $("#tabcourse").datagrid('reload');
                },
                error:function(){
                    //消息提示
                    $.messager.show({title:'提示',msg:"删除失败!!!"});
                    //刷新datagrid
                    $("#tabcourse").datagrid('reload');
                }
            });
        }
    }
</script>
<table id="tabcourse"></table>
<div id="btncourse">
    <a href="#" class="easyui-linkbutton" onclick="courseadd();" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="#" class="easyui-linkbutton" onclick="courseupdate();" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="#" class="easyui-linkbutton" onclick="coursedelete();" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a href="#" class="easyui-linkbutton" onclick="coursesave();" data-options="iconCls:'icon-save',plain:true">保存</a>
</div>
<!--添加轮播图对话框-->
<div id="savecourseDialog" data-options="href:'${pageContext.request.contextPath}/back/course/addcourse.jsp',iconCls:'icon-save',draggable:false,width:600,height:400,title:'添加轮播图',"></div>
<!--更新轮播图对话框-->
<div id="updatecourseDialog" data-options="iconCls:'icon-edit',draggable:false,width:600,height:400,title:'更新轮播图',"></div>