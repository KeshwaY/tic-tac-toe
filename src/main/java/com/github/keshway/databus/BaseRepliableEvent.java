package com.github.keshway.databus;

public abstract class BaseRepliableEvent<R> extends BaseEvent implements Repliable<R> {
    private final ResponseListener<R> responseListener;

    public BaseRepliableEvent(ResponseListener<R> responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void reply(R response) {
        responseListener.accept(response);
    }
}
