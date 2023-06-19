package com.java.naga.JiraCreationThruFunction.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="incident")
public class Incident {
	
	@Id
	private String incidentNumber;
	private Integer severity;
	private String shortDescription;
	private String status;
	private String assignmentGroup;

}
