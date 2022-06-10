package com.example.tuyen_sinh.repository;

import com.example.tuyen_sinh.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByIdstudent(String s);

    @Query("select student from Student student where student.name like %:name%")
    List<Student> findByName(@Param("name") String name);

    @Query("select student from Student student where student.idstudent like %:idstudent%")
    List<Student> findByIds(@Param("idstudent") String idstudent);
}
