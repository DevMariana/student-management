package com.college.sms.repositories;

import com.college.sms.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    /** Find all attendance by date */
    List<Attendance> findByDate(LocalDate date);

    /** Find all attendance for a given student */
    List<Attendance> findByStudentId(Long studentId);

    /** Old duplicate check (student + date only) */
    boolean existsByStudentIdAndDate(Long studentId, LocalDate date);

    /** ✅ New duplicate check: same student + same subject + same date */
    boolean existsByStudentIdAndSubjectIdAndDate(Long studentId, Long subjectId, LocalDate date);

    /** Optional: get all attendance for a subject on a given date */
    List<Attendance> findBySubjectIdAndDate(Long subjectId, LocalDate date);
}