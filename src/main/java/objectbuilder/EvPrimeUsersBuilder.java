package objectbuilder;

import models.request.EvPrimeSignUpLoginRequestModelPostPut;

public class EvPrimeUsersBuilder {

    public static EvPrimeSignUpLoginRequestModelPostPut createBodyForSignUpLoginRequest() {
        return EvPrimeSignUpLoginRequestModelPostPut.builder()
                .email(null)
                .password(null)
                .build();
    }
}
