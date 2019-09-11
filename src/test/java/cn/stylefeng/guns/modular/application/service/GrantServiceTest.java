package cn.stylefeng.guns.modular.application.service;

import cn.stylefeng.guns.modular.application.entity.Grant;
import cn.stylefeng.guns.modular.application.util.AGrantUtil;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GrantServiceTest {

    @Test
    public void insertGrant(){
//        Grant grant = new Grant();
//        grant.setStudentId("1");
//        grant.setName("2");
//        grant.setSex("9");
//        grant.setAge("3");
//        grant.setPhone("4");
//        grant.setAddress("5");
//        grant.setPoliticalStatus("6");
//        grant.setFamilyAnnualIncome("10");
//        grant.setImage(StrToBinstr("100"));
//        grant.setDept_id("8");
        GrantService grantService = new GrantService();
//        int a = grantService.insertGrant(grant);

    }
    // 将字符串转换成二进制字符串，以空格相隔
    private String StrToBinstr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]);
        }
        return result;
    }
    @Test
    public void getfile(){
        System.out.println("ffff");
        AGrantUtil aGrantUtil= new AGrantUtil();
        try {
            ArrayList<File> files = aGrantUtil.getFiles("D:/File/20170001/助学金");
            for(int i = 0;i<files.size();i++){
                System.out.println("路径"+files.get(i).getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}