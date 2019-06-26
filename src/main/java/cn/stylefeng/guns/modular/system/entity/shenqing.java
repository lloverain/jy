package cn.stylefeng.guns.modular.system.entity;

public class shenqing {
    private String account;
    private String phone;
    private String xibie;
    private String nianji;
    private String zhuanye;
    private String banji;
    private double dychengji;
    private double tychengji;
    private double zychengji;
    private String state;
    private String prize;

    @Override
    public String toString() {
        return "shenqing{" +
                "account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", xibie='" + xibie + '\'' +
                ", nianji='" + nianji + '\'' +
                ", zhuanye='" + zhuanye + '\'' +
                ", banji='" + banji + '\'' +
                ", dychengji=" + dychengji +
                ", tychengji=" + tychengji +
                ", zychengji=" + zychengji +
                ", state='" + state + '\'' +
                ", prize='" + prize + '\'' +
                '}';
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
