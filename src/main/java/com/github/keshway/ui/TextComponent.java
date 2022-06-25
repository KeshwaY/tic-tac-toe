package com.github.keshway.ui;

record TextComponent(String body) {
    @Override
    public String toString() {
        return body;
    }
}
