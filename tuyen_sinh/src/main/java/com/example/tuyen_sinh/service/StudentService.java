package com.example.tuyen_sinh.service;

import com.example.tuyen_sinh.model.Student;
import com.example.tuyen_sinh.repository.StudentRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    //      https://www.codejava.net/coding/working-with-formula-cells-in-excel-using-apache-poi
    public void addDataFromExcel(MultipartFile excelFile) {
        try {
//            Read file xlsx
//            FileInputStream excelFile = new FileInputStream(new File("src/main/resources/sample.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile.getInputStream());
            Sheet sheet = workbook.getSheet("data");
            DataFormatter fmt = new DataFormatter();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            for (int index = 5; index < sheet.getPhysicalNumberOfRows(); index++) {
                Row currentRow = sheet.getRow(index);
                Student student = new Student();

                student.setSchool(fmt.formatCellValue(currentRow.getCell(1)));
                student.setAddress(fmt.formatCellValue(currentRow.getCell(2)));
                student.setIdstudent(fmt.formatCellValue(currentRow.getCell(3)));
                student.setClassName(fmt.formatCellValue(currentRow.getCell(4)));
                student.setName(fmt.formatCellValue(currentRow.getCell(5)));

                SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
                Date birthday = DateFor.parse(currentRow.getCell(8) + "/" + currentRow.getCell(7) + "/" + currentRow.getCell(6));
                student.setBirthday(birthday);

                student.setSex(fmt.formatCellValue(currentRow.getCell(9)));
                student.setNoiSinh(fmt.formatCellValue(currentRow.getCell(10)));
                student.setDanToc(fmt.formatCellValue(currentRow.getCell(11)));
                student.setHoKhau(fmt.formatCellValue(currentRow.getCell(12)));
                student.setPhone(fmt.formatCellValue(currentRow.getCell(13)));
                student.setPoint1(isNumeric(fmt.formatCellValue(currentRow.getCell(14), evaluator)));
                student.setPoint2(isNumeric(fmt.formatCellValue(currentRow.getCell(15), evaluator)));
                student.setPoint3(isNumeric(fmt.formatCellValue(currentRow.getCell(16), evaluator)));
                student.setPoint4(isNumeric(fmt.formatCellValue(currentRow.getCell(17), evaluator)));
                student.setPoint5(isNumeric(fmt.formatCellValue(currentRow.getCell(18), evaluator)));
                student.setTotal5year(isNumeric(fmt.formatCellValue(currentRow.getCell(19), evaluator)));
                student.setPriorityPoints(isNumeric(fmt.formatCellValue(currentRow.getCell(20), evaluator)));
                student.setTotalPoint(isNumeric(fmt.formatCellValue(currentRow.getCell(21), evaluator)));
                student.setNote(fmt.formatCellValue(currentRow.getCell(22)));

//                System.out.println("\n" + student+"\n");
                studentRepository.save(student);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> searchStudent(String id, String name) {
        name = formatString(name);
        id = formatString(id);

        if (id.equals("")) {
            return studentRepository.findByName(name);
        }
//        if (!name.equals("") && !id.equals("")) {
//            return studentRepository.findByIds(id);
//        }
        return studentRepository.findByIds(id);
    }

    private String formatString(String str) {
        return str.toLowerCase().replaceAll("\\s+", " ").trim();
    }

    private Double isNumeric(String str){
        if (NumberUtils.isParsable(str)) {
            return Double.parseDouble(str);
        } else {
            return 0.0;
        }
    }
}
