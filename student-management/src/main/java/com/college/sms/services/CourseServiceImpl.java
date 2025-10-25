package com.college.sms.services;

import com.college.sms.model.Course;
import com.college.sms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /** Save or update a course */
    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    /** Delete course by ID */
    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    /** Get all courses */
    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    /** Get course by ID */
    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}