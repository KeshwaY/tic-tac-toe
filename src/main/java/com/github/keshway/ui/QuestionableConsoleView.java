package com.github.keshway.ui;

// TODO: I do question my life tho
interface QuestionableConsoleView extends QuestionableView, ConsoleInputView {
    @Override
    default void renderQuestion(TextComponent textComponent) {
        System.out.println(textComponent);
    }
}
