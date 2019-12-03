package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.entity.Examine;
import cn.stylefeng.guns.modular.application.entity.StudentComment;
import cn.stylefeng.guns.modular.application.entity.review;
import cn.stylefeng.guns.modular.application.mapper.ReviewMapper;
import cn.stylefeng.guns.modular.application.util.AGrantUtil;
import cn.stylefeng.guns.modular.system.entity.Notice;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.NoticeService;
import cn.stylefeng.guns.modular.system.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    public IPage selectAuditRemark(String applyId) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = userService.getById(userId);
        List<Examine> examineList = new LinkedList<>();

        if (!"1141523474871881730".equals(user.getRoleId())) {
            StudentComment studentComment = reviewMapper.selectStudentComment(Long.valueOf(applyId));
            String audit = studentComment.getAuditComments();
            String remark = studentComment.getRemarks();
            JSONArray auditJson = JSONArray.parseArray(audit);
            JSONArray remarkJson = JSONArray.parseArray(remark);

            for (int i = 0; i < auditJson.size(); i++) {
                Examine examine = new Examine();
                JSONObject jsonObject = auditJson.getJSONObject(i);
                JSONObject jsonObject1 = remarkJson.getJSONObject(i);
                examine.setTeacherId(jsonObject.getString("id"));
                examine.setAuditComment(jsonObject.getString("audit"));
                examine.setRemark(jsonObject1.getString("remark"));
                examineList.add(examine);
            }
        } else {
            examineList = null;
        }

        Page page = LayuiPageFactory.defaultPage();
        page.setRecords(examineList);
        return page;
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
     * 根据applyid查询studentcomment
     *
     * @param applyId
     * @return
     */
    public StudentComment selectStudentComment(Long applyId) {
        return reviewMapper.selectStudentComment(applyId);
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
    public int addOpinion(Long applyId, String auditComments, String remarks) throws JSONException {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        String role = user.getRoleId();
        AGrantUtil aGrantUtil = new AGrantUtil();

        JSONObject auditJson = new JSONObject();
        auditJson.put("id", user.getAccount());
        auditJson.put("audit", auditComments);

        JSONObject remarkJson = new JSONObject();
        remarkJson.put("id", user.getAccount());
        remarkJson.put("remark", remarks);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(auditJson);
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(remarkJson);
        if ("1141523474871881730".equals(role)) {
            //辅导员
            StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, Long.valueOf(role), user.getName(), 1, String.valueOf(jsonArray), String.valueOf(jsonArray1));
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733288554147841".equals(role)) {
            //系领导
            StudentComment tiqu = reviewMapper.selectStudentComment(applyId);
            String shenheJson = tiqu.getAuditComments();//old
            String beizhuJson = tiqu.getRemarks();//old
            JSONArray jsonArray2 = JSONArray.parseArray(shenheJson);

            for (Object o : jsonArray) {
                jsonArray2.add(o);
            }
            JSONArray jsonArray3 = JSONArray.parseArray(beizhuJson);

            for (Object o : jsonArray1) {
                jsonArray3.add(o);
            }
            StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, Long.valueOf(role), user.getName(), 1, String.valueOf(jsonArray2), String.valueOf(jsonArray3));
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733430061576194".equals(role)) {
            //教务处
            StudentComment tiqu = reviewMapper.selectStudentComment(applyId);
            String shenheJson = tiqu.getAuditComments();//old
            String beizhuJson = tiqu.getRemarks();//old
            JSONArray jsonArray2 = JSONArray.parseArray(shenheJson);

            for (Object o : jsonArray) {
                jsonArray2.add(o);
            }
            JSONArray jsonArray3 = JSONArray.parseArray(beizhuJson);

            for (Object o : jsonArray1) {
                jsonArray3.add(o);
            }
            StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, Long.valueOf(role), user.getName(), 1, String.valueOf(jsonArray2), String.valueOf(jsonArray3));
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733580544815105".equals(role)) {
            //学院评审
            StudentComment tiqu = reviewMapper.selectStudentComment(applyId);
            String shenheJson = tiqu.getAuditComments();//old
            String beizhuJson = tiqu.getRemarks();//old
            JSONArray jsonArray2 = JSONArray.parseArray(shenheJson);

            for (Object o : jsonArray) {
                jsonArray2.add(o);
            }
            JSONArray jsonArray3 = JSONArray.parseArray(beizhuJson);

            for (Object o : jsonArray1) {
                jsonArray3.add(o);
            }
            StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, Long.valueOf(role), user.getName(), 1, String.valueOf(jsonArray2), String.valueOf(jsonArray3));
            return reviewMapper.toExamine(studentComment);
        } else if ("1160733692717281281".equals(role)) {
            //学院领导
            StudentComment tiqu = reviewMapper.selectStudentComment(applyId);
            String shenheJson = tiqu.getAuditComments();//old
            String beizhuJson = tiqu.getRemarks();//old
            JSONArray jsonArray2 = JSONArray.parseArray(shenheJson);

            for (Object o : jsonArray) {
                jsonArray2.add(o);
            }
            JSONArray jsonArray3 = JSONArray.parseArray(beizhuJson);

            for (Object o : jsonArray1) {
                jsonArray3.add(o);
            }
            StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, Long.valueOf(role), user.getName(), 1, String.valueOf(jsonArray2), String.valueOf(jsonArray3));

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
