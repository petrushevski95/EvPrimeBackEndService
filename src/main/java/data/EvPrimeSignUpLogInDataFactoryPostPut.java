package data;

import models.request.EvPrimeSignUpLoginRequestModelPostPut;

public class EvPrimeSignUpLogInDataFactoryPostPut {

    private final EvPrimeSignUpLoginRequestModelPostPut request;

    public EvPrimeSignUpLogInDataFactoryPostPut(EvPrimeSignUpLoginRequestModelPostPut request) {
        this.request = request;
    }

    public EvPrimeSignUpLogInDataFactoryPostPut setEmail(String value) {
        request.setEmail(value);
        return this;
    }

    public EvPrimeSignUpLogInDataFactoryPostPut setPassword(String value) {
        request.setPassword(value);
        return this;
    }

    public EvPrimeSignUpLoginRequestModelPostPut createRequest() {
        return request;
    }

}
