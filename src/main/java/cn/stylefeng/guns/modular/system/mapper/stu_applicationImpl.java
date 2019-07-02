package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Users;
import cn.stylefeng.guns.modular.system.entity.shenqing;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class stu_applicationImpl implements stu_application {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
    @Override
    public int insert(shenqing shenqing) {
        return sqlSession.insert("student.shenqing_insert",shenqing);
    }

    @Override
    public int selectAll(String account) {
        return sqlSession.selectOne("student.shenqing_zongji",account);
    }

    @Override
    public int selectpass(String account) {
        return sqlSession.selectOne("student.shenqing_pass",account);
    }

    @Override
    public int selectfail(String account) {
        return sqlSession.selectOne("student.shenqing_fail",account);
    }

    @Override
    public int selectrepeat(Users users) {
        return sqlSession.selectOne("student.shengqing_repeat",users);
    }
}
