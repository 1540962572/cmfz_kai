<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
$(function () {
    $("#updatecourseInput").form('load','${pageContext.request.contextPath}/course/findId?id=${param.id}');
})
</script>
<div style="text-align: center;">
    <form id="updatecourseInput" class="easyui-form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${param.id}">
        <div style="padding-top: 50px;">
            必修课标题：<input type="text" name="title" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入标题',validateOnBlur:'true',">
        </div>
        <div style="padding-top: 10px;">
            必修课mark：<input type="text" name="marking" class="easyui-textbox" data-options="width:240,required:'true',prompt:'请输入标题',validateOnBlur:'true',">
        </div>
    </form>
</div>