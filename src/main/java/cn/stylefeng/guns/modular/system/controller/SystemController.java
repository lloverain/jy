/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.DefaultAvatar;
import cn.stylefeng.guns.core.common.constant.dictmap.UserDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.entity.*;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.mapper.stu_applicationImpl;
import cn.stylefeng.guns.modular.system.mapper.studentMapper;
import cn.stylefeng.guns.modular.system.mapper.studentMapperImpl;
import cn.stylefeng.guns.modular.system.model.UserDto;
import cn.stylefeng.guns.modular.system.service.FileInfoService;
import cn.stylefeng.guns.modular.system.service.NoticeService;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 通用控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController extends BaseController {

    private static stu_applicationImpl stu_application = new stu_applicationImpl();
    @Autowired
    private UserService userService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private GunsProperties gunsProperties;

    private final static Logger log = LoggerFactory.getLogger(SystemController.class);
    /**
     * 控制台页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/console")
    public String console(Model model) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        String xuehao = user.getAccount();
        int zong = stu_application.selectAll(xuehao);
        int pass = stu_application.selectpass(xuehao);
        int fail = stu_application.selectfail(xuehao);
        model.addAttribute("dai",zong-pass-fail);
        model.addAttribute("zong",zong);
        model.addAttribute("pass",pass);
        model.addAttribute("fail",fail);
        return "/modular/frame/console.html";
    }

    /**
     * 分析页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/console2")
    public String console2(Model model) {
        Users users = new Users();
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        //转换成Users，方便多表查询
        studentMapperImpl studentMapper = new studentMapperImpl();
        student student = studentMapper.selectAll(user.getUserId());
        try {
            BeanUtils.copyProperties(users,user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        users.setXibie(student.getXibie());
        users.setNianji(student.getNianji());
        users.setZhuanye(student.getZhuanye());
        users.setBanji(student.getBanji());
        users.setDychengji(student.getDychengji());
        users.setTychengji(student.getTychengji());
        users.setZychengji(student.getZychengji());
//        System.out.println(users.toString());

        model.addAllAttributes(BeanUtil.beanToMap(users));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(users.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(users.getDeptId()));
        LogObjectHolder.me().set(users);
        return "/modular/frame/console2.html";
    }

    /**
     * 跳转到首页通知
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/notice")
    public String hello() {
        List<Notice> notices = noticeService.list();
        super.setAttr("noticeList", notices);
        return "/modular/frame/notice.html";
    }

    /**
     * 主页面
     *
     * @author fengshuonan
     * @Date 2019/1/24 3:38 PM
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "/modular/frame/welcome.html";
    }

    /**
     * 主题页面
     *
     * @author fengshuonan
     * @Date 2019/1/24 3:38 PM
     */
    @RequestMapping("/theme")
    public String theme() {
        return "/modular/frame/theme.html";
    }

    /**
     * 跳转到修改密码界面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return "/modular/frame/password.html";
    }

    /**
     * 个人消息列表
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/message")
    public String message() {
        return "/modular/frame/message.html";
    }

    /**
     * 跳转到查看用户详情页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Users users = new Users();
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        //转换成Users，方便多表查询
        studentMapperImpl studentMapper = new studentMapperImpl();
        student student = studentMapper.selectAll(user.getUserId());
        try {
            BeanUtils.copyProperties(users,user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        users.setXibie(student.getXibie());
        users.setNianji(student.getNianji());
        users.setZhuanye(student.getZhuanye());
        users.setBanji(student.getBanji());
        users.setDychengji(student.getDychengji());
        users.setTychengji(student.getTychengji());
        users.setZychengji(student.getZychengji());
//        System.out.println(users.toString());

        model.addAllAttributes(BeanUtil.beanToMap(users));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(users.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(users.getDeptId()));
        LogObjectHolder.me().set(users);
        return "/modular/frame/user_info.html";
    }

    /**
     * 通用的树列表选择器
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/commonTree")
    public String deptTreeList(@RequestParam("formName") String formName,
                               @RequestParam("formId") String formId,
                               @RequestParam("treeUrl") String treeUrl, Model model) {

        if (ToolUtil.isOneEmpty(formName, formId, treeUrl)) {
            throw new RequestEmptyException("请求数据不完整！");
        }

        try {
            model.addAttribute("formName", URLDecoder.decode(formName, "UTF-8"));
            model.addAttribute("formId", URLDecoder.decode(formId, "UTF-8"));
            model.addAttribute("treeUrl", URLDecoder.decode(treeUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RequestEmptyException("请求数据不完整！");
        }

        return "/common/tree_dlg.html";
    }

    /**
     * 上传头像
     *
     * @author fengshuonan
     * @Date 2018/11/9 12:45 PM
     */
    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public Object uploadAvatar(@RequestParam String avatar) {

        if (ToolUtil.isEmpty(avatar)) {
            throw new RequestEmptyException("请求头像为空");
        }

        avatar = avatar.substring(avatar.indexOf(",") + 1);

        fileInfoService.uploadAvatar(avatar);

        return SUCCESS_TIP;
    }

    /**
     * 预览头像
     *
     * @author fengshuonan
     * @Date 2018/11/9 12:45 PM
     */
    @RequestMapping("/previewAvatar")
    @ResponseBody
    public Object previewAvatar(HttpServletResponse response) {

        ShiroUser currentUser = ShiroKit.getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        //获取当前用户的头像id
        User user = userService.getById(currentUser.getId());
        String avatar = user.getAvatar();

        //如果头像id为空就返回默认的
        if (ToolUtil.isEmpty(avatar)) {
            avatar = DefaultAvatar.BASE_64_AVATAR;
        } else {
            FileInfo fileInfo = fileInfoService.getById(avatar);
            if (fileInfo == null) {
                avatar = DefaultAvatar.BASE_64_AVATAR;
            } else {
                avatar = fileInfo.getFileData();
            }
        }

        //输出图片的文件流
        try {
            response.setContentType("image/jpeg");
            byte[] decode = Base64.decode(avatar);
            response.getOutputStream().write(decode);
        } catch (IOException e) {
            log.error("获取图片的流错误！", avatar);
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }

    /**
     * 获取当前用户详情
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/currentUserInfo")
    @ResponseBody
    public ResponseData getUserInfo() {
        Users users = new Users();
        ShiroUser currentUser = ShiroKit.getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        User user = userService.getById(currentUser.getId());
        //转换成Users，方便多表查询
        studentMapperImpl studentMapper = new studentMapperImpl();
        student student = studentMapper.selectAll(user.getUserId());
//        System.out.println("查询出来的：---------"+student.toString());

        try {
            BeanUtils.copyProperties(users,user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        users.setXibie(student.getXibie());
        users.setNianji(student.getNianji());
        users.setZhuanye(student.getZhuanye());
        users.setBanji(student.getBanji());
        users.setDychengji(student.getDychengji());
        users.setTychengji(student.getTychengji());
        users.setZychengji(student.getZychengji());

        //------------------------------------
        Map<String, Object> map = UserFactory.removeUnSafeFields(users);

        HashMap<Object, Object> hashMap = CollectionUtil.newHashMap();
        hashMap.putAll(map);
        hashMap.put("roleName", ConstantFactory.me().getRoleName(users.getRoleId()));
        hashMap.put("deptName", ConstantFactory.me().getDeptName(users.getDeptId()));
//        System.out.println(hashMap.toString());
        return ResponseData.success(hashMap);
    }

    /**
     * layui上传组件 通用文件上传接口
     *
     * @author fengshuonan
     * @Date 2019-2-23 10:48:29
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData layuiUpload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        return ResponseData.success(0, "上传成功", map);
    }

    @RequestMapping("/shenqing")
    @ResponseBody
    public ResponseData add(@Valid shenqing shenqing, BindingResult result) {
        System.out.println(shenqing.toString());
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            shenqing.setState("待审核");
            int num = stu_application.insert(shenqing);
            if(num==0){
                throw new ServiceException(BizExceptionEnum.SERVER_ERROR);
            }
        }catch (Exception e){
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR);
        }

        return SUCCESS_TIP;
    }
}
