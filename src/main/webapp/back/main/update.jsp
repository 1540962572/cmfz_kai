<%@page isELIgnored="false" pageEncoding="UTF-8" %>
<div style="text-align: center">
    <form id="updateAdminInput" class="easyui-form" method="post">
        <div style="padding-top: 50px;">
            <input type="text" name="paw" class="easyui-passwordbox" data-options="label:'原密码：',width:240,required:'true',prompt:'请输入密码',validType:'pwd'"/>
        </div>
        <div style="padding-top: 10px;">
            <input type="text" name="password" class="easyui-passwordbox" data-options="label:'新密码：',width:240,required:'true',prompt:'请输入密码',validType:'pwd'"/>
        </div>
    </form>
</div>