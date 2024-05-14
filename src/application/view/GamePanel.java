package application.view;

import application.controller.ControllerPlayer;
import application.model.Game;
import application.model.Settings;
import application.model.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private final PlayerView playerView = new PlayerView();

    private Image backgroundImage;
    private Image personaggio;
    private Image bloccoTerra;
    private Image bloccoMuro;


    public void setController(ControllerPlayer controllerPlayer) {
        this.addKeyListener(controllerPlayer);
    }

    public GamePanel() throws IOException {
        backgroundImage = ImageIO.read(new File("src/application/resources/background/8.png"));
        backgroundImage=backgroundImage.getScaledInstance(Settings.WINDOW_SIZE_Y,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);
        personaggio = ImageIO.read(new File("src/application/resources/personaggio/prova.png"));
        personaggio=personaggio.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        bloccoTerra = ImageIO.read(new File("src/application/resources/background/bloccoTerra.jpg"));
        bloccoTerra=bloccoTerra.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro = ImageIO.read(new File("src/application/resources/background/Muro.png"));
        bloccoMuro=bloccoMuro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);

    }


    public void updateDirection(int direction) {
        //il panel aggiorna la direzione del player sulla view
        playerView.setDirection(direction);
    }


    boolean trovatoPersonaggio =false;
    @Override
    protected void paintComponent(Graphics g) {

        trovatoPersonaggio=false;
        super.paintComponent(g);

        g.drawImage(backgroundImage,0,0,this);

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
                    g.drawImage(bloccoMuro,colonna,riga,this);

                }
                else if(world.isPlayer(i, j)) {

                    if (!trovatoPersonaggio)
                    {
                        g.drawImage(personaggio,colonna,riga,this);
                        trovatoPersonaggio=true;
                    }


//                    g.setColor(Color.WHITE);
//                    g.fillRect(colonna, riga, Settings.CELL_SIZE_RIGA, Settings.CELL_SIZE_COLONNA);

                }
                else if(world.isTerra(i,j))
                {
                    g.drawImage(bloccoTerra,colonna,riga,this);
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
