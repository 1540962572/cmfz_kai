<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<div style="text-align: center;">
    <form id="addbannerInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="padding-top: 50px;">
            轮播标题：<input type="text" name="title" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入标题',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            轮播描述：<input type="text" name="desc" class="easyui-textbox" data-options="width:240,height:50,required:true,prompt:'请输入轮播描述',">
        </div>
        <div style="padding-top: 10px;">
            轮播状态：<select class="easyui-combobox" name="status" style="width:240px;" data-options="panelHeight:50,">
                        <option value="1">开启</option>
                        <option value="2">禁用</option>
                    </select>
        </div>
        <div style="padding-top: 10px;">
            轮播图片：<input name="file" class="easyui-filebox" data-options="buttonText:'选择文件'," style="width:240px">
        </div>
    </form>
</div>