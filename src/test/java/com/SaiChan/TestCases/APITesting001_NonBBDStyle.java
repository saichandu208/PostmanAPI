package com.SaiChan.TestCases;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class APITesting001_NonBBDStyle {

    RequestSpecification r = RestAssured.given();
    @Description("TC1-Non BDD Style-PositiveTestcases")
    @Test
    public void Api_NonBDD_positiveCase() {

        r.baseUri("https://api.zippopotam.us");
        r.basePath("/In/388620");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
    @Test
    public void Api_NonBDD_NegativeCase() {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/In/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}
