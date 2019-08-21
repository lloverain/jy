package cn.stylefeng.guns.modular.application.entity;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.application.entity
 * @date 2019/8/13 9:36
 */
public class review {
    private String studentId;           //学号
    private String name;                //姓名
    private String sex;                 //性别
    private String age;                 //年龄
    private String phone;               //电话
    private String address;             //地址
    private String politicalStatus;     //政治面貌
    private String familyAnnualIncome;  //家庭年收入
    private String caption;             //证明材料图片的标题
    private Object image;               //证明材料图片
    private String deptId;             //区别班级，以后老师对自己对班级审核
    private String bonusType;           //奖金类型

    private String instructor;          //辅导员审核
    private String firstInstance;      //系领导
    private String secondInstance;     //教务处
    private String threeInstance;      //学院委员
    private String fourInstance;       //学院领导
    private String state;              //申请状态

    @Override
    public String toString() {
        return "review{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", familyAnnualIncome='" + familyAnnualIncome + '\'' +
                ", caption='" + caption + '\'' +
                ", image=" + image +
                ", deptId='" + deptId + '\'' +
                ", bonusType='" + bonusType + '\'' +
                ", instructor='" + instructor + '\'' +
                ", firstInstance='" + firstInstance + '\'' +
                ", secondInstance='" + secondInstance + '\'' +
                ", threeInstance='" + threeInstance + '\'' +
                ", fourInstance='" + fourInstance + '\'' +
                ", state='" + state + '\'' +
                '}';
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getFamilyAnnualIncome() {
        return familyAnnualIncome;
    }

    public void setFamilyAnnualIncome(String familyAnnualIncome) {
        this.familyAnnualIncome = familyAnnualIncome;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getFirstInstance() {
        return firstInstance;
    }

    public void setFirstInstance(String firstInstance) {
        this.firstInstance = firstInstance;
    }

    public String getSecondInstance() {
        return secondInstance;
    }

    public void setSecondInstance(String secondInstance) {
        this.secondInstance = secondInstance;
    }

    public String getThreeInstance() {
        return threeInstance;
    }

    public void setThreeInstance(String threeInstance) {
        this.threeInstance = threeInstance;
    }

    public String getFourInstance() {
        return fourInstance;
    }

    public void setFourInstance(String fourInstance) {
        this.fourInstance = fourInstance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
