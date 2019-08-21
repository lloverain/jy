package cn.stylefeng.guns.modular.student.entity;

/**
 * @author 杨佳颖
 * @version V1.0
 * @Package cn.stylefeng.guns.modular.student.entity
 * @date 2019/8/8 11:46
 */
public class Stu {
    private String studentId;  //学号
    private String xingming;
    private String xingbie;
    private String age;
    private String idCart;
    private String phone;
    private String address;
    private String politicalStatus;    //政治面貌
    private String familyAnnualIncome;

    @Override
    public String toString() {
        return "Stu{" +
                "studentId='" + studentId + '\'' +
                ", xingming='" + xingming + '\'' +
                ", xingbie='" + xingbie + '\'' +
                ", age='" + age + '\'' +
                ", idCart='" + idCart + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", familyAnnualIncome='" + familyAnnualIncome + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
