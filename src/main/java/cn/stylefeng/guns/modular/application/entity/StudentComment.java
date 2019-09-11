package cn.stylefeng.guns.modular.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @PackageName:cn.stylefeng.guns.modular.application.entity
 * @ClassName:studentComment
 * @author:yangjiaying
 * @date 2019/9/4 8:47
 */
public class StudentComment {
    /**
     * 申请id（外键）
     */
    @TableId(value = "apply_id",type = IdType.ID_WORKER)
    private Long applyId;

    /**
     * 审核部门
     */
    @TableField("audit_department")
    private Long auditDepartment;

    /**
     * 审核人员
     */
    @TableField("auditors")
    private String auditors;

    /**
     * 审核结果
     */
    @TableField("examine_state")
    private int examineState;

    /**
     * 审核评语
     */
    @TableField("audit_comments")
    private String auditComments;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    @Override
    public String toString() {
        return "studentComment{" +
                "applyId=" + applyId +
                ", auditDepartment=" + auditDepartment +
                ", auditors='" + auditors + '\'' +
                ", examineState=" + examineState +
                ", auditComments='" + auditComments + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getAuditDepartment() {
        return auditDepartment;
    }

    public void setAuditDepartment(Long auditDepartment) {
        this.auditDepartment = auditDepartment;
    }

    public String getAuditors() {
        return auditors;
    }

    public void setAuditors(String auditors) {
        this.auditors = auditors;
    }

    public int getExamineState() {
        return examineState;
    }

    public void setExamineState(int examineState) {
        this.examineState = examineState;
    }

    public String getAuditComments() {
        return auditComments;
    }

    public void setAuditComments(String auditComments) {
        this.auditComments = auditComments;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
