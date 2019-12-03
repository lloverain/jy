package cn.stylefeng.guns.modular.application.util;

import cn.stylefeng.guns.modular.application.entity.StudentFamily;
import cn.stylefeng.guns.modular.application.entity.StudentGrant;
import cn.stylefeng.guns.modular.application.entity.StudentComment;
import cn.stylefeng.guns.modular.application.service.GrantService;
import cn.stylefeng.guns.modular.student.entity.Stu;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @PackageName:cn.stylefeng.guns.modular.application.util
 * @ClassName:SaveFile
 * @author:yangjiaying
 * @date 2019/9/4 15:47
 */
@Service
public class AGrantUtil {

    @Autowired
    private UserService userService;

    @Autowired
    private GrantService grantService;

    /**
     * 存文件
     *
     * @param inputStream
     * @param fileName
     * @param studentId
     * @return
     */
    public static String savePic(InputStream inputStream, String fileName, String studentId, String bonusType) {
        String path = null;
        OutputStream os = null;
        try {
            path = "D:\\File\\" + studentId + "\\" + bonusType;
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public ArrayList<File> getFiles(String path) throws Exception {
        //目标集合fileList
        ArrayList<File> fileList = new ArrayList<File>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath());
                } else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }


    public Stu fillGrant(Stu stu, User user) {
        stu.setBonusType("助学金");
        stu.setDeptId(user.getDeptId());
        stu.setStudentId(Long.valueOf(user.getAccount()));
        return stu;
    }

    public StudentGrant studentGrantcopy(Stu stu, String path) {
        StudentGrant studentGrant = new StudentGrant();
        studentGrant.setStudentId(String.valueOf(stu.getStudentId()));
        studentGrant.setName(stu.getStudentName());
        studentGrant.setSex(stu.getSex());
        studentGrant.setAge(stu.getAge());
        studentGrant.setFamousRace(stu.getFamousRace());
        studentGrant.setPoliticalStatus(stu.getPoliticalStatus());
        studentGrant.setIdCart(stu.getIdCart());
        studentGrant.setPhone(stu.getPhone());
        studentGrant.setDeptId(stu.getDeptId());
        studentGrant.setAddress(stu.getAddress());
        studentGrant.setGradePoint(stu.getGradePoint());
        studentGrant.setVocationalEducationCredits(stu.getVocationalEducationCredits());
        studentGrant.setQuantitativeCredit(stu.getQuantitativeCredit());
        studentGrant.setApplyReason(stu.getApplyReason());
        studentGrant.setBonus_type(stu.getBonusType());
        studentGrant.setMaterial(path);
        studentGrant.setMaterialOne("");
        studentGrant.setMaterialTwo("");
        studentGrant.setMaterialThree("");
        studentGrant.setMaterialFour("");
        studentGrant.setMaterialFive("");
        return studentGrant;
    }

    public StudentFamily studentFamilycopy(Stu stu, Long applyId) {
        StudentFamily studentFamily = new StudentFamily();
        studentFamily.setApplyId(applyId);
        studentFamily.setFatherName(stu.getFatherName());
        studentFamily.setFatherAge(stu.getFatherAge());
        studentFamily.setFatherPhone(stu.getFatherPhone());
        studentFamily.setFatherCompany(stu.getFatherCompany());
        studentFamily.setFatherMonthlyIncome(stu.getFatherMonthlyIncome());
        studentFamily.setMotherName(stu.getMotherName());
        studentFamily.setMotherAge(stu.getMotherAge());
        studentFamily.setMotherPhone(stu.getMotherPhone());
        studentFamily.setMotherCompany(stu.getMotherCompany());
        studentFamily.setMotherMonthlyIncome(stu.getMotherMonthlyIncome());
        studentFamily.setFamilyAccount(stu.getFamilyAccount());
        studentFamily.setFamilyNumbs(stu.getFamilyNumbs());
        return studentFamily;
    }

