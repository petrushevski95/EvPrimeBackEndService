package users;

import client.EvPrimeClient;
import io.restassured.response.Response;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import models.response.EvPrimeErrorsResponseModelPostPut;
import models.response.EvPrimeValidLoginResponseModelPost;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EvPrimeLoginApiTests {

    private final String email = "new_email1235676789_test@gmail.com";
    private final String password = "password123";
    private final String invalidEmail = "new_email_addressgmail.com";
    private final String invalidPassword = "password1234";
    private EvPrimeSignUpLoginRequestModelPostPut signUpRequestBody;
    private EvPrimeSignUpLoginRequestModelPostPut loginRequestBody;
    private Response response;
    private EvPrimeErrorsResponseModelPostPut signUpResponseBody;
    private EvPrimeValidLoginResponseModelPost loginResponseBody;
    private EvPrimeErrorsResponseModelPostPut loginErrorResponseBody;

    @Before
    public void setUp() {
        loginRequestBody = HelperClass.createDefaultSignUpLoginRequestBody(email,password);
    }

    @Test
    public void successfulLoginTest() {
        response = new EvPrimeClient().postLoginRequest(loginRequestBody);

        loginResponseBody = response.as(EvPrimeValidLoginResponseModelPost.class);

        assertEquals(200, response.statusCode());
        assertNotNull(loginResponseBody.getExpirationTime());
        assertNotNull(loginResponseBody.getToken());
    }

    @Test
    public void unsuccessfulLoginInvalidEmailTest() {
        loginRequestBody.setEmail(invalidEmail);

        response = new EvPrimeClient().postLoginRequest(loginRequestBody);

        loginErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(401, response.statusCode());
        assertEquals("Authentication failed.", loginErrorResponseBody.getMessage());
    }

    @Test
    public void unsuccessfulLoginInvalidPasswordTest() {
        loginRequestBody.setPassword(invalidPassword);

        response = new EvPrimeClient().postLoginRequest(loginRequestBody);

        loginErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422, response.statusCode());
        assertEquals("Invalid email or password entered.", loginErrorResponseBody.getErrors().getCredentials());
    }

    @Test
    public void unsuccessfulLoginEmptyPasswordTest() {
        loginRequestBody.setPassword("");

        response = new EvPrimeClient().postLoginRequest(loginRequestBody);

        loginErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422, response.statusCode());
        assertEquals("Invalid email or password entered.", loginErrorResponseBody.getErrors().getCredentials());
    }
}
