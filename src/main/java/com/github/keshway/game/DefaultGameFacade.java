package com.github.keshway.game;

import com.github.keshway.databus.EventLogger;
import com.github.keshway.databus.event.ConfigurationEvent;
import com.github.keshway.databus.implementation.ConfigurationBus;
import com.github.keshway.game.match.DefaultMatchFacade;
import com.github.keshway.game.referee.DefaultRefereeFacade;
import com.github.keshway.game.round.DefaultRoundFacade;
import com.github.keshway.game.validator.DefaultValidatorFacade;

import java.util.List;

public class DefaultGameFacade implements GameFacade {

    private final Configurer configurer;
    private final EventLogger<ConfigurationEvent> configurationLogger;

    public DefaultGameFacade() {
        this.configurer = new DefaultConfigurer(
                List.of(
                        new DefaultMatchFacade(),
                        new DefaultValidatorFacade(),
                        new DefaultRoundFacade(),
                        new DefaultRefereeFacade()
                )
        );
        this.configurationLogger = new ConfigurationLogger();
    }

    @Override
    public void initialize(ConfigurationBus configurationBus) {
        configurationBus.subscribe(configurationLogger);
        configurationBus.subscribe(configurer);
    }
}
