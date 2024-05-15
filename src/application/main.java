package application;

import application.controller.ControllerPlayer;
import application.model.World;
import application.view.GameFrame;
import application.view.GamePanel;
import application.view.ImmaginiGioco;


import java.io.IOException;

public class main {



    public static void main(String[] args) throws IOException {

        ImmaginiGioco immaginigioco = new ImmaginiGioco();
        GamePanel gamePanel = new GamePanel(immaginigioco);
        ControllerPlayer controllerPlayer = new ControllerPlayer(gamePanel);
        gamePanel.setController(controllerPlayer);
        GameFrame frame = new GameFrame(gamePanel);
        frame.showWindow();
        GameLoop gameLoop = new GameLoop(controllerPlayer);
        gameLoop.inizioGioco();

    }
}
