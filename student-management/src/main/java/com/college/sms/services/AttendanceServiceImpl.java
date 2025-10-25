package com.college.sms.services;

import com.college.sms.model.Attendance;
import com.college.sms.model.Section;
import com.college.sms.model.Student;
import com.college.sms.model.Subject;
import com.college.sms.repositories.AttendanceRepository;
import com.college.sms.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    public List<Attendance> getByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    @Override
    public List<Attendance> getByDateAndSection(LocalDate date, String sectionName) {
        // ⚠️ Only works if Attendance has a relation to Section
        // Otherwise you’ll need a custom query
        return attendanceRepository.findByDate(date); 
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public List<Attendance> getFiltered(LocalDate date, Long sectionId, Long semesterId) {
        // TODO: implement actual filtering logic
        return attendanceRepository.findByDate(date);
    }

    @Override
    public boolean existsByStudentIdAndDate(Long studentId, LocalDate date) {
        return attendanceRepository.existsByStudentIdAndDate(studentId, date);
    }

    @Override
    public boolean existsByStudentAndSubjectAndDate(Student student, Subject subject, LocalDate date) {
        return attendanceRepository.existsByStudentIdAndSubjectIdAndDate(
                student.getId(),
                subject.getId(),
                date
        );
    }
}