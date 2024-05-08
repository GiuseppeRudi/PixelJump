package src.view;

import controller.controller;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Deque;


public class panel extends JPanel {

    int rows = 30 ;
    int cols= 30;

    Icon cerchio = new CircleIcon(18);
    Icon quadrato = new SquareIcon(18);

    JFrame f = new JFrame("Snake Game");
    JPanel principale = new JPanel();
    JLabel[][] griglia = new JLabel[rows][cols];

    JPanel Sconfitta = new JPanel();
    JPanel Vittoria = new JPanel();


    JTextArea testo = new JTextArea("Hai perso premi n per ricominciare altrimenti q per uscire");

    private controller control ;
    public void setController(controller controller)
    {
        control = controller;
        f.addKeyListener(control);
        f.setFocusable(true);
        f.requestFocusInWindow();
    }

    public panel() {

        JOptionPane.showMessageDialog(null,"Usa per le frecce direzionali per giocare . Premi n per una nuova partita e q per terminare ","Istruzioni",JOptionPane.INFORMATION_MESSAGE);
    }

    public void creaSnake()
    {
        griglia[1][1].setIcon(cerchio);
    }

    public void riempiGriglia()
    {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                griglia[i][j] = new JLabel();
                //griglia[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                principale.add(griglia[i][j]);

            }
        }

    }




    public void schermataPrincipale() {
        f.setSize(400, 400);

        principale.setLayout(new GridLayout(rows,cols));
        principale.setBackground(Color.WHITE);
        f.add(principale);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    public void attiva()
    {

        f.setVisible(true);
        f.requestFocusInWindow();
    }

    public void modificaPosizioneMela(int indiceRiga,int indiceColonna)
    {
        griglia[indiceRiga][indiceColonna].setIcon(quadrato);
    }

    public void snakeSopra(int indiceRiga, int indiceColonna) {
        griglia[indiceRiga][indiceColonna].setIcon(null);
        griglia[indiceRiga-1][indiceColonna].setIcon(cerchio);
    }

    public void snakeSotto(int indiceRiga, int indiceColonna) {
        griglia[indiceRiga][indiceColonna].setIcon(null);
        griglia[indiceRiga+1][indiceColonna].setIcon(cerchio);
    }

    public void snakeSinistra(int indiceRiga, int indiceColonna) {
        griglia[indiceRiga][indiceColonna].setIcon(null);
        griglia[indiceRiga][indiceColonna-1].setIcon(cerchio);

    }

    public void snakeDestra(int indiceRiga, int indiceColonna) {
        griglia[indiceRiga][indiceColonna].setIcon(null);
        griglia[indiceRiga][indiceColonna+1].setIcon(cerchio);
    }

    public void setNullGriglia() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (!(i == 0 || j == 0 || i == rows - 1 || j == cols - 1))
                {griglia[i][j].setIcon(null);}
            }
        }
    }

    public void setBordi() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    griglia[i][j].setOpaque(true);
                    griglia[i][j].setBackground(Color.green);
                }
            }
        }
    }

    public void haiPerso() {
        controller.cambiaCheck();
        System.out.println("Ciao");
        Sconfitta.add(testo);
        Sconfitta.setBackground(Color.gray);
        f.setContentPane(Sconfitta);
        f.revalidate();
        f.setVisible(true);

    }


    public void Principale() {
        f.setContentPane(principale);
        f.setComponentZOrder(principale,0);
        f.setVisible(true);
    }

    public void stampaLista(Deque<SimpleEntry<Integer, Integer>> deque) {
        for(SimpleEntry<Integer, Integer> i : deque)
        {
            griglia[i.getKey()][i.getValue()].setIcon(cerchio);
        }
    }

    public void cancellaLista(Deque<SimpleEntry<Integer, Integer>> deque) {
        for(SimpleEntry<Integer, Integer> i : deque)
        {
            griglia[i.getKey()][i.getValue()].setIcon(null);
        }
    }

    public void haiVinto() {
        controller.cambiaCheck();
        Vittoria.setBackground(Color.gray);
        JTextArea testo = new JTextArea("Hai VINTO premi n per giocare acnora altrimenti q per uscire");
        Vittoria.add(testo);
        f.setContentPane(Vittoria);
        f.revalidate();
        f.setVisible(true);

    }
}
