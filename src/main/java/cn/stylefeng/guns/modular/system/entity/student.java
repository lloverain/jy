package cn.stylefeng.guns.modular.system.entity;


public class student{

    private Long user_id;

    private String xibie;

    private String nianji;

    private String zhuanye;

    private String banji;

    private double dychengji;

    private double tychengji;

    private double zychengji;

    @Override
    public String toString() {
        return "student{" +
                "user_id='" + user_id + '\'' +
                ", xibie='" + xibie + '\'' +
                ", nianji='" + nianji + '\'' +
                ", zhuanye='" + zhuanye + '\'' +
                ", banji='" + banji + '\'' +
                ", dychengji=" + dychengji +
                ", tychengji=" + tychengji +
                ", zychengji=" + zychengji +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
