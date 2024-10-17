package utils;

import data.EvPrimePostUpdateEventDataFactoryPostPut;
import data.EvPrimeSignUpLogInDataFactoryPostPut;
import models.request.EvPrimePostUpdateEventRequestModelPostPut;
import models.request.EvPrimeSignUpLoginRequestModelPostPut;
import org.apache.commons.lang3.RandomStringUtils;

import static objectbuilder.EvPrimeEventsBuilder.createBodyForPostUpdateEventRequest;
import static objectbuilder.EvPrimeUsersBuilder.createBodyForSignUpLoginRequest;
import static utils.EvPrimeDateBuilder.currentTime;


public class HelperClass {

    public static EvPrimePostUpdateEventRequestModelPostPut createDefaultPostUpdateEventRequestBody() {
        return new EvPrimePostUpdateEventDataFactoryPostPut(createBodyForPostUpdateEventRequest())
                .setTitle(RandomStringUtils.randomAlphanumeric(20))
                .setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEiCWEymdP563mAt2rKoTs3wC6cF4v13uVqQ&s")
                .setDate(currentTime())
                .setLocation(RandomStringUtils.randomAlphabetic(20))
                .setDescription(RandomStringUtils.randomAlphabetic(20))
                .createRequest();
    }

    public static EvPrimeSignUpLoginRequestModelPostPut createDefaultSignUpLoginRequestBody(String email, String password) {
        return new EvPrimeSignUpLogInDataFactoryPostPut(createBodyForSignUpLoginRequest())
                .setEmail(email)
                .setPassword(password)
                .createRequest();
    }
}
