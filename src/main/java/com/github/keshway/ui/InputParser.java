package com.github.keshway.ui;

final class InputParser {
    static UserInput<Integer> pareToIntWithin(UserInput<String> userInput, int floor, int ceiling) throws NumberFormatException {
        int value = Integer.parseInt(userInput.body());
        if (value < floor || value > ceiling) throw new NumberFormatException();
        return new UserInput<>(value);
    }
}
