package com.pci.workforcemanagement;

import com.pci.workforcemanagement.util.DbInitializerJDBC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class WorkforceManagementApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(WorkforceManagementApplication.class, args);
	}
}
