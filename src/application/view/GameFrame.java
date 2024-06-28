package application.view;

import application.model.LevelProgress;
import application.model.Settings;

import javax.swing.*;
import java.awt.*;

public class GameFrame {

    private final GamePanel panel;
    private final LevelProgress pb;

    public GameFrame(GamePanel gamePanel, LevelProgress pb) {
        this.panel = gamePanel;
        this.pb=pb;
    }

    public void showWindow() {
        JFrame frame = new JFrame();
        frame.setSize(Settings.WINDOW_SIZE_X, Settings.WINDOW_SIZE_Y);
        frame.add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        frame.setUndecorated(true);  // riesce ad eliminare la barra del titolo
        // Set the frame in the middle of the screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();  //qua serve per prendere la grandezza dello schermo del pc in pixel
        //questo servere per creare la finestra centrale
        frame.setLocation((screenDimension.width-frame.getWidth())/2, (screenDimension.height- frame.getHeight())/2);
        frame.setVisible(true);
    }
}
