package users;

import client.EvPrimeClient;
import io.restassured.response.Response;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import models.response.EvPrimeErrorsResponseModelPostPut;
import models.response.EvPrimeValidSignUpResponseModelPost;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import utils.HelperClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.EvPrimeDateBuilder.currentTime;

public class EvPrimeSignUpApiTests {

    private final String email = "new_email@gmail.com";
    private final String password = "password123";
    private final String invalidEmail = "new_email_addressgmail.com";
    private final String shortPassword = "pass1";
    private EvPrimeSignUpLoginRequestModelPostPut signUpRequestBody;
    private Response response;
    private EvPrimeValidSignUpResponseModelPost signUpResponseBody;
    private EvPrimeErrorsResponseModelPostPut signUpErrorResponseBody;

    @Before
    public void setUp() {
       signUpRequestBody = HelperClass.createDefaultSignUpLoginRequestBody
               (currentTime() + RandomStringUtils.randomAlphanumeric(10) + "@mail.com"
                       ,RandomStringUtils.randomAlphanumeric(10));
    }

    @Test
    public void successfulSignUpTest() {
       response = new EvPrimeClient().postSignUpRequest(signUpRequestBody);

       signUpResponseBody = response.as(EvPrimeValidSignUpResponseModelPost.class);

       assertEquals(201, response.statusCode());
       assertEquals("User created.", signUpResponseBody.getMessage());
       assertNotNull(signUpResponseBody.getUser().getId());
       assertEquals(signUpRequestBody.getEmail(),signUpResponseBody.getUser().getEmail());
       assertNotNull(signUpResponseBody.getToken());
}

    @Test
    public void unsuccessfulSignUpInvalidEmailFormatTest() {
        signUpRequestBody.setEmail(invalidEmail);

        response = new EvPrimeClient().postSignUpRequest(signUpRequestBody);

        signUpErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422,response.statusCode());
        assertEquals("User signup failed due to validation errors.", signUpErrorResponseBody.getMessage());
        assertEquals("Invalid email.", signUpErrorResponseBody.getErrors().getEmail());
    }

    @Test
    public void unsuccessfulSignUpMissingEmailTest() {
        signUpRequestBody.setEmail("");

        response = new EvPrimeClient().postSignUpRequest(signUpRequestBody);

        signUpErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422, response.statusCode());
        assertEquals("User signup failed due to validation errors.",signUpErrorResponseBody.getMessage());
        assertEquals("Invalid email.",signUpErrorResponseBody.getErrors().getEmail());
    }

    @Test
    public void unsuccessfulSignUpExistingEmailTest() {
        signUpRequestBody.setEmail(email);
        signUpRequestBody.setPassword(password);

        response = new EvPrimeClient().postSignUpRequest(signUpRequestBody);

        signUpErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422, response.statusCode());
        assertEquals("User signup failed due to validation errors.",signUpErrorResponseBody.getMessage());
        assertEquals("Email exists already.",signUpErrorResponseBody.getErrors().getEmail());
    }

    @Test
    public void unsuccessfulSignUpEmptyPasswordTest() {
        signUpRequestBody.setPassword("");

        response = new EvPrimeClient().postSignUpRequest(signUpRequestBody);

        signUpErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422, response.statusCode());
        assertEquals("User signup failed due to validation errors.",signUpErrorResponseBody.getMessage());
        assertEquals("Invalid password. Must be at least 6 characters long.",signUpErrorResponseBody.getErrors().getPassword());
    }

    @Test
    public void unsuccessfulSignUpShortPasswordTest() {
        signUpRequestBody.setPassword(shortPassword);

        response = new EvPrimeClient().postSignUpRequest(signUpRequestBody);

        signUpErrorResponseBody = response.as(EvPrimeErrorsResponseModelPostPut.class);

        assertEquals(422, response.statusCode());
        assertEquals("User signup failed due to validation errors.",signUpErrorResponseBody.getMessage());
        assertEquals("Invalid password. Must be at least 6 characters long.",signUpErrorResponseBody.getErrors().getPassword());
    }
}
