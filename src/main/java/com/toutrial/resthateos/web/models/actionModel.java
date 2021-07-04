package com.toutrial.resthateos.web.models;

import org.springframework.hateoas.RepresentationModel;

public class actionModel
        extends RepresentationModel<actionModel> {

    private boolean action;
    private String message;

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
