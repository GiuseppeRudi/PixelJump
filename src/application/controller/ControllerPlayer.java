package application.controller;

import application.model.Game;
import application.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControllerPlayer  extends KeyAdapter {

    private final GamePanel gamePanel;

    public ControllerPlayer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void update() {
        Game.getInstance().update();
        gamePanel.update();
    }
}
