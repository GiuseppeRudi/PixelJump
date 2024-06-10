//package application.view;
//
//import application.model.Settings;
//
//import javax.swing.*;
//
//import java.awt.*;
//
//import static application.view.GamePanel.startAnimazione;
//import static application.view.GamePanel.startAnimazioneSalto;
//
//public class PlayerView {
//
//    private int direzione;
//
//    private int indiceMovimento =0;
//    private int indiceSalto=0;
//    private int direzionePrecedente =1;
//
//    private Timer timer ;
//    private Timer timerSalto;
//    private  GamePanel gamePanel;
//
//    Image[] animazione = new Image[4];
//    Image[] animazioneSalto = new Image[4];
//
//
//    public PlayerView(GamePanel gamePanel) {
//        this.gamePanel= gamePanel;
//    }
//
//    public void setDirection(int direction) {
//        //il panel aggiorna la direzione del player sulla view
//        //playerView.setDirection(direction);
//
//        //System.out.println("sono entrato");
//
//        if (!startAnimazione && (direction==Settings.MOVE_RIGHT || direction==Settings.MOVE_LEFT) && !startAnimazioneSalto)
//        {
//
//            direzione = direction;
//            startAnimazione= true;
//            animazione = gamePanel.getImmaginiGioco().getAnimazioneMovimento();
//
//
//
//            timer = new Timer(50, e -> {
//
//                if (indiceMovimento < animazione.length-1) {
////                    System.out.println("--SONO IN TIMER--");
////                    System.out.println(startAnimazione);
////                    System.out.println(indiceCorrente);
////                    System.out.println("-------------");
//                    gamePanel.repaint();
//                    indiceMovimento++;
//                } else {
//                    ((Timer) e.getSource()).stop();
//                    indiceMovimento = 0;
//                    startAnimazione = false;
//                    if(direzione!=2)
//                    {
//                        direzionePrecedente=direzione;
//                    }
//
//                    direzione=0;
//                }
//
//            });
//
//            //System.out.println("timer partito");
//
//
//            timer.start();
//        }
//
//        if (!startAnimazioneSalto && direction==Settings.JUMP && !startAnimazione)
//        {
//            direzione = direction;
//            startAnimazioneSalto= true;
//            animazioneSalto = gamePanel.getImmaginiGioco().getAnimazioneSalto();
//
//
//
//            timerSalto = new Timer(50, e -> {
//
//                if (indiceSalto < animazioneSalto.length-1) {
////                    System.out.println("--SONO IN TIMER--");
////                    System.out.println(startAnimazione);
////                    System.out.println(indiceCorrente);
////                    System.out.println("-------------");
//                    gamePanel.repaint();
//                    indiceSalto++;
//                } else {
//                    ((Timer) e.getSource()).stop();
//                    indiceSalto = 0;
//                    startAnimazioneSalto = false;
//                    if(direzione!=2)
//                    {
//                        direzionePrecedente=direzione;
//                    }
//
//                    direzione=0;
//                }
//
//            });
//
//            //System.out.println("timer partito");
//
//
//            timerSalto.start();
//        }
//    }
//
//    public int getDirezione() {
//        return direzione;
//    }
//
//    public void setDirezione(int direzione) {
//        this.direzione = direzione;
//    }
//
//    public int getIndiceMovimento() {
//        return indiceMovimento;
//    }
//
//    public void setIndiceMovimento(int indiceMovimento) {
//        this.indiceMovimento = indiceMovimento;
//    }
//
//    public int getIndiceSalto() {
//        return indiceSalto;
//    }
//
//    public void setIndiceSalto(int indiceSalto) {
//        this.indiceSalto = indiceSalto;
//    }
//
//    public int getDirezionePrecedente() {
//        return direzionePrecedente;
//    }
//
//    public void setDirezionePrecedente(int direzionePrecedente) {
//        this.direzionePrecedente = direzionePrecedente;
//    }
//
//    public GamePanel getGamePanel() {
//        return gamePanel;
//    }
//
//    public void setGamePanel(GamePanel gamePanel) {
//        this.gamePanel = gamePanel;
//    }
//}
