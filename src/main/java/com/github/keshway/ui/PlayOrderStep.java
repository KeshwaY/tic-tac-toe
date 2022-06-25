package com.github.keshway.ui;

import com.github.keshway.databus.component.PlayOrder;
import com.github.keshway.databus.component.PlayerType;

import java.util.Locale;
import java.util.Map;

class PlayOrderStep extends BaseConfigurationStep {
    private final Map<String, PlayerType> players;

    PlayOrderStep(ConfigurationModel model, TextComponent textComponent) {
        super(model, new DefaultConsoleView(textComponent));
        this.players = Map.of(
                "X", PlayerType.PLAYER_X,
                "O", PlayerType.PLAYER_O
        );
    }


    @Override
    public void start() {
        // TODO: hardcoded for now
        boolean isValid = false;
        while (!isValid) {
            view.render();
            UserInput<String> userInput = view.askForInput();
            String body = userInput.body().toUpperCase(Locale.ROOT);
            if (!body.equals("X") && !body.equals("O")) {
                view.renderError(
                        new ErrorMessage("Invalid input!")
                );
                continue;
            }
            PlayOrder playOrder = new PlayOrder(players.get(body));
            model.setPlayOrder(playOrder);
            isValid = true;
        }
    }

    @Override
    BaseConfigurationStep next() {
        return null;
    }
}