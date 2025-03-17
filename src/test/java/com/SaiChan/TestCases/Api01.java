package com.SaiChan.TestCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Api01 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void api01(){

        requestSpecification= RestAssured.given();
        response=requestSpecification.when().get("https://restful-booker.herokuapp.com/booking");
        validatableResponse= response.then().statusCode(200);

        Assert.assertEquals(1, 1);

    }

}
