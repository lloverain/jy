package cn.stylefeng.guns.modular.application.mapper;

import cn.stylefeng.guns.modular.application.entity.Grant;
import cn.stylefeng.guns.modular.application.entity.StudentComment;
import cn.stylefeng.guns.modular.application.entity.StudentFamily;
import cn.stylefeng.guns.modular.application.entity.StudentGrant;
import cn.stylefeng.guns.modular.student.entity.Stu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("GrantMapperRepository")
public interface GrantMapper extends BaseMapper<Grant> {
    /**
     * 上传student_grant
     * @param studentGrant
     * @return
     */
    int insertgrant(StudentGrant studentGrant);

    /**
     * 上传student_family
     * @param studentFamily
     * @return
     */
    int insertfamily(StudentFamily studentFamily);

    /**
     * 上传student_comment
     * @param studentComment
     * @return
     */
    int insertcomment(StudentComment studentComment);

    /**
     * 查询申请Id
     * @param studentId
     * @param bonusType
     * @return
     */
    Long select_applyId(@Param("studentId") String studentId,
                        @Param("bonusType") String bonusType);

    Stu selectStudentGrant(@Param("studentId") String studentId,@Param("bonusType") String bonusType);//根据学生id查询该申请资料

    StudentGrant selectNameAndMaterial(@Param("applyId") Long applyId);

    /**
     * 查询是否申请过
     * @param studentId
     * @param bonusType
     * @return
     */
    int select_repeat(@Param("studentId") String studentId,
                              @Param("bonusType") String bonusType);

    /**
     * 查询审核的状态
     * @return
     */
    StudentComment select_state(@Param("applyId")Long applyId);

    /**
     * 更新grant
     * @param studentGrant
     * @return
     */
    int updateStudentGrant(StudentGrant studentGrant);

    /**
     * 跟新family
     * @param studentFamily
     * @return
     */
    int updateStudentFamily(StudentFamily studentFamily);

    /**
     * 更新comment
     * @param studentComment
     * @return
     */
    int updateStudentComment(StudentComment studentComment);

    String selectFilePath(@Param("applyId") Long applyId);
}
