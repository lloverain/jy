package cn.stylefeng.guns.modular.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @PackageName:cn.stylefeng.guns.modular.application.entity
 * @ClassName:StudentGrant
 * @author:yangjiaying
 * @date 2019/9/3 18:39
 */
public class StudentGrant {

    /**
     * 申请id
     */
    @TableId(value = "apply_id",type = IdType.ID_WORKER)
    private Long applyId;

    /**
     *学号
     */
    @TableField("student_id")
    private String studentId;

    /**
     *姓名
     */
    @TableField("name")
    private String name;

    /**
     *性别
     */
    @TableField("sex")
    private String sex;

    /**
     *年龄
     */
    @TableField("age")
    private String age;

    /**
     *名族
     */
    @TableField("famous_race")
    private String famousRace;

    /**
     *政治面貌
     */
    @TableField("political_status")
    private String politicalStatus;

    /**
     *身份证
     */
    @TableField("id_cart")
    private String idCart;

    /**
     *电话
     */
    @TableField("phone")
    private String phone;

    /**班级
     *
     */
    @TableField("dept_id")
    private long deptId;

    /**
     *地址
     */
    @TableField("address")
    private String address;

    /**
     *学分绩点
     */
    @TableField("grade_point")
    private double gradePoint;

    /**
     *职业教育应修学分
     */
    @TableField("vocational_education_credits")
    private double vocationalEducationCredits;

    /**
     *品学量化分
     */
    @TableField("quantitative_credit")
    private double quantitativeCredit;

    /**
     *申请理由
     */
    @TableField("apply_reason")
    private String applyReason;

    /**
     * 申请类型
     */
    @TableField("bonus_type")
    private String bonus_type;

    /**
     *材料
     */
    @TableField("material")
    private String material;

    /**
     *材料1
     */
    @TableField("material_one")
    private String materialOne;

    /**
     *材料2
     */
    @TableField("material_two")
    private String materialTwo;

    /**
     *材料3
     */
    @TableField("material_three")
    private String materialThree;

    /**
     *材料4
     */
    @TableField("material_four")
    private String materialFour;

    /**
     *材料5
     */
    @TableField("material_five")
    private String materialFive;


    @Override
    public String toString() {
        return "StudentGrant{" +
                "applyId=" + applyId +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", famousRace='" + famousRace + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", idCart='" + idCart + '\'' +
                ", phone='" + phone + '\'' +
                ", deptId=" + deptId +
                ", address='" + address + '\'' +
                ", gradePoint=" + gradePoint +
                ", vocationalEducationCredits=" + vocationalEducationCredits +
                ", quantitativeCredit=" + quantitativeCredit +
                ", applyReason='" + applyReason + '\'' +
                ", bonus_type='" + bonus_type + '\'' +
                ", material='" + material + '\'' +
                ", materialOne='" + materialOne + '\'' +
                ", materialTwo='" + materialTwo + '\'' +
                ", materialThree='" + materialThree + '\'' +
                ", materialFour='" + materialFour + '\'' +
                ", materialFive='" + materialFive + '\'' +
                '}';
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFamousRace() {
        return famousRace;
    }

    public void setFamousRace(String famousRace) {
        this.famousRace = famousRace;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    public double getVocationalEducationCredits() {
        return vocationalEducationCredits;
    }

    public void setVocationalEducationCredits(double vocationalEducationCredits) {
        this.vocationalEducationCredits = vocationalEducationCredits;
    }

    public double getQuantitativeCredit() {
        return quantitativeCredit;
    }

    public void setQuantitativeCredit(double quantitativeCredit) {
        this.quantitativeCredit = quantitativeCredit;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getBonus_type() {
        return bonus_type;
    }

    public void setBonus_type(String bonus_type) {
        this.bonus_type = bonus_type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialOne() {
        return materialOne;
    }

    public void setMaterialOne(String materialOne) {
        this.materialOne = materialOne;
    }

    public String getMaterialTwo() {
        return materialTwo;
    }

    public void setMaterialTwo(String materialTwo) {
        this.materialTwo = materialTwo;
    }

    public String getMaterialThree() {
        return materialThree;
    }

    public void setMaterialThree(String materialThree) {
        this.materialThree = materialThree;
    }

    public String getMaterialFour() {
        return materialFour;
    }

    public void setMaterialFour(String materialFour) {
        this.materialFour = materialFour;
    }

    public String getMaterialFive() {
        return materialFive;
    }

    public void setMaterialFive(String materialFive) {
        this.materialFive = materialFive;
    }
}

