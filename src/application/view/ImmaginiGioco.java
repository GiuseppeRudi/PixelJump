package application.view;

import application.model.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImmaginiGioco {

    private Image personaggio;

    private final Image[] animazioneMovimento = new Image[4];
    private final Image[] animazioneSalto = new Image[4];
    private Image backgroundImage ;
    private Image bloccoTerra;
    private Image bloccoErba;
    private Image bloccoMuro;
    private Image bloccoSpeciale;
    private Image bloccoTubo;
    private Image bloccoBarile;
    private Image bloccoFine ;
    private Image bloccoCastello;

    public ImmaginiGioco() throws IOException {

        backgroundImage = ImageIO.read(new File("src/application/resources/background/sfondo_liv1.png"));
        backgroundImage = backgroundImage.getScaledInstance(6240,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);
        personaggio = ImageIO.read(new File("src/application/resources/personaggio/steve.png"));
        personaggio = personaggio.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        bloccoErba = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoErba.png"));
        bloccoErba = bloccoErba.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoTerra.png"));
        bloccoTerra = bloccoTerra.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoMuro.png"));
        bloccoMuro = bloccoMuro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoSpeciale.png"));
        bloccoSpeciale = bloccoSpeciale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTubo = ImageIO.read(new File("src/application/resources/background/Blocks/tubo.png"));
        bloccoTubo = bloccoTubo.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoBarile = ImageIO.read(new File("src/application/resources/background/Blocks/barile.png"));
        bloccoBarile = bloccoBarile.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoFine = ImageIO.read(new File("src/application/resources/background/Blocks/fine.png"));
        bloccoFine = bloccoFine.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        //bloccoCastello = ImageIO.read(new File("src/application/resources/background/Blocks/tuboSotto.png"));
        //bloccoCastello = bloccoCastello.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);

        animazioneMovimento[0] = ImageIO.read(new File("src/application/resources/personaggio/muove.png"));
        animazioneMovimento[1] = ImageIO.read(new File("src/application/resources/personaggio/muove.png"));
        animazioneMovimento[2] = ImageIO.read(new File("src/application/resources/personaggio/muove_1.png"));
        animazioneMovimento[3] = ImageIO.read(new File("src/application/resources/personaggio/muove_1.png"));

        animazioneSalto[0] = ImageIO.read(new File("src/application/resources/personaggio/SALTO.png"));
        animazioneSalto[1] = ImageIO.read(new File("src/application/resources/personaggio/SALTO.png"));
        animazioneSalto[2] = ImageIO.read(new File("src/application/resources/personaggio/SALTO.png"));
        animazioneSalto[3] = ImageIO.read(new File("src/application/resources/personaggio/SALTO.png"));
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

    public Image[] getAnimazioneMovimento() {
        return animazioneMovimento;
    }
    public Image getBloccoTubo() {return bloccoTubo;}
    public Image getBloccoBarile() {return bloccoBarile;}
    public Image getBloccoFine() {return bloccoFine;}
    public Image[] getAnimazioneSalto() {return animazioneSalto;}
    public Image getBloccoCastello() {return bloccoCastello;}
}
