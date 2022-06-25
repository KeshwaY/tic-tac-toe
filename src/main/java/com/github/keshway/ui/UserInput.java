package com.github.keshway.ui;

record UserInput<T>(T body) {
    @Override
    public String toString() {
        return body.toString();
    }
}
