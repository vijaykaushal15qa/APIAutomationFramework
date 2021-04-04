package api;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import commonutilities.BaseTest;

import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseTest {
    public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String, String> data) {
        Response response = given().auth().basic(config.getProperty("validSecretKey"), "")
                .formParam("name",data.get("name")).formParam("email", data.get("email")).
                 formParam("description", data.get("description"))
                .post(config.getProperty("customerAPIEndPoint"));

        return response;

    }

    public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(Hashtable<String, String> data) {

        Response response = given().auth().basic(config.getProperty("inValidSecretKey"), "")
                .formParam("name",data.get("name")).formParam("email", data.get("email")).
                 formParam("description", data.get("description"))
                .post(config.getProperty("customerAPIEndPoint"));
        return response;
    }

}
