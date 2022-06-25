package com.github.keshway.databus;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;


@Listeners(MockitoTestNGListener.class)
public class BaseRepliableEventTest {

    private final static class TestRepliableEvent extends BaseRepliableEvent<Response> {
        TestRepliableEvent(ResponseListener<Response> responseListener) {
            super(responseListener);
        }
    }
    private final static class Response{}

    @Mock private ResponseListener<Response> responseListener;

    @Test
    private void shouldSendResponse() {
        // given
        var underTest = new TestRepliableEvent(
                responseListener
        );
        Response response = new Response();
        // when
        underTest.reply(response);
        // then
        verify(responseListener).accept(response);
    }

}
