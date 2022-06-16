package com.example.tuyen_sinh.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class Student {
    @Id
    @Column(name = "idstudent", unique = true)
    private String idstudent;

    @Column(name = "class")
    private String className;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "school")
    private String school;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @Column(name = "sex")
    private String sex;

    @Column(name = "noi_sinh")
    private String noiSinh;

    @Column(name = "dan_toc")
    private String danToc;

    @Column(name = "ho_khau")
    private String hoKhau;

    @Column(name = "phone")
    private String phone;

    private Double point1;
    private Double point2;
    private Double point3;
    private Double point4;
    private Double point5;
    private Double total5year = 0.0;
    private Double priorityPoints = 0.0;
    private Double totalPoint = 0.0;
    private String note;

}
