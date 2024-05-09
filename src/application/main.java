package application;

import application.controller.ControllerPlayer;
import application.model.World;
import application.view.GamePanel;

import java.io.IOException;

public class main {



    public static void main(String[] args) throws IOException {

        GamePanel gamePanel = new GamePanel();
        ControllerPlayer controllerPlayer = new ControllerPlayer(gamePanel);
        gamePanel.setController(controllerPlayer);


    }
}
