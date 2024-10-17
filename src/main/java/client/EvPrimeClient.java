package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;

import static utils.EvPrimeConfiguration.*;


public class EvPrimeClient {

    public Response postSignUpRequest(EvPrimeSignUpLoginRequestModelPostPut requestBody) {
        return RestAssured
                .given()
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(SIGN_UP_URL)
                .thenReturn();
    }

    public Response postLoginRequest(EvPrimeSignUpLoginRequestModelPostPut requestBody) {
        return RestAssured
                .given()
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(LOGIN_URL)
                .thenReturn();
    }

    public Response getAllEventsRequest() {
        return RestAssured
                .given()
                .when().log().all()
                .get(GET_POST_PUT_DELETE_EVENTS)
                .thenReturn();
    }

    public Response getSingleEventRequest(String id) {
        return RestAssured
                .given()
                .when().log().all()
                .get(GET_POST_PUT_DELETE_EVENTS + "/" + id)
                .thenReturn();
    }

    public Response postEventAuthenticationRequest(EvPrimePostUpdateEventRequestModelPostPut requestBody, String authToken) {
        return RestAssured
                .given()
                .when().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(requestBody)
                .post(GET_POST_PUT_DELETE_EVENTS)
                .thenReturn();
    }

    public Response postEventNoAuthenticationRequest(EvPrimePostUpdateEventRequestModelPostPut requestBody) {
        return RestAssured
                .given()
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(GET_POST_PUT_DELETE_EVENTS)
                .thenReturn();
    }

    public Response putUpdateEventAuthenticationRequest
            (EvPrimePostUpdateEventRequestModelPostPut requestBody, String id, String authToken) {
        return RestAssured
                .given()
                .when().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .body(requestBody)
                .put(GET_POST_PUT_DELETE_EVENTS + "/" + id)
                .thenReturn();
    }

    public Response putUpdateEventNoAuthenticationRequest(EvPrimePostUpdateEventRequestModelPostPut requestBody) {
        return RestAssured
                .given()
                .when().log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(GET_POST_PUT_DELETE_EVENTS)
                .thenReturn();
    }

    public Response deleteEventRequest(String id, String authToken) {
        return RestAssured
                .given()
                .when().log().all()
                .header("Authorization", "Bearer " + authToken)
                .delete(GET_POST_PUT_DELETE_EVENTS + "/" + id)
                .thenReturn();
    }
}
