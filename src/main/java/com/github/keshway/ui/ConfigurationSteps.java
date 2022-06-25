package com.github.keshway.ui;

import com.github.keshway.databus.component.PlayOrder;
import com.github.keshway.databus.component.PlayerType;

class ConfigurationSteps extends BaseController<ConfigurationModel, ConsoleView> {

    ConfigurationSteps(ConfigurationModel model, ConsoleView view) {
        super(model, view);
    }

    @Override
    public void start() {
        BaseConfigurationStep configurationStep = getFirstConfigurationStep();
        while (!model.wasConfigured().wasSuccessful()) {
            configure(configurationStep);
        }
        getPlayerNames();
        GameModel gameModel = new GameModel(
                model.gameBus(),
                model.players(),
                model.playOrder(),
                model.messageBundle()
        );
        model.gameBus().subscribe(gameModel);
        GameView gameView = new GameView(model.dimensions());
        GameController gameController = new GameController(gameModel, gameView);
        gameController.start();
    }

    private BaseConfigurationStep getFirstConfigurationStep() {
        TextComponent textComponent = model.messageBundle()
                .getQuestion(new BundleKey("BOARD_DIMENSION"));
        return new BoardDimensionStep(model, textComponent);
    }

    private void configure(BaseConfigurationStep configurationStep) {
        while (configurationStep != null) {
            configurationStep.start();
            configurationStep = configurationStep.next();
        }
        model.configureGame();
    }

    private void getPlayerNames() {
        TextComponent textComponent = model.messageBundle()
                .getQuestion(new BundleKey("SELECT_NAME_FOR_PLAYER"));
        PlayOrder playOrder = model.playOrder();
        PlayerType player = playOrder.whoStarts();
        var firstPlayerName = getUserName(textComponent, player);
        var secondPlayerName = getUserName(textComponent, player.getOpponent());
        var firstPlayer = new Player(firstPlayerName.body(), player);
        var secondPlayer = new Player(secondPlayerName.body(), player.getOpponent());
        model.setPlayers(
                new Players(firstPlayer, secondPlayer)
        );
    }

    private UserInput<String> getUserName(TextComponent textComponent, PlayerType player) {
        TextComponent firstPlayer = new TextComponent(
                textComponent.body() + " " + player.sign() + ": "
        );
        ConsoleInputView consoleInputView = new DefaultConsoleView(firstPlayer);
        consoleInputView.render();
        return consoleInputView.askForInput();
    }
}
