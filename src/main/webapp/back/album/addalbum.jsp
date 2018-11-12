<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;">
    <form id="addalbumInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="padding-top: 50px;">
            专辑标题：<input type="text" name="title" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入专辑标题',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            专辑集数：<input type="text" name="count" class="easyui-numberbox" data-options="width:240,required:'true',prompt:'请输入专辑集数',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            观众评分：<select class="easyui-combobox" name="score" style="width:240px;" data-options="panelHeight:120,">
            <option value="10">满分</option>
            <option value="10">满分</option>
            <option value="10">满分</option>
            <option value="10">满分</option>
            <option value="10">满分</option>
        </select>
        </div>
        <div style="padding-top: 10px;">
            专辑作者：<input type="text" name="author" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入专辑作者',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            专辑播音：<input type="text" name="broadCast" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入专辑播音',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            专辑简介：<input type="text" name="brief" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入专辑简介',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            封面图片：<input name="file" class="easyui-filebox" data-options="buttonText:'选择图片'," style="width:240px">
        </div>
    </form>
</div>