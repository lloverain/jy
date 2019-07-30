package cn.stylefeng.guns.modular.student.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.student.entity.Student;

import cn.stylefeng.guns.modular.student.mapper.StudentMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FM
 * @date 2019/7/22 23:47
 */
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    @Autowired
    private StudentMapper studentMapper;

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);


    /**
     * 查询所有学生信息
     *
     * @return
     */
    public IPage selectAllStudentPage() {

        logger.debug("开始创建page......");
        Page page = LayuiPageFactory.defaultPage();
        return page.setRecords(studentMapper.selectStudent(page, null));
    }

    /**
     * 查询单个学生信息
     *
     * @return
     */
    public Student selectOneStudent(String studentId) {
        logger.debug("开始查询单个学生....");
        return studentMapper.selectStudent(null, studentId).get(0);
    }

    /**
     * 删除学生
     *
     * @param studentId
     * @return
     */
    public int deleteStudent(String studentId) {
        logger.debug("删除学生：" + studentId);
        return studentMapper.deleteStudent(studentId);
    }

    /**
     * 更新学生信息
     *
     * @param student
     * @return
     */
    public int updateStudent(Student student) {

        if (student.getStudentId() == null || student.getStudentId().equals("")) {
            logger.error("学号为空");
            throw new NullPointerException();
        }
        return studentMapper.updateStudent(student.getStudentId(),
                student.getName(),
                student.getSex(),
                student.getAge(),
                student.getIdCart(),
                student.getPhone(),
                student.getAddress(),
                student.getPoliticalStatus()
        );
    }


    /**
     * 将学生信息导入到数据库
     *
     * @param file
     * @return
     */
    public boolean importstudent(MultipartFile file) throws SQLIntegrityConstraintViolationException, DataAccessException, IOException {

        logger.debug("执行导入操作......");
        checkFile(file);
        logger.debug("文件检查通过......");
        Workbook workBook = getWorkBook(file);
        logger.debug("获取工作簿成功.....");
        List<Student> list = new ArrayList<>();
        //得到所有工作表
        for (int sheetNum = 0; sheetNum < workBook.getNumberOfSheets(); sheetNum++) {
            //得到当前工作表
            Sheet sheet = workBook.getSheetAt(sheetNum);
            if (sheet == null) {
                logger.debug("工作表为空！");
                continue;
            }
            logger.debug("得到工作表.....");
            //得到工作表开始行
            int firstRowNum = sheet.getFirstRowNum();
            logger.debug("工作表开始行：" + firstRowNum);
            //得到工作表结束行
            int lastRowNum = sheet.getLastRowNum();
            logger.debug("工作表结束行：" + lastRowNum);
            //循环得到行
            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                //得到当前行
                Row row = sheet.getRow(rowNum);
                logger.debug("得到当前行....:" + rowNum);
                if (row == null) {
                    continue;
                }
                //开始列
                int firstCellNum = row.getFirstCellNum();
                logger.debug("开始列：" + firstCellNum);
                //当前行的列数
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                logger.debug("当前行的列数：" + physicalNumberOfCells);
                //创建数组保存单元格内容
                String[] cells = new String[physicalNumberOfCells + 1];
                logger.debug("当前行单元格数量：" + cells.length);
                //循环当前行
                for (int cellNum = firstCellNum; cellNum <= physicalNumberOfCells; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    //得到单元格内容
                    cells[cellNum] = getCellValue(cell);
                    logger.debug("得到" + cellNum + "单元格数据");
                }
                //取到一行
                Student student = exclToStudent(cells);
                list.add(student);
                logger.debug("将一行数据添加进集合.....");
            }
            logger.debug("集合大小：" + list.size());

            if (studentMapper.importStudent(list) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查文件是否为空，是否类型错误
     *
     * @param file
     * @throws IOException
     */
    private void checkFile(MultipartFile file) throws IOException {
        if (file == null) {
            logger.error("文件为空！");
            throw new FileNotFoundException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            logger.error("文件类型错误，文件不是excel文件");
            throw new IOException("文件类型错误，文件不是excel文件");
        }
    }

    /**
     * 获取工作簿对象
     *
     * @param file
     * @return
     */
    private Workbook getWorkBook(MultipartFile file) {

        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建工作簿对象
        Workbook workbook = null;

        try {
            InputStream is = file.getInputStream();
            //根据后缀不同创建不同类型的文件流
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(is);
            } else {
                throw new IOException("文件类型错误:" + fileName);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return workbook;
    }

    /**
     * 得到单元格内容
     *
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * 将每行的单元格数据封装成student对象
     *
     * @param cells
     * @return
     */
    public Student exclToStudent(String[] cells) {
        Student student = new Student();
        for (int i = 0; i < cells.length; i++) {
            switch (i) {
                case 0:
                    student.setStudentId(cells[i]);
                    break;
                case 1:
                    student.setName(cells[i]);
                    break;
                case 2:
                    student.setSex(cells[i]);
                    break;
                case 3:
                    student.setAge(cells[i]);
                    break;
                case 4:
                    student.setIdCart(cells[i]);
                    break;
                case 5:
                    student.setPhone(cells[i]);
                    break;
                case 6:
                    student.setAddress(cells[i]);
                    break;
                case 7:
                    student.setPoliticalStatus(cells[i]);
                    break;
                default:
                    logger.error("参数错误");
            }
        }
        return student;
    }
}
