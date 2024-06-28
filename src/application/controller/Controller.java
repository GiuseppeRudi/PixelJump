package application.controller;

import application.GameStatus;
import application.audio.Sound;
import application.model.Game;
import application.model.Settings;
import application.view.GamePanel;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class Controller implements KeyListener, MouseListener {

    private final GamePanel gamePanel;
    private int direction;

    public Controller(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    private static Set<Integer> pressed=new HashSet<>();

    public static Set<Integer> getPressed() {
        return pressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        GameStatus status = Game.getInstance().getGameStatus();
        if(status==GameStatus.IN_GAME) {
            if(gamePanel.getWorld().getPlayer().getProgresso()!=(gamePanel.getWorld().getViewPort().getFirst().length()-Settings.Filtro_Size_Colonna)) {
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

                    case KeyEvent.VK_ESCAPE -> direction = Settings.PAUSE;
                    default -> direction = Settings.NOT_MOVING;
                }
                if (direction == Settings.PAUSE)
                    Game.getInstance().setGameStatus(GameStatus.PAUSE);
                // PER ADESSO CHIUDO TUTTO
                else if (direction != Settings.NOT_MOVING) {
                    // CON QUESTO AGGIORNO IL MDOEL e gli dico a game / world / player di cambiare la direction e tenerla aggiornata
                    Game.getInstance().setDirection(direction);

                    // CON QUESTO AGGIORNO LA VIEW
                    gamePanel.updateDirection(direction);
                }

            }
        }
        else if(status==GameStatus.PAUSE){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE) Game.getInstance().setGameStatus(GameStatus.IN_GAME);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameStatus status = Game.getInstance().getGameStatus();
        if (status == GameStatus.IN_GAME || status ==GameStatus.PAUSE) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) pressed.remove(Settings.MOVE_LEFT);
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) pressed.remove(Settings.MOVE_RIGHT);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) pressed.remove(Settings.JUMP);
        }
    }

    //viene chiamato dal thread  della classe gameloop
    public void update(){
        // questo serve all'inizio per creare l'istanza al primo avvio
        // dopo serve per aggioranre la model
        Game.getInstance().update();
        //questo serve per aggiornare la view
        gamePanel.update();
    }
    Sound click = new Sound("click.wav");
    @Override
    public void mouseClicked(MouseEvent e) {
        click.play();
        //GameStatus status = Game.getInstance().getGameStatus();
        gamePanel.select();
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
