package com.pci.workforcemanagement.service;

import com.pci.workforcemanagement.model.Schedule;
import com.pci.workforcemanagement.util.DbInitializer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import java.sql.SQLException;

@Component
public interface DbInitService extends CrudRepository<Schedule, Integer> {

    default Iterable<Schedule> getAll(String dateString) {
        Iterable<Schedule> schedules = null;
        try {
            schedules = new DbInitializer().getSchedules(dateString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    };
}