    public StudentComment studentCommentcopy(Long applyId, Long auditDepartment, String auditors,
                                             int examineState, String auditComments, String remarks) {
        StudentComment studentComment = new StudentComment();
        studentComment.setApplyId(applyId);
        studentComment.setAuditDepartment(auditDepartment);
        studentComment.setAuditors(auditors);
        studentComment.setExamineState(examineState);
        studentComment.setAuditComments(auditComments);
        studentComment.setRemarks(remarks);
        return studentComment;
    }

    public String pingyujson(Long applyId, String auditDepartment, String auditors, String auditComments) {
        StudentComment studentComment = grantService.selectStudentComment(applyId);
        String pingyu = studentComment.getAuditComments();
        com.alibaba.fastjson.JSONArray pingyujson = JSON.parseArray(pingyu);
        if (!pingyu.equals("") || !pingyu.equals(null)) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("部门", String.valueOf(auditDepartment));
                jsonObject.put("审核人员", auditors);
                jsonObject.put("审核评语", auditComments);
                pingyujson.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("部门", String.valueOf(auditDepartment));
                jsonObject.put("审核人员", auditors);
                jsonObject.put("审核评语", auditComments);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return pingyujson.toString();

    }


    public String beizhujson(Long applyId, String auditDepartment, String auditors, String remarks) {
        StudentComment studentComment = grantService.selectStudentComment(applyId);
        String beizhu = studentComment.getRemarks();
        com.alibaba.fastjson.JSONArray beizhujson = JSON.parseArray(beizhu);
        if (!beizhu.equals("") || !beizhu.equals(null)) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("部门", String.valueOf(auditDepartment));
                jsonObject.put("审核人员", auditors);
                jsonObject.put("备注", remarks);
                beizhujson.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("部门", String.valueOf(auditDepartment));
                jsonObject.put("审核人员", auditors);
                jsonObject.put("备注", remarks);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return beizhujson.toString();
    }

    /**
     * 更新
     *
     * @param newstu
     * @param oldstu
     * @return
     */
    public int contrastValue(Stu newstu, Stu oldstu, String studentId) {
//                String path = null;
//                for (MultipartFile f : newstu.getMaterial()) {
//                    try {
//                        path = savePic(f.getInputStream(), f.getOriginalFilename(), studentId);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
        System.out.println(newstu.toString() + "\n" + oldstu.toString());
        if (
                newstu.getStudentName().equals(oldstu.getStudentName()) &&
                        newstu.getSex().equals(oldstu.getSex()) &&
                        newstu.getAge().equals(oldstu.getAge()) &&
                        newstu.getFamousRace().equals(oldstu.getFamousRace()) &&
                        newstu.getPoliticalStatus().equals(oldstu.getPoliticalStatus()) &&
                        newstu.getIdCart().equals(oldstu.getIdCart()) &&
                        newstu.getPhone().equals(oldstu.getPhone()) &&
                        newstu.getAddress().equals(oldstu.getAddress()) &&
                        newstu.getGradePoint() == oldstu.getGradePoint() &&
                        newstu.getVocationalEducationCredits() == oldstu.getVocationalEducationCredits() &&
                        newstu.getQuantitativeCredit() == oldstu.getQuantitativeCredit() &&
                        newstu.getFatherName().equals(oldstu.getFatherName()) &&
                        newstu.getFatherAge().equals(oldstu.getFatherAge()) &&
                        newstu.getFatherPhone().equals(oldstu.getFatherPhone()) &&
                        newstu.getFatherCompany().equals(oldstu.getFatherCompany()) &&
                        newstu.getFatherMonthlyIncome() == oldstu.getFatherMonthlyIncome() &&
                        newstu.getMotherName().equals(oldstu.getMotherName()) &&
                        newstu.getMotherAge().equals(oldstu.getMotherAge()) &&
                        newstu.getMotherPhone().equals(oldstu.getMotherPhone()) &&
                        newstu.getMotherCompany().equals(oldstu.getMotherCompany()) &&
                        newstu.getMotherMonthlyIncome() == oldstu.getMotherMonthlyIncome() &&
                        newstu.getFamilyAccount().equals(oldstu.getFamilyAccount()) &&
                        newstu.getFamilyNumbs().equals(oldstu.getFamilyNumbs())


        ) {
            return 1;
        } else {
            return 0;
        }
    }

}
