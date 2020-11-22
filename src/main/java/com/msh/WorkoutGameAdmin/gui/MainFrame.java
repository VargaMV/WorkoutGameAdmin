package com.msh.WorkoutGameAdmin.gui;

import com.msh.WorkoutGameAdmin.model.Game;
import com.msh.WorkoutGameAdmin.model.Player;
import com.msh.WorkoutGameAdmin.websocket.WebSocketManager;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

@Getter
public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final Container containerPanel;
    private JPanel connectPanel;
    private JPanel gameInitPanel;
    private JPanel gamePanel;
    private WebSocketManager wsm;

    public MainFrame(WebSocketManager wsm ) {

        super("Workout Game Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        gameInitPanel = new GameInitPanel(wsm);
        gamePanel = new GamePanel(wsm);

        containerPanel.add(gameInitPanel, "init");
        containerPanel.add(gamePanel, "game");
        add(containerPanel);
        createMenuBar();
        setVisible(true);

    }

    public void switchToInit() {
        cardLayout.show(containerPanel, "init");
    }

    public void switchToGame() {
        cardLayout.show(containerPanel, "game");
    }

    public void updateGamePanel(List<Game> games) {
        ((GamePanel)gamePanel).updateGames(games);
    }

    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Panels");
        fileMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(fileMenu);

        JMenuItem newGamePanelItem = new JMenuItem("New");
        newGamePanelItem.addActionListener(e -> switchToInit());
        fileMenu.add(newGamePanelItem);

        JMenuItem gamesPanelItem = new JMenuItem("Games");
        gamesPanelItem.addActionListener(e -> switchToGame());
        fileMenu.add(gamesPanelItem);
    }
}
