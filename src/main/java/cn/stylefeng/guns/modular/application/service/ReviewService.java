package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.entity.StudentComment;
import cn.stylefeng.guns.modular.application.entity.review;
import cn.stylefeng.guns.modular.application.mapper.ReviewMapper;
import cn.stylefeng.guns.modular.application.util.AGrantUtil;
import cn.stylefeng.guns.modular.system.entity.Notice;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.NoticeService;
import cn.stylefeng.guns.modular.system.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.application.service
 * @date 2019/8/13 9:59
 */
@Service
public class ReviewService extends ServiceImpl<ReviewMapper, review> {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(ReviewMapper.class);

    /**
     * 查询学生审请的数据
     *
     * @return
     */
    public IPage selectAllStudentsReview(User user) {
        logger.debug("开始查询学生申请的数据");
        Page page = LayuiPageFactory.defaultPage();
        Long dept_id = user.getDeptId();
        String role = user.getRoleId();
        if ("1141523474871881730".equals(role)) {
            //辅导员
            logger.debug(reviewMapper.selectAllStudentsReview(String.valueOf(dept_id), null, "3").toString());
            return page.setRecords(reviewMapper.selectAllStudentsReview(String.valueOf(dept_id), null, "3"));
        } else if ("1160733288554147841".equals(role)) {
            //系领导
            return page.setRecords(reviewMapper.selectAllStudentsReview(String.valueOf(dept_id), "1141523474871881730", "1"));
        } else if ("1160733430061576194".equals(role)) {
            //教务处
            return page.setRecords(reviewMapper.selectAllStudentsReview(null, "1160733288554147841", "1"));
        } else if ("1160733580544815105".equals(role)) {
            //学院评审
            return page.setRecords(reviewMapper.selectAllStudentsReview(null, "1160733430061576194", "1"));
        } else if ("1160733692717281281".equals(role)) {
            //学院领导
            return page.setRecords(reviewMapper.selectAllStudentsReview(null, "1160733580544815105", "1"));
        } else if ("1141523550411296769".equals(role)) {
            //学生

        } else if ("1".equals(role)) {
            return page.setRecords(reviewMapper.selectAllStudentsReview(null, null, null));
        } else {
            return null;
        }

        return null;
    }

//    public String selectImage(String studentId,String bonusType){
//        logger.debug("开始查询证明图片");
//        return reviewMapper.selectImage(studentId,bonusType);
//    }

    public int deletereview(String studentId, String bonusType) {
        logger.debug("学号为" + studentId + "，开始删除" + bonusType);
        return reviewMapper.deletereview(studentId, bonusType);
    }

    /**
     * 审核不通过
     *
     * @param applyId
     * @return
     */
    public int nopass(String applyId) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        AGrantUtil aGrantUtil = new AGrantUtil();
        StudentComment studentComment = aGrantUtil.studentCommentcopy(Long.valueOf(applyId), Long.valueOf(user.getRoleId()), user.getName(), 2, "", "");
        return reviewMapper.toExamine(studentComment);
    }


    /**
     * 审核通过
     *
     * @param applyId
     * @param auditComments
     * @param remarks
     * @return
     */
    public int addOpinion(Long applyId, String auditComments, String remarks) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        String role = user.getRoleId();
        AGrantUtil aGrantUtil = new AGrantUtil();

        StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, Long.valueOf(role), user.getName(), 1, auditComments, remarks);

        if ("1141523474871881730".equals(role)) {
            //辅导员
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733288554147841".equals(role)) {
            //系领导
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733430061576194".equals(role)) {
            //教务处
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733580544815105".equals(role)) {
            //学院评审
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733692717281281".equals(role)) {
            //学院领导
            Notice notice = new Notice();
            notice.setTitle("管理员");
            notice.setContent("助学金审核通过！");
            notice.setCreateUser(Long.valueOf(1));
            notice.setJurisdiction("1");
            notice.setCreateTime(new Date());
            this.noticeService.save(notice);
            return reviewMapper.toExamine(studentComment);
        }
        return 0;
    }

}
