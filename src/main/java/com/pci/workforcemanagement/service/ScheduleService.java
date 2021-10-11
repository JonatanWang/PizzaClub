package com.pci.workforcemanagement.service;

import com.pci.workforcemanagement.model.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleService extends CrudRepository<Schedule, Integer> {
}
