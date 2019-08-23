package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.application.entity.review;
import cn.stylefeng.guns.modular.application.mapper.ReviewMapper;
import cn.stylefeng.guns.modular.system.entity.Notice;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.NoticeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.application.service
 * @date 2019/8/13 9:59
 */
@Service
public class ReviewService extends ServiceImpl<ReviewMapper, review> {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private NoticeService noticeService;

    private static Logger logger = LoggerFactory.getLogger(ReviewMapper.class);

    /**
     * 查询学生审请的数据
     * @return
     */
    public IPage selectAllStudentsReview(User user){
        logger.debug("开始查询学生申请的数据");
        Page page = LayuiPageFactory.defaultPage();
        String dept_id = String.valueOf(user.getDeptId());
        String role = user.getRoleId();
        System.out.println(user.toString());
        if("1141523474871881730".equals(role)){
            //辅导员
            return page.setRecords(reviewMapper.selectAllStudentsReview(page,dept_id,null,null,"F",null,null,null,null,"3"));
        }else if("1160733288554147841".equals(role)){
            //系领导
            return page.setRecords(reviewMapper.selectAllStudentsReview(page,dept_id,null,null,"T","F",null,null,null,"3"));
        }else if("1160733430061576194".equals(role)){
            //教务处
            return page.setRecords(reviewMapper.selectAllStudentsReview(page,null,null,null,"T","T","F",null,null,"3"));
        }else if("1160733580544815105".equals(role)){
            //学院评审
            return page.setRecords(reviewMapper.selectAllStudentsReview(page,null,null,null,"T","T","T","F",null,"3"));
        }else if("1160733692717281281".equals(role)){
            //学院领导
            return page.setRecords(reviewMapper.selectAllStudentsReview(page,null,null,null,"T","T","T","T","F","3"));
        }else if("1141523550411296769".equals(role)){
            //学生

        }else if("1".equals(role)){
            return page.setRecords(reviewMapper.selectAllStudentsReview(page,null,null,null,null,null,null,null,null,null));
        }else {
            return null;
        }

        return null;
    }

    public String selectImage(String studentId,String bonusType){
        logger.debug("开始查询证明图片");
        return reviewMapper.selectImage(studentId,bonusType);
    }

    public int deletereview(String studentId,String bonusType){
        logger.debug("学号为"+studentId+"，开始删除"+bonusType);
        return reviewMapper.deletereview(studentId,bonusType);
    }

    public int nopass(String studentId,String bonusType){
        logger.debug("学号为"+studentId+"，不同意申请"+bonusType);
        return reviewMapper.nopass(studentId, bonusType);
    }

    public int toExamine(User user,String studentId,String bonusType){
        logger.debug("开始审核");
        String role = user.getRoleId();
        if("1141523474871881730".equals(role)){
            //辅导员
            return reviewMapper.toExamine(studentId,bonusType,"T",null,null,null,null,"3");
        }else if("1160733288554147841".equals(role)){
            //系领导
            return reviewMapper.toExamine(studentId,bonusType,null,"T",null,null,null,"3");
        }else if("1160733430061576194".equals(role)){
            //教务处
            return reviewMapper.toExamine(studentId,bonusType,null,null,"T",null,null,"3");
        }else if("1160733580544815105".equals(role)){
            //学院评审
            return reviewMapper.toExamine(studentId,bonusType,null,null,null,"T",null,"3");
        }else if("1160733692717281281".equals(role)){
            //学院领导
            Notice notice = new Notice();
            notice.setTitle("管理员");
            notice.setContent("助学金审核通过！");
            notice.setCreateUser(Long.valueOf(1));
            notice.setJurisdiction("1");
            notice.setCreateTime(new Date());
            this.noticeService.save(notice);
            return reviewMapper.toExamine(studentId,bonusType,null,null,null,null,"T","1");
        }
        return 0;
    }

}
