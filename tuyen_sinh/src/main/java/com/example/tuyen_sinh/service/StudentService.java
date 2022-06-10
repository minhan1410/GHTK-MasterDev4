package com.example.tuyen_sinh.service;

import com.example.tuyen_sinh.model.Student;
import com.example.tuyen_sinh.repository.StudentRepository;
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

            List<List<Object>> values = new ArrayList<>();

            for (int i = 5; i < sheet.getPhysicalNumberOfRows(); i++) {
                List<Object> valueRow = new ArrayList<Object>();
                sheet.getRow(i).forEach(
                        cell -> {
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_BOOLEAN:
                                    valueRow.add(cell.getBooleanCellValue());
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    valueRow.add(cell.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    valueRow.add(cell.getStringCellValue());
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    valueRow.add(" ");
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    break;

                                // CELL_TYPE_FORMULA will never occur
                                case Cell.CELL_TYPE_FORMULA:
                                    valueRow.add(fmt.formatCellValue(cell, evaluator));
                                    break;
                            }
                        });
                values.add(valueRow);
            }

//            add db
            for (List<Object> row : values) {
                Student student = new Student();
                for (int i = 1; i < row.size(); i++) {
                    switch (i) {
                        case 1:
                            student.setSchool((String) row.get(i));
                            break;
                        case 2:
                            student.setAddress((String) row.get(i));
                            break;
                        case 3:
                            student.setIdstudent(formatString((String) row.get(i)));
                            break;
                        case 4:
                            student.setClassName((String) row.get(i));
                            break;
                        case 5:
                            student.setName(formatString((String) row.get(i)));
                            break;
                        case 6:
                            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
                            Date birthday = DateFor.parse((String) row.get(i + 2) + "/" + (String) row.get(i + 1) + "/" + (String) row.get(i));
                            student.setBirthday(birthday);
                            break;
                        case 9:
                            student.setSex((String) row.get(i));
                            break;
                        case 10:
                            student.setNoiSinh((String) row.get(i));
                            break;
                        case 11:
                            student.setDanToc((String) row.get(i));
                            break;
                        case 12:
                            student.setHoKhau((String) row.get(i));
                            break;
                        case 13:
                            student.setPhone((String) row.get(i));
                            break;
                    }
                }
//                System.out.println("\n" + student + "\n");
                Optional<Student> optional = studentRepository.findByIdstudent(student.getIdstudent());
                if (!optional.isPresent()) {
                    studentRepository.save(student);
                }
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
}
