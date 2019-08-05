package cn.stylefeng.guns.modular.application.grant.service;

import cn.stylefeng.guns.modular.application.grant.entity.Grant;
import org.junit.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;

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
        List<Grant> grantList = grantService.selectGrant("1");
        System.out.println(grantList.toString());

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
}