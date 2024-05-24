package application.controller;

import application.model.Game;
import application.model.Settings;
import application.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class ControllerPlayer  extends KeyAdapter {

    private final GamePanel gamePanel;
    private int direction;

    public ControllerPlayer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    private static Set<Integer> pressed= new HashSet<>();

    public static Set<Integer> getPressed() {
        return pressed;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_LEFT) pressed.remove(Settings.MOVE_LEFT);
        else if (e.getKeyCode()==KeyEvent.VK_RIGHT) pressed.remove(Settings.MOVE_RIGHT);
        else if (e.getKeyCode()==KeyEvent.VK_SPACE) pressed.remove(Settings.JUMP);
//            Game.getInstance().setDirection(Settings.NOT_MOVING);
//            gamePanel.updateDirection(Settings.NOT_MOVING);
        }


    @Override
    public void keyPressed(KeyEvent e) {
        //con l'hash map ci serve per memorizzare i tasti contemporaneamente premuti quando un tasto viene rilasciato
        //nel key released allora viene rimosso dalla hashmap
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                direction = Settings.MOVE_LEFT;
                pressed.add(Settings.MOVE_LEFT);
            }
            case KeyEvent.VK_RIGHT -> {
                direction = Settings.MOVE_RIGHT;
                pressed.add(Settings.MOVE_RIGHT);
            }
            case KeyEvent.VK_SPACE -> {
                direction = Settings.JUMP;
                pressed.add(Settings.JUMP);
            }
        }

        if(direction == Settings.EXIT)
            System.exit(0);
            // PER ADESSO CHIUDO TUTTO

        if(direction != Settings.NOT_MOVING ) {
                // CON QUESTO AGGIORNO IL MDOEL e gli dico a game / world / player di cambiare la direction e tenerla aggiornata
                Game.getInstance().setDirection(direction);
                // CON QUESTO AGGIORNO LA VIEW E LE ANIMAZIONI
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
