layui.use(['layer', 'form', 'table', 'upload', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var upload = layui.upload;

    /**
     * 学生管理
     */
    var review = {
        tableId: "reviewTable",    //表格id
        condition: {
            name: ""
        }
    };

    /**
     * 初始化表格的列
     */
    review.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'studentId', sort: true, title: '学号'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'sex', sort: true, title: '性别'},
            {field: 'age', sort: true, title: '年龄'},
            {field: 'phone', sort: false, title: '电话'},
            {field: 'address', sort: false, title: '住址'},
            {field: 'politicalStatus', sort: false, title: '政治面貌'},
            {field:'image',toolbar: '#image',title:'证明图片'},
            {field:'familyAnnualIncome',sort:true,title:'家庭年收入'},
            {field:'bonusType',sort:true,title:'奖金类型'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 140}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + review.tableId,
        url: Feng.ctxPath + '/review/selectAll',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: review.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    
    // 工具条点击事件
    table.on('tool(' + review.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'agree') {
            review.agree(data);
        } else if (layEvent === 'noagree') {
            review.noagree(data);
        }else if(layEvent === 'showimage'){
            review.showimage(data);
        }
    });

    /**
     * 跳转查看图片证明
     * @param data
     */
    review.showimage = function (data) {
        top.layui.admin.open({
            type: 2,
            area: ['700px', '800px'],
            shadeClose: true, //点击遮罩关闭
            offset:'auto',//垂直水平居中
            title: '图片证明',
            content: Feng.ctxPath + '/review/toiamge?studentId=' + data.studentId+'&bonusType=' + data.bonusType,
            end: function () {
                admin.getTempData('formOk') && table.reload(review.tableId);
            }
        });
    };

    review.agree = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/review/pass", function () {
                table.reload(review.tableId);
                Feng.success("审核成功!");
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("studentId", data.studentId);
            ajax.set("bonusType",data.bonusType);
            ajax.start();
        };
        Feng.confirm("是否同意学生[" + data.name + "]的["+data.bonusType+"]申请?", operation);
    };

    review.noagree = function (data){
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/review/nopass", function () {
                table.reload(review.tableId);
                Feng.success("审核成功!");
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("studentId", data.studentId);
            ajax.set("bonusType",data.bonusType);
            ajax.start();
        };
        Feng.confirm("是否不同意学生[" + data.name + "]的["+data.bonusType+"]申请?", operation);
    };

    /**
     * 删除学生审核
     * @param data
     */
    review.delete_review = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/review/deletereview", function () {
                table.reload(review.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("studentId", data.studentId);
            ajax.set("bonusType",data.bonusType);
            ajax.start();
        };
        Feng.confirm("是否删除学生" + data.studentId + "的"+data.bonusType+"申请?", operation);
    };
});
