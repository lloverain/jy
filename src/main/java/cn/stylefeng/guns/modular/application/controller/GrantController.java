package cn.stylefeng.guns.modular.application.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.entity.Grant;
import cn.stylefeng.guns.modular.application.service.GrantService;
import cn.stylefeng.guns.modular.student.entity.Stu;
import cn.stylefeng.guns.modular.student.entity.Student;
import cn.stylefeng.guns.modular.student.service.StudentService;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
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

import javax.validation.Valid;
import java.util.List;

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
     * @param model
     * @return
     */
    @RequestMapping("/console2")
    public String console2(Model model){
        logger.debug("进入申请页面");
        return ROOTS+"console2.html";
    }

    /**
     * 未申请请求地址
     * @param model
     * @return
     */
    @RequestMapping("/console2_edit")
    public String toreview(Model model){
        logger.debug("进入助学金申请");
        Stu stu = new Stu();
        User user = new User();
        Long userId = ShiroKit.getUserNotNull().getId();
        user = this.userService.getById(userId);//查询sys_user表中的数据
        Student student = studentService.selectOneStudent(String.valueOf(userId));
        BeanUtils.copyProperties(student,stu);
        stu.setXingming(student.getName());
        stu.setXingbie(student.getSex());
        model.addAllAttributes(BeanUtil.beanToMap(stu));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        LogObjectHolder.me().set(user);
        return ROOTS+"/console2/console2_edit.html";
    }

    /**
     * 申请但是被拒绝再修改信息的请求地址
     * @param model
     * @return
     */
    @RequestMapping("/console2_alertedit")
    public String alertreview(Model model,@Param("bonusType")String bonusType){
        logger.debug("进入修改助学金申请");
        Stu stu = new Stu();
        User user = new User();
        Long userId = ShiroKit.getUserNotNull().getId();
        List<Grant> grantList = grantService.select_repeat(String.valueOf(userId),bonusType);
        Grant grant = grantList.get(0);
        BeanUtils.copyProperties(grant,stu);
        stu.setXingming(grant.getName());
        stu.setXingbie(grant.getSex());
        model.addAllAttributes(BeanUtil.beanToMap(stu));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        model.addAttribute("reviewiamge",grant.getImage());
        LogObjectHolder.me().set(user);
        return ROOTS+"/console2/console2_updata.html";
    }

    @RequestMapping("/selectReview")
    @ResponseBody
    public int select_review(@RequestParam String bonusType){
        Long userId = ShiroKit.getUserNotNull().getId();
        String num = grantService.select_state(String.valueOf(userId),bonusType);
        if(num==null){
            return 0;//没申请
        }else if("1".equals(num)){
            return 1;//申请通过
        }else if("2".equals(num)){
            return 2;//申请不通过
        }else {
            return 3;//审核中
        }
    }

    /**
     * 上传申请数据
     * @param stu
     * @return
     */
    @RequestMapping("/shenqing")
    @ResponseBody
    public int add(@Valid Stu stu, @Param("image")String image){
        logger.debug("开始上传申请数据");
        int num = 0;
        Long userId = ShiroKit.getUserNotNull().getId();
        String dept_id = grantService.select_dept_id(String.valueOf(userId));
        Grant grant = new Grant();
        BeanUtils.copyProperties(stu, grant);
        grant.setName(stu.getXingming());
        grant.setSex(stu.getXingbie());
        grant.setDept_id(dept_id);
        grant.setBonusType("助学金");
        grant.setImage(image);
        System.out.println(grant);
        List<Grant> repeat = grantService.select_repeat(grant.getStudentId(),grant.getBonusType());//查是否存在已申请
        num = repeat.size();
        if(num==0){
            num = grantService.insertGrant(grant);
            if(num!=0){
                return 2;
            }else {
                return 3;
            }
        }else {
            return 1;
        }
    }

    @RequestMapping("/updatashenqing")
    @ResponseBody
    public int updataadd(@Valid Stu stu, @Param("image")String image) {
        logger.debug("开始更新申请数据");
        Long userId = ShiroKit.getUserNotNull().getId();
        return grantService.updataGrant(userId,stu,image);
    }
}
