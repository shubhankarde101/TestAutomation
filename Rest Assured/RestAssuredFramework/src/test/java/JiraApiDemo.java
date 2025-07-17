import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.sun.jersey.core.util.Base64;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraApiDemo {

	public static void main(String[] args) {

		// Access Token must be generated from Atlassian JIRA site before doing
		// Automation. We have used Basic Authentication here
		String apitoken = "WWJ6fQeviK4H6af68ltY9EBB";

		// Login Scenario and Create Issue(Bug)
		RestAssured.baseURI = "https://shubhankar.atlassian.net/rest/api/2";
		String createIssueUrl = "/issue";
		// JQL; JIRA Query Language
		String searchurl = "/search?jql= assignee = currentuser() AND project = 'MyFirstJiraProject' AND issuetype='Bug'";
		String auth = new String(Base64.encode("de.subho9@gmail.com" + ":" + apitoken));
		final String headerAuthorization = "Authorization";
		final String headerAuthorizationValue = "Basic " + auth;
		final String headerType = "application/json";
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", headerType);
		headerMap.put(headerAuthorization, headerAuthorizationValue);
		
		// Sample Json
		/*
		 * { "fields": { "project": { "key": "MYF" }, "summary": "Issue REST Summary",
		 * "description": "Creating an issue", "issuetype": { "name": "Bug" }, "labels":
		 * [ "Automation" ], "assignee": { "name": "" } }
		 */

		String createPaylod = "{\r\n" + "  \"fields\": {\r\n" + "    \"project\": {\r\n" + "      \"key\": \"MYF\"\r\n"
				+ "    },\r\n" + "    \"summary\": \"Issue REST Summary_" + Integer.toString(new Random().nextInt())
				+ "\",\r\n" + "    \"description\": \"Creating an issue\",\r\n" + "    \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n" + "    },\r\n" + "    \"labels\": [\r\n" + "      \"Automation\"\r\n"
				+ "    ],\r\n" + "    \"assignee\": {\r\n" + "      \"name\": \"\"\r\n" + "    }   \r\n" + "  }\r\n"
				+ "}";
		Response response_NewIssue = null;
		try {
			response_NewIssue = RestAssured.given().log().all().headers(headerMap).body(createPaylod).when().log().all()
					.post(createIssueUrl).then().log().all().assertThat().statusCode(201).extract().response();
			JsonPath js = new JsonPath(response_NewIssue.asString());
			response_NewIssue.prettyPrint();
			System.out.println(response_NewIssue.getStatusCode());

			// Get Issue
			String issuekey = js.get("key");
			Response issueDetails = RestAssured.given().log().all().headers(headerMap).pathParam("key", issuekey)
					.queryParam("fields", "summary").log().all().when().get("/issue/{key}").then().log().all()
					.assertThat().statusCode(200).extract().response();

			System.out.println(issueDetails);

			JsonPath js1 = new JsonPath(issueDetails.asString());
			String key = js1.getString("fields.summary");
			System.out.println(issueDetails.getStatusCode());
			System.out.println("------------------" + key + "--------------------");

			// Add comment
			RestAssured.given().log().all().headers(headerMap).pathParam("key", issuekey).body("\r\n" + "{\r\n"
					+ "    \"body\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\"\r\n"
					+ "    \r\n" + "}\r\n" + "").when().log().all().post("/issue/{key}/comment").then().log().all()
					.assertThat().statusCode(201).extract().response();

			// Add Attachment
			HashMap<String, String> headerMap1 = new HashMap<String, String>();
			headerMap1.put("Content-Type", headerType);
			headerMap1.put(headerAuthorization, headerAuthorizationValue);
			headerMap1.put("X-Atlassian-Token", "no-check");
			headerMap1.put("Content-Type", "multipart/form-data");
			RestAssured.given().log().all().headers(headerMap1).pathParam("key", issuekey)
					.multiPart("file", new File("jira.txt")).when().post("/issue/{key}/attachments").then().log().all()
					.assertThat().statusCode(200).extract().response();

			// Delete Issue
			RestAssured.given().log().all().headers(headerMap).pathParam("key", issuekey).log().all().when()
					.delete("/issue/{key}").then().log().all().assertThat().statusCode(204).extract().response();

			// Get all Issue Types
			Response allIssues = RestAssured.given().log().all().headers(headerMap).log().all().when().get(searchurl)
					.then().log().all().assertThat().statusCode(200).extract().response();
			JsonPath path = new JsonPath(allIssues.asString());
			ArrayList<String> allKeys = path.get("issues.key");
			allKeys.forEach(name -> {
				System.out.println(name);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
