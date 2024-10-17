package data;

import models.request.EvPrimePostUpdateEventRequestModelPostPut;

public class EvPrimePostUpdateEventDataFactoryPostPut {

    private final EvPrimePostUpdateEventRequestModelPostPut request;

    public EvPrimePostUpdateEventDataFactoryPostPut(EvPrimePostUpdateEventRequestModelPostPut request) {
        this.request = request;
    }

    public EvPrimePostUpdateEventDataFactoryPostPut setTitle(String value) {
        request.setTitle(value);
        return this;
    }

    public EvPrimePostUpdateEventDataFactoryPostPut setImage(String value) {
        request.setImage(value);
        return this;
    }

    public EvPrimePostUpdateEventDataFactoryPostPut setDate(String value) {
        request.setDate(value);
        return this;
    }

    public EvPrimePostUpdateEventDataFactoryPostPut setLocation(String value) {
        request.setLocation(value);
        return this;
    }

    public EvPrimePostUpdateEventDataFactoryPostPut setDescription(String value) {
        request.setDescription(value);
        return this;
    }

    public EvPrimePostUpdateEventRequestModelPostPut createRequest() {
        return request;
    }

}
