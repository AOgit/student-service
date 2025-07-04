package ait.cohort60.student.controller;

import ait.cohort60.student.dto.ScoreDto;
import ait.cohort60.student.dto.StudentCredentialsDto;
import ait.cohort60.student.dto.StudentDto;
import ait.cohort60.student.dto.StudentUpdateDto;
import ait.cohort60.student.model.Student;
import ait.cohort60.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentCredentialsDto studentCredentialsDto) {
       return studentService.addStudent(studentCredentialsDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudentById(@PathVariable Long id) {
        return studentService.removeStudentById(id);
    }

    @PatchMapping("/student/{id}")
    public StudentCredentialsDto updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PatchMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Long id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @GetMapping("/quantity/students")
    public Long countStudentsByNames(@RequestParam Set<String> names) {
        return studentService.countStudentsByNames(names);
    }

    @GetMapping("/students/exam/{examName}/minscore/{minScore}")
    public List<StudentDto> findStudentsByExamNameMinScore(@PathVariable String examName, @PathVariable Integer minScore) {
        return studentService.findStudentsByExamNameMinScore(examName, minScore);
    }
}
