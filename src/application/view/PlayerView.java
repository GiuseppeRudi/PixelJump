package application.view;

import application.model.Settings;

import javax.swing.*;
import java.awt.*;

public class PlayerView {

    Image[] animazioneDestra=new Image[4];
    Image[] animazioneSinistra=new Image[4];

    public Image[] getAnimazione(int direction) {
        return animazioneDestra;
    }
    private Timer timer;
    boolean startAnimation=false;
    public boolean isStartAnimation() {
        return startAnimation;
    }
    private int indiceCorrente=0;
    public int getIndiceCorrente() {
        return indiceCorrente;
    }
    public void setDirection(int direction, ImmaginiGioco immaginiGioco, GamePanel panel) {

    }

    public void update()
    {

    }

}
