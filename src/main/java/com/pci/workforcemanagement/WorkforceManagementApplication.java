package com.pci.workforcemanagement;

import com.pci.workforcemanagement.controller.ScheduleController;
import com.pci.workforcemanagement.model.Schedule;
import com.pci.workforcemanagement.util.DataRetriever;
import com.pci.workforcemanagement.util.DbInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class WorkforceManagementApplication {

	public static void main(String[] args) throws SQLException {

		new DbInitializer().writeToDatabase();
		SpringApplication.run(WorkforceManagementApplication.class, args);
	}
}
