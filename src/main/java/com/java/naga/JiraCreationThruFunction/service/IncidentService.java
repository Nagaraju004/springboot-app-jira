package com.java.naga.JiraCreationThruFunction.service;

import java.util.List;
import java.util.Optional;



import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.naga.JiraCreationThruFunction.dto.IncidentDto;
import com.java.naga.JiraCreationThruFunction.model.Incident;
import com.java.naga.JiraCreationThruFunction.repository.IncidentRepository;

@Service
public class IncidentService {
	
	Logger logger=LoggerFactory.getLogger(IncidentService.class);

	@Autowired
	private IncidentRepository incidentRepository;

	public String addIncident(IncidentDto incidentDto) {
		Incident incident = Incident.builder().incidentNumber(incidentDto.getIncidentNumber())
				.severity(incidentDto.getSeverity()).shortDescription(incidentDto.getShortDescription())
				.status(incidentDto.getStatus())
				.assignmentGroup(incidentDto.getAssignmentGroup())
				.build();

		System.out.println(incident);

		try {
			incidentRepository.save(incident);
			logger.info("The data saved successfully for the incidentNumber:{}",incident.getIncidentNumber());
			return "Details added successfully";
		} catch (Exception e) {
			logger.error("The data was not saved");
			return "Details was not added";
		}

	}

	public Incident getIncident(String inc) {
		Optional<Incident> Opincident=incidentRepository.findById(inc);
		if(Opincident.isPresent()) {
			return Opincident.get();
		}
		return null;
		
	}

	public List<IncidentDto> getAllIncidents() {
		List<Incident> incidents=incidentRepository.findAll();
		return incidents.stream().map( inc -> mapToDto(inc)).toList();
	}
	
	public IncidentDto mapToDto(Incident incident) {
		IncidentDto dto=IncidentDto.builder()
						.incidentNumber(incident.getIncidentNumber())
						.severity(incident.getSeverity())
						.shortDescription(incident.getShortDescription())
						.status(incident.getStatus())
						.assignmentGroup(incident.getAssignmentGroup())
						.build();
		return dto;
	}

	public void checkForTicket() {
		logger.trace("Checking for any incident with given ass grp");
		List<Incident> incidents=incidentRepository.findByAssignmentGroup("tpx_auto_voice");
		if(incidents!=null) {
			for(Incident inc:incidents) {
				System.out.println(inc.getIncidentNumber());
			}
		}
		
	}

}
