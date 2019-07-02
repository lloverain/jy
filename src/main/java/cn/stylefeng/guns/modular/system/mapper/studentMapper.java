package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.shenqing;
import cn.stylefeng.guns.modular.system.entity.student;

import java.util.List;
import java.util.Map;

public interface studentMapper {
    student selectAll(Long id);//查询student表所有信息
    String selectID(String xuehao);//根据账户查主键
    int updata(student student);//更新信息
    int insert(student student);//插入信息
    List<shenqing> selectAllshenqing(String account);
}
