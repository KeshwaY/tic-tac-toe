package com.github.keshway.ui;

abstract class BaseConfigurationStep extends BaseController<ConfigurationModel, DefaultConsoleView> {
    BaseConfigurationStep(ConfigurationModel model, DefaultConsoleView view) {
        super(model, view);
    }

    abstract BaseConfigurationStep next();
}
