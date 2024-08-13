package com.luisetin.userBase.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luisetin.userBase.models.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students  = new ArrayList<>(List.of(
            new Student(1, "Luis", 100),
            new Student(2, "Lucia", 90)
    ));

    @GetMapping("/students")
    private List<Student> getStudents(){
        return students;
    }

    @PostMapping("/students")
    private Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    @GetMapping("csrf-token")
    private CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
