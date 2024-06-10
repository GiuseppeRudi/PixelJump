package application.view;

import application.controller.ControllerPlayer;
import application.model.Game;
import application.model.Position;
import application.model.Settings;
import application.model.World;
import application.resources.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GamePanel extends JPanel {

//    private PlayerView playerView = new PlayerView();
    private Font customFont;
    private World world ;

    private ImmaginiGioco immaginiGioco = new ImmaginiGioco();

    public void setController(ControllerPlayer controllerPlayer) {
        this.addKeyListener(controllerPlayer);
    }

    public GamePanel(ImmaginiGioco immaginigioco) throws IOException {
        this.immaginiGioco = immaginigioco;
        try {
            // Assicurati che il percorso al file del font sia corretto.
            customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("C:\\Users\\giu20\\OneDrive - Università della Calabria\\USER INTERFACE\\Progetto\\src\\application\\resources\\GraphicsManager\\font\\fontMinecraft.ttf")).deriveFont(18f); // Indica la dimensione del font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // Registra il font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 18); // Usa un font di backup nel caso il caricamento fallisca
        }
    }

    boolean startAnimazione = false;
    boolean startAnimazioneSalto= false;
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
//        playerView.setDirection(direction);

        System.out.println("sono entrato");
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
    int spostamento_immagine_salto;
    int spostamento_immagine_sinistra;
    boolean trovatoPersonaggio =false;
    @Override
    protected void paintComponent(Graphics g) {

        trovatoPersonaggio=false;
        super.paintComponent(g);

        world = Game.getInstance().getWorld();

//        System.out.println("+++++++++++++++");
//        System.out.println(world.getPlayer().getProgresso());
//        System.out.println("+++++++++++++++");

        //questo serve per spostare lo sfondo man mano che il progresso avanza e quindi lo sfondo si muove man mano
        g.drawImage(immaginiGioco.getBackgroundImage(world.getLivello()),-(world.getPlayer().getProgresso()*5),0,this);


//        System.out.println("-----PROGRESSO-----------");
//        System.out.println(world.getPlayer().getProgresso());
//        System.out.println("-----++++++++++++++-----------");
//        int coordinateGiocatore = trovaGiocatore();

        // Scegliere il font desiderato
        g.setFont(customFont); // Imposta il font per il disegno del testo

        // Impostare il colore del testo
        g.setColor(Color.WHITE); // Cambia il colore del testo in blu

        // Disegnare un testo
        g.drawString(world.getPlayer().getMoneta()+"", 850, 30);  // Disegna "Hello World!" nella posizione x=10, y=20
        g.drawImage(immaginiGioco.getBloccoMoneta(),870,8, this);

        if (world.getPlayer().getMoneta()>=1)
        {
            g.drawString("Obiettivo Raggiunto", 400, 30);  // Disegna "Hello World!" nella posizione x=10, y=20
        }

        for(int i = 0; i < Settings.Filtro_Size_Riga; i++) {
            int riga = i * Settings.CELL_SIZE_RIGA; //coordinate x sulla view
            for(int j = 0; j < Settings.Filtro_Size_Colonna; j++) {
                int colonna = j * Settings.CELL_SIZE_COLONNA; //coordinate y sulla view

                LinkedList<Position> arrayvita = world.getArrayVita();
                //System.out.println(world.getPlayer().getVita());
                for(int f=0;f<world.getPlayer().getVita();f++)
                {

                    g.drawImage(immaginiGioco.getBloccoCuore(),arrayvita.get(f).j()*Settings.CELL_SIZE_COLONNA+3,arrayvita.get(f).i()*Settings.CELL_SIZE_RIGA+8, this);
                }

                if(world.isWall(i, j + world.getPlayer().getProgresso())) {

                    g.drawImage(immaginiGioco.getBloccoMuro(world.getLivello()),colonna,riga,this);
                }
                else if(world.isPlayer(i, j + world.getPlayer().getProgresso())) {
//                    System.out.println("-- SONO IN PAINT --");
//                    System.out.println(startAnimazione);
//                    System.out.println(indiceCorrente);
//                    System.out.println("-------------");
                    if (!trovatoPersonaggio) {
                        if (startAnimazione) {

                            spostamento_immagine_destra = -30 + (5 * indiceMovimento);
                            spostamento_immagine_sinistra = +10 - (5 * indiceMovimento);


                            if (direzione == 1) {
                                g.drawImage(animazione[indiceMovimento], colonna + spostamento_immagine_destra, riga, this);
                            } else if (direzione == -1) {
                                g.drawImage(ImageUtil.flipImageHorizontally(animazione[indiceMovimento]), colonna + spostamento_immagine_sinistra, riga, this);
                            }

                        }
                        if (startAnimazioneSalto) {


                            spostamento_immagine_salto = -10 - (5 * indiceMovimento);

                            if (direzionePrecedente == 1) {
                                g.drawImage(animazioneSalto[indiceMovimento], colonna, riga + spostamento_immagine_salto, this);
                            } else if (direzionePrecedente == -1) {
                                g.drawImage(ImageUtil.flipImageHorizontally(animazioneSalto[indiceMovimento]), colonna, riga + spostamento_immagine_salto, this);
                            }


                        }
//                        System.out.println("****************+");
//                        System.out.println(startAnimazione);
//                        System.out.println(startAnimazioneSalto);
//                        System.out.println("****************+");

                        if (!startAnimazione && !startAnimazioneSalto) {
                            if (direzionePrecedente == 1) {
                                g.drawImage(immaginiGioco.getPersonaggio(), colonna, riga, this);
                            } else if (direzionePrecedente == -1) {
                                g.drawImage(ImageUtil.flipImageHorizontally(immaginiGioco.getPersonaggio()), colonna, riga, this);
                            }

                        }

                        trovatoPersonaggio = true;
                    }
                }
                else if(world.isTerra(i,j + world.getPlayer().getProgresso()))
                {
                    g.drawImage(immaginiGioco.getBloccoTerra(world.getLivello()),colonna,riga,this);
                }
                else if(world.isErba(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoErba(),colonna,riga,this);
                }
                else if(world.isSpeciale(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoSpeciale(world.getLivello()), colonna,riga,this);
                }
                else if(world.isTubo(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoTubo(world.getLivello()), colonna, riga, this);
                }
                else if(world.isBarile(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoBarile(world.getLivello()), colonna, riga, this);
                }
                else if(world.isFine(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoFine(world.getLivello()), colonna, riga, this);
                }
                else if(world.isPortale(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoPortale(world.getLivello()), colonna, riga, this);
                }
                else if(world.isMorte(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoMorte(world.getLivello()), colonna, riga, this);
                }
                else if(world.isPonte(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoPonte(world.getLivello()), colonna, riga, this);
                }
                else if(world.isTeletrasporto(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoTeletrasporto(world.getLivello()), colonna, riga, this);
                }
                else if(world.isCoin(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoMoneta(), colonna, riga, this);
                }
                else if(world.isUsato(i,j + world.getPlayer().getProgresso())){
                    g.drawImage(immaginiGioco.getBloccoUsato(), colonna, riga, this);
                }
                else if(world.isNemico(i,j+world.getPlayer().getProgresso()))
                {
                    //se sotto di me ce un blocco nemico allora sono un nemico di 2 celle
                    if(world.isNemico(i+1,j+world.getPlayer().getProgresso()))
                    {
                        g.drawImage(immaginiGioco.getBloccoNemico2(),colonna,riga,this);
                    }

                    else {
                        if (!world.isNemico(i-1,j+world.getPlayer().getProgresso()))
                        {
                            g.drawImage(immaginiGioco.getBloccoNemico1(),colonna,riga,this);
                        }

                    }

//                    if(world.getEnemy().getDirection()==-1)
//                    {
//                        g.drawImage(immaginiGioco.getBloccoNemico1(),colonna,riga,this);
//
//                    }
//                    else if(world.getEnemy().getDirection()==1)
//                    {
//                        g.drawImage(ImageUtil.flipImageHorizontally(immaginiGioco.getBloccoNemico1()),colonna,riga,this);
//                    }

                }

                //System.out.println(world.getPlayer().getMoneta());

            }
        }
    }

    public  void update() {
        this.repaint();
        //viene chiamato da Game/controllerPlayer
    }

}
