package cn.stylefeng.guns.modular.application.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.service.GrantService;
import cn.stylefeng.guns.modular.application.util.AGrantUtil;
import cn.stylefeng.guns.modular.student.entity.Stu;
import cn.stylefeng.guns.modular.student.entity.Student;
import cn.stylefeng.guns.modular.student.service.StudentService;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 申请
 */
@Controller
@RequestMapping("/application")
public class GrantController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(GrantController.class);

    @Autowired
    private GrantService grantService;


    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    private static String ROOTS = "/modular/frame/";

    /**
     * 申请页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/console2")
    public String console2(Model model) {
        logger.debug("进入申请页面");
        return ROOTS + "console2.html";
    }

    /**
     * 未申请请求地址
     *
     * @param model
     * @return
     */
    @RequestMapping("/console2_edit")
    public String toreview(Model model) {
        logger.debug("进入助学金申请");
        Stu stu = new Stu();
        User user = new User();
        Long userId = ShiroKit.getUserNotNull().getId();
        user = this.userService.getById(userId);//查询sys_user表中的数据
        List<Long> dept = ShiroKit.getDeptDataScope();
        DataScope dataScope = new DataScope(dept);
        Student student = studentService.selectOneStudent(user.getAccount(), dataScope);

        stu.setStudentId(Long.valueOf(student.getStudentId()));

        model.addAllAttributes(BeanUtil.beanToMap(stu));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        LogObjectHolder.me().set(user);
        return ROOTS + "/console2/console2_edit.html";
    }

    /**
     * 申请但是被拒绝再修改信息的请求地址
     *
     * @param model
     * @return
     */
    @RequestMapping("/console2_alertedit")
    public String alertreview(Model model, @Param("bonusType") String bonusType) {
        logger.debug("进入修改助学金申请");
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        //根据学号+奖项查询申请信息
        Stu stu = this.grantService.selectStudentGrant(user.getAccount(), bonusType);

        logger.debug("接口console2_alertedit查询的申请信息:" + stu.toString());
        model.addAllAttributes(BeanUtil.beanToMap(stu));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
//        model.addAttribute("reviewiamge",grant.getImage());
        LogObjectHolder.me().set(user);
        return ROOTS + "/console2/console2_updata.html";
    }


    /**
     * 进入申请页面就检测奖项申请情况
     *
     * @param bonusType
     * @return
     */
    @RequestMapping("/selectReview")
    @ResponseBody
    public int select_review(@RequestParam String bonusType) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        int num = grantService.select_repeat(user.getAccount(), bonusType);
        if (num == 1) {
            /**
             * 申请过
             */
            String state = grantService.select_state(user.getAccount(), bonusType);
            if ("1".equals(state)) {
                /**
                 * 申请通过
                 */
                return 1;
            } else if ("2".equals(state)) {
                /**
                 * 申请不通过
                 */
                return 2;
            } else {
                /**
                 * 审核中
                 */
                return 3;
            }

        } else {
            /**
             * 未申请
             */
            return 0;
        }

    }

    /**
     * 上传申请助学金数据
     *
     * @return
     */
    @RequestMapping("/shenqing_grant")
    @ResponseBody
    public int add(@Valid Stu stu) {
        logger.debug("开始上传助学金申请数据");
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        return this.grantService.addgrant(stu, user);
    }

    /**
     * 更新上传信息
     *
     * @param stu
     * @param bonusType
     * @return
     */
    @RequestMapping("/updatashenqing")
    @ResponseBody
    public int updataadd(@Valid Stu stu, @Param("bonusType") String bonusType) {
        logger.debug("updatashenqing更新申请" + stu.getStudentId());
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        String state = grantService.select_state(user.getAccount(), bonusType);
        if ("3".equals(state)) {
            return 1;
        } else {
            int update = this.grantService.updataGrant(stu, bonusType);
            if (update == 2) {
                return 2;
            } else {
                return 3;
            }
        }
    }

}
