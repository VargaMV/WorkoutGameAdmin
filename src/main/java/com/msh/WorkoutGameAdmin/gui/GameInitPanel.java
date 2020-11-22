package com.msh.WorkoutGameAdmin.gui;

import com.msh.WorkoutGameAdmin.model.GameInit;
import com.msh.WorkoutGameAdmin.websocket.WebSocketManager;

import javax.swing.*;
import java.awt.*;

public class GameInitPanel extends JPanel {

    private JButton newButton;

    private JTextField titleTextField;
    private JTextField mapSizeTextField;
    private JTextField waitingTimeTextField;

    public GameInitPanel(WebSocketManager wsm) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setVisible(true);

        newButton = new JButton("New");
        newButton.addActionListener(e ->
                wsm.init(
                        "new",
                        new GameInit(
                                titleTextField.getText(),
                                Integer.parseInt(mapSizeTextField.getText()),
                                Integer.parseInt(waitingTimeTextField.getText())
                        )
                )
        );
        newButton.setPreferredSize(new Dimension(150, 40));
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(newButton, gbc);

        titleTextField = new JTextField("Name");
        titleTextField.setPreferredSize(new Dimension(150, 30));
        titleTextField.setAlignmentX(CENTER_ALIGNMENT);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(titleTextField,gbc);

        mapSizeTextField = new JTextField("Map size");
        mapSizeTextField.setPreferredSize(new Dimension(150, 30));
        mapSizeTextField.setAlignmentX(CENTER_ALIGNMENT);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(mapSizeTextField,gbc);

        waitingTimeTextField = new JTextField("Waiting");
        waitingTimeTextField.setPreferredSize(new Dimension(150, 30));
        waitingTimeTextField.setAlignmentX(CENTER_ALIGNMENT);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(waitingTimeTextField,gbc);

    }
}
