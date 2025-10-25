package com.college.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.sms.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}