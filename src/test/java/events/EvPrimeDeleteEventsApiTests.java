package events;

import client.EvPrimeClient;
import database.EvPrimeDbClient;
import io.restassured.response.Response;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import models.response.EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete;
import models.response.EvPrimeValidLoginResponseModelPost;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EvPrimeDeleteEventsApiTests {

    private final String email = "new_email_test@gmail.com";
    private final String password = "password123";
    private EvPrimeDbClient dbClient;
    private String createdEventId;
    private EvPrimeSignUpLoginRequestModelPostPut loginRequestBody;
    private EvPrimeValidLoginResponseModelPost loginResponseBody;
    private Response response;
    private EvPrimePostUpdateEventRequestModelPostPut postEventRequestBody;
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
        postEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        createdEventId = postEventResponseBody.getMessage().substring(39);
    }

    @Test
    public void deleteEventTest() throws SQLException {
        response = new EvPrimeClient().deleteEventRequest(createdEventId,loginResponseBody.getToken());

        deleteEventResponseBody = response.as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        assertEquals(200,response.statusCode());
        assertEquals("Successfully deleted the event with id: " + createdEventId,deleteEventResponseBody.getMessage());
        assertFalse(dbClient.doesEventExistInDbById(createdEventId));
    }
}

