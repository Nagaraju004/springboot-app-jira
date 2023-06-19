package com.java.naga.JiraCreationThruFunction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java.naga.JiraCreationThruFunction.model.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, String>{
	
	List<Incident> findByAssignmentGroup(String assignmentGroup);

}
