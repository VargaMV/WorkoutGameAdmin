package com.msh.WorkoutGameAdmin.model.message;

import com.msh.WorkoutGameAdmin.model.GameInit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInitMessage extends SimpleMessage {

    private GameInit gameInit;

    public GameInitMessage() {
    }

    public GameInitMessage(String from, String text, GameInit gameInit) {
        super(from, text);
        this.gameInit = gameInit;
    }
}
