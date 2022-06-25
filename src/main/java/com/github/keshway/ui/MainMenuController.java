package com.github.keshway.ui;

import java.util.Map;

class MainMenuController extends BaseController<MainMenuModel, MainMenuView> {

    private final Map<Integer, Language> supportedLanguages;

    MainMenuController(MainMenuModel model, MainMenuView view) {
        super(model, view);
        supportedLanguages = Map.of(
                1, Language.ENGLISH,
                2, Language.POLISH
        );
    }

    @Override
    public void start() {
        view.render();
        // TODO: can be dynamic
        TextComponent textComponent = new TextComponent("""
                Select language:
                1. English
                2. Polish
                """);
        view.renderQuestion(textComponent);
        UserInput<Integer> userChoice = null;
        while (userChoice == null) {
            try {
                UserInput<String> userInput = view.askForInput();
                userChoice = InputParser.pareToIntWithin(userInput, 1, 2);
            } catch (NumberFormatException e) {
                view.renderError(
                        new ErrorMessage("Invalid input")
                );
            }
        }
        Language language = supportedLanguages.get(userChoice.body());
        MessageBundle bundle = model.messageBundle(language);
        ConfigurationModel nextModel = new DefaultConfigurationModel(model.configurationBus(), bundle);
        ConsoleView nextView = new DefaultConsoleView(bundle.getQuestion(new BundleKey("CONFIGURATION_STEPS")));
        Controller controller = new ConfigurationSteps(nextModel, nextView);
        controller.start();
    }
}
