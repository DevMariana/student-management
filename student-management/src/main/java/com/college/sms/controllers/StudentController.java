package com.college.sms.controllers;

import com.college.sms.model.Student;
import com.college.sms.model.Department;
import com.college.sms.model.Semester;
import com.college.sms.model.Section;
import com.college.sms.services.StudentService;
import com.college.sms.repositories.DepartmentRepository;
import com.college.sms.repositories.SemesterRepository;
import com.college.sms.repositories.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }
    
    @GetMapping("/testform")
    public String testForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    // Show form for adding a new student with an empty Student object
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student()); // Add empty student for binding
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("sections", sectionRepository.findAll());
        return "student_form";
    }

    // Show form for editing existing student by id, adds found Student to model
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        System.out.println("DEBUG: /edit called with id = " + id);
        Student student;
        try {
            student = studentService.getStudentById(id);
            System.out.println("DEBUG: Fetched student: " + student);
        } catch (RuntimeException e) {
            System.out.println("DEBUG: Student not found with id: " + id);
            return "redirect:/students"; // Redirect if not found
        }
        model.addAttribute("student", student);
        System.out.println("DEBUG: Added student to model? " + (student != null));
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("semesters", semesterRepository.findAll());
        model.addAttribute("sections", sectionRepository.findAll());
        return "student_form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        if (student.getDepartment() != null && student.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(student.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Invalid Department"));
            student.setDepartment(dept);
        }
        if (student.getSemester() != null && student.getSemester().getId() != null) {
            Semester sem = semesterRepository.findById(student.getSemester().getId())
                .orElseThrow(() -> new RuntimeException("Invalid Semester"));
            student.setSemester(sem);
        }
        if (student.getSection() != null && student.getSection().getId() != null) {
            Section sec = sectionRepository.findById(student.getSection().getId())
                .orElseThrow(() -> new RuntimeException("Invalid Section"));
            student.setSection(sec);
        }
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}