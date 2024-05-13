package application.view;

import application.controller.ControllerPlayer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static void update() {
    }

    private final PlayerView  playerView = new PlayerView();

    public void setController(ControllerPlayer controllerPlayer) {
        this.addKeyListener(controllerPlayer);
    }

    public GamePanel() {
        setBackground(Color.BLACK);
    }


    public void updateDirection(int direction) {
        //il panel aggiorna la direzione del player sulla view
        playerView.setDirection(direction);

    }
}
