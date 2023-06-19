package com.java.naga.JiraCreationThruFunction.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.naga.JiraCreationThruFunction.dto.IncidentDto;
import com.java.naga.JiraCreationThruFunction.model.Incident;
import com.java.naga.JiraCreationThruFunction.model.TicketRequest;
import com.java.naga.JiraCreationThruFunction.service.IncidentService;
import com.java.naga.JiraCreationThruFunction.service.JiraService;



@RestController
@EnableScheduling
@RequestMapping("/api/incident")
public class IncidentController {
	
	Logger logger=LoggerFactory.getLogger(IncidentController.class);

	
	@Autowired
	private IncidentService incidentService;
	
	@Autowired
    private JiraService jiraService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addIncident(@RequestBody IncidentDto incidentDto) {
		logger.info("Hit the add incident api and calling inc service");
		return incidentService.addIncident(incidentDto);
	}
	
	@GetMapping("/{inc}")
	public String getIncident(@PathVariable String inc) {
		logger.info("Hit the get incident api and calling inc service");
		Incident incident=incidentService.getIncident(inc);
		if(incident==null) {
			logger.error("Incident detils not found for given incNumber:{}",inc);
			return "No Incidents found";
		}
		logger.info("Incident detils found for given incNumber:{}",incident.getIncidentNumber());
		return incident.toString();
	}
	
	@GetMapping
	public List<IncidentDto> getAllIncidents(){
		logger.info("Hit the get all incident api and calling inc service");
		List<IncidentDto> incidents=incidentService.getAllIncidents();
		if(incidents==null) {
			logger.error("Incident detils not found in the db");
			return null;
		}
		logger.info("Incident detils found in the db");
		return incidents;
	}
	
	@PostMapping("/create-ticket")
    public void createTicket(@RequestBody TicketRequest ticketRequest) throws Exception {
		
		logger.info("Inside  incident controller for jira creation");
        // Extract necessary details from ticketRequest
        String projectKey = ticketRequest.getProjectKey();
        String summary = ticketRequest.getSummary();
        String description = ticketRequest.getDescription();

        // Call the JiraService to create the ticket
        jiraService.createJiraTicket(projectKey, summary, description);
    }

	
	//@Scheduled(cron = "1 * * * * *")
	@GetMapping("/get")
	public void checkForTicket() {
		logger.trace("Schduled function is running now");
		incidentService.checkForTicket();
	}
}
