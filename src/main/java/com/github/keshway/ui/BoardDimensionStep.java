package com.github.keshway.ui;

import com.github.keshway.databus.component.BoardDimensions;
import com.github.keshway.databus.component.Height;
import com.github.keshway.databus.component.Length;

class BoardDimensionStep extends BaseConfigurationStep {
    BoardDimensionStep(ConfigurationModel model, TextComponent textComponent) {
        super(model, new DefaultConsoleView(textComponent));
    }

    @Override
    public void start() {
        boolean isValid = false;
        while (!isValid) {
            view.render();
            UserInput<String> userInput = view.askForInput();
            String[] split = userInput.body().split("x");
            if (split.length != 2) continue;
            try {
                int userLength = Integer.parseInt(split[0]);
                int userHeight = Integer.parseInt(split[1]);
                if ( userLength < 3 || userHeight < 3 || userLength > 31 || userHeight > 31) throw new NumberFormatException();
                Length length = new Length(userLength);
                Height height = new Height(userHeight);
                // TODO: swap height with length
                BoardDimensions boardDimensions = new BoardDimensions(height, length);
                model.setBoardDimensions(boardDimensions);
                isValid = true;
            } catch (NumberFormatException e) {
                view.renderError(new ErrorMessage("Invalid input!"));
            }
        }
    }

    @Override
    BaseConfigurationStep next() {
        TextComponent textComponent = model.messageBundle()
                .getQuestion(new BundleKey("WIN_CONDITION"));
        return new WinConditionStep(model, textComponent);
    }

}
