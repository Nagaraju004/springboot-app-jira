package com.java.naga.JiraCreationThruFunction.service;

import java.io.*;
import java.net.*;
import javax.json.*;
import java.util.Base64;

import org.springframework.stereotype.Component;


import java.net.URI;

@Component
public class JiraExample {
	public void createJira() throws Exception {
		try {
			URL jiraREST_URL = new URL("https://nagarajusaravanan.atlassian.net/rest/api/2/issue/");
			URLConnection urlConnection = jiraREST_URL.openConnection();
			urlConnection.setDoInput(true);
			
			HttpURLConnection conn = (HttpURLConnection) jiraREST_URL.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);

			String encodedData = URLEncoder.encode(getJSON_Body(), "UTF-8");
			
			String username = "nagarajutechcse@gmail.com";
            String password = "ATATT3xFfGF0LzwkKeXquUgpjg89UJOpEi7ge36H3dkP-tDqygS6GnZ49JcFTWpfu37h24Vszb_J8t96zqjM1dLtdEEGCO_wnO8T0SbSNdhcW8d6ihA2W-BC2Bw3SIXNldLa9kAYYXBOnbJpQwxxJAUOq3EBbNkE39MbKiZxmy7h652dOPfDyQ8=369FF78E";
            String credentials = username + ":" + password;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

			System.out.println(getJSON_Body() + "/" + encodedData);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Basic "+encodedCredentials);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
			conn.getOutputStream().write(getJSON_Body().getBytes());

			try {
				InputStream inputStream = conn.getInputStream();
				System.out.println(inputStream.toString());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getJSON_Body() {
		JsonObject createIssue = Json.createObjectBuilder()
				.add("fields",
						Json.createObjectBuilder().add("project", Json.createObjectBuilder().add("key", "DEMO"))
								.add("summary", "sum").add("description", "descr")
								.add("issuetype", Json.createObjectBuilder().add("name", "Bug")))
				.build();

		return createIssue.toString();

	}

}