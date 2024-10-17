package objectbuilder;


import models.request.EvPrimePostUpdateEventRequestModelPostPut;

public class EvPrimeEventsBuilder {

    public static EvPrimePostUpdateEventRequestModelPostPut createBodyForPostUpdateEventRequest() {
        return EvPrimePostUpdateEventRequestModelPostPut.builder()
                .title(null)
                .image(null)
                .date(null)
                .location(null)
                .description(null)
                .build();
    }
}
