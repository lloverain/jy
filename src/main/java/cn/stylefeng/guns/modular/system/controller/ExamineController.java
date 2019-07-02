package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.entity.Users;
import cn.stylefeng.guns.modular.system.entity.shenqing;
import cn.stylefeng.guns.modular.system.entity.student;
import cn.stylefeng.guns.modular.system.mapper.quanxianImpl;
import cn.stylefeng.guns.modular.system.mapper.studentMapperImpl;
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
    private static studentMapperImpl studentMapper = new studentMapperImpl();
    private static quanxianImpl jiancha = new quanxianImpl();
    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public String toexamine(Model model){
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        String quanxian = user.getRoleId();
        String role = jiancha.role(quanxian);
        if("超级管理员".equals(role)){

        }
        if("教师".equals(role)){

        }
        List<shenqing> shenqing_list = studentMapper.selectAllshenqing(user.getAccount());
        System.out.println(shenqing_list);
        List<Users> userList = new ArrayList<Users>();
        for(int i = 0;i<shenqing_list.size();i++){
            Users users = new Users();
            try {
                BeanUtils.copyProperties(users,user);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            users.setPhone(shenqing_list.get(i).getPhone());
            users.setXibie(shenqing_list.get(i).getXibie());
            users.setNianji(shenqing_list.get(i).getNianji());
            users.setZhuanye(shenqing_list.get(i).getZhuanye());
            users.setBanji(shenqing_list.get(i).getBanji());
            users.setDychengji(shenqing_list.get(i).getDychengji());
            users.setTychengji(shenqing_list.get(i).getTychengji());
            users.setZychengji(shenqing_list.get(i).getZychengji());
            users.setState(shenqing_list.get(i).getState());
            users.setPrize(shenqing_list.get(i).getPrize());
            users.setFudaoyuan(shenqing_list.get(i).getFudaoyuan());
            users.setJiaowuchu(shenqing_list.get(i).getJiaowuchu());
            users.setXueyuan(shenqing_list.get(i).getXueyuan());
            userList.add(users);
        }
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(userList));
        model.addAttribute("list",jsonArray.toString());
        return PREFIX+"examine.html";
    }
}
