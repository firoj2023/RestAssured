package common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class common_method_get_api {
	public static int responsestatus_extractor(String baseuri ,
			String resource)
	{
		RestAssured.baseURI=baseuri;
		int response_statuscode=given().when().get(resource).then().
				extract().statusCode();
		return response_statuscode;
	}
	public static String reponsebody_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
		String response_body = given().when().get(resource).then().extract().response().asString();
		return response_body;
	}
}
