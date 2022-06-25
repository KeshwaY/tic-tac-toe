package com.github.keshway.databus;

public abstract class BaseEvent implements Event {
    private DataBus<Event> dataBus;
    private Pipe<Event> pipe;

    @Override
    public DataBus<Event> getDataBus() {
        return dataBus;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setDataBus(DataBus<? extends Event> genericDataBus) {
        this.dataBus = (DataBus<Event>) genericDataBus;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setPipe(Pipe<? extends Event> pipe) {
        this.pipe = (Pipe<Event>) pipe;
    }

    @Override
    public void remove() {
        pipe.remove(this);
    }
}
