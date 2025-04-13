package com.SaiChan.TestCases.Get;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class APITesting006_NonBDDStyle_Get {
    static RequestSpecification r = RestAssured.given();

    @Description("TC1 - NonBDDStyleGET - Positive Testcase")
    @Test
    public void test_NonBDDStyleGET_positive(){
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/388620");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }

    @Description("TC2 - NonBDDStyleGET - Negative Testcase")
    @Test
    public void test_NonBDDStyleGET_negative(){
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}
