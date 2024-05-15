package application.view;

import application.model.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImmaginiGioco {

    private Image personaggio;

    private final Image[] animazioneDestra = new Image[4];
    private Image backgroundImage ;
    private Image bloccoTerra;
    private Image bloccoErba;
    private Image bloccoMuro;
    private Image bloccoSpeciale;

    public ImmaginiGioco() throws IOException {

        backgroundImage = ImageIO.read(new File("src/application/resources/background/sfondo_livello1.jpg"));
        backgroundImage = backgroundImage.getScaledInstance(Settings.WINDOW_SIZE_Y,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);
        personaggio = ImageIO.read(new File("src/application/resources/personaggio/Steve.png"));
        personaggio = personaggio.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        bloccoErba = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoErba.png"));
        bloccoErba = bloccoErba.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoTerra.png"));
        bloccoTerra = bloccoTerra.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoMuro.png"));
        bloccoMuro = bloccoMuro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoSpeciale.png"));
        bloccoSpeciale = bloccoSpeciale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);

        animazioneDestra[0] = ImageIO.read(new File("src/application/resources/personaggio/muove.png"));
        animazioneDestra[1] = ImageIO.read(new File("src/application/resources/personaggio/muove.png"));
        animazioneDestra[2] = ImageIO.read(new File("src/application/resources/personaggio/muove_1.png"));
        animazioneDestra[3] = ImageIO.read(new File("src/application/resources/personaggio/muove_1.png"));
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

    public Image[] getAnimazioneDestra() {
        return animazioneDestra;
    }
}
