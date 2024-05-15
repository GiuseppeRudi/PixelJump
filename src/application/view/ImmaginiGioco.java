package application.view;

import application.model.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImmaginiGioco {

    private Image personaggio;
    private Image backgroundImage ;
    private Image bloccoTerra;
    private Image bloccoErba;
    private Image bloccoMuro;
    private Image bloccoSpeciale;

    public ImmaginiGioco() throws IOException {

        backgroundImage = ImageIO.read(new File("src/application/resources/background/45908.jpg"));
        backgroundImage = backgroundImage.getScaledInstance(Settings.WINDOW_SIZE_Y,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);
        personaggio = ImageIO.read(new File("src/application/resources/personaggio/Steve.png"));
        personaggio = personaggio.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        bloccoErba = ImageIO.read(new File("src/application/resources/background/bloccoErba.png"));
        bloccoErba = bloccoErba.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra = ImageIO.read(new File("src/application/resources/background/bloccoTerra.png"));
        bloccoTerra = bloccoTerra.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro = ImageIO.read(new File("src/application/resources/background/Muro.png"));
        bloccoMuro = bloccoMuro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale = ImageIO.read(new File("src/application/resources/background/bloccoSpeciale.png"));
        bloccoSpeciale = bloccoSpeciale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);


    }

    public Image getPersonaggio() {
        return personaggio;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public Image getBloccoTerra() {
        return bloccoTerra;
    }

    public Image getBloccoErba() {
        return bloccoErba;
    }

    public Image getBloccoMuro() {
        return bloccoMuro;
    }

    public Image getBloccoSpeciale() {
        return bloccoSpeciale;
    }
}
