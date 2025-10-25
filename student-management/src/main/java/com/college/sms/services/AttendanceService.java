package com.college.sms.services;

import com.college.sms.model.Attendance;
import com.college.sms.model.Section;
import com.college.sms.model.Student;
import com.college.sms.model.Subject;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance save(Attendance attendance);

    List<Attendance> getByStudent(Long studentId);

    List<Attendance> getByDate(LocalDate date);

    List<Attendance> getByDateAndSection(LocalDate date, String sectionName);

    List<Section> getAllSections(); // For dropdowns in UI

    List<Attendance> getFiltered(LocalDate date, Long sectionId, Long semesterId);

    /** ✅ Old check (student + date only) */
    boolean existsByStudentIdAndDate(Long studentId, LocalDate date);

    /** ✅ New check for duplicates: student + subject + date */
    boolean existsByStudentAndSubjectAndDate(Student student, Subject subject, LocalDate date);
}