package com.SaiChan.TestCases;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class Sample {

    @Test
    public void test_get_Request() {
    RestAssured
            .given()
            .baseUri(  "https://restful-booker.herokuapp.com")
            .basePath( "/booking/1")
            .when()
            .get()
            .then().log().all()
            .statusCode(200);


}
}
