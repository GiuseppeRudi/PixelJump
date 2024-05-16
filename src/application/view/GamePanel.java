package application.view;

import application.controller.ControllerPlayer;
import application.model.Game;
import application.model.Position;
import application.model.Settings;
import application.model.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class GamePanel extends JPanel {
    private final PlayerView playerView = new PlayerView();

    private ImmaginiGioco immaginiGioco = new ImmaginiGioco();

    public void setController(ControllerPlayer controllerPlayer) {
        this.addKeyListener(controllerPlayer);
    }

    public GamePanel(ImmaginiGioco immaginigioco) throws IOException {this.immaginiGioco = immaginigioco;}

    static boolean startAnimazione = false;
    int indiceCorrente =0;


    private Timer timer ;

    Image[] animazioneDestra = new Image[4];

    public void updateDirection(int direction) {
        //il panel aggiorna la direzione del player sulla view
        //playerView.setDirection(direction);

        System.out.println("sono entrato");
        if (!startAnimazione)
        {
            startAnimazione= true;
            animazioneDestra = immaginiGioco.getAnimazioneDestra();


            timer = new Timer(100, e -> {

                if (indiceCorrente < animazioneDestra.length-1) {
                    System.out.println("--SONO IN TIMER--");
                    System.out.println(startAnimazione);
                    System.out.println(indiceCorrente);
                    System.out.println("-------------");
                    this.repaint();
                    indiceCorrente++;
                } else {
                    ((Timer) e.getSource()).stop();
                    indiceCorrente = 0;
                    startAnimazione = false;
                }

            });

            System.out.println("timer partito");
            timer.start();
        }





    }

    boolean trovatoPersonaggio =false;
    @Override
    protected void paintComponent(Graphics g) {

        trovatoPersonaggio=false;
        super.paintComponent(g);

        g.drawImage(immaginiGioco.getBackgroundImage(),0,0,this);

        World world = Game.getInstance().getWorld();
        int coordinateGiocatore = trovaGiocatore();

        for(int i = 0; i < Settings.Filtro_Size_Riga; i++) {
            int riga = i * Settings.CELL_SIZE_RIGA; //coordinate x sulla view
            for(int j = coordinateGiocatore-5; j < coordinateGiocatore+25; j++) {
                int colonna = j * Settings.CELL_SIZE_COLONNA; //coordinate y sulla view
//                if(world.isCoin(i, j)) {
//                    g.setColor(Color.YELLOW);
//                    g.fillOval(x + Settings.CELL_SIZE/4, y + Settings.CELL_SIZE/4, Settings.CELL_SIZE/2, Settings.CELL_SIZE/2);
//                }
                if(world.isWall(i, j)) {
                    g.drawImage(immaginiGioco.getBloccoMuro(),colonna,riga,this);
                }
                else if(world.isPlayer(i, j)) {
                    System.out.println("-- SONO IN PAINT --");
                    System.out.println(startAnimazione);
                    System.out.println(indiceCorrente);
                    System.out.println("-------------");
                    if (!trovatoPersonaggio)
                    {
                        if (startAnimazione)
                        {
                            g.drawImage(animazioneDestra[indiceCorrente],colonna-10,riga,this);
                        }

                        if (!startAnimazione)
                            {g.drawImage(immaginiGioco.getPersonaggio(),colonna,riga,this);}
                        trovatoPersonaggio=true;
                    }
//                    g.setColor(Color.WHITE);
//                    g.fillRect(colonna, riga, Settings.CELL_SIZE_RIGA, Settings.CELL_SIZE_COLONNA);
                }
                else if(world.isTerra(i,j))
                {
                    g.drawImage(immaginiGioco.getBloccoTerra(),colonna,riga,this);
                }
                else if(world.isErba(i,j)){
                    g.drawImage(immaginiGioco.getBloccoErba(),colonna,riga,this);
                }
                else if(world.isSpeciale(i,j)){
                    g.drawImage(immaginiGioco.getBloccoSpeciale(), colonna,riga,this);
                }
            }
        }
    }

    private int trovaGiocatore() {

        World world = Game.getInstance().getWorld();

        for (int i = 0; i < Settings.World_Size_Riga; i++) {

            for (int j = 0; j < Settings.World_Size_Colonna; j++) {

                if (world.isPlayer(i, j)) {
                    System.out.println("coordinata giocatore");
                    System.out.println(j);
                    return (j);}
            }
        }
        return 0;
    }

    public  void update() {
        //
        playerView.update();

        //ristampa ogni volta il paintcomponent che ristampa la matrice

        this.repaint();
        //viene chiamato da Game/controllerPlayer

    }
}
