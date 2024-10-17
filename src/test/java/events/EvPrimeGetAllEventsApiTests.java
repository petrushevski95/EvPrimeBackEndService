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

public class EvPrimeGetAllEventsApiTests {

    private final String email = "new_email_test@gmail.com";
    private final String password = "password123";
    private String createdEventId;
    private EvPrimeDbClient dbClient;
    private EvPrimeSignUpLoginRequestModelPostPut loginRequestBody;
    private EvPrimeValidLoginResponseModelPost loginResponseBody;
    private Response response;
    private EvPrimePostUpdateEventRequestModelPostPut postEventRequestBody;
    private EvPrimeGetAllEventsResponseModelGetAll getAllEventsResponseBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete postEventResponseBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete deleteEventResponseBody;

    @Before
    public void setUp() {
        dbClient = new EvPrimeDbClient();
        loginRequestBody = HelperClass.createDefaultSignUpLoginRequestBody(email,password);
        response = new EvPrimeClient().postLoginRequest(loginRequestBody);
        loginResponseBody = response.as(EvPrimeValidLoginResponseModelPost.class);

        postEventRequestBody = HelperClass.createDefaultPostUpdateEventRequestBody();
        response = new EvPrimeClient().postEventAuthenticationRequest(postEventRequestBody,loginResponseBody.getToken());
        postEventResponseBody = response.body().as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);
        createdEventId = postEventResponseBody.getMessage().substring(39);
    }

    @Test
    public void getAllEventsRequestTest() {
        response = new EvPrimeClient().getAllEventsRequest();

        getAllEventsResponseBody = response.as(EvPrimeGetAllEventsResponseModelGetAll.class);

        List<EvPrimeEvents> events = getAllEventsResponseBody.getEvents();

        assertEquals(200,response.statusCode());
        assertFalse(events.isEmpty());
    }

    @After
    public void cleanUp() throws SQLException {
        response = new EvPrimeClient().deleteEventRequest(createdEventId,loginResponseBody.getToken());

        deleteEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);
        assertFalse(dbClient.doesEventExistInDbById(createdEventId));
    }
}
