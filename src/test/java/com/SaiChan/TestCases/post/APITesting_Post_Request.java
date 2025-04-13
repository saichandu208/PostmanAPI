package com.SaiChan.TestCases.post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_Post_Request {
     RequestSpecification requestSpecification;
    @Test
            public void Positive_Case()

    {
        String jsonPayload = "{\"username\" : \"admin\", \"password\" : \"password123\"}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(jsonPayload);

        //When
        Response response = requestSpecification.when().post();

        //Then
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
    }
}
