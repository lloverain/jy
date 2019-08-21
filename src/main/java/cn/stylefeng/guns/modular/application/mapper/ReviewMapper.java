package cn.stylefeng.guns.modular.application.mapper;

import cn.stylefeng.guns.modular.application.entity.review;
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

    List<review> selectAllStudentsReview(@Param("page") Page<review> page,
                                         @Param("dept_id") String dept_id,
                                         @Param("studentId") String studentId,
                                         @Param("name") String name,
                                         @Param("instructor") String instructor,
                                         @Param("firstInstance") String firstInstance,
                                         @Param("secondInstance") String secondInstance,
                                         @Param("threeInstance") String threeInstance,
                                         @Param("fourInstance") String fourInstance,
                                         @Param("state") String state
        );

    String selectImage(@Param("studentId") String studentId,@Param("bonusType") String bonusType);

    int toExamine(@Param("studentId") String studentId,
                  @Param("bonusType") String bonusType,
                  @Param("instructor") String instructor,
                  @Param("firstInstance") String firstInstance,
                  @Param("secondInstance") String secondInstance,
                  @Param("threeInstance") String threeInstance,
                  @Param("fourInstance") String fourInstance,
                  @Param("state") String state
    );

    int deletereview(@Param("studentId") String studentId,@Param("bonusType") String bonusType);

    int nopass(@Param("studentId") String studentId,@Param("bonusType") String bonusType);
}
