package application.view;

import application.controller.ControllerPlayer;
import application.model.Game;
import application.model.Settings;
import application.model.World;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final PlayerView playerView = new PlayerView();


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


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        World world = Game.getInstance().getWorld();

        for(int i = 0; i < Settings.Filtro_Size_Riga; i++) {
            int riga = i * Settings.CELL_SIZE_RIGA; //coordinate x sulla view
            for(int j = 0; j < Settings.Filtro_Size_Colonna; j++) {
                int colonna = j * Settings.CELL_SIZE_COLONNA; //coordinate y sulla view
//                if(world.isCoin(i, j)) {
//                    g.setColor(Color.YELLOW);
//                    g.fillOval(x + Settings.CELL_SIZE/4, y + Settings.CELL_SIZE/4, Settings.CELL_SIZE/2, Settings.CELL_SIZE/2);
//                }


                if(world.isWall(i, j)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(colonna, riga, Settings.CELL_SIZE_RIGA, Settings.CELL_SIZE_COLONNA);

                }
                else if(world.isPlayer(i, j)) {
                    g.setColor(Color.WHITE);
                    g.fillRect(colonna, riga, Settings.CELL_SIZE_RIGA, Settings.CELL_SIZE_COLONNA);
                    //g.drawImage(playerView.getCurrentImage(), x, y, null);
                }
                else if(world.isTerra(i,j))
                {
                    g.setColor(Color.GREEN);
                    g.fillRect(colonna, riga, Settings.CELL_SIZE_RIGA, Settings.CELL_SIZE_COLONNA);
                }
            }
        }
    }

    public  void update() {
        //
        playerView.update();

        //ristampa ogni volta il paintcomponent che ristampa la matrice
        this.repaint();
        //viene chiamato da Game/controllerPlayer

    }
}
