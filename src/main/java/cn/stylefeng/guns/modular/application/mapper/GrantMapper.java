package cn.stylefeng.guns.modular.application.mapper;

import cn.stylefeng.guns.modular.application.entity.Grant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("GrantMapperRepository")
public interface GrantMapper extends BaseMapper<Grant> {

    int insertgrant(Grant grant);//申请

    List<Grant> selectgrant(@Param("studentId") String studentId);//根据学生id查询该申请资料

    List<Grant> select_repeat(@Param("studentId") String studentId,
                              @Param("bonusType") String bonusType);//根据stuid和奖金类型查询是否申请；

    String select_dept_id(@Param("studentId") String studentId);//根据id查班级

    String select_state(@Param("studentId")String studentId,
                        @Param("bonusType") String bonusType);//查询申请状态

    int updatagrant(Grant grant);
}
