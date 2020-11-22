package com.msh.WorkoutGameAdmin.model.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {
    String from;
    String text;
    String response;

    public SimpleResponse() {

    }

    public SimpleResponse(String from, String text, String response) {
        this.from = from;
        this.text = text;
        this.response = response;
    }

    @Override
    public String toString() {
        return from + ": " + text + ", " + response;
    }
}
