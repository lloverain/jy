package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.entity.Users;
import cn.stylefeng.guns.modular.system.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/examine")
public class ExamineController {
    private static String PREFIX = "/modular/frame/";
    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public String toexamine(Model model){
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        return PREFIX+"examine.html";
    }
}
