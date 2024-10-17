package events;

import client.EvPrimeClient;
import database.EvPrimeDbClient;
import io.restassured.response.Response;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import models.response.EvPrimeGetAllEventsResponseModelGetAll;
import models.response.EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete;
import models.response.EvPrimeValidLoginResponseModelPost;
import models.response.events.EvPrimeEvents;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EvPrimeGetSingleEventApiTests {

    private final String email = "new_email_test@gmail.com";
    private final String password = "password123";
    private String createdEventId;
    private EvPrimeDbClient dbClient;
    private EvPrimeSignUpLoginRequestModelPostPut loginRequestBody;
    private EvPrimeValidLoginResponseModelPost loginResponseBody;
    private Response response;
    private EvPrimePostUpdateEventRequestModelPostPut postEventRequestBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete postEventResponseBody;
    private EvPrimeGetAllEventsResponseModelGetAll getSingleEventResponseBody;
    private EvPrimePostUpdateEventRequestModelPostPut databasePostedEvent;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete deleteEventResponseBody;

    @Before
    public void setUp() {
        dbClient = new EvPrimeDbClient();

        loginRequestBody = HelperClass.createDefaultSignUpLoginRequestBody(email,password);
        response = new EvPrimeClient().postLoginRequest(loginRequestBody);
        loginResponseBody = response.as(EvPrimeValidLoginResponseModelPost.class);

        postEventRequestBody = HelperClass.createDefaultPostUpdateEventRequestBody();
        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());
        postEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        createdEventId = postEventResponseBody.getMessage().substring(39);
    }

    @Test
    public void getSingleEventTest() throws SQLException {
        response = new EvPrimeClient().getSingleEventRequest(createdEventId);

        getSingleEventResponseBody = response.body().as(EvPrimeGetAllEventsResponseModelGetAll.class);

        List<EvPrimeEvents> event = getSingleEventResponseBody.getEvents();
        databasePostedEvent = dbClient.getEventFromDbById(createdEventId);

        assertEquals(200,response.statusCode());
        assertEquals(createdEventId,event.getFirst().getId());
        assertEquals(databasePostedEvent.getTitle(),event.getFirst().getTitle());
        assertEquals(databasePostedEvent.getImage(),event.getFirst().getImage());
        assertEquals(databasePostedEvent.getDate(),event.getFirst().getDate());
        assertEquals(databasePostedEvent.getLocation(),event.getFirst().getLocation());
        assertEquals(databasePostedEvent.getDescription(),event.getFirst().getDescription());
    }

    @After
    public void cleanUp() throws SQLException {
        response = new EvPrimeClient().deleteEventRequest(createdEventId,loginResponseBody.getToken());

        deleteEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);
        assertFalse(dbClient.doesEventExistInDbById(createdEventId));
    }

}
