<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#tabalbum").treegrid({
            width:1000,
            height:400,
            animate:true,//是否用动画效果
            collapsible:false,//是否可折叠
            pagination:true,//分页控件
            rownumbers:true,//行号
            fitColumns:true,
            url:'${pageContext.request.contextPath}/album/findAll',
            method: 'post',
            idField:'id',//根据那个字段判断树节点关系
            treeField:'title',//根据那个列展现树
            showFooter:false,//是否使用页脚
            sortName:'creditcode',//默认排序字段,后台通过参数名“sort”获取
            sortOrder:'desc',//默认排序规则，后台通过参数名“order”获取
            columns:[[
                {title:'',field:'',checkbox:true,height:100},
                {title:'名字',field:'title',width:220,sortable:true},
                {title:'下载路径',field:'downPath',width:150,sortable:true},
                {title:'章节大小',field:'size',width:80},
                {title:'章节时长',field:'duration',width:100,sortable:true}
                ]],
            toolbar:'#btnalbum',
        })
    })
    function albumadd() {
        $("#savealbumDialog").dialog({
            buttons: [{
                iconCls:'icon-save',
                text:'保存',
                handler:function () {
                    //将用户信息存储到数据库
                    $("#addalbumInput").form('submit',{
                        url: '${pageContext.request.contextPath}/album/add',
                        success: function (result) {
                            var resultObj = $.parseJSON(result);
                            if (resultObj.success) {
                                $.messager.show({title: '提示', msg: '专辑添加成功！！！'});
                            } else {
                                $.messager.show({title: '提示', msg: resultObj.messager});
                            }
                            //关闭对话框
                            $("#savealbumDialog").dialog('close');
                            //刷新
                            $("#tabalbum").datagrid('reload');
                        }
                    })
                },
            }],
        });
    }
    function chapteradd() {
        $("#savechapterDialog").dialog({
            buttons:[{
                iconCls:'icon-save',
                text:'保存',
                handler:function () {
                    //将章节信息存储到数据库
                    $("#addchapterInput").form('submit',{
                        url: '${pageContext.request.contextPath}/chapter/add',
                        success: function (result) {
                            var resultObj = $.parseJSON(result);
                            if (resultObj.success) {
                                $.messager.show({title: '提示', msg: '章节添加成功！！！'});
                            } else {
                                $.messager.show({title: '提示', msg: resultObj.messager});
                            }
                            //关闭对话框
                            $("#savechapterDialog").dialog('close');
                            //刷新
                            $("#tabalbum").datagrid('reload');
                        }
                    })
                },
            }],
        });
    }
    function downloadAudio() {
        var rows=$("#tabalbum").treegrid('getSelections');
        var id=null;
        var newsize=null;
        var downPaths=null;
        if (rows.length<=0){
            $.messager.show({title:'提示',msg:'请至少选择一行数据'});
        }else {
            for (var i=0;i<rows.length;i++){
                newsize=rows[i].size;
                downPaths=rows[i].downPath;
                id=rows[i].id;
            }
            if (newsize==undefined){
                location.href="${pageContext.request.contextPath}/album/download?id="+id;
            }else{
                location.href="${pageContext.request.contextPath}/chapter/download?downPath="+downPaths;
            }
        }
    }
    function albumdetails() {
        var rows = $("#tabalbum").treegrid('getSelections');
        var id = null;
        var newsize = null;
        if (rows.length <= 0) {
            $.messager.show({title: '提示', msg: '请至少选择一行数据'});
        } else {
            for (var i = 0; i < rows.length; i++) {
                newsize = rows[i].size;
                id = rows[i].id;
            }
            if (newsize == undefined) {
                $("#albumdetailsDialog").dialog({
                    href:'${pageContext.request.contextPath}/back/album/albumdetails.jsp?id='+id,
                })
            }else{
                $.messager.show({title: '提示', msg: '请选择专辑😊😊😊😊😊😊'});
            }
        }
    }
</script>
<table id="tabalbum"></table>
<div id="btnalbum">
    <a href="#" class="easyui-linkbutton" onclick="albumdetails()" data-options="iconCls:'icon-add',plain:true">专辑详情</a>
    <a href="#" class="easyui-linkbutton" onclick="albumadd();" data-options="iconCls:'icon-edit',plain:true">添加专辑</a>
    <a href="#" class="easyui-linkbutton" onclick="chapteradd();" data-options="iconCls:'icon-remove',plain:true">添加章节</a>
    <a href="#" class="easyui-linkbutton" onclick="downloadAudio();" data-options="iconCls:'icon-save',plain:true">下载音频</a>
</div>
<!--添加专辑对话框-->
<div id="savealbumDialog" data-options="href:'${pageContext.request.contextPath}/back/album/addalbum.jsp',iconCls:'icon-save',draggable:false,width:600,height:400,title:'添加专辑',"></div>
<!--添加章节对话框-->
<div id="savechapterDialog" data-options="href:'${pageContext.request.contextPath}/back/album/addchapter.jsp',iconCls:'icon-save',draggable:false,width:600,height:400,title:'添加章节',"></div>
<!--专辑详情对话框-->
<div id="albumdetailsDialog" data-options="iconCls:'icon-save',draggable:false,width:600,height:400,title:'章节详情',"></div>
<!--更新轮播图对话框-->
<div id="updatealbumDialog" data-options="iconCls:'icon-edit',draggable:false,width:600,height:400,title:'更新轮播图',"></div>