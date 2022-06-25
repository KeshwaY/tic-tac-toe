package com.github.keshway.ui;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayerType;

class GameController extends BaseController<GameModel, GameView> {
    GameController(GameModel model, GameView view) {
        super(model, view);
    }

    // TODO: this should not exists
    @Override
    public void start() {
        while (!model.getGameStatus().shouldEnd()) {
            view.render(model.getMessageBundle()
                    .getQuestion(new BundleKey("SCORE")).body(), model.getPlayerScores(), model.getPlayers());
            while (!model.getRoundStatus().ended()) {
                view.render();
                Index move = null;
                PlayerType playerType = model.getPlayer();
                while (!model.getLastMove().wasSuccessful()) {
                    TextComponent textComponent = new TextComponent(
                            model.getMessageBundle().getQuestion(
                                    new BundleKey("PLAYER_MOVE")
                            ).body().replace("$1", playerType.sign().value())
                    );
                    ConsoleInputView consoleInputView = new DefaultConsoleView(textComponent);
                    consoleInputView.render();
                    UserInput<String> input = consoleInputView.askForInput();
                    if (input.body().equals("q")) return;
                    try {
                        UserInput<Integer> integerUserInput = InputParser.pareToIntWithin(input, 0, view.getDimensions().size().value() - 1);
                        move = new Index(integerUserInput.body());
                        model.makeAMove(move);
                    } catch (NumberFormatException e) {
                        consoleInputView.renderError(new ErrorMessage("Invalid input"));
                    }
                }
                model.consumeMove();
                view.registerMove(move, playerType);
            }
            model.consumeResult();
            this.view = view.reset();
        }
        view.render(model.getMessageBundle()
                .getQuestion(new BundleKey("SCORE")).body(), model.getPlayerScores(), model.getPlayers());
    }
}
