package com.college.sms.services;

import com.college.sms.model.Course;
import java.util.List;

public interface CourseService {
    Course save(Course course);
    void delete(Long id);
    List<Course> getAll();
    Course getCourseById(Long id);
}