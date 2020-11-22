package com.msh.WorkoutGameAdmin.logic;

import com.msh.WorkoutGameAdmin.gui.MainFrame;
import com.msh.WorkoutGameAdmin.websocket.WebSocketManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class AdminRunner implements CommandLineRunner {

    private JFrame mainFrame;
    private WebSocketManager webSocketManager;
    //private final String url = "ws://34.65.59.146:8080/admin";
    private final String url = "ws://localhost:8080/admin";

    @Override
    public void run(String... args) throws Exception {
        webSocketManager = new WebSocketManager();
        mainFrame = new MainFrame(webSocketManager);
        webSocketManager.setGUI(mainFrame);
        webSocketManager.establishConnection(url);
        webSocketManager.getGames();
    }
}
