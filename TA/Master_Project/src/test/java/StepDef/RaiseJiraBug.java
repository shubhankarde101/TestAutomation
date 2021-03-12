package StepDef;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.automation.JiraManagementPojo.IssueTypeBugPojo;
import com.automation.JiraManagementPojo.IssueTypeBugPojo.Assignee;
import com.automation.JiraManagementPojo.IssueTypeBugPojo.Field;
import com.automation.JiraManagementPojo.IssueTypeBugPojo.IssueType;
import com.automation.JiraManagementPojo.IssueTypeBugPojo.Project;
import com.sun.jersey.core.util.Base64;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RaiseJiraBug extends BasePage {

	public String raiseBug(String description, String summary, String userid, File file) {

		// Access Token must be generated from Atlassian JIRA site before doing
		// Automation. We have used Basic Authentication here
		String apitoken = props.readPropertiesFile("JiraApiToken");
		// Login Scenario and Create Issue(Bug)
		RestAssured.baseURI = props.readPropertiesFile("BaseURI");
		String createIssueUrl = "/issue";
		// JQL; JIRA Query Language
		String searchurl = props.readPropertiesFile("searchURLwithJiraQueryLanguage");
		String auth = new String(Base64.encode(props.readPropertiesFile("UserId") + ":" + apitoken));
		final String headerAuthorization = "Authorization";
		final String headerAuthorizationValue = "Basic " + auth;
		final String headerType = "application/json";
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", headerType);
		headerMap.put(headerAuthorization, headerAuthorizationValue);
		Response response_NewIssue = null;
		JsonPath path = null;
		IssueTypeBugPojo b = new IssueTypeBugPojo();
		Assignee a = b.new Assignee();
		a.setAssignee(userid);
		Project p = b.new Project();
		p.setKey(props.readPropertiesFile("ProjectKey"));
		IssueType i = b.new IssueType();
		i.setName("Bug");
		Field f = b.new Field();
		f.setProject(p);
		f.setSummary(summary);
		f.setDescription(description);
		f.setIssuetype(i);
		List<String> label = new ArrayList<String>();
		label.add("Automation");
		f.setLabels(label);
		f.setAssignee(a);
		b.setFields(f);

		try {

			// Delete all issue types (Bug)
			Response allIssues = RestAssured.given().log().all().headers(headerMap).log().all().when().get(searchurl)
					.then().log().all().assertThat().statusCode(200).extract().response();
			path = new JsonPath(allIssues.asString());
			ArrayList<String> allKeys = path.get("issues.key");
			if (allKeys.size() > 0) {
				for (String key : allKeys) {
					RestAssured.given().log().all().headers(headerMap).pathParam("key", key).log().all().when()
							.delete("/issue/{key}").then().log().all().assertThat().statusCode(204).extract()
							.response();
					System.out.println(key);
				}
			}

			// Create issue type (Bug)
			response_NewIssue = RestAssured.given().log().all().headers(headerMap).body(b).when().log().all()
					.post(createIssueUrl).then().log().all().assertThat().statusCode(201).extract().response();
			response_NewIssue.prettyPrint();
			System.out.println(
					"-----------------------" + response_NewIssue.getStatusCode() + "------------------------");
			path = new JsonPath(response_NewIssue.asString());		

			// Add Attachment
			HashMap<String, String> headerMap1 = new HashMap<String, String>();
			headerMap1.put("Content-Type", headerType);
			headerMap1.put(headerAuthorization, headerAuthorizationValue);
			headerMap1.put("X-Atlassian-Token", "no-check");
			headerMap1.put("Content-Type", "multipart/form-data");
			RestAssured.given().log().all().headers(headerMap1).pathParam("key", path.getString("key"))
					.multiPart("file", file).when().post("/issue/{key}/attachments").then().log().all()
					.assertThat().statusCode(200).extract().response();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path.getString("self");

	}

}
//Sample Json for raising a Jira Bug
/*
 * { "fields": { "project": { "key": "MYF" }, "summary": "Issue REST Summary",
 * "description": "Creating an issue", "issuetype": { "name": "Bug" }, "labels":
 * [ "Automation" ], "assignee": { "name": "" } }}
 */