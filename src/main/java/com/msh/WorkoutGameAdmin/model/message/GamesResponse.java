package com.msh.WorkoutGameAdmin.model.message;

import com.msh.WorkoutGameAdmin.model.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GamesResponse extends SimpleResponse {

    List<Game> games;

    public GamesResponse() {

    }

    public GamesResponse(String from, String text, String response, List<Game> games) {
        super(from, text, response);
        this.games = games;
    }
}
