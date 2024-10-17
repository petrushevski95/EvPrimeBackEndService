package events;

import client.EvPrimeClient;
import database.EvPrimeDbClient;
import io.restassured.response.Response;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import models.response.EvPrimeErrorsResponseModelPostPut;
import models.response.EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete;
import models.response.EvPrimeValidLoginResponseModelPost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EvPrimePostEventsApiTest {

    private final String email = "new_email@gmail.com";
    private final String password = "password123";
    private EvPrimeDbClient dbClient;
    private String createdEventId;
    private EvPrimeSignUpLoginRequestModelPostPut loginRequestBody;
    private EvPrimeValidLoginResponseModelPost loginResponseBody;
    private Response response;
    private EvPrimePostUpdateEventRequestModelPostPut postEventRequestBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete postEventResponseBody;
    private EvPrimePostUpdateEventRequestModelPostPut databasePostedEvent;
    private EvPrimeErrorsResponseModelPostPut postErrorResponseBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete deleteEventResponseBody;

    @Before
    public void setUp() {
        dbClient = new EvPrimeDbClient();

        loginRequestBody = HelperClass.createDefaultSignUpLoginRequestBody(email,password);
        response = new EvPrimeClient().postLoginRequest(loginRequestBody);
        loginResponseBody = response.as(EvPrimeValidLoginResponseModelPost.class);

        postEventRequestBody = HelperClass.createDefaultPostUpdateEventRequestBody();
    }

    @Test
    public void successfulPostEventTest() throws SQLException {
        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());

        postEventResponseBody = response.body().as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        createdEventId = postEventResponseBody.getMessage().substring(39);
        databasePostedEvent = dbClient.getEventFromDbById(createdEventId);

        assertEquals(201,response.statusCode());
        assertEquals("Successfully created an event with id: " + createdEventId,postEventResponseBody.getMessage());
        assertEquals(postEventRequestBody.getTitle(),databasePostedEvent.getTitle());
        assertEquals(postEventRequestBody.getImage(),databasePostedEvent.getImage());
        assertEquals(postEventRequestBody.getDate(),databasePostedEvent.getDate());
        assertEquals(postEventRequestBody.getLocation(),databasePostedEvent.getLocation());
        assertEquals(postEventRequestBody.getDescription(),databasePostedEvent.getDescription());
    }

    @Test
    public void unsuccessfulPostEventEmptyTitleTest() {
        postEventRequestBody.setTitle("");

        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());

        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
        assertEquals("Invalid title.",postErrorResponseBody.getErrors().getTitle());
    }

    @Test
    public void unsuccessfulPostEventIncorrectImagePathTest() {
        postEventRequestBody.setImage("encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEiCWEymdP563mAt2rKoTs3wC6cF4v13uVqQ&s");

        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());

        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
        assertEquals("Invalid image.",postErrorResponseBody.getErrors().getImage());
    }

    @Test
    public void unsuccessfulPostEventEmptyImagePathTest() {
        postEventRequestBody.setImage("");

        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());

        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
        assertEquals("Invalid image.",postErrorResponseBody.getErrors().getImage());
    }

    @Test
    public void unsuccessfulPostEventEmptyDateTest() {
        postEventRequestBody.setDate("");

        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());

        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
        assertEquals("Invalid date.",postErrorResponseBody.getErrors().getDate());
    }

    @Test
    public void unsuccessfulPostEventEmptyDescriptionTest() {
        postEventRequestBody.setDescription("");

        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());

        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
        assertEquals("Invalid description.",postErrorResponseBody.getErrors().getDescription());
    }

    @After
    public void cleanUp() throws SQLException {
        response = new EvPrimeClient().deleteEventRequest(createdEventId,loginResponseBody.getToken());

        deleteEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);
        assertFalse(dbClient.doesEventExistInDbById(createdEventId));
    }

//    @Test //BUG FOUND: wrong error message Test case 18.
//    public void unsuccessfulPostEventEmptyLocationTest() {
//        postEventRequestBody.setLocation("");
//
//        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());
//
//        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModel.class);
//
//        assertEquals(422,response.statusCode());
//        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
//        assertEquals("Invalid location.",postErrorResponseBody.getErrors().getLocation());
//    }
//
//    @Test //BUG FOUND: location field is missing and description has wrong error message into the response body Test case 20.
//    public void unsuccessfulPostEventEmptyValuesTest() {
//        postEventRequestBody.setTitle("");
//        postEventRequestBody.setImage("");
//        postEventRequestBody.setDate("");
//        postEventRequestBody.setLocation("");
//        postEventRequestBody.setDescription("");
//
//        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());
//
//        postErrorResponseBody = response.body().as(EvPrimeErrorsResponseModel.class);
//
//        assertEquals(422,response.statusCode());
//        assertEquals("Adding the event failed due to validation errors.",postErrorResponseBody.getMessage());
//        assertEquals("Invalid title.",postErrorResponseBody.getErrors().getTitle());
//        assertEquals("Invalid image.",postErrorResponseBody.getErrors().getImage());
//        assertEquals("Invalid date.",postErrorResponseBody.getErrors().getDate());
//        assertEquals("Invalid location.",postErrorResponseBody.getErrors().getLocation());
//        assertEquals("Invalid description.",postErrorResponseBody.getErrors().getDescription());
//    }
}

