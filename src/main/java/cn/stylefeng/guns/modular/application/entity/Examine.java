package cn.stylefeng.guns.modular.application.entity;

/**
 * @PackageName:cn.stylefeng.guns.modular.application.entity
 * @ClassName:Examine
 * @author:yangjiaying
 * @date 2019/9/17 11:28
 */
public class Examine {
    private String teacherId;
    private String auditComment;
    private String remark;

    @Override
    public String toString() {
        return "Examine{" +
                "teacherId='" + teacherId + '\'' +
                ", auditComment='" + auditComment + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
