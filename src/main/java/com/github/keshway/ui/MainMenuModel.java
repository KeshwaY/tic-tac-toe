package com.github.keshway.ui;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.event.ConfigurationEvent;

class MainMenuModel implements Model {

    private final DataBus<ConfigurationEvent> configurationBus;

    MainMenuModel(DataBus<ConfigurationEvent> configurationBus) {
        this.configurationBus = configurationBus;
    }

    MessageBundle messageBundle(Language language) {
        LocaleBundle localeBundle = new SelectedBundle(language);
        return new LocalizedMessageBundle(localeBundle);
    }

    DataBus<ConfigurationEvent> configurationBus() {
        return this.configurationBus;
    }
}
