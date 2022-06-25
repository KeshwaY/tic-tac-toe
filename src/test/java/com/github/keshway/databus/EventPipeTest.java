package com.github.keshway.databus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Test
public class EventPipeTest {

    private static class TestEvent implements Event{
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

    private Pipe<Event> eventPipe;

    @BeforeMethod
    public void setUp() {
        eventPipe = new EventPipe<>();
    }

    @DataProvider
    public Object[][] testEvents() {
        return new Object[][] {
                {new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent()},
                {new TestEvent(), new TestEvent()}
        };
    }

    @Test(dataProvider = "testEvents")
    void shouldAddToPipe(TestEvent... elements) {
        // given
        List<TestEvent> eventList = Arrays.stream(elements).toList();
        // when
        eventList.forEach(eventPipe::addLast);
        // then
        assertThat(eventPipe.size()).isEqualTo(eventList.size());
    }

    @Test(dataProvider = "testEvents")
    void shouldRemoveLastElementFromPipe(TestEvent... elements) {
        // given
        List<TestEvent> eventList = Arrays.stream(elements).toList();
        TestEvent shouldBeLast = eventList.get(eventList.size() - 2);
        eventList.forEach(eventPipe::addLast);
        // when
        eventPipe.pollLast();
        // then
        assertThat(eventPipe.size()).isEqualTo(eventList.size() - 1);
        assertThat(eventPipe.pollLast().orElseThrow()).isEqualTo(shouldBeLast);
    }

    @Test(dataProvider = "testEvents")
    void shouldRemoveFirstElementFromPipe(TestEvent... elements) {
        // given
        List<TestEvent> eventList = Arrays.stream(elements).toList();
        TestEvent shouldBeFirst = eventList.get(1);
        eventList.forEach(eventPipe::addLast);
        // when
        eventPipe.poll();
        // then
        assertThat(eventPipe.size()).isEqualTo(eventList.size() - 1);
        assertThat(eventPipe.poll().orElseThrow()).isEqualTo(shouldBeFirst);
    }

    @Test
    void shouldAddFirstElement() {
        // given
        eventPipe.addLast(new TestEvent());
        TestEvent newEvent = new TestEvent();
        // when
        eventPipe.addFirst(newEvent);
        // then
        assertThat(eventPipe.size()).isEqualTo(2);
        assertThat(eventPipe.poll().orElseThrow()).isEqualTo(newEvent);
    }

    @Test
    void shouldAddLastElement() {
        // given
        eventPipe.addFirst(new TestEvent());
        TestEvent newEvent = new TestEvent();
        // when
        eventPipe.addLast(newEvent);
        // then
        assertThat(eventPipe.size()).isEqualTo(2);
        assertThat(eventPipe.pollLast().orElseThrow()).isEqualTo(newEvent);
    }

    @Test
    void shouldReturnSize() {
        // given
        TestEvent event = new TestEvent();
        // when
        eventPipe.addLast(event);
        eventPipe.addFirst(event);
        // then
        assertThat(eventPipe.size()).isEqualTo(2);
    }

    @Test
    void shouldRemoveEvent() {
        // given
        TestEvent event = new TestEvent();
        eventPipe.addFirst(event);
        // when
        boolean removed = eventPipe.remove(event);
        // then
        assertThat(eventPipe.size()).isEqualTo(0);
        assertThat(removed).isTrue();
    }

    @Test
    void shouldPeekFirstElement() {
        // given
        TestEvent event = new TestEvent();
        eventPipe.addFirst(event);
        // when
        Event peeked = eventPipe.peek().orElseThrow();
        // then
        assertThat(peeked).isEqualTo(event);
    }

    @Test
    void shouldReturnEmptyOptional() {
        // given
        // when
        Optional<Event> poll = eventPipe.poll();
        Optional<Event> pollLast = eventPipe.pollLast();
        Optional<Event> peek = eventPipe.peek();
        // then
        assertThat(poll.isEmpty()).isTrue();
        assertThat(pollLast.isEmpty()).isTrue();
        assertThat(peek.isEmpty()).isTrue();
    }
}
