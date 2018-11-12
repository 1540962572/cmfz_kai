<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/form.validator.rules.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	<!--菜单处理-->
    $(function () {
       //页面加载完成显示菜单系统
        $.ajax({
            url:"${pageContext.request.contextPath}/menu/queryAll",
            type:"POST",
            data:"menu",
            dataType:"json",
            success:function (menu) {
                //遍历一级菜单
                $.each(menu,function (index,m) {
                    //遍历二级菜单
                    var context="<div style='text-align: center;'>";
                    $.each(m.menus,function (idx,child) {
                        context+="<a class='easyui-linkbutton' onclick=\"addTab('"+child.title+"','"+child.iconCls+"','"+child.href+"')\" data-options=\"width:'95%',iconCls:'"+child.iconCls+"',\">"+child.title+"</a>"
                    });
                    context+="</div>";
                    //添加菜单
                    $("#aa").accordion('add',{
                        title:m.title,
                        iconCls:m.iconCls,
                        content:context,
                    });
                });
            },
            error:function () {
                location.href="${pageContext.request.contextPath}/back/login.jsp";
            },
        })
    });
    //更新管理员账户
    function funupdateAdmin(id) {
        $("#updateAdminDialog").dialog({
           href:'${pageContext.request.contextPath}/back/main/update.jsp',
            buttons:[{
                iconCls:'icon-edit',
                text:'修改',
                handler:function () {
                    $("#updateAdminInput").form('submit',{
                        url:'${pageContext.request.contextPath}/admin/update?id='+id,
                        success:function (result) {
                            var resultObj = $.parseJSON(result);
                            //关闭对话框
                            $("#updateAdminDialog").dialog('close');
                            if(resultObj.success){
                                $.messager.show({title:'提示',msg:'密码修改成功✔，5秒后请重新登录👏'});
                                setTimeout(function () {
                                    window.location.href="${pageContext.request.contextPath}/back/login.jsp";
                                },5000)
                            }else{
                                $.messager.show({title:'提示',msg:resultObj.messager});
                            }
                        }
                    });
                }
            }]
        });
    }
    function addTab(title,icon,href) {
        if(!$("#tt").tabs('exists',title)){
            $("#tt").tabs('add',{
                title:title,
                iconCls:icon,
                closable:true,
                fit:true,
                href:"${pageContext.request.contextPath}/back"+href,
            });
        }else{
            $("#tt").tabs("select",title);
        }
    }
    <!--退出-->
    function loginOut(){
        location.replace("${pageContext.request.contextPath}/admin/out");
    }

</script>

</head>
<body class="easyui-layout">
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.admin.username} &nbsp;<a href="javascript:;" id="updateAdmina" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="funupdateAdmin('${sessionScope.admin.id}')" >修改密码</a>&nbsp;
            <a href="javascript:loginOut();" class="easyui-linkbutton" data-options="iconCls:'icon-01'" onclick="loginOut()">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true"></div>
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
        </div>
    </div>
    <!--更新管理员账户对话框-->
    <div id="updateAdminDialog" data-options="iconCls:'icon-edit',draggable:false,width:600,height:400,title:'更新管理员密码',"></div>
</body> 
</html>