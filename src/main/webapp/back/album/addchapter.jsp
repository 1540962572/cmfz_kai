<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#queryAlbum").combobox({
            url:'${pageContext.request.contextPath}/album/findAlbum',
            valueField:'id',
            width:240,
            textField:'title',
            limitToList:true,
            panelHeight:120,
        })
    })
</script>
<div style="text-align: center;">
    <form id="addchapterInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <div style="padding-top: 50px;">
            章节标题：<input type="text" name="title" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入章节标题',validateOnBlur:'true',">
        </div>

        <div style="padding-top: 10px;">
            隶属专辑：<input id="queryAlbum" name="album_id" type="text">
        </div>
        <div style="padding-top: 10px;">
            音频文件：<input name="file" class="easyui-filebox" data-options="buttonText:'选择音频'," style="width:240px">
        </div>
    </form>
</div>