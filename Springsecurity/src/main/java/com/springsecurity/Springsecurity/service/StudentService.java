package com.springsecurity.Springsecurity.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springsecurity.Springsecurity.bean.StudentBean;
import com.springsecurity.Springsecurity.repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public StudentBean saveStudent(StudentBean student) {
        return studentRepo.save(student);
    }

    public List<StudentBean> getAllStudentList() {
        return studentRepo.findAll();
    }

    public StudentBean updateStudent(StudentBean student) {
        return studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}