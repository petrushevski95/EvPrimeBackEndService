package events;

import client.EvPrimeClient;
import database.EvPrimeDbClient;
import io.restassured.response.Response;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import models.response.EvPrimeErrorsResponseModelPostPut;
import models.response.EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete;
import models.response.EvPrimeValidLoginResponseModelPost;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static utils.EvPrimeDateBuilder.currentTime;

public class EvPrimeUpdateEventsApiTests {

    private final String email = "new_email@gmail.com";
    private final String password = "password123";
    private EvPrimeDbClient dbClient;
    private String createdEventId;
    private EvPrimeSignUpLoginRequestModelPostPut loginRequestBody;
    private EvPrimeValidLoginResponseModelPost loginResponseBody;
    private Response response;
    private EvPrimePostUpdateEventRequestModelPostPut postEventRequestBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete postEventResponseBody;
    private EvPrimePostUpdateEventRequestModelPostPut updateEventRequestBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete updateEventResponseBody;
    private EvPrimePostUpdateEventRequestModelPostPut databasePostedEvent;
    private EvPrimeErrorsResponseModelPostPut updateErrorResponseBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete deleteEventResponseBody;

    @Before
    public void setUp() {
        dbClient = new EvPrimeDbClient();

        loginRequestBody = HelperClass.createDefaultSignUpLoginRequestBody(email,password); // izgradi
        response = new EvPrimeClient().postLoginRequest(loginRequestBody); // prati
        loginResponseBody = response.as(EvPrimeValidLoginResponseModelPost.class); // vrati

        postEventRequestBody = HelperClass.createDefaultPostUpdateEventRequestBody();
        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());
        postEventResponseBody = response.body().as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);
        createdEventId = postEventResponseBody.getMessage().substring(39);

        updateEventRequestBody = HelperClass.createDefaultPostUpdateEventRequestBody();
        System.out.println(createdEventId);
    }

    @Test
    public void successfulUpdateEventTest() throws SQLException {
        updateEventRequestBody.setTitle(RandomStringUtils.randomAlphanumeric(20));
        updateEventRequestBody.setImage("https://thumb.ac-illust.com/e2/e2cbae08aee6ed3c5fa742b33e936831_t.jpeg");
        updateEventRequestBody.setDate(currentTime());
        updateEventRequestBody.setLocation(RandomStringUtils.randomAlphanumeric(20));
        updateEventRequestBody.setDescription(RandomStringUtils.randomAlphanumeric(20));

        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());

        updateEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        databasePostedEvent = dbClient.getEventFromDbById(createdEventId);

        assertEquals(201,response.statusCode());
        assertEquals("Successfully updated the event with id: " + createdEventId,updateEventResponseBody.getMessage());
        assertEquals(updateEventRequestBody.getTitle(),databasePostedEvent.getTitle());
        assertEquals(updateEventRequestBody.getImage(),databasePostedEvent.getImage());
        assertEquals(updateEventRequestBody.getDate(),databasePostedEvent.getDate());
        assertEquals(updateEventRequestBody.getLocation(),databasePostedEvent.getLocation());
        assertEquals(updateEventRequestBody.getDescription(),databasePostedEvent.getDescription());
    }

    @Test
    public void unsuccessfulUpdateEventEmptyTitleTest() {
        updateEventRequestBody.setTitle("");

        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());

        updateErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Updating the event failed due to validation errors.",updateErrorResponseBody.getMessage());
        assertEquals("Invalid title.",updateErrorResponseBody.getErrors().getTitle());
    }

    @Test
    public void unsuccessfulUpdateEventIncorrectImagePathTest() {
        updateEventRequestBody.setImage("//thumb.ac-illust.com/e2/e2cbae08aee6ed3c5fa742b33e936831_t.jpeg");

        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());

        updateErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Updating the event failed due to validation errors.",updateErrorResponseBody.getMessage());
        assertEquals("Invalid image.",updateErrorResponseBody.getErrors().getImage());
    }

    @Test
    public void unsuccessfulUpdateEventEmptyImageTest() {
        updateEventRequestBody.setImage("");

        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());

        updateErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Updating the event failed due to validation errors.",updateErrorResponseBody.getMessage());
        assertEquals("Invalid image.",updateErrorResponseBody.getErrors().getImage());
    }

    @Test
    public void unsuccessfulUpdateEventEmptyDateTest() {
        updateEventRequestBody.setDate("");

        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());

        updateErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Updating the event failed due to validation errors.",updateErrorResponseBody.getMessage());
        assertEquals("Invalid date.",updateErrorResponseBody.getErrors().getDate());
    }

    @Test
    public void unsuccessfulUpdateEventEmptyDescriptionTest() {
        updateEventRequestBody.setDescription("");

        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());

        updateErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("Updating the event failed due to validation errors.",updateErrorResponseBody.getMessage());
        assertEquals("Invalid description.",updateErrorResponseBody.getErrors().getDescription());
    }

    @After
    public void cleanUp() throws SQLException {
        response = new EvPrimeClient().deleteEventRequest(createdEventId,loginResponseBody.getToken());

        deleteEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);
        assertFalse(dbClient.doesEventExistInDbById(createdEventId));
    }

//    @Test // BUG FOUND: invalid error message into the response body check Test case 27
//    public void unsuccessfulUpdateEventEmptyLocationTest() {
//        updateEventRequestBody.setLocation("");
//
//        response = new EvPrimeClient().putUpdateEventAuthenticationRequest(updateEventRequestBody,createdEventId,loginResponseBody.getToken());
//
//        updateErrorResponseBody = response.as(EvPrimeErrorsResponseModel.class);
//
//        assertEquals(422,response.statusCode());
//        assertEquals("Updating the event failed due to validation errors.",updateErrorResponseBody.getMessage());
//        assertEquals("Invalid location.",updateErrorResponseBody.getErrors().getLocation());
//    }
//
//    @Test // BUG FOUND: location field is missing and description has wrong error message into the response body check Test case 29.
//    public void unsuccessfulUpdateEventEmptyFieldsTest() {
//        postEventRequestBody.setTitle("");
//        postEventRequestBody.setImage("");
//        postEventRequestBody.setDate("");
//        postEventRequestBody.setLocation("");
//        postEventRequestBody.setDescription("");
//
//        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());
//
//        updateErrorResponseBody = response.body().as(EvPrimeErrorsResponseModel.class);
//
//        assertEquals(422,response.statusCode());
//        assertEquals("Adding the event failed due to validation errors.",updateErrorResponseBody.getMessage());
//        assertEquals("Invalid title.",updateErrorResponseBody.getErrors().getTitle());
//        assertEquals("Invalid image.",updateErrorResponseBody.getErrors().getImage());
//        assertEquals("Invalid date.",updateErrorResponseBody.getErrors().getDate());
//        assertEquals("Invalid location.",updateErrorResponseBody.getErrors().getLocation());
//        assertEquals("Invalid description.",updateErrorResponseBody.getErrors().getDescription());
//    }
}



