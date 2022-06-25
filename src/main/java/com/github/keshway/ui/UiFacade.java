package com.github.keshway.ui;

import com.github.keshway.databus.implementation.ConfigurationBus;

// TODO: can share interface with GameFacade
public interface UiFacade {
    void initialize(ConfigurationBus configurationBus);
}
