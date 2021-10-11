package com.pci.workforcemanagement.service;

import com.pci.workforcemanagement.model.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityService extends CrudRepository<Activity, Integer> {
}
