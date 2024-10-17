package events;

import client.EvPrimeClient;
import io.restassured.response.Response;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.response.EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import static org.junit.Assert.assertEquals;

public class EvPrimeNoAuthenticationTests {

    private EvPrimePostUpdateEventRequestModelPostPut postEventUpdateEventRequestBody;
    private EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete postEventUpdateEventResponseBody;
    private Response response;

    @Before
    public void setUp() {
        postEventUpdateEventRequestBody = HelperClass.createDefaultPostUpdateEventRequestBody();
    }

    @Test
    public void unsuccessfulUpdateEventNoAuthenticationTest() {
        response = new EvPrimeClient().putUpdateEventNoAuthenticationRequest(postEventUpdateEventRequestBody);

        postEventUpdateEventResponseBody = response.body().as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        assertEquals(401,response.statusCode());
        assertEquals("Not authenticated.", postEventUpdateEventResponseBody.getMessage());
    }

    @Test
    public void unsuccessfulPostEventNoAuthenticationTest() {
        response = new EvPrimeClient().postEventNoAuthenticationRequest(postEventUpdateEventRequestBody);

        postEventUpdateEventResponseBody = response.body().as(EvPrimeValidDeletePostUpdateInvalidLoginResponseModelPostPutDelete.class);

        assertEquals(401,response.statusCode());
        assertEquals("Not authenticated.", postEventUpdateEventResponseBody.getMessage());
    }
}

