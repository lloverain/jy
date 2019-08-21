var tu;
var bonusType = document.getElementsByClassName("layui-layer-title");
var studentId = document.getElementsByName("studentId");
layui.use(['form', 'upload', 'element', 'ax', 'laydate'],function () {
    var form = layui.form;
    var upload = layui.upload;
    var element = layui.element;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var $ = layui.jquery;

    //渲染时间选择框
    laydate.render({
        elem: '#birthday'
    });

    var ajax = new $ax(Feng.ctxPath + "/review/stu_showimage?studentId=" + studentId[0].value +"&bonusType=助学金");
    var result = ajax.start();
    var path = result;
    $("#preview").attr('src',path);
    //表单提交事件
    form.on('submit(userInfoSubmit)', function (data) {
        form.val('userInfoForm', result.data);
        var ajax = new $ax(Feng.ctxPath + "/application/shenqing", function (data) {
            if (data===1){
                Feng.success("已申请,请勿重复申请!");
            }
            if (data===2){
                Feng.success("申请成功!");
            }
            if (data===3){
                Feng.error("申请失败!");
            }
        }, function (data) {
            Feng.error("申请失败，稍后再试！" + data.responseJSON.message + "!");
        });
        if(tu===null){
            alert("1111")
        }
        ajax.set(data.field);
        ajax.set("image",tu);
        ajax.start();
    });
});


function imgPreview(fileDom){
    //判断是否支持FileReader
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }        //获取文件
    var file = fileDom.files[0];        var imageType = /^image\//;        //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");            return;
    }        //读取完成
    reader.onload = function(e) {
        //获取图片dom
        tu = e.target.result;
        var img = document.getElementById("preview");            //图片路径设置为读取的图片
        img.src = e.target.result;
    };
    layui.jquery("#text").html(layui.jquery("#file").val());
    reader.readAsDataURL(file);
}
