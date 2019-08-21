package cn.stylefeng.guns.modular.application.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.service.ReviewService;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.application.controller
 * @date 2019/8/12 10:12
 */
@Controller
public class ReviewController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    private static String ROOTS = "/modular/review/";

    /**
     * 进入审核页面
     * @param model
     * @return
     */
    @RequestMapping("/review")
    public String review(Model model){
        logger.debug("进入审核页面");
        return ROOTS+"review.html";
    }

    /**
     * 查看学生的审核
     * @param basis
     * @return
     */
    @RequestMapping("/review/selectAll")
    @ResponseBody
    public Object selectAllStudentsReview(@RequestParam(required = false) String basis){
        IPage page = null;
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        page = reviewService.selectAllStudentsReview(user);

        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 进入图片查看页面
     * @param studentId
     * @param bonusType
     * @return
     */
    @RequestMapping("/review/toiamge")
    public String showImage(@RequestParam String studentId,@RequestParam String bonusType){
        logger.debug("进入查看证明页面");
        String image = reviewService.selectImage(studentId,bonusType);
        LogObjectHolder.me().set(image);
        return ROOTS+"/review/review_showimage.html";
    }

    /**
     * 教师以上查询图片
     * @param studentId
     * @param bonusType
     * @return 返回的是base64的图片
     */
    @RequestMapping("/review/showimage")
    @ResponseBody
    public String iamge(@RequestParam String studentId,@RequestParam String bonusType){
        try {
            bonusType = new String(bonusType.getBytes("ISO8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return reviewService.selectImage(studentId,bonusType);
    }

    /**
     * 学生自己查询图片
     * @param studentId
     * @param bonusType
     * @return
     */
    @RequestMapping("/review/stu_showimage")
    @ResponseBody
    public String iamges(@RequestParam String studentId,@RequestParam String bonusType){
        return reviewService.selectImage(studentId,bonusType);
    }

    /**
     * 删除学生的申请
     * @param studentId
     * @param bonusType
     * @return
     */
    @RequestMapping("/review/deletereview")
    @ResponseBody
    public ResponseData deletereview(@RequestParam String studentId, @RequestParam String bonusType){
        this.reviewService.deletereview(studentId,bonusType);
        return SUCCESS_TIP;
    }

    /**
     * 审核
     * @param studentId
     * @param bonusType
     * @return
     */
    @RequestMapping("/review/pass")
    @ResponseBody
    public ResponseData pass(@RequestParam String studentId, @RequestParam String bonusType){
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        this.reviewService.toExamine(user,studentId,bonusType);
        return SUCCESS_TIP;
    }

    @RequestMapping("/review/nopass")
    @ResponseBody
    public ResponseData nopass(@RequestParam String studentId, @RequestParam String bonusType){
        this.reviewService.nopass(studentId, bonusType);
        return SUCCESS_TIP;
    }
    /**
     * 解密base64位输入流
     * @param file
     */
    private ByteArrayInputStream inputstream(String file){
        //将字符串转换为byte数组
        byte[] bytes = new byte[0];
        try {
            //解密，转byte
            bytes = new BASE64Decoder().decodeBuffer(file.trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
        //转化为输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return inputStream;

    }
}
