package com.github.keshway.ui;

class MainMenuView implements QuestionableConsoleView {
    @Override
    public void render() {
        System.out.println(new Logo());
        System.out.println(new ProgramSignature());
    }
}
