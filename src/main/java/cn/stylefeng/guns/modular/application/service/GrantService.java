package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.modular.application.entity.Grant;
import cn.stylefeng.guns.modular.application.mapper.GrantMapper;
import cn.stylefeng.guns.modular.student.entity.Stu;
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

    @Autowired
    private ReviewService reviewService;
    private static Logger logger = LoggerFactory.getLogger(GrantService.class);

    public int insertGrant(Grant grant){
        logger.debug("开始申请");
        return grantMapper.insertgrant(grant);
    }


    public List<Grant> selectGrant(String studentId){
        logger.debug("开始查询个人学生的所有申请");
        return  grantMapper.selectgrant(studentId);
    }

    public List<Grant> select_repeat(String studentId,String bonusType){
        logger.debug("开始查询是否申请过");
        return grantMapper.select_repeat(studentId,bonusType);
    }

    public String select_dept_id(String studentId){
        return grantMapper.select_dept_id(studentId);
    }


    public String select_state(String studentId,String bonusType){
        logger.debug("开始查询申请状态");
        return grantMapper.select_state(studentId, bonusType);
    }

    public int updataGrant(Long userId,Stu stu,String image){
        List<Grant> grantList = select_repeat(String.valueOf(userId),"助学金");
        Grant grant = grantList.get(0);
        if(!grant.getName().equals(stu.getXingming())){
            grant.setName(stu.getXingming());
        }
        if(!grant.getSex().equals(stu.getXingbie())){
            grant.setSex(stu.getXingbie());
        }
        if(!grant.getAge().equals(stu.getAge())){
            grant.setAge(stu.getAge());
        }
        if(!grant.getIdCart().equals(stu.getIdCart())){
            grant.setIdCart(stu.getIdCart());
        }
        if(!grant.getPhone().equals(stu.getPhone())){
            grant.setPhone(stu.getPhone());
        }
        if(!grant.getAddress().equals(stu.getAddress())){
            grant.setAddress(stu.getAddress());
        }
        if(!grant.getPoliticalStatus().equals(stu.getPoliticalStatus())){
            grant.setPoliticalStatus(stu.getPoliticalStatus());
        }
        if(!grant.getName().equals(stu.getXingming())){
            grant.setName(stu.getXingming());
        }
        if(!grant.getFamilyAnnualIncome().equals(stu.getFamilyAnnualIncome())){
            grant.setFamilyAnnualIncome(stu.getFamilyAnnualIncome());
        }
        if(image==null || "".equals(image)){
            return grantMapper.updatagrant(grant);
        }else {
            grant.setImage(image);
            return grantMapper.updatagrant(grant);
        }
    }
}
