package com.springsecurity.Springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springsecurity.Springsecurity.bean.StudentBean;

@Repository
public interface StudentRepo extends JpaRepository<StudentBean, Long> {
}
