package src.controller;

import model.file;
import view.panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.AbstractMap.SimpleEntry;

public class controller extends KeyAdapter  {

    private file objModel;
    private panel objView;

    static int check = 0;


    public controller(file model, panel view) {
        objModel = model;
        objView = view;

    }

    public static void cambiaCheck() {
        check = 1;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        SimpleEntry<Integer, Integer> testa;
        testa = objModel.cercoTestaSnake();

        if (check == 0) {
            switch (key) {
                case KeyEvent.VK_N:
                    System.out.println("RESTART");
                    objModel.start();
                    break;
                case KeyEvent.VK_UP:
                    objModel.muovoSnakeSopra(testa); //chiama anche funziona sulla view
                    break;
                case KeyEvent.VK_DOWN:
                    objModel.muovoSnakeSotto(testa);
                    break;
                case KeyEvent.VK_LEFT:
                    objModel.muovoSnakeSinistra(testa);
                    break;
                case KeyEvent.VK_RIGHT:
                    objModel.muovoSnakeDestra(testa);
                    break;
                case KeyEvent.VK_Q:
                    System.exit(0);
                    break;

            }
        } else if (check == 1) {
            switch (key) {
                case KeyEvent.VK_N:
                    check=0;
                    System.out.println("RESTART");
                    objModel.start();
                    objView.Principale();
                    break;
                case KeyEvent.VK_Q:
                    System.exit(0);
                    break;
            }

        }


    }

}


