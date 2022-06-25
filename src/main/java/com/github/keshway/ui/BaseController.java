package com.github.keshway.ui;

abstract class BaseController<M extends Model, V extends View> implements Controller {
    protected final M model;
    protected V view;

    BaseController(M model, V view) {
        this.model = model;
        this.view = view;
    }
}
