package com.msh.WorkoutGameAdmin.websocket;

import com.msh.WorkoutGameAdmin.gui.MainFrame;
import com.msh.WorkoutGameAdmin.model.Game;
import com.msh.WorkoutGameAdmin.model.Player;
import com.msh.WorkoutGameAdmin.model.message.GamesResponse;
import com.msh.WorkoutGameAdmin.model.message.PlayersResponse;
import com.msh.WorkoutGameAdmin.model.message.SimpleResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger logger = LogManager.getLogger(MyStompSessionHandler.class);
    private JFrame gui;

    public MyStompSessionHandler(JFrame gui) {
        this.gui = gui;
    }

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        stompSession.subscribe("/admin", this);
        stompSession.subscribe("/admin/results", this);
        stompSession.subscribe("/admin/games", this);
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        logger.error("Exception at " + stompHeaders.getDestination(), throwable);
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        logger.error("Transport exception: ", throwable);
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        String[] destination = Objects.requireNonNull(stompHeaders.getDestination()).split("/");
        if (destination.length > 2 && destination[2].equals("results")) {
            return PlayersResponse.class;
        } else if (destination.length > 2 && destination[2].equals("games")) {
            return GamesResponse.class;
        }
        return SimpleResponse.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object payload) {
        SimpleResponse msg = (SimpleResponse) payload;
        MainFrame mainFrame = (MainFrame) gui;
        System.out.println("Resp:" + msg.getResponse());
        switch (msg.getResponse()) {
            case "GAMES":
                List<Game> games = ((GamesResponse) payload).getGames();
                games.forEach(System.out::println);
                mainFrame.updateGamePanel(games);
                break;
            case "INIT":
                mainFrame.switchToGame();
                break;
            case "START":
                System.out.println("Game started.");
                break;
            case "STOP":
                System.out.println("Game stopped.");
                break;
            case "PLAYERS":
                List<Player> players = ((PlayersResponse) payload).getPlayers();
                //mainFrame.updateGamePanel(players);
                break;
        }

    }

}

