var tu;
var zxj = document.getElementById("zxj");
layui.use(['form', 'upload', 'element', 'ax', 'laydate'], function () {
    var form = layui.form;
    var upload = layui.upload;
    var element = layui.element;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var $ = layui.jquery;
    var stateimage = document.getElementById("state");
    var ajax = new $ax(Feng.ctxPath + "/application/selectReview",
        function (data) {
            if(data===0){
                stateimage.src = "/assets/expand/images/noaudit.png"
            }
            if(data===1){
                stateimage.src = "/assets/expand/images/pass.png"
            }
            if(data===2){
                stateimage.src = "/assets/expand/images/nopass.png"
            }
            if(data===3){
                stateimage.src = "/assets/expand/images/audit.png";
                $("#zhuxuejin").addClass("notclick");
            }

        },
        function (data) {
            Feng.error("请求失败，稍后再试！" + data.responseJSON.message + "!");
        });
    ajax.set("bonusType",zxj.innerText);
    ajax.start();



});


function toconsole2_edit(){
    var ajax = new layui.ax(Feng.ctxPath + "/application/selectReview",
        function (data) {
            if(data===0){
                top.layui.admin.open({
                    type: 2,
                    area: ['80%', '90%'],
                    shadeClose: false, //点击遮罩关闭
                    offset:'auto',//垂直水平居中
                    title: zxj.innerText,
                    content: Feng.ctxPath + '/application/console2_edit',
                    end:function () {
                        location.reload();
                    }
                });
            }
            if(data===1){

            }
            if(data===2){

                top.layui.admin.open({
                    type: 2,
                    area: ['80%', '90%'],
                    shadeClose: false, //点击遮罩关闭
                    offset:'auto',//垂直水平居中
                    title: zxj.innerText,
                    content: Feng.ctxPath + '/application/console2_alertedit?bonusType='+zxj.innerText,
                    end:function () {
                        location.reload();
                    }
                });
            }
            if(data===3){

            }

        },
        function (data) {
            Feng.error("请求失败，稍后再试！" + data.responseJSON.message + "!");
    });

    ajax.set("bonusType","助学金");
    ajax.start();

}
