package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.application.entity.Grant;
import cn.stylefeng.guns.modular.application.entity.StudentComment;
import cn.stylefeng.guns.modular.application.entity.StudentFamily;
import cn.stylefeng.guns.modular.application.entity.StudentGrant;
import cn.stylefeng.guns.modular.application.mapper.GrantMapper;
import cn.stylefeng.guns.modular.application.util.AGrantUtil;
import cn.stylefeng.guns.modular.student.entity.Stu;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GrantService extends ServiceImpl<GrantMapper, Grant> {

    @Autowired
    private GrantMapper grantMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;
    private static Logger logger = LoggerFactory.getLogger(GrantService.class);

    /**
     * 上传到student_grant
     *
     * @param studentGrant
     * @return
     */
    public int insertGrant(StudentGrant studentGrant) {
        logger.debug("开始上传到student_grant");
        return grantMapper.insertgrant(studentGrant);
    }

    /**
     * 上传到studentFamily
     *
     * @param studentFamily
     * @return
     */
    public int insertFamily(StudentFamily studentFamily) {
        logger.debug("开始上传到student_family");
        return grantMapper.insertfamily(studentFamily);
    }

    /**
     * 上传到studentComment
     *
     * @param studentComment
     * @return
     */
    public int insertComment(StudentComment studentComment) {
        logger.debug("开始上传到student_comment");
        return grantMapper.insertcomment(studentComment);
    }

    /**
     * 查询申请ID
     *
     * @param studentId
     * @param bonusType
     * @return
     */
    public Long selectApplyId(String studentId, String bonusType) {
        logger.debug("开始查询申请Id");
        return grantMapper.select_applyId(studentId, bonusType);
    }

    /**
     * 查询状态
     * @param applyId 申请id
     * @return
     */
    public StudentComment selectStudentComment(Long applyId){
       return grantMapper.select_state(applyId);
    }

    /**
     * 查询是否申请过
     *
     * @param studentId
     * @param bonusType
     * @return
     */
    public int select_repeat(String studentId, String bonusType) {
        logger.debug("开始查询是否申请过");
        return grantMapper.select_repeat(studentId, bonusType);
    }

    /**
     * 查询申请状态
     *
     * @param studentId
     * @param bonusType
     * @return
     */
    public String select_state(String studentId, String bonusType) {
        logger.debug("开始查询申请状态");
        int repeat = select_repeat(studentId, bonusType);
        logger.debug("申请:"+repeat);
        if (repeat == 1) {
            Long applyId = selectApplyId(studentId, bonusType);
            StudentComment studentComment = grantMapper.select_state(applyId);
            if (studentComment.getExamineState() == 2) {
                return "2";
            } else if ("1160733692717281281".equals(String.valueOf(studentComment.getAuditDepartment())) && studentComment.getExamineState() == 1) {
                return "1";
            } else {
                return "3";
            }
        } else {
            return "0";
        }
    }

    /**
     * 查询单个学生的申请信息
     *
     * @param studentId 学号
     * @param bonusType 奖项
     * @return
     */
    public Stu selectStudentGrant(String studentId, String bonusType) {
        logger.debug("开始查询单人学生的所有申请");
        Stu stu = grantMapper.selectStudentGrant(studentId, bonusType);
        StudentGrant studentGrant = grantMapper.selectNameAndMaterial(stu.getApplyId());
        stu.setStudentName(studentGrant.getName());
        return stu;
    }

    /**
     * 更新申请信息
     *
     * @param stu
     * @return 2是成功，3失败
     */
    public int updataGrant(Stu stu, String bonusType) {
        AGrantUtil aGrantUtil = new AGrantUtil();
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);
        stu = aGrantUtil.fillGrant(stu, user);
        String path = null;
        for (MultipartFile f : stu.getMaterial()) {
            try {
                path = AGrantUtil.savePic(f.getInputStream(), f.getOriginalFilename(), user.getAccount(),bonusType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Long applyId = selectApplyId(user.getAccount(), bonusType);
        stu.setApplyId(applyId);


        StudentGrant studentGrant = aGrantUtil.studentGrantcopy(stu, path);
        studentGrant.setApplyId(applyId);
        StudentFamily studentFamily = aGrantUtil.studentFamilycopy(stu, applyId);
        StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, null, "", 3, "", "");
        int grant = this.grantMapper.updateStudentGrant(studentGrant);
        if (grant == 1) {
            int family = this.grantMapper.updateStudentFamily(studentFamily);
            if (family == 1) {
                int comment = this.grantMapper.updateStudentComment(studentComment);
                if (comment == 1) {
                    return 2;
                } else {
                    logger.debug("插入comment失败");
                    return 3;
                }
            } else {
                logger.debug("插入family失败");
                return 3;
            }
        } else {
            logger.debug("更新grant表失败");
            return 3;
        }
    }

    /**
     * 把申请信息添加到3张表
     *
     * @param stu
     * @param user
     * @return
     */
    public int addgrant(Stu stu, User user) {
        AGrantUtil aGrantUtil = new AGrantUtil();
        stu = aGrantUtil.fillGrant(stu, user);
        int repeat = select_repeat(String.valueOf(stu.getStudentId()), stu.getBonusType());
        logger.debug("上传申请信息前检查是否申请过:" + repeat);
        if (repeat == 1) {
            logger.debug("申请过了");
            return 1;
        } else {
            logger.debug("没申请过，正在申请中");
            String path = null;
            for (MultipartFile f : stu.getMaterial()) {
                try {
                    path = AGrantUtil.savePic(f.getInputStream(), f.getOriginalFilename(), user.getAccount(),"助学金");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            StudentGrant studentGrant = aGrantUtil.studentGrantcopy(stu, path);
            logger.debug("开始插入student_grant表，数据：" + studentGrant.toString());
            int returngrant = this.insertGrant(studentGrant);
            System.out.println(returngrant);
            Long applyId = studentGrant.getApplyId();
            if (returngrant == 1) {
                /**
                 * 插入student_grant成功
                 */
                logger.debug("插入student_grant成功");
                StudentFamily studentFamily = aGrantUtil.studentFamilycopy(stu, applyId);
                int returnfamily = this.insertFamily(studentFamily);
                logger.debug("开始插入family，数据：" + studentFamily.toString());
                if (returnfamily == 1) {
                    /**
                     * 插入student_family成功
                     */
                    logger.debug("插入student_family成功");
                    StudentComment studentComment = aGrantUtil.studentCommentcopy(applyId, null, "", 3, "", "");
                    int returncomment = this.insertComment(studentComment);
                    logger.debug("开始插入comment，数据：" + studentComment.toString());
                    if (returncomment == 1) {
                        /**
                         * 这里是最终结果的产生
                         * 插入student_comment成功
                         */
                        logger.debug("插入student_comment成功");
                        return 2;
                    } else {
                        /**
                         * 插入student_comment失败
                         */
                        logger.debug("插入student_comment失败！");
                        return 3;
                    }

                } else {
                    /**
                     * 插入student_family失败
                     */
                    logger.debug("插入student_family失败");
                    return 3;
                }
            } else {
                /**
                 * 插入student_grant失败
                 */
                logger.debug("插入student_grant失败");
                return 3;
            }
        }
    }

public String selectFilePath(Long applyId){
        return grantMapper.selectFilePath(applyId);
}

}
