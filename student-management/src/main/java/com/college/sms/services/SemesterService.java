package com.college.sms.services;

import com.college.sms.model.Semester;
import java.util.List;

public interface SemesterService {
    Semester save(Semester semester);
    void delete(Long id);
    List<Semester> getAll();
    Semester getSemesterById(Long id); // ✅ Added
}