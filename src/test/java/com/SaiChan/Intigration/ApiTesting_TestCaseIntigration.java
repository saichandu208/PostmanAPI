package com.SaiChan.Intigration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTesting_TestCaseIntigration {

//    Create Token
//    Create Booking
//    Perform the put request
//    Verify the put request
//    Delete the
//    Verify it doesn't get the get request

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse  validatableResponse;

    String token="";
    String bookingId= "";

    public String getToken() {
        String jsonPayload = "{\"username\" : \"admin\", \"password\" : \"password123\"}";
        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(jsonPayload);

        //When
        Response  response = requestSpecification.when().post();

        //Then
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        //Extract the Token
        token = response.jsonPath().getString(  "token");
        System.out.println(token);
        return token;

    }

    public String getBookingid() {

        String jsonString = "{\n" +
                "    \"firstname\": \"Sally\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2013-02-23\",\n" +
                "        \"checkout\": \"2014-10-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonString).log().all();

        Response  response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        bookingId = response.jsonPath().getString(  "bookingid");
        System.out.println(bookingId);

        return bookingId;
    }
    @Test(priority = 1)
    public void test_update_request_put() {
        token = getToken();
        String bookingid = getBookingid();
        System.out.println(token);
        System.out.println(bookingid);

        String payloadPut = "{\n" +
                "    \"firstname\": \"tommy\",\n" +
                "    \"lastname\": \"jain\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2013-02-23\",\n" +
                "        \"checkout\": \"2014-10-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Lunch\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" +bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payloadPut).log().all();

        Response  response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }
    @Test(priority = 2)
            public void Test_verify_request_get() {
        System.out.println(bookingId);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" +bookingId);
        Response response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstname, "tommy");



    }
   @Test(priority = 3)
    public void test_delete_Booking() {
        System.out.println(bookingId);
        System.out.println(token);
       requestSpecification.baseUri("https://restful-booker.herokuapp.com");
       requestSpecification.basePath("/booking/" +bookingId);
       response = requestSpecification.when().log().all().delete();
       requestSpecification.then().log().all().statusCode(201);

    }
   @Test(priority = 4)
    public void test_after_Delete_Request() {
        System.out.println(bookingId);

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" +bookingId);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(400);

    }

}
