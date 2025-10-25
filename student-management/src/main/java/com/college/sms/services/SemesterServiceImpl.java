package com.college.sms.services;

import com.college.sms.model.Semester;
import com.college.sms.repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester save(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public void delete(Long id) {
        semesterRepository.deleteById(id);
    }

    @Override
    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    @Override
    public Semester getSemesterById(Long id) {
        return semesterRepository.findById(id).orElse(null);
    }
}