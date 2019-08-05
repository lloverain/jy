package cn.stylefeng.guns.modular.application.mapper;

import cn.stylefeng.guns.modular.application.entity.Grant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("GrantMapperRepository")
public interface GrantMapper extends BaseMapper<Grant> {

    int insertgrant(Grant grant);

    List<Grant> selectgrant(@Param("studentId") String studentId);

}
