package com.github.keshway.ui;

class LocalizedMessageBundle implements MessageBundle {
    private final LocaleBundle bundle;

    LocalizedMessageBundle(LocaleBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public TextComponent getQuestion(BundleKey key) {
        return ComponentMapper.bundleStringToTextComponent(
                bundle.getString(key)
        );
    }
}
