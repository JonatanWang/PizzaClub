package com.pci.workforcemanagement.controller;

import com.pci.workforcemanagement.error.ErrorMessage;
import com.pci.workforcemanagement.error.FieldErrorMessage;
import com.pci.workforcemanagement.model.Schedule;
import com.pci.workforcemanagement.service.ScheduleService;
import com.pci.workforcemanagement.util.DbInitializer;
import com.pci.workforcemanagement.util.DbInitializerJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController @RequestMapping(path = "api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public Schedule create(@RequestBody Schedule schedule) throws ValidationException {
        if(schedule.getId() == 0 && schedule.getName() != null && schedule.getPersonId() != null) {
            return scheduleService.save(schedule);
        } else {
            throw new ValidationException("schedule can not be created.");
        }
    }

    @GetMapping
    public Iterable<Schedule> read() {
        return scheduleService.findAll();
    }


    @GetMapping("/import")
    public Iterable<Schedule> importData() {
        try {
            new DbInitializerJDBC().writeToDatabase();
            /*var schedules = new DbInitializer().getSchedules(dateString);
            scheduleService.saveAll(schedules);*/
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scheduleService.findAll();
    }

    @GetMapping("/import/{dateString}")
    public Iterable<Schedule> importData(@PathVariable String dateString) {
        try {
            var schedules = new DbInitializer().getSchedules(dateString);
            scheduleService.saveAll(schedules);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scheduleService.findAll();
    }

    @PutMapping
    ResponseEntity<Schedule> update(@RequestBody Schedule schedule) {
        if (scheduleService.findById(schedule.getId()).isPresent()) {
            return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.OK);
        }
        return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        scheduleService.deleteById(id);
    }
}
