package application;

import application.controller.ControllerPlayer;
import application.model.World;
import application.view.GameFrame;
import application.view.GamePanel;
import application.view.ImmaginiGioco;


import java.io.IOException;

public class main {



    public static void main(String[] args) throws IOException {

        //classe che riproduci la soundtrack di sottofondo e il loop serve per far ripartire la classe
        Sound suono_principale = new Sound ("creative4.wav");
        suono_principale.loop();

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
