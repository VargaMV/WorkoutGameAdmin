package com.msh.WorkoutGameAdmin.websocket;

import com.msh.WorkoutGameAdmin.model.GameInit;
import com.msh.WorkoutGameAdmin.model.message.GameInitMessage;
import com.msh.WorkoutGameAdmin.model.message.SimpleMessage;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.swing.*;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

public class WebSocketManager {

    private WebSocketClient client;
    private WebSocketStompClient stompClient;
    private StompSession session;
    private JFrame gui;

    private boolean connected = false;

    public WebSocketManager() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxTextMessageBufferSize(20 * 1024 * 1024);
        client = new StandardWebSocketClient(container);
        stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    public void establishConnection(String URL) {
        StompSessionHandler sessionHandler = new MyStompSessionHandler(gui);
        if (!connected) {
            try {
                session = stompClient.connect(URL, sessionHandler).get();
                connected = true;
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void getGames() {
        session.send("/app/admin/games", new SimpleMessage("admin", "Get active games."));
    }

    public boolean isSessionConnected() {
        return session.isConnected();
    }

    public void init(String type, GameInit gameInit) {
        session.send("/app/admin/init", new GameInitMessage("Admin", type, gameInit));
    }

    public void start(String gameId) {
        session.send("/app/admin/start", new SimpleMessage("Admin", gameId) {
        });
    }

    public void stop(String gameId) {
        session.send("/app/admin/stop", new SimpleMessage("Admin", gameId) {
        });
    }

    public void update(String gameId) {
        session.send("/app/admin/update", new SimpleMessage("Admin", gameId) {
        });
    }

    public void delete(String gameId) {
        session.send("/app/admin/delete", new SimpleMessage("Admin", gameId) {
        });
    }

    public void setGUI(JFrame gui) {
        this.gui = gui;
    }
}
