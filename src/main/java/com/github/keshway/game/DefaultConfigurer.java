package com.github.keshway.game;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.GameConfiguration;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.event.ConfigurationEvent;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.implementation.GameBus;
import com.github.keshway.databus.response.ConfigurationResponse;

import java.util.List;
import java.util.Optional;

class DefaultConfigurer implements Configurer {

    // TODO: wrap this
    private final List<Initializable> initializables;

    DefaultConfigurer(List<Initializable> initializables) {
        this.initializables = initializables;
    }

    @Override
    public void accept(ConfigurationEvent event) {
        GameConfiguration configuration = event.configuration();
        DataBus<GameEvent> gameBus = new GameBus<>();
        GameLogger logger = new GameLogger();
        gameBus.subscribe(logger);
        boolean initializationResult = isInitializationResult(configuration, gameBus);
        ConfigurationResponse response = getConfigurationResponse(gameBus, initializationResult);
        event.reply(response);
    }

    private boolean isInitializationResult(GameConfiguration configuration, DataBus<GameEvent> gameBus) {
        return initializables.stream()
                .map(i -> i.initialize(configuration, gameBus))
                .allMatch(Result::wasSuccessful);
    }

    private ConfigurationResponse getConfigurationResponse(DataBus<GameEvent> gameBus, boolean initializationResult) {
        return new ConfigurationResponse(
                new Result(initializationResult),
                initializationResult ? Optional.of(gameBus) : Optional.empty()
        );
    }
}
