package com.java.naga.JiraCreationThruFunction.service;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.java.naga.JiraCreationThruFunction.controller.IncidentController;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.api.AuthenticationHandler;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.*;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class JiraService {

	Logger logger = LoggerFactory.getLogger(IncidentController.class);
	
	@Autowired
	private JiraExample jiraExample;

	private static final String JIRA_URL = "https://nagarajusaravanan.atlassian.net/jira";
	private static final String JIRA_USERNAME = "nagarajutechcse@gmail.com";
	private static final String JIRA_PASSWORD = "ATATT3xFfGF0lZkDEJPnCbTj2mcHih2C-sKqoim1WDv66irX6ua8b8jghtT_92O0zIyo38uGtXIKqbVcd_HWhjixLrAQnputRi5lD5cxw8oILxqpOZs0hsfFmJkCuGj9xV8x9UmcnULxRp9NPa1ytX8V8-3FKxoQg5YPWdtXxGMVuRkfewrAnJA=11F38C0E";

	public void createJiraTicket(String projectKey, String summary, String description) throws Exception {
		
		jiraExample.createJira();
//
//		logger.info("Inside  jira service for jira creation");
//		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
//		URI jiraServerUri = URI.create(JIRA_URL);
//		JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, JIRA_USERNAME,
//				JIRA_PASSWORD);
//
//	
//			logger.info("Authentication is success");
//			// Get the project by key
////            BasicProject project = restClient.getProjectClient().getProject(projectKey).claim();
////            
////
////            IssueInputBuilder issueInputBuilder = new IssueInputBuilder();
////            issueInputBuilder.setProjectKey(project.getKey())
////                    .setIssueTypeId(10003L) // Replace with the appropriate issue type ID for your Jira instance
////                    .setDescription(description)
////                    .setSummary(summary);
////            
////            logger.info("Identified project details is success");
////
////            IssueInput issueInput = issueInputBuilder.build();
////
////            Issue issue = (Issue) restClient.getIssueClient().createIssue(issueInput).claim();
////
////            System.out.println("Jira ticket created successfully. Key: " + issue.getKey());
////			HttpResponse<JsonNode> response = Unirest.get("https://nagarajusaravanan.atlassian.net/rest/api/3/events")
////					.basicAuth("nagarajutechcse@gmail.com", "ATATT3xFfGF0lZkDEJPnCbTj2mcHih2C-sKqoim1WDv66irX6ua8b8jghtT_92O0zIyo38uGtXIKqbVcd_HWhjixLrAQnputRi5lD5cxw8oILxqpOZs0hsfFmJkCuGj9xV8x9UmcnULxRp9NPa1ytX8V8-3FKxoQg5YPWdtXxGMVuRkfewrAnJA=11F38C0E").header("Accept", "application/json").asJson();
////
////			System.out.println(response.getBody());
//			
////			String jsonString = "{\"fields\":{\"project\":{\"key\":\"DEMO\"},\"summary\":\"REST ye merry gentlemen.\",\"description\":\"Creating of an issue using project keys and issue type names using the REST API\",\"issuetype\":{\"name\":\"Bug\"}}}";
////
////	        ObjectMapper mapper = new ObjectMapper();
////	        JsonNode jsonNode = mapper.readTree(jsonString);
////	        
//	        
//	        String jsonString = "{\n" +
//	                "    \"fields\": {\n" +
//	                "       \"project\":\n" +
//	                "       {\n" +
//	                "          \"key\": \"DEMO\"\n" +
//	                "       },\n" +
//	                "       \"summary\": \"REST ye merry gentlemen.\",\n" +
//	                "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
//	                "       \"issuetype\": {\n" +
//	                "          \"name\": \"Bug\"\n" +
//	                "       }\n" +
//	                "   }\n" +
//	                "}";
//
//	    
//	        	
//	        	JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
//
//	            // Convert the JsonObject to a JSON string
//	            String jsonStringGson = jsonObject.toString();
//
//	            // Convert the JSON string to a JsonNode using Jackson's ObjectMapper
//	            ObjectMapper objectMapper = new ObjectMapper();
//	            JsonNode jsonNode=null;
//	            try {
//	                jsonNode = objectMapper.readTree(jsonStringGson);
//	                // Print the JsonNode
//	                System.out.println(jsonNode);
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//	            
//	            
//	            // Parse the JSON string into a JsonObject
////	            JsonParser parser = new JsonParser();
////	            JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
////	            System.out.println(jsonObject);
////	            // Create a Gson instance
////	            Gson gson = new Gson();
////
////	            // Convert the JsonObject to a JsonNode
////	            JsonNode jsonNode = gson.fromJson(jsonObject, JsonNode.class);
////	            System.out.println(jsonNode);
//
//			
//	            HttpResponse<com.mashape.unirest.http.JsonNode> response1 = Unirest.post("https://nagarajusaravanan.atlassian.net/rest/api/3/issue")
//					  .basicAuth("nagarajutechcse@gmail.com", "ATATT3xFfGF0lZkDEJPnCbTj2mcHih2C-sKqoim1WDv66irX6ua8b8jghtT_92O0zIyo38uGtXIKqbVcd_HWhjixLrAQnputRi5lD5cxw8oILxqpOZs0hsfFmJkCuGj9xV8x9UmcnULxRp9NPa1ytX8V8-3FKxoQg5YPWdtXxGMVuRkfewrAnJA=11F38C0E")
//					  .header("Accept", "application/json")
//					  .header("Content-Type", "application/json")
//					  .body(jsonNode)
//					  .asJson();
//			
//			
//			//System.out.println(response1.getBody());
	
//	URI jiraServerUri = URI.create("https://nagarajusaravanan.atlassian.net/jira");
//
//    AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
//
//    AuthenticationHandler auth = new BasicHttpAuthenticationHandler(JIRA_USERNAME, JIRA_PASSWORD);
//    JiraRestClient restClient = factory.create(jiraServerUri, auth);
//    IssueRestClient issueClient = restClient.getIssueClient();
//    
//    try {
//    	
//    	logger.info("Auth success");
//        IssueInputBuilder iib = new IssueInputBuilder();
//        iib.setProjectKey("DEMO");
//        iib.setSummary("Test Summary");
//        iib.setIssueTypeId(10003L);
//        iib.setDescription("Test Description");
//        IssueInput issue = iib.build();
//        BasicIssue issueObj = issueClient.createIssue(issue).claim();
//        logger.info("Issue created success");
//        System.out.println("Issue " + issueObj.getKey() + " created successfully");
//        }
//    finally {
//        restClient.close();
//    }

	        }

}
