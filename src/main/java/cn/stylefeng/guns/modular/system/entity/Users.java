package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
public class Users {
    private Long userId;
    private String avatar;
    private String account;
    private String password;
    private String salt;
    private String name;
    private Date birthday;
    private String sex;
    private String email;
    private String phone;
    private String roleId;
    private Long deptId;
    private String status;
    private Date createTime;
    private Long createUser;
    private Date updateTime;
    private Long updateUser;
    private Integer version;


    private String xibie;

    private String nianji;

    private String zhuanye;

    private String banji;

    private double dychengji;

    private double tychengji;

    private double zychengji;

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId='" + roleId + '\'' +
                ", deptId=" + deptId +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", version=" + version +
                ", xibie='" + xibie + '\'' +
                ", nianji='" + nianji + '\'' +
                ", zhuanye='" + zhuanye + '\'' +
                ", banji='" + banji + '\'' +
                ", dychengji=" + dychengji +
                ", tychengji=" + tychengji +
                ", zychengji=" + zychengji +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getXibie() {
        return xibie;
    }

    public void setXibie(String xibie) {
        this.xibie = xibie;
    }

    public String getNianji() {
        return nianji;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public double getDychengji() {
        return dychengji;
    }

    public void setDychengji(double dychengji) {
        this.dychengji = dychengji;
    }

    public double getTychengji() {
        return tychengji;
    }

    public void setTychengji(double tychengji) {
        this.tychengji = tychengji;
    }

    public double getZychengji() {
        return zychengji;
    }

    public void setZychengji(double zychengji) {
        this.zychengji = zychengji;
    }
}
