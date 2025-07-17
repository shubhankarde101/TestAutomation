package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.constants.Constants;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BestBuyAPIStoreTest extends BaseTest {

	@Test
	public void get_post_update_delete_Stores(Hashtable<String, String> data) throws IOException {
		/*
		 * Replacing the name parameter in the endpoint with the data from excel sheet.
		 * Data providers return a hastable and the column name is used as a key to get
		 * the value
		 */
		// Get call
		System.out.println(
				"-----------------This is get call to get store records upto specified limit------------------");
		Response response_get = given().filter(new RequestLoggingFilter(captor)) // This line is mandatory to log the
				// request details to extent report
				.log().all().get(Constants.BASEURL_BESTBUY
						+ Constants.BESTBUY_GETSTORES_ENDPOINT.replace("{limit}", data.get("limit")));

		writeRequestAndResponseInReport(writer.toString(), response_get.prettyPrint());

		// Asserting status code
		response_get.then().statusCode(200);
		// Asserting the list size in the response
		Assert.assertTrue(response_get.jsonPath().getList("data").size() > 0, "No response fetched for this URI");

		// Post call
		System.out.println("-----------------This is post call to create a new store record------------------");
		String academy_name = "DreamLandAcademy";
		Response response_post = given().filter(new RequestLoggingFilter(captor))
				.header("Content-Type", "application/json").contentType(ContentType.JSON).log().all()
				.body("{\r\n" + "  \"name\": \"" + academy_name + "\",\r\n"

						+ "  \"type\": \"Educational Institute\",\r\n" + "  \"address\": \"Barrackpore\",\r\n"
						+ "  \"address2\": \"North 24 PGS\",\r\n" + "  \"city\": \"Kolkata\",\r\n"
						+ "  \"state\": \"West Bengal\",\r\n" + "  \"zip\": \"700122\",\r\n" + "  \"lat\": 5,\r\n"
						+ "  \"lng\": 6,\r\n" + "  \"hours\": \"Eight\",\r\n" + "  \"services\":\r\n"
						+ "         {\"name\":\"Beautician\", \"CourseType\":\"Regular\"}\r\n" + "       \r\n" + "} ")
				.post(Constants.BASEURL_BESTBUY + Constants.BESTBUY_POSTSTORES_ENDPOINT);

		writeRequestAndResponseInReport(writer.toString(), response_post.prettyPrint());

		response_post.then().statusCode(201);
		int id = response_post.jsonPath().get("id");
		System.out.println(id);

		Response response_get_with_id = given().log().all()
				.get(Constants.BASEURL_BESTBUY + Constants.BESTBUY_POSTSTORES_ENDPOINT + "/" + Integer.toString(id));
		Assert.assertEquals(response_get_with_id.jsonPath().getString("name"), academy_name);
		Assert.assertEquals(response_get_with_id.jsonPath().getString("type"), "Educational Institute");

		// Patch call
		System.out.println("-----------------This is patch call to upodate a store record partially------------------");
		Response response_patch = given().filter(new RequestLoggingFilter(captor))
				.header("Content-Type", "application/json").contentType(ContentType.JSON).log().all()
				.body("{\r\n" + "  \"name\": \"" + academy_name + "\",\r\n"

						+ "  \"type\": \"Educational Institute For Cultural Programme\",\r\n"
						+ "  \"address\": \"Barrackpore\",\r\n" + "  \"address2\": \"North 24 PGS\",\r\n"
						+ "  \"city\": \"Kolkata\",\r\n" + "  \"state\": \"West Bengal\",\r\n"
						+ "  \"zip\": \"700122\",\r\n" + "  \"lat\": 5,\r\n" + "  \"lng\": 6,\r\n"
						+ "  \"hours\": \"Eight\",\r\n" + "  \"services\":\r\n"
						+ "         {\"name\":\"Beautician\", \"CourseType\":\"Regular\"}\r\n" + "       \r\n" + "} ")
				.patch(Constants.BASEURL_BESTBUY + Constants.BESTBUY_POSTSTORES_ENDPOINT + "/" + Integer.toString(id));
		response_patch.then().statusCode(200);
		writeRequestAndResponseInReport(writer.toString(), response_patch.prettyPrint());
		Assert.assertEquals(response_patch.jsonPath().getString("type"), "Educational Institute For Cultural Programme");

		System.out.println("-----------------Deleting the Recored just added------------------");
		// Delete call
		Response response_delete = given().log().all()
				.delete(Constants.BASEURL_BESTBUY + Constants.BESTBUY_POSTSTORES_ENDPOINT + "/" + Integer.toString(id));
		response_delete.then().statusCode(200);

	}

}
