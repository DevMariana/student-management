package com.college.sms.services;

import com.college.sms.model.Subject;
import java.util.List;

public interface SubjectService {
    Subject save(Subject subject);
    void delete(Long id);
    List<Subject> getAll();
    Subject getSubjectById(Long id); // ✅ Added
}