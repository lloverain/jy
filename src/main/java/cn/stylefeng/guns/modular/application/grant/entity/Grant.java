package cn.stylefeng.guns.modular.application.grant.entity;

public class Grant {
    private String studentId;           //学号
    private String name;                //姓名
    private String sex;                 //性别
    private String age;                 //年龄
    private String phone;               //电话
    private String address;             //地址
    private String politicalStatus;     //政治面貌
    private String familyAnnualIncome;  //家庭年收入
    //    private Object image;               //证明材料图片
    private String dept_id;             //区别班级，以后老师对自己对班级审核

    @Override
    public String toString() {
        return "Grant{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", familyAnnualIncome='" + familyAnnualIncome + '\'' +
                ", dept_id='" + dept_id + '\'' +
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

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }
}