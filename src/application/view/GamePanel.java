package application.view;

import application.controller.ControllerPlayer;

import javax.swing.*;

public class GamePanel extends JPanel {

    public static void update() {
    }

    private final PlayerView  playerView = new PlayerView();

    public void setController(ControllerPlayer controllerPlayer) {
        this.addKeyListener(controllerPlayer);
    }
}
