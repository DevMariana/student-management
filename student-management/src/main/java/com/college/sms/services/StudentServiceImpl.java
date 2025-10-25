package com.college.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.sms.model.Student;
import com.college.sms.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findBySemesterAndSection(Long semesterId, Long sectionId) {
        return studentRepository.findBySemesterIdAndSectionId(semesterId, sectionId);
    }

    @Override
    public Student getStudentById(Long id) {
        // Correctly fetch student from database instead of returning null
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public List<Student> getStudentsBySemesterAndSection(Long semesterId, Long sectionId) {
        // Implement as needed or delegate to findBySemesterAndSection
        return studentRepository.findBySemesterIdAndSectionId(semesterId, sectionId);
    }
}