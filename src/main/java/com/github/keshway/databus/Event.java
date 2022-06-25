package com.github.keshway.databus;

public interface Event {
    DataBus<? extends Event> getDataBus();
    void setDataBus(DataBus<? extends Event> genericDataBus);
    void setPipe(Pipe<? extends Event> pipe);
    void remove();
}
