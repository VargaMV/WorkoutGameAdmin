package com.msh.WorkoutGameAdmin.model.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SimpleMessage {
    String from;
    String text;

    public SimpleMessage() {

    }

    public SimpleMessage(String from, String text) {
        this.from = from;
        this.text = text;
    }

    @Override
    public String toString() {
        return "from='" + from + '\'' +
                ", text='" + text;
    }
}
