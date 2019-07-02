package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.shenqing;
import cn.stylefeng.guns.modular.system.entity.student;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class studentMapperImpl implements studentMapper {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
    @Override
    public student selectAll(Long id) {
        return sqlSession.selectOne("student.selectAll",id);
    }

    @Override
    public String selectID(String xuehao) {
        return sqlSession.selectOne("student.selectID",xuehao);
    }

    @Override
    public int updata(student student) {
        return sqlSession.update("student.updata",student);
    }

    @Override
    public int insert(student student) {
        return sqlSession.insert("student.insert",student);
    }

    @Override
    public List<shenqing> selectAllshenqing(String account) {
        return sqlSession.selectList("student.selectAllshenqing",account);
    }
}
