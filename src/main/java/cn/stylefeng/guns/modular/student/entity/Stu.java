package cn.stylefeng.guns.modular.student.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.student.entity
 * @date 2019/8/8 11:46
 */
public class Stu {
    private Long applyId;
    /**
     * 学号
     */
    private Long studentId;
    /**
     * 姓名
     */
    private String studentName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 名族
     */
    private String famousRace;

    /**
     * 政治面貌
     */
    private String politicalStatus;
    /**
     * 身份证
     */
    private String idCart;
    /**
     * 电话
     */
    private String phone;
    /**
     * 班级
     */
    private Long deptId;
    /**
     * 地址
     */
    private String address;
    /**
     * 学分绩点
     */
    private double gradePoint;
    /**
     * 职业教育应修学分
     */
    private double vocationalEducationCredits;

    /**
     * 品学量化分
     */
    private double quantitativeCredit;

    /**
     * 申请理由
     */
    private String applyReason;

    /**
     * 申请类型
     */
    private String bonusType;
    /**
     * 材料
     */
    private List<MultipartFile> material;
    /**
     * 材料1
     */
    private List<MultipartFile> materialOne;
    /**
     * 材料2
     */
    private List<MultipartFile> materialTwo;
    /**
     * 材料3
     */
    private List<MultipartFile> materialThree;
    /**
     * 材料4
     */
    private List<MultipartFile> materialFour;
    /**
     * 材料5
     */
    private List<MultipartFile> materialFive;
    /**
     * 父亲姓名
     */
    private String fatherName;

    /**
     * 父亲年龄
     */
    private String fatherAge;

    /**
     * 父亲电话
     */
    private String fatherPhone;
    /**
     * 父亲劳动单位
     */
    private String fatherCompany;

    /**
     * 父亲月工资
     */
    private double fatherMonthlyIncome;

    /**
     * 母亲姓名
     */
    private String motherName;

    /**
     * 母亲年龄
     */
    private String motherAge;

    /**
     * 母亲电话
     */
    private String motherPhone;

    /**
     * 母亲劳动单位
     */
    private String motherCompany;

    /**
     * 母亲月工资
     */
    private double motherMonthlyIncome;

    /**
     * 家庭户口
     */
    private String familyAccount;

    /**
     * 家庭人数
     */
    private String familyNumbs;

    @Override
    public String toString() {
        return "Stu{" +
                "applyId=" + applyId +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
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
                ", bonusType='" + bonusType + '\'' +
                ", material=" + material +
                ", materialOne=" + materialOne +
                ", materialTwo=" + materialTwo +
                ", materialThree=" + materialThree +
                ", materialFour=" + materialFour +
                ", materialFive=" + materialFive +
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
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

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public List<MultipartFile> getMaterial() {
        return material;
    }

    public void setMaterial(List<MultipartFile> material) {
        this.material = material;
    }

    public List<MultipartFile> getMaterialOne() {
        return materialOne;
    }

    public void setMaterialOne(List<MultipartFile> materialOne) {
        this.materialOne = materialOne;
    }

    public List<MultipartFile> getMaterialTwo() {
        return materialTwo;
    }

    public void setMaterialTwo(List<MultipartFile> materialTwo) {
        this.materialTwo = materialTwo;
    }

    public List<MultipartFile> getMaterialThree() {
        return materialThree;
    }

    public void setMaterialThree(List<MultipartFile> materialThree) {
        this.materialThree = materialThree;
    }

    public List<MultipartFile> getMaterialFour() {
        return materialFour;
    }

    public void setMaterialFour(List<MultipartFile> materialFour) {
        this.materialFour = materialFour;
    }

    public List<MultipartFile> getMaterialFive() {
        return materialFive;
    }

    public void setMaterialFive(List<MultipartFile> materialFive) {
        this.materialFive = materialFive;
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