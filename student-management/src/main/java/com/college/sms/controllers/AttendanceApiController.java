package com.college.sms.controllers;

import com.college.sms.model.Attendance;
import com.college.sms.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceApiController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public Attendance markAttendanceApi(@RequestBody Attendance attendance) {
        return attendanceService.save(attendance);
    }

    @GetMapping("/student/{id}")
    public List<Attendance> getByStudent(@PathVariable Long id) {
        return attendanceService.getByStudent(id);
    }

    @GetMapping("/date/{date}")
    public List<Attendance> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return attendanceService.getByDate(date);
    }
}