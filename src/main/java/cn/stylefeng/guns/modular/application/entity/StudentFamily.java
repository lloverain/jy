package cn.stylefeng.guns.modular.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @PackageName:cn.stylefeng.guns.modular.application.entity
 * @ClassName:StudentFamily
 * @author:yangjiaying
 * @date 2019/9/4 8:39
 */
public class StudentFamily {
    /**
     * 申请id（外键）
     */
    @TableId(value = "apply_id",type = IdType.ID_WORKER)
    private Long applyId;

    /**
     * 父亲姓名
     */
    @TableField("father_name")
    private String fatherName;

    /**
     * 父亲年龄
     */
    @TableField("father_age")
    private String fatherAge;

    /**
     * 父亲电话
     */
    @TableField("father_phone")
    private String fatherPhone;

    /**
     * 父亲劳动单位
     */
    @TableField("father_company")
    private String fatherCompany;

    /**
     * 父亲月工资
     */
    @TableField("father_monthly_income")
    private double fatherMonthlyIncome;


    /**
     * 母亲姓名
     */
    @TableField("mother_name")
    private String motherName;

    /**
     * 母亲年龄
     */
    @TableField("mother_age")
    private String motherAge;

    /**
     * 母亲电话
     */
    @TableField("mother_phone")
    private String motherPhone;

    /**
     * 母亲劳动单位
     */
    @TableField("mother_company")
    private String motherCompany;

    /**
     * 母亲月工资
     */
    @TableField("mother_monthly_income")
    private double motherMonthlyIncome;

    /**
     * 家庭户口
     */
    @TableField("family_account")
    private String familyAccount;

    /**
     * 家庭人数
     */
    @TableField("family_numbs")
    private String familyNumbs;

    @Override
    public String toString() {
        return "StudentFamily{" +
                "applyId=" + applyId +
                ", fatherName='" + fatherName + '\'' +
                ", fatherAge='" + fatherAge + '\'' +
                ", fatherPhone='" + fatherPhone + '\'' +
                ", fatherCompany='" + fatherCompany + '\'' +
                ", fatherMonthlyIncome=" + fatherMonthlyIncome +
                ", motherName='" + motherName + '\'' +
                ", motherAge='" + motherAge + '\'' +
                ", motherPhone='" + motherPhone + '\'' +
                ", motherCompany='" + motherCompany + '\'' +
                ", motherMonthlyIncome=" + motherMonthlyIncome +
                ", familyAccount='" + familyAccount + '\'' +
                ", familyNumbs='" + familyNumbs + '\'' +
                '}';
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherAge() {
        return fatherAge;
    }

    public void setFatherAge(String fatherAge) {
        this.fatherAge = fatherAge;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getFatherCompany() {
        return fatherCompany;
    }

    public void setFatherCompany(String fatherCompany) {
        this.fatherCompany = fatherCompany;
    }

    public double getFatherMonthlyIncome() {
        return fatherMonthlyIncome;
    }

    public void setFatherMonthlyIncome(double fatherMonthlyIncome) {
        this.fatherMonthlyIncome = fatherMonthlyIncome;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(String motherAge) {
        this.motherAge = motherAge;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public String getMotherCompany() {
        return motherCompany;
    }

    public void setMotherCompany(String motherCompany) {
        this.motherCompany = motherCompany;
    }

    public double getMotherMonthlyIncome() {
        return motherMonthlyIncome;
    }

    public void setMotherMonthlyIncome(double motherMonthlyIncome) {
        this.motherMonthlyIncome = motherMonthlyIncome;
    }

    public String getFamilyAccount() {
        return familyAccount;
    }

    public void setFamilyAccount(String familyAccount) {
        this.familyAccount = familyAccount;
    }

    public String getFamilyNumbs() {
        return familyNumbs;
    }

    public void setFamilyNumbs(String familyNumbs) {
        this.familyNumbs = familyNumbs;
    }
}
