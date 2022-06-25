package com.github.keshway.ui;

import java.util.ResourceBundle;

class SelectedBundle implements LocaleBundle {
    private final ResourceBundle resourceBundle;

    SelectedBundle(Language language) {
        resourceBundle = ResourceBundle.getBundle("Messages", language.locale());
    }

    @Override
    public BundleString getString(BundleKey key) {
        String body = resourceBundle.getString(key.value());
        return new BundleString(body);
    }
}
