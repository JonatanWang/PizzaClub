package com.pci.workforcemanagement.controller;

import com.pci.workforcemanagement.model.Schedule;
import com.pci.workforcemanagement.service.DbInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/initDb")
public class DbInitController {

    @Autowired
    private DbInitService dbInitService;

    @GetMapping("/{dateString}")
    public Iterable<Schedule> getAllSchedules(@PathVariable String dateString) throws Exception {
        return dbInitService.getAll(dateString);
    };

    @PostMapping
    public Iterable<Schedule> initDb(@RequestBody Iterable<Schedule> schedules)  {

        return dbInitService.saveAll(schedules);
    }
}
