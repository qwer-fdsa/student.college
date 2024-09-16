package com.springsecurity.Springsecurity.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springsecurity.Springsecurity.bean.StudentBean;
import com.springsecurity.Springsecurity.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping("/studentdetails")
    public List<StudentBean> getAllStudents() {
        return studentService.getAllStudentList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/studentdetails")
    public StudentBean save(@RequestBody StudentBean student) {
        return studentService.saveStudent(student);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/studentdetails/{id}")
    public StudentBean updateStudent(@PathVariable Long id, @RequestBody StudentBean student) {
        student.setId(id);
        return studentService.updateStudent(student);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/studentdetails/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student with ID " + id + " has been deleted.";
    }
}