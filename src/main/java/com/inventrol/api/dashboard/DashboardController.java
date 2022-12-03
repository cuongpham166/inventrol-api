package com.inventrol.api.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/dashboard")
	public ResponseEntity<Dashboard>getDashboardData () {
		try {
			Dashboard dashboardData = new Dashboard ();
			dashboardData = dashboardService.getAllDashboardData();
			return new ResponseEntity<>(dashboardData, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
