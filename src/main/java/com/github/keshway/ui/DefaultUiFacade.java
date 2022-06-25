package com.github.keshway.ui;

import com.github.keshway.databus.implementation.ConfigurationBus;

public class DefaultUiFacade implements UiFacade {
    @Override
    public void initialize(ConfigurationBus configurationBus) {
        var model = new MainMenuModel(configurationBus);
        var view = new MainMenuView();
        var controller = new MainMenuController(model, view);
        controller.start();
    }
}
