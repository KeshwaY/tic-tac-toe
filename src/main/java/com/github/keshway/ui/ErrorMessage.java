package com.github.keshway.ui;

record ErrorMessage(String body) {
    @Override
    public String toString() {
        return body;
    }
}
