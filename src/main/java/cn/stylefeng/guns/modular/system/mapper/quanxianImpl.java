package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.shenqing;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class quanxianImpl implements quanxian{
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
    @Override
    public String role(String quanxian) {
        return sqlSession.selectOne("student.role",quanxian);
    }

    @Override
    public List<shenqing> guanliyuan() {
        return sqlSession.selectList("student.guanliyuan");
    }

    @Override
    public List<shenqing> jiaoshi() {
        return sqlSession.selectList("student.jiaoshi");
    }
}
