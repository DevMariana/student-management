package com.college.sms.controllers;

import com.college.sms.model.Attendance;
import com.college.sms.model.Student;
import com.college.sms.model.Course;
import com.college.sms.model.Subject;
import com.college.sms.model.Semester;
import com.college.sms.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    /** Redirect /attendance → /attendance/view */
    @GetMapping({"", "/"})
    public String root() {
        return "redirect:/attendance/view";
    }

    /** Add defaults for model so template never breaks */
    @ModelAttribute
    public void addDefaultModelAttributes(Model model) {
        if (!model.containsAttribute("attendanceList")) {
            model.addAttribute("attendanceList", Collections.emptyList());
        }
        if (!model.containsAttribute("sections")) {
            model.addAttribute("sections", sectionService.getAll());
        }
        if (!model.containsAttribute("semesters")) {
            model.addAttribute("semesters", semesterService.getAll());
        }
        if (!model.containsAttribute("students")) {
            model.addAttribute("students", studentService.getAll());
        }
        if (!model.containsAttribute("courses")) {
            model.addAttribute("courses", courseService.getAll());
        }
        if (!model.containsAttribute("subjects")) {
            model.addAttribute("subjects", subjectService.getAll());
        }
        if (!model.containsAttribute("selectedDate")) {
            model.addAttribute("selectedDate", LocalDate.now());
        }
    }

    /** View attendance with optional filters */
    @GetMapping("/view")
    public String viewAttendance(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "sectionId", required = false) Long sectionId,
            @RequestParam(value = "semesterId", required = false) Long semesterId,
            Model model) {

        if (date == null) {
            date = LocalDate.now();
        }

        List<Attendance> attendanceList = attendanceService.getFiltered(date, sectionId, semesterId);

        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("selectedDate", date);
        model.addAttribute("selectedSectionId", sectionId);
        model.addAttribute("selectedSemesterId", semesterId);

        return "attendance";
    }

    /** Show attendance form */
    @GetMapping("/new")
    public String showAttendanceForm(Model model) {
        model.addAttribute("sections", sectionService.getAll());
        model.addAttribute("semesters", semesterService.getAll());
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("today", LocalDate.now());
        return "attendance_new";
    }

    /** ✅ Mark attendance with Student, Course, Subject, Semester */
    @PostMapping("/mark")
    public String markAttendance(
            @RequestParam("studentId") Long studentId,
            @RequestParam("subjectId") Long subjectId,
            @RequestParam("courseId") Long courseId,
            @RequestParam("semesterId") Long semesterId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "present", required = false) Boolean present,
            RedirectAttributes redirectAttributes) {

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        Subject subject = subjectService.getSubjectById(subjectId);
        Semester semester = semesterService.getSemesterById(semesterId);

        // ✅ Prevent duplicates (student + subject + date)
        if (attendanceService.existsByStudentAndSubjectAndDate(student, subject, date)) {
            redirectAttributes.addFlashAttribute("warning",
                    "⚠ Attendance already marked for " + student.getName() +
                            " in subject " + subject.getName() + " on " + date);
            return "redirect:/attendance/new";
        }

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setSubject(subject);
        attendance.setSemester(semester);
        attendance.setDate(date);
        attendance.setPresent(present != null && present);

        attendanceService.save(attendance);

        redirectAttributes.addFlashAttribute("success",
                "✅ Attendance marked successfully for " + student.getName() +
                        " in subject " + subject.getName());

        return "redirect:/attendance/new";
    }

    /** Fetch students by semester + section (AJAX) */
    @GetMapping("/students")
    @ResponseBody
    public List<Student> getStudentsBySemesterAndSection(
            @RequestParam Long semesterId,
            @RequestParam Long sectionId) {
        return studentService.getStudentsBySemesterAndSection(semesterId, sectionId);
    }
}