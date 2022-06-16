package com.example.tuyen_sinh.controller;

import com.example.tuyen_sinh.model.Student;
import com.example.tuyen_sinh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @PostMapping("/add")
    public String addDataFromExcel(@RequestParam("excelFile") MultipartFile excelFile, Model model) {
        if (excelFile.getOriginalFilename().contains(".xlsx")) {
            studentService.addDataFromExcel(excelFile);
            return "redirect:search";
        }
        model.addAttribute("error", "File không đúng định dạng");
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        return "search";
    }

    @PostMapping("/search")
    public String searchStudent(@RequestParam(name = "id", defaultValue = "") String id, @RequestParam(name = "name", defaultValue = "") String name, Model model) {
//        System.out.println(studentService.searchStudent(id, name));
        model.addAttribute("students", studentService.searchStudent(id, name));
        return "search";
    }
}
