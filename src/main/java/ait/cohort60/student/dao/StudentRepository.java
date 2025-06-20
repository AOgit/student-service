package ait.cohort60.student.dao;

import ait.cohort60.student.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    void deleteById(Long id);
    List<Student> findAll();
}
