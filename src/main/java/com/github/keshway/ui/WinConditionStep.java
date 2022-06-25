package com.github.keshway.ui;

import com.github.keshway.databus.component.WinCondition;

class WinConditionStep extends BaseConfigurationStep {
    WinConditionStep(ConfigurationModel model, TextComponent textComponent) {
        super(model, new DefaultConsoleView(textComponent));
    }

    @Override
    public void start() {
        boolean isValid = false;
        WinCondition maxWinCondition = model.maxWinCondition();
        while (!isValid) {
            view.render();
            UserInput<String> userInput = view.askForInput();
            try {
                UserInput<Integer> integerUserInput = InputParser.pareToIntWithin(
                        userInput, 3, maxWinCondition.value()
                );
                model.setWinCondition(
                        new WinCondition(integerUserInput.body())
                );
                isValid = true;
            } catch (NumberFormatException e) {
                view.renderError(
                        new ErrorMessage("Invalid input")
                );
            }
        }
    }

    @Override
    BaseConfigurationStep next() {
        TextComponent textComponent = model.messageBundle()
                        .getQuestion(new BundleKey("PLAY_ORDER"));
        return new PlayOrderStep(model, textComponent);
    }
}
