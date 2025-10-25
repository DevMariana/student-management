package com.college.sms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student relation
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Subject relation
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // Course relation
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Semester relation
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    // Date of attendance
    private LocalDate date;

    // Attendance status
    private boolean present;

    // Constructors
    public Attendance() {}

    public Attendance(LocalDate date, boolean present, Student student) {
        this.date = date;
        this.present = present;
        this.student = student;
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Semester getSemester() { return semester; }
    public void setSemester(Semester semester) { this.semester = semester; }
}