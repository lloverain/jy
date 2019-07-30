package cn.stylefeng.guns.modular.student.controller;


import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.student.entity.Student;
import cn.stylefeng.guns.modular.student.service.StudentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

/**
 * @author FM
 * @date 2019/7/22 21:47
 * <p>
 * 学生信息穿
 */

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    private static String ROOTS = "/modular/student/";

    /**
     * 学生管理界面
     *
     * @return
     */
    @RequestMapping("/studentMgr")
    public String studentImAndEx() {
        logger.debug("进入学生管理页面");
        return ROOTS + "studentMgr.html";
    }

    /**
     * 导入文件到数据库
     *
     * @param studentFile
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @ResponseBody
    @RequestMapping("/import")
    public String studentImport(@RequestParam(value = "file", required = false) MultipartFile studentFile, HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, SQLIntegrityConstraintViolationException {

        JSONObject jsonObject = new JSONObject();

        if (studentService.importstudent(studentFile)) {
            jsonObject.put("code", "ok");
            logger.debug("导入成功");
            return jsonObject.toString();
        } else {
            logger.debug("导入文件失败");
            jsonObject.put("code", "no");
        }
        return jsonObject.toString();
    }


    /**
     * 查询所有学生信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectAllStudent")
    public Object selectAllStudent() {

        IPage page = studentService.selectAllStudentPage();
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 删除学生
     *
     * @param studentId
     * @return
     */
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public ResponseData deleteStudent(@RequestParam String studentId) {
        this.studentService.deleteStudent(studentId);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到编辑学生信息界面
     *
     * @param studentId
     * @return
     */
    @RequestMapping("/editStudent")
    public String editStudent(@RequestParam String studentId) {

        Student student = studentService.selectOneStudent(studentId);
        LogObjectHolder.me().set(student);
        logger.debug("跳转编辑页面");
        return ROOTS + "student/student_edit.html";
    }

    /**
     * 更新学生信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStudent")
    public Object updateStudent(@Valid Student student) {

        String studentId = "";  //学号
        String name = "";
        String sex = "";
        String age = "";
        String idCart = "";
        String phone = "";
        String address = "";
        String politicalStatus = "";

        studentId = student.getStudentId();
        name = student.getName();
        sex = student.getSex();
        age = student.getAge();
        idCart = student.getIdCart();
        phone = student.getPhone();
        address = student.getAddress();
        politicalStatus = student.getPoliticalStatus();

        logger.debug(studentId);
        logger.debug(name);
        logger.debug(sex);
        logger.debug(age);
        logger.debug(idCart);
        logger.debug(phone);
        logger.debug(address);
        logger.debug(politicalStatus);

        studentService.updateStudent(student);
        return SUCCESS_TIP;
    }

    /**
     * 得到学生详细信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getStudentInfo")
    public Object getStudentInfo(@RequestParam String studentId) {

        if (ToolUtil.isEmpty(studentId)) {
            logger.error("studentId为空");
            throw new RequestEmptyException();
        }

        Student student = studentService.selectOneStudent(studentId);
        HashMap<String, String> map = new HashMap<>();
        map.put("studentId",studentId);
        map.put("name", student.getName());
        map.put("sex", student.getSex());
        map.put("age", student.getAge());
        map.put("idCart", student.getIdCart());
        map.put("phone", student.getPhone());
        map.put("address", student.getAddress());
        map.put("politicalStatus", student.getPoliticalStatus());

        return ResponseData.success(map);
    }
}
