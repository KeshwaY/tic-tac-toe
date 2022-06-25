package com.github.keshway.databus.implementation;

import com.github.keshway.databus.BaseDataBus;
import com.github.keshway.databus.event.ConfigurationEvent;

import java.util.LinkedHashSet;

final public class ConfigurationBus extends BaseDataBus<ConfigurationEvent> {
    static final ConfigurationBus INSTANCE = new ConfigurationBus();

    private ConfigurationBus() {
        super(new LinkedHashSet<>());
    }

    public static ConfigurationBus getInstance() {
        return INSTANCE;
    }
}
