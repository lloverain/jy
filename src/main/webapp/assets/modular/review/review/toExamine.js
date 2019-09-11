layui.use(['layer', 'form', 'table', 'upload', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var upload = layui.upload;

});

function submission() {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = new FormData($("#form_opinion")[0]);
    // var applyId = $("[name ='applyId']").val();
    // var auditComments = $("[name = 'auditComments']").val();
    // var remarks = $("[name = 'remarks']").val();
    $.ajax({
        url: Feng.ctxPath + "/review/addOpinion",
        type: 'POST',
        data: form,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
           if(data==="1"){
               Feng.success("审核成功，已通过！");

           }else {
               Feng.success("审核失败，未通过！");

           }
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index);

        }
    });
}