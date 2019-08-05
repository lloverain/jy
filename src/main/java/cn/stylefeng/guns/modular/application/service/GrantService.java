package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.modular.application.entity.Grant;
import cn.stylefeng.guns.modular.application.mapper.GrantMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GrantService extends ServiceImpl<GrantMapper, Grant> {

    @Autowired
    private GrantMapper grantMapper;

    private static Logger logger = LoggerFactory.getLogger(GrantService.class);

    public int insertGrant(Grant grant){
        System.out.println(grant.toString());
        logger.debug("开始插入");
        return grantMapper.insertgrant(grant);
    }
    public List<Grant> selectGrant(String studentId){

        return  grantMapper.selectgrant(studentId);
    }
}
