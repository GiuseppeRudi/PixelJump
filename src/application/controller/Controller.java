package application.controller;

import application.GameLoop;
import application.GameStatus;
import application.audio.Sound;
import application.model.Game;
import application.model.Settings;
import application.view.GamePanel;

import java.awt.event.*;
import java.util.*;

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
    private static int tipo=0;

    public static int getTipo() {
        return tipo;
    }

    public static void setTipo(int tipo){
        Controller.tipo =tipo;
    }
    private final int[][] comandi= {{KeyEvent.VK_LEFT, KeyEvent.VK_A}, {KeyEvent.VK_RIGHT, KeyEvent.VK_D}, {KeyEvent.VK_SPACE, KeyEvent.VK_SPACE}, {KeyEvent.VK_ESCAPE, KeyEvent.VK_ESCAPE}};

    @Override
    public void keyPressed(KeyEvent e) {
        GameStatus status = Game.getInstance().getGameStatus();
        if(status==GameStatus.IN_GAME) {
            if(gamePanel.getWorld().getPlayer().getProgresso()!=(gamePanel.getWorld().getViewPort().getFirst().length()-Settings.Filtro_Size_Colonna)) {
                if(e.getKeyCode()==comandi[0][tipo]){
                    direction = Settings.MOVE_LEFT;
                    pressed.add(Settings.MOVE_LEFT);
                }
                else if(e.getKeyCode()==comandi[1][tipo]) {
                    direction = Settings.MOVE_RIGHT;
                    pressed.add(Settings.MOVE_RIGHT);
                }
                else if(e.getKeyCode()==comandi[2][tipo]) {
                    direction = Settings.JUMP;
                    pressed.add(Settings.JUMP);
                }
                else if(e.getKeyCode()==comandi[3][tipo]) {
                    direction = Settings.PAUSE;
                }
                else direction = Settings.NOT_MOVING;

                if (direction == Settings.PAUSE)
                    Game.getInstance().setGameStatus(GameStatus.PAUSE);
                // PER ADESSO CHIUDO TUTTO
                else if (direction != Settings.NOT_MOVING) {
                    // CON QUESTO AGGIORNO IL MDOEL e gli dico a game / world / player di cambiare la direction e tenerla aggiornata
                    Game.getInstance().setDirection(direction);

                    // CON QUESTO AGGIORNO LA VIEW
//                    gamePanel.updateDirection(direction);
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
        if (status == GameStatus.IN_GAME || status == GameStatus.PAUSE || status == GameStatus.WIN) {
            if (e.getKeyCode()==comandi[0][tipo]) pressed.remove(Settings.MOVE_LEFT);
            else if (e.getKeyCode()==comandi[1][tipo]) pressed.remove(Settings.MOVE_RIGHT);
            else if (e.getKeyCode()==comandi[2][tipo]) pressed.remove(Settings.JUMP);
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
    @Override
    public void mouseClicked(MouseEvent e) {
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
