package application.view;

import application.controller.ControllerPlayer;
import application.model.Game;
import application.model.Settings;
import application.model.World;
import application.resources.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {
    private final PlayerView playerView = new PlayerView();

    private ImmaginiGioco immaginiGioco = new ImmaginiGioco();

    public void setController(ControllerPlayer controllerPlayer) {
        this.addKeyListener(controllerPlayer);
    }

    public GamePanel(ImmaginiGioco immaginigioco) throws IOException {this.immaginiGioco = immaginigioco;}

    static boolean startAnimazione = false;
    int indiceCorrente =0;
    int direzione = 0;
    int direzionePrecedente =1;

    private Timer timer ;

    Image[] animazione = new Image[4];

    public void updateDirection(int direction) {
        //il panel aggiorna la direzione del player sulla view
        //playerView.setDirection(direction);

        //System.out.println("sono entrato");
        if (!startAnimazione && (direction==Settings.MOVE_RIGHT || direction==Settings.MOVE_LEFT))
        {
            direzione = direction;
            startAnimazione= true;
            animazione = immaginiGioco.getAnimazioneDestra();



            timer = new Timer(50, e -> {

                if (indiceCorrente < animazione.length-1) {
//                    System.out.println("--SONO IN TIMER--");
//                    System.out.println(startAnimazione);
//                    System.out.println(indiceCorrente);
//                    System.out.println("-------------");
                    repaint();
                    indiceCorrente++;
                } else {
                    ((Timer) e.getSource()).stop();
                    indiceCorrente = 0;
                    startAnimazione = false;
                    if(direzione!=2)
                    {
                        direzionePrecedente=direzione;
                    }

                    direzione=0;
                }

            });

            //System.out.println("timer partito");


            timer.start();
        }

    }

    int spostamento_immagine_destra;
    int spostamento_immagine_sinistra;
    boolean trovatoPersonaggio =false;
    @Override
    protected void paintComponent(Graphics g) {

        trovatoPersonaggio=false;
        super.paintComponent(g);

        g.drawImage(immaginiGioco.getBackgroundImage(),0,0,this);

        World world = Game.getInstance().getWorld();
//        System.out.println("-----PROGRESSO-----------");
//        System.out.println(world.getPlayer().getProgresso());
//        System.out.println("-----++++++++++++++-----------");
//        int coordinateGiocatore = trovaGiocatore();

        for(int i = 0; i < Settings.Filtro_Size_Riga; i++) {
            int riga = i * Settings.CELL_SIZE_RIGA; //coordinate x sulla view
            for(int j = 0; j < Settings.Filtro_Size_Colonna; j++) {
                int colonna = j * Settings.CELL_SIZE_COLONNA; //coordinate y sulla view
//                if(world.isCoin(i, j)) {
//                    g.setColor(Color.YELLOW);
//                    g.fillOval(x + Settings.CELL_SIZE/4, y + Settings.CELL_SIZE/4, Settings.CELL_SIZE/2, Settings.CELL_SIZE/2);
//                }
                if(world.isWall(i, j + world.getPlayer().getProgresso())) {

                    g.drawImage(immaginiGioco.getBloccoMuro(),colonna,riga,this);
                }
                else if(world.isPlayer(i, j + world.getPlayer().getProgresso())) {
//                    System.out.println("-- SONO IN PAINT --");
//                    System.out.println(startAnimazione);
//                    System.out.println(indiceCorrente);
//                    System.out.println("-------------");
                    if (!trovatoPersonaggio)
                    {
                        if (startAnimazione)
                        {

                            spostamento_immagine_destra=-30+(5*indiceCorrente);
                            spostamento_immagine_sinistra=+30-(5*indiceCorrente);

                            if(direzione==1)
                            {
                                g.drawImage(animazione[indiceCorrente],colonna+spostamento_immagine_destra,riga,this);
                            }
                            else if(direzione==-1)
                            {
                                g.drawImage(ImageUtil.flipImageHorizontally(animazione[indiceCorrente]),colonna+spostamento_immagine_sinistra,riga,this);
                            }

                        }

                        if (!startAnimazione)
                            {
                                if (direzionePrecedente==1)
                                {
                                    g.drawImage(immaginiGioco.getPersonaggio(),colonna,riga,this);
                                }
                                else if(direzionePrecedente==-1)
                                {
                                    g.drawImage(ImageUtil.flipImageHorizontally(immaginiGioco.getPersonaggio()),colonna,riga,this);
                                }

                            }

                        trovatoPersonaggio=true;
                    }
//                    g.setColor(Color.WHITE);
//                    g.fillRect(colonna, riga, Settings.CELL_SIZE_RIGA, Settings.CELL_SIZE_COLONNA);
                }
                else if(world.isTerra(i,j + world.getPlayer().getProgresso()))
                {
                    g.drawImage(immaginiGioco.getBloccoTerra(),colonna,riga,this);
                }
                else if(world.isErba(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoErba(),colonna,riga,this);
                }
                else if(world.isSpeciale(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoSpeciale(), colonna,riga,this);
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
