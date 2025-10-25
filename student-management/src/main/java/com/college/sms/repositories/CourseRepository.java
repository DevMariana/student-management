package com.college.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.sms.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {}