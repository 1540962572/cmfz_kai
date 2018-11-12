<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;">
    <form id="addguruInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="padding-top: 50px;">
            上师姓名：<input type="text" name="title" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入姓名',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            上师状态：<select class="easyui-combobox" name="status" style="width:240px;" data-options="panelHeight:50,">
            <option value="1">开启</option>
            <option value="2">禁用</option>
        </select>
        </div>
        <div style="padding-top: 10px;">
            上师性别：<select class="easyui-combobox" name="status" style="width:240px;" data-options="panelHeight:50,">
            <option value="男">男</option>
            <option value="女">女</option>
        </select>
        </div>
        <div style="padding-top: 10px;">
            上师照片：<input name="file" class="easyui-filebox" data-options="buttonText:'选择文件'," style="width:240px">
        </div>
    </form>
</div>