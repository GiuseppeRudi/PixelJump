package application.controller;

import application.model.Game;
import application.model.Settings;
import application.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControllerPlayer  extends KeyAdapter {

    private final GamePanel gamePanel;

    public ControllerPlayer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
//        {
//            Game.getInstance().setDirection(Settings.NOT_MOVING);
//
//           // gamePanel.updateDirection(Settings.NOT_MOVING);
//        }
//    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("ciao");
        int direction = switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> Settings.MOVE_LEFT;
            case KeyEvent.VK_RIGHT -> Settings.MOVE_RIGHT;
            case KeyEvent.VK_SPACE -> Settings.JUMP;

            case KeyEvent.VK_ESCAPE -> Settings.EXIT;
            default -> Settings.NOT_MOVING;
        };

//        System.out.println(direction);

        if(direction == Settings.EXIT)
            System.exit(0);
            // PER ADESSO CHIUDO TUTTO

        if(direction != Settings.NOT_MOVING ) {
            // CON QUESTO AGGIORNO IL MDOEL e gli dico a game / world / player di cambiare la direction e tenerla aggiornata
            Game.getInstance().setDirection(direction);

            // CON QUESTO AGGIORNO LA VIEW

            gamePanel.updateDirection(direction);

        }

        //ANIMAZIONE NEL CASO DI SALTO AL MOMENTO NON SERVE
//        if(direction == Settings.ANIMATION) {
//            gamePanel.startAnimation();
//        }
    }

    //viene chiamato dal thread  dellla classe gameloop
    public void update() {

        // questo serve all'inizio per creare l'istanza al primo avvio
        // dopo serve per aggioranre la model
        Game.getInstance().update();

        //questo serve per aggiornare la view
        gamePanel.update();


    }
}
