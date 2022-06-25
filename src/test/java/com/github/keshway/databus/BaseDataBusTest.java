package com.github.keshway.databus;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.*;

import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Listeners(MockitoTestNGListener.class)
public class BaseDataBusTest {

    private static final class TestBus extends BaseDataBus<Event> {
        TestBus() {
            super(new LinkedBlockingQueue<>());
        }
    }

    private final static class TestEvent implements Event {
            private DataBus<?> dataBus;
            @Override
            public DataBus<?> getDataBus() {
                return dataBus;
            }
            @Override
            public void setDataBus(DataBus<?> genericDataBus) {
                this.dataBus = genericDataBus;
            }
            @Override
            public void setPipe(Pipe<? extends Event> pipe) {
            }
            @Override
            public void remove() {
            }
    }

    @Mock EventListener<Event> listener;

    private BaseDataBus<Event> underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new TestBus();
    }

    @Test
    void shouldSubscribeListener() {
        // given
        Event event = new TestEvent();
        // when
        underTest.publish(event);
        // then
        assertThat(event.getDataBus()).isEqualTo(underTest);
    }

    @Test
    void shouldBroadcastEvent() {
        // given
        Event event = new TestEvent();
        // when
        underTest.subscribe(listener);
        underTest.publish(event);
        // then
        verify(listener, times(1)).accept(any());
        assertThat(event.getDataBus()).isEqualTo(underTest);
    }

    @Test
    void shouldUnsubscribeListener() {
        // given
        Event event = new TestEvent();
        underTest.subscribe(listener);
        // when
        underTest.unsubscribe(listener);
        underTest.publish(event);
        // then
        verify(listener, times(0)).accept(any());
        assertThat(event.getDataBus()).isEqualTo(underTest);
    }

}