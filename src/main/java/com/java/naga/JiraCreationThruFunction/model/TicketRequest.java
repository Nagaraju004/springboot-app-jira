package com.java.naga.JiraCreationThruFunction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
	
	private String projectKey;
	private String summary;
	private String description;

}
