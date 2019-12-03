layui.use(['layer', 'form', 'table', 'upload', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;
    var upload = layui.upload;

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
            {field:'applyId',sort:true,title:'申请ID'},
            {field: 'studentId', sort: true, title: '学号'},
            {field: 'studentName', sort: true, title: '姓名'},
            {field: 'sex', sort: true, title: '性别'},
            {field: 'age', sort: true, title: '年龄'},
            {field:'famousRace',sort:false,title:'名族'},
            {field:'politicalStatus',sort:false,title:'政治面貌'},
            {field: 'phone', sort: false, title: '电话'},
            {field:'deptId',sort:false,title:'班级'},
            {field: 'address', sort: false, title: '住址'},
            {field:'gradePoint',sort:true,title:'学分绩点'},
            {field:'vocationalEducationCredits',sort:true,title:'职业教育应修学分'},
            {field:'quantitativeCredit',sort:true,title:'品学量化分'},
            {field:'applyReason',sort:false,title:'申请理由'},

            {field:'fatherName',sort:false,title:'父亲姓名'},
            {field:'fatherAge',sort:false,title:'父亲年龄'},
            {field:'fatherPhone',sort:false,title:'父亲电话'},
            {field:'fatherCompany',sort:false,title:'父亲劳动单位'},
            {field:'fatherMonthlyIncome',sort:false,title:'父亲月工资'},
            {field:'motherName',sort:false,title:'母亲姓名'},
            {field:'motherAge',sort:false,title:'母亲年龄'},
            {field:'motherPhone',sort:false,title:'母亲电话'},
            {field:'motherCompany',sort:false,title:'母亲劳动单位'},
            {field:'motherMonthlyIncome',sort:false,title:'母亲月工资'},
            {field:'familyAccount',sort:false,title:'家庭户口'},
            {field:'familyNumbs',sort:true,title:'家庭人数'},

            {field:'bonusType',sort:false,title:'奖金类型'},
            {align: 'center', toolbar: '#prove', title: '查看证明'},
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
        }else if(layEvent === 'showProve'){
            review.showProve(data);
        }
    });

    /**
     * 跳转查看图片证明
     * @param data
     */
    review.showProve = function (data) {
        var $ = layui.jquery;
        var $ax = layui.ax;
        // var ajax = new $ax( Feng.ctxPath + '/review/down?studentId=' + data.studentId+'&bonusType=' + data.bonusType);
        // var result = ajax.start();
        var down = $("#down");
        down.attr("href",Feng.ctxPath + '/review/down?studentId=' + data.studentId+'&bonusType=' + data.bonusType);
        down.click();
        //
        // top.layui.admin.open({
        //     type: 2,
        //     area: ['80%', '90%'],
        //     shadeClose: true, //点击遮罩关闭
        //     offset:'auto',//垂直水平居中
        //     title: '下载成功',
        //     time:0,
        //     content: Feng.ctxPath + '/review/down?studentId=' + data.studentId+'&bonusType=' + data.bonusType,
        //     end: function () {
        //         admin.getTempData('formOk') && table.reload(review.tableId);
        //     }
        // });
    };

    review.agree = function (data) {
        top.layui.admin.open({
            type: 2,
            area: ['80%', '90%'],
            shadeClose: true, //点击遮罩关闭
            offset:'auto',//垂直水平居中
            title: '审核通过意见',
            content: Feng.ctxPath + '/review/totoExamine?applyId='+data.applyId,
            end: function () {
                admin.getTempData('formOk') && table.reload(review.tableId);
            }
        });

    };

    review.noagree = function (data){
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/review/toExamine", function () {
                table.reload(review.tableId);
                Feng.success("审核成功!");
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("applyId", data.applyId);
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
            ajax.set("applyId", data.applyId);
            ajax.start();
        };
        Feng.confirm("是否删除学生" + data.studentId + "的"+data.bonusType+"申请?", operation);
    };
});
