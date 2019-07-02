package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.shenqing;

import java.util.List;

public interface quanxian {
    String role(String quanxian);
    List<shenqing> guanliyuan();
    List<shenqing> jiaoshi();
}
