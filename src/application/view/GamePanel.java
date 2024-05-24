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
    static boolean startAnimazioneSalto= false;
    int indiceMovimento =0;
    int indiceSalto=0;

    int direzione = 0;
    int direzionePrecedente =1;

    private Timer timer ;
    private Timer timerSalto;

    Image[] animazione = new Image[4];
    Image[] animazioneSalto = new Image[4];

    public void updateDirection(int direction) {
        //il panel aggiorna la direzione del player sulla view
        //playerView.setDirection(direction);

        //System.out.println("sono entrato");
        if (!startAnimazione && (direction==Settings.MOVE_RIGHT || direction==Settings.MOVE_LEFT) && !startAnimazioneSalto)
        {
            direzione = direction;
            startAnimazione= true;
            animazione = immaginiGioco.getAnimazioneMovimento();



            timer = new Timer(50, e -> {

                if (indiceMovimento < animazione.length-1) {
//                    System.out.println("--SONO IN TIMER--");
//                    System.out.println(startAnimazione);
//                    System.out.println(indiceCorrente);
//                    System.out.println("-------------");
                    repaint();
                    indiceMovimento++;
                } else {
                    ((Timer) e.getSource()).stop();
                    indiceMovimento = 0;
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

        if (!startAnimazioneSalto && direction==Settings.JUMP && !startAnimazione)
        {
            direzione = direction;
            startAnimazioneSalto= true;
            animazioneSalto = immaginiGioco.getAnimazioneSalto();



            timerSalto = new Timer(50, e -> {

                if (indiceSalto < animazioneSalto.length-1) {
//                    System.out.println("--SONO IN TIMER--");
//                    System.out.println(startAnimazione);
//                    System.out.println(indiceCorrente);
//                    System.out.println("-------------");
                    repaint();
                    indiceSalto++;
                } else {
                    ((Timer) e.getSource()).stop();
                    indiceSalto = 0;
                    startAnimazioneSalto = false;
                    if(direzione!=2)
                    {
                        direzionePrecedente=direzione;
                    }

                    direzione=0;
                }

            });

            //System.out.println("timer partito");


            timerSalto.start();
        }

    }

    int spostamento_immagine_destra;
    int spostamento_immagine_sinistra;
    boolean trovatoPersonaggio =false;
    @Override
    protected void paintComponent(Graphics g) {

        trovatoPersonaggio=false;
        super.paintComponent(g);

        World world = Game.getInstance().getWorld();
        //questo serve per spostare lo sfondo man mano che il progresso avanza e quindi lo sfondo si muove man mano
        g.drawImage(immaginiGioco.getBackgroundImage(),-(world.getPlayer().getProgresso()*5),0,this);


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

                            spostamento_immagine_destra=-30+(5* indiceMovimento);
                            spostamento_immagine_sinistra=+30-(5* indiceMovimento);

                            if(direzione==1)
                            {
                                g.drawImage(animazione[indiceMovimento],colonna+spostamento_immagine_destra,riga,this);
                            }
                            else if(direzione==-1)
                            {
                                g.drawImage(ImageUtil.flipImageHorizontally(animazione[indiceMovimento]),colonna+spostamento_immagine_sinistra,riga,this);
                            }

                        }
                        if(startAnimazioneSalto){


//                        spostamento_immagine_destra=-30+(5*indiceCorrente);
//                        spostamento_immagine_sinistra=+30-(5*indiceCorrente);

                        if(direzionePrecedente==1)
                        {
                            g.drawImage(animazioneSalto[indiceMovimento],colonna,riga,this);
                        }
                        else if(direzionePrecedente==-1)
                        {
                            g.drawImage(ImageUtil.flipImageHorizontally(animazioneSalto[indiceMovimento]),colonna,riga,this);
                        }


                    }
                        System.out.println("****************+");
                        System.out.println(startAnimazione);
                        System.out.println(startAnimazioneSalto);
                        System.out.println("****************+");

                        if (!startAnimazione && !startAnimazioneSalto)
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
                else if(world.isTubo(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoTubo(), colonna, riga, this);
                }
                else if(world.isBarile(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoBarile(), colonna, riga, this);
                }
                else if(world.isFine(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoFine(), colonna, riga, this);
                }
                else if(world.isCastello(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoCastello(), colonna, riga, this);
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
