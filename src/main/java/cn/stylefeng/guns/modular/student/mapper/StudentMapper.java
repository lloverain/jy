package cn.stylefeng.guns.modular.student.mapper;

import cn.stylefeng.guns.modular.student.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author FM
 * @date 2019/7/22 20:47
 */

@Repository("StudentMapperRepository")
public interface StudentMapper extends BaseMapper<Student> {

    //插入一组学生
    int importStudent(List<Student> list) throws DataAccessException;

    //查询学生信息
    List<Student> selectStudent(@Param("page") Page<Student> page,@Param("studentId") String studentId);

    //删除学生
    int deleteStudent(@Param("studentId") String studentId);

    //更新学生信息
    int updateStudent(@Param("studentId") String studentId,@Param("name") String name,@Param("sex") String sex,@Param("age") String age,@Param("idCart") String idCart,@Param("phone") String phone,@Param("address") String address,@Param("politicalStatus") String politicalStatus);

}
