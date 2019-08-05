package cn.stylefeng.guns.modular.application.controller;
import cn.stylefeng.guns.modular.application.service.GrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/application")
public class GrantController {

    @Autowired
    private GrantService grantService;

    @RequestMapping("/grant")
    public void test(){

    }
}
