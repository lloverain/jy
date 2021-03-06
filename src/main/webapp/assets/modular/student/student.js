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
    var MgrStudent = {
        tableId: "studentTable",    //表格id
        condition: {
            name: "",
            deptId: ""
        }
    };

    /**
     * 选择部门时
     */
    MgrStudent.onClickDept = function (e, treeId, treeNode) {
        MgrStudent.condition.deptId = treeNode.id;
        console.log(treeNode.id + "部门id");
        MgrStudent.search();
    };

    /**
     * 初始化表格的列
     */
    MgrStudent.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'studentId', sort: true, title: '学号'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'sex', sort: true, title: '性别'},
            {field: 'age', sort: true, title: '年龄'},
            {field: 'idCart', sort: false, title: '身份证号'},
            {field: 'phone', sort: false, title: '电话'},
            {field: 'address', sort: false, title: '住址'},
            {field: 'politicalStatus', sort: false, title: '政治面貌'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 140}
        ]];
    };


    /**
     * 点击查询按钮
     */
    MgrStudent.search = function () {
        table.reload(MgrStudent.tableId, {
            where: {'basis': $("#name").val(),'deptId':MgrStudent.condition.deptId}
        });
    };


    /**
     * 点击添加按钮
     */

    MgrStudent.add = function () {

        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加学生',
            content: Feng.ctxPath + '/student/addStudentPage',
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrStudent.tableId);
            }
        });

    }


    /**
     * 导出excel按钮
     */
    MgrStudent.exportExcel = function () {
        var checkRows = table.checkStatus(MgrStudent.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    MgrStudent.onEditUser = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑用户',
            content: Feng.ctxPath + '/student/editStudent?studentId=' + data.studentId,
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrStudent.tableId);
            }
        });
    };

    /**
     * 点击删除学生按钮
     *
     * @param data 点击按钮时候的行数据
     */
    MgrStudent.onDeleteUser = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/student/deleteStudent", function () {
                table.reload(MgrStudent.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("studentId", data.studentId);
            ajax.start();
        };
        Feng.confirm("是否删除学生" + data.studentId + "?", operation);
    };

    //加载动画
    let loads;
    //上传导入文件
    upload.render({
        elem: '#btnImp',
        url: Feng.ctxPath + '/student/import',
        acceptMime: 'file/xlsx, file/xls',
        exts: 'xls|xlsx',
        before: function (obj) {
            loads = layer.load();
        },
        done: function (res) {
            if (res.code === "userNo") {
                layer.close(loads);
                layer.msg("只有辅导员才可以批量导入")
            }
            if (res.code === "ok") {
                layer.close(loads);
                layer.msg("导入成功")
                table.reload(MgrStudent.tableId)
            }
            if (res.code === "no") {
                layer.close(loads);
                layer.msg("导入失败")
            }
        },
        error: function () {
            layer.close(loads);
            layer.msg("导入出错")
        }
    })

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrStudent.tableId,
        url: Feng.ctxPath + '/student/selectAllStudent',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: MgrStudent.initColumn()
    });


    //初始化左侧部门树
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(MgrStudent.onClickDept);
    ztree.init();


    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MgrStudent.search();
    });

    // 导入按钮点击事件
   // $('#btnImp').click(function () {
       // MgrStudent.importExcel();
   // });

    // 导出excel
    $('#btnExp').click(function () {
        MgrStudent.exportExcel();
    });

    //添加单个学生
    $('#btnAdd').click(function () {
        MgrStudent.add();
    });

    // 工具条点击事件
    table.on('tool(' + MgrStudent.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            MgrStudent.onEditUser(data);
        } else if (layEvent === 'delete') {
            MgrStudent.onDeleteUser(data);
        }
    });

});
