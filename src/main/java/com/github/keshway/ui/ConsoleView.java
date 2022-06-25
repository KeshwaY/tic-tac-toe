package com.github.keshway.ui;

interface ConsoleView extends View {
    default void renderError(ErrorMessage errorMessage) {
        System.err.println(errorMessage);
    }
}
