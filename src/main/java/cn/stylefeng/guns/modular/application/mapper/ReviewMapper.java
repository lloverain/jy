package cn.stylefeng.guns.modular.application.mapper;

import cn.stylefeng.guns.modular.application.entity.StudentComment;
import cn.stylefeng.guns.modular.application.entity.review;
import cn.stylefeng.guns.modular.student.entity.Stu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.application.mapper
 * @date 2019/8/12 10:16
 */
@Repository("ReviewMapperRepository")
public interface ReviewMapper extends BaseMapper<review> {

    /**
     * 查询申请信息
     * @param deptId 班级
     * @param auditDepartment 审核部门
     * @param examineState  审核结果
     * @return
     */
    List<Stu> selectAllStudentsReview(@Param("deptId") String deptId,
                                      @Param("auditDepartment") String auditDepartment,
                                      @Param("examineState") String examineState
    );

    /**
     * 审核通过和不通过
     * @param studentComment
     * @return
     */
    int toExamine(StudentComment studentComment);

    /**
     * 根据applyId查询studentcomment
     * @param ApplyId
     * @return
     */
    StudentComment selectStudentComment(Long ApplyId);

    int deletereview(@Param("studentId") String studentId, @Param("bonusType") String bonusType);

}
