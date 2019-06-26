package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.student;

public interface studentMapper {
    student selectAll(Long id);//查询student表所有信息
    String selectID(String xuehao);//根据账户查主键
    int updata(student student);//更新信息
    int insert(student student);//插入信息

}
