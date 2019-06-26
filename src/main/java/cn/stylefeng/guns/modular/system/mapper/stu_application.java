package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.shenqing;

public interface stu_application {
    int insert(shenqing shenqing);//申请插入
    int selectAll(String account);//个人申请总数
    int selectpass(String account);//申请通过数量
    int selectfail(String account);//申请失败数量
}
