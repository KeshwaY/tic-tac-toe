package com.github.keshway.ui;

class ComponentMapper {
    static TextComponent bundleStringToTextComponent(BundleString string) {
        return new TextComponent(string.body());
    }
}
