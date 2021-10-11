package com.pci.workforcemanagement.controller;

import com.pci.workforcemanagement.model.Schedule;
import com.pci.workforcemanagement.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(path = "api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public Schedule create(@RequestBody Schedule schedule)  {
        // A method to check if the schedule already exists(Date & PersonId)
        return scheduleService.save(schedule);
    }

    @GetMapping
    public Iterable<Schedule> read() {
        return scheduleService.findAll();
    }

    @PutMapping
    Schedule update(@RequestBody Schedule schedule) {
        // A method to check if the schedule already exists(Date & PersonId)
        return scheduleService.save(schedule);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        scheduleService.deleteById(id);
    }
}
