package com.java.naga.JiraCreationThruFunction.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDto {
	
	private String incidentNumber;
	private Integer severity;
	private String shortDescription;
	private String status;
	private String assignmentGroup;

}
