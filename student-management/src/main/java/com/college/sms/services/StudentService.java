package com.college.sms.services;

import java.util.List;
import com.college.sms.model.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student save(Student student);
    Student getStudentById(Long id);
    void deleteStudentById(Long id);

    // Alias method for UI/service reuse
    List<Student> getAll();
    
    // student by semester and section
    List<Student> getStudentsBySemesterAndSection(Long semesterId, Long sectionId);
    
    Student findById(Long id);
    List<Student> findBySemesterAndSection(Long semesterId, Long sectionId);
    
}