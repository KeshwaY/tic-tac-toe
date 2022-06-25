package com.github.keshway.databus;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

@Listeners(MockitoTestNGListener.class)
public class BaseEventTest {

    private static final class TestEvent extends BaseEvent { }

    @Mock private DataBus<BaseEvent> dataBus;
    private BaseEvent underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new TestEvent();
    }

    @Test
    private void shouldSetDataBus() {
        // given
        // when
        underTest.setDataBus(dataBus);
        // then
        assertThat(underTest.getDataBus()).isEqualTo(dataBus);
    }


}