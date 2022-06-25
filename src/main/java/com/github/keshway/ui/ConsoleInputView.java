package com.github.keshway.ui;

import java.util.Scanner;

interface ConsoleInputView extends ConsoleView {
    default UserInput<String> askForInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(System.lineSeparator());
        System.out.print(">>> ");
        String answer = scanner.nextLine().strip();
        return new UserInput<>(answer);
    }
}
