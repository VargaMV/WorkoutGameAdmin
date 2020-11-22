package com.msh.WorkoutGameAdmin.gui;

import com.msh.WorkoutGameAdmin.model.Game;
import com.msh.WorkoutGameAdmin.model.GameInit;
import com.msh.WorkoutGameAdmin.model.Player;
import com.msh.WorkoutGameAdmin.websocket.WebSocketManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class GamePanel extends JPanel {

    private WebSocketManager wsm;

    public GamePanel(WebSocketManager wsm) {
        this.wsm = wsm;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);

    }


    public void updateGames(List<Game> games) {
        removeAll();
        for (var game : games) {

            CardLayout cardLayout = new CardLayout();
            Container containerPanel = new JPanel(cardLayout);

            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(null);
            gamePanel.setPreferredSize(new Dimension(300,170));
            if (game.isRunning()) {
                gamePanel.setBackground(Color.GREEN);
            } else {
                gamePanel.setBackground(Color.YELLOW);
            }

            JLabel titleLabel = new JLabel(game.getTitle());
            titleLabel.setBounds(10, 10,250, 30);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            gamePanel.add(titleLabel);

            JButton deleteButton = new JButton();
            deleteButton.setToolTipText("DELETE");
            deleteButton.setBounds(260,10,30,30);
            deleteButton.setBackground(Color.RED);
            deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            deleteButton.setFocusPainted(false);
            deleteButton.addActionListener(e -> wsm.delete(game.getId()));
            gamePanel.add(deleteButton);

            JLabel stateLabel = new JLabel((game.isRunning() ? "RUNNING" : "SUB"));
            stateLabel.setBounds(10, 50,130, 30);
            gamePanel.add(stateLabel);

            JLabel playerLabel = new JLabel("Player num: " + game.getPlayers().size());
            playerLabel.setBounds(160, 50,130, 30);
            gamePanel.add(playerLabel);

            JLabel mapSizeLabel = new JLabel("Map size: " + game.getMapSize());
            mapSizeLabel.setBounds(10, 90,130, 30);
            gamePanel.add(mapSizeLabel);

            JLabel timeLabel = new JLabel("Waiting time: " + game.getWaitingTime());
            timeLabel.setBounds(160, 90,130, 30);
            gamePanel.add(timeLabel);

            JButton startButton = new JButton("Start");
            startButton.setBounds(10,230,100,30);
            startButton.addActionListener(e -> wsm.start(game.getId()));
            startButton.setEnabled(!game.isRunning() && game.getPlayers().size() != 0);
            gamePanel.add(startButton);

            JButton stopButton = new JButton("Stop");
            stopButton.setBounds(120,230,100,30);
            stopButton.addActionListener(e -> wsm.stop(game.getId()));
            stopButton.setEnabled(game.isRunning());
            gamePanel.add(stopButton);

            JButton switchButton = new JButton(">>>");
            switchButton.setBounds(230, 230, 60, 30);
            switchButton.addActionListener(e -> cardLayout.show(containerPanel, "players"));
            gamePanel.add(switchButton);

            //playersPanel
            JPanel playersPanel = new JPanel();
            playersPanel.setLayout(null);
            playersPanel.setPreferredSize(new Dimension(300,270));

            if (game.isRunning()) {
                playersPanel.setBackground(Color.GREEN);
            } else {
                playersPanel.setBackground(Color.YELLOW);
            }

            JLabel playersField = new JLabel();
            playersField.setBounds(10,10,280, 210);
            playersField.setFont(new Font("Arial", Font.BOLD, 13));
            playersField.setVerticalAlignment(SwingConstants.NORTH);
            StringBuilder playersBuilder = new StringBuilder();
            playersBuilder.append("<html>");
            playersBuilder.append(LocalDateTime.now()).append("<br>");
            List<Player> players = game.getPlayers();
            players.sort(
                    Comparator.comparing(Player::getFieldsOwned, Comparator.reverseOrder())
                            .thenComparing(Player::getTotalScore, Comparator.reverseOrder())
            );
            players.forEach(p -> playersBuilder.append(p.toString()).append("<br>"));
            playersBuilder.append("</html>");
            playersField.setText(playersBuilder.toString());
            playersPanel.add(playersField);

            JButton updateButton = new JButton("Update");
            updateButton.setBounds(10,230,220,30);
            updateButton.addActionListener(e -> wsm.update(game.getId()));
            updateButton.setEnabled(game.isRunning());
            playersPanel.add(updateButton);

            JButton switchBackButton = new JButton(">>>");
            switchBackButton.setBounds(230, 230, 60, 30);
            switchBackButton.addActionListener(e -> cardLayout.show(containerPanel, "game"));
            playersPanel.add(switchBackButton);

            containerPanel.add(gamePanel, "game");
            containerPanel.add(playersPanel, "players");

            add(containerPanel);
        }
        repaint();
        revalidate();
    }
}
