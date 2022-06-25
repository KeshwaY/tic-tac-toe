package com.github.keshway.ui;

class DefaultConsoleView implements ConsoleInputView {

    private final TextComponent textComponent;

    DefaultConsoleView(TextComponent textComponent) {
        this.textComponent = textComponent;
    }

    @Override
    public void render() {
        System.out.println(textComponent);
    }
}
