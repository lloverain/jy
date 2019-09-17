package cn.stylefeng.guns.modular.application.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.service.GrantService;
import cn.stylefeng.guns.modular.application.service.ReviewService;
import cn.stylefeng.guns.modular.application.util.AGrantUtil;
import cn.stylefeng.guns.modular.student.entity.Stu;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    private GrantService grantService;

    @Autowired
    private ReviewService reviewService;

    private static String ROOTS = "/modular/review/";

    /**
     * 进入审核页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/review")
    public String review(Model model) {
        logger.debug("进入审核页面");
        return ROOTS + "review.html";
    }

    /**
     * 查看学生的审核
     *
     * @param basis
     * @return
     */
    @RequestMapping("/review/selectAll")
    @ResponseBody
    public Object selectAllStudentsReview(@RequestParam(required = false) String basis) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        IPage page = reviewService.selectAllStudentsReview(user);
        return LayuiPageFactory.createPageInfo(page);
    }


    @RequestMapping("/review/totoExamine")
    public String showImage(@RequestParam String applyId, Model model) {
        logger.debug("进入添加审核意见页面");
        model.addAttribute("applyId", applyId);
        return ROOTS + "/review/toExamine.html";
    }
    @RequestMapping("/review/selectAuditRemark")
    @ResponseBody
    public Object selectAuditRemark(@RequestParam String applyId){
        logger.debug("查询审核意见和备注");
        IPage page = reviewService.selectAuditRemark(applyId);
        return LayuiPageFactory.createPageInfo(page);
//        Long userId = ShiroKit.getUserNotNull().getId();
//        User user = this.userService.getById(userId);
//        IPage page = reviewService.selectAuditRemark(applyId);
//        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 下载
     * @param studentId
     * @param bonusType
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/review/down")
    @ResponseBody
    public void down(@RequestParam String studentId, @RequestParam String bonusType,HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println("showimage:" + studentId + bonusType);
        Long applyId = grantService.selectApplyId(studentId, bonusType);
        String path = grantService.selectFilePath(applyId);//获取文件路径
        AGrantUtil aGrantUtil = new AGrantUtil();
        ArrayList<File> files = aGrantUtil.getFiles(path);

        String fileSaveRootPath = "D:\\File\\";
        //得到要下载的文件
        BufferedInputStream bis = null;
        byte[] buffer = new byte[0];
        int read = 0;
        //先压缩
        String zipName = studentId+".zip";

        String zipPath = fileSaveRootPath + zipName;
        ZipOutputStream zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
        for(int i =0;i<files.size();i++){
            ZipEntry zEntry = new ZipEntry(files.get(i).getName());
            zipOutput.putNextEntry(zEntry);
             bis = new BufferedInputStream(new FileInputStream(files.get(i)));
             buffer = new byte[1024];
            while((read = bis.read(buffer)) != -1){
                zipOutput.write(buffer, 0, read);
            }

        }
        bis.close();
        zipOutput.close();
        //创建输出流，下载zip
        try(OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(new File(zipPath));){
            //设置响应头，控制浏览器下载该文件
            response.setHeader("Content-Type","application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename="+java.net.URLEncoder.encode(zipName, "UTF-8"));
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
            System.out.println("zip下载路径："+zipPath);
        }finally {
            try {
                //删除压缩包
                File zfile = new File(zipPath);
                zfile.delete();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    /**
     * 删除学生的申请
     *
     * @param studentId
     * @param bonusType
     * @return
     */
    @RequestMapping("/review/deletereview")
    @ResponseBody
    public ResponseData deletereview(@RequestParam String studentId, @RequestParam String bonusType) {
        this.reviewService.deletereview(studentId, bonusType);
        return SUCCESS_TIP;
    }

    /**
     * 审核不通过
     *
     * @return
     */
    @RequestMapping("/review/toExamine")
    @ResponseBody
    public ResponseData pass(@RequestParam Long applyId) {
        logger.debug("审核接收数据:" + applyId);
        int num = this.reviewService.nopass(String.valueOf(applyId));
        return SUCCESS_TIP;
    }

    /**
     * 审核通过
     *
     * @param applyId
     * @param auditComments
     * @param remarks
     * @return
     */
    @RequestMapping("/review/addOpinion")
    @ResponseBody
    public String opinion(@RequestParam Long applyId, @RequestParam String auditComments, @RequestParam String remarks) {
        int num = this.reviewService.addOpinion(applyId, auditComments, remarks);
        if (num == 1) {
            return "1";
        }
        return "0";
    }
}
