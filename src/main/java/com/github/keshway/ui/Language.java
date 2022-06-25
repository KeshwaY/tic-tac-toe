package com.github.keshway.ui;

import java.util.Locale;

enum Language {
    ENGLISH(new Locale("en", "US")),
    POLISH(new Locale("pl", "PL"));

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    Locale locale() {
        return locale;
    }
}
