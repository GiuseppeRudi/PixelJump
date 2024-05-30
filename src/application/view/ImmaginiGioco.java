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
    private Image backgroundImage2 ;
    private Image backgroundImage3 ;
    private Image bloccoTerra;
    private Image bloccoTerra2;
    private Image bloccoTerra3;
    private Image bloccoErba;
    private Image bloccoMuro;
    private Image bloccoMuro2;
    private Image bloccoMuro3;
    private Image bloccoSpeciale;
    private Image bloccoSpeciale2;
    private Image bloccoTubo;
    private Image bloccoTubo2;
    private Image bloccoTubo3;
    private Image bloccoBarile;
    private Image bloccoEndFrame;
    private Image bloccoFine ;
    private Image bloccoFine3;
    private Image bloccoOro;
    private Image bloccoPonte;
    private Image bloccoTeletrasporto;
    private Image bloccoCuore;
    private Image bloccoMorte;
    private Image bloccoMoneta;
    private Image bloccoPortale;
    private Image bloccoPortale2;
    private Image bloccoPortale3;
    private Image bloccoAcqua;
    private Image bloccoPonte2;
    private Image bloccoPonte3;
    private Image bloccoTeletrasporto2;
    private Image bloccoTeletrasporto3;
    private Image bloccoLava;
    private Image bloccoUsato;
    private Image bloccoNemico1;

    public ImmaginiGioco() throws IOException {
        backgroundImage = ImageIO.read(new File("src/application/resources/GraphicsManager/BackgroundImage/sfondo_liv1.png"));
        backgroundImage = backgroundImage.getScaledInstance(6240,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);
        backgroundImage2 = ImageIO.read(new File("src/application/resources/GraphicsManager/BackgroundImage/sfondo_liv2.png"));
        backgroundImage2 = backgroundImage2.getScaledInstance(6240,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);
        backgroundImage3 = ImageIO.read(new File("src/application/resources/GraphicsManager/BackgroundImage/sfondo_liv3.png"));
        backgroundImage3 = backgroundImage3.getScaledInstance(6240,Settings.WINDOW_SIZE_X,Image.SCALE_SMOOTH);

        personaggio = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/steve.png"));
        personaggio = personaggio.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        bloccoErba = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoErba.png"));
        bloccoErba = bloccoErba.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTerra.png"));
        bloccoTerra = bloccoTerra.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTerra2.png"));
        bloccoTerra2 = bloccoTerra2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTerra3.png"));
        bloccoTerra3 = bloccoTerra3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoMuro.png"));
        bloccoMuro = bloccoMuro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoMuro2.png"));
        bloccoMuro2 = bloccoMuro2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoMuro3.png"));
        bloccoMuro3 = bloccoMuro3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoEndFrame = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoEndFrame.png"));
        bloccoEndFrame = bloccoEndFrame.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoOro = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoOro.png"));
        bloccoOro = bloccoOro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoSpeciale.png"));
        bloccoSpeciale = bloccoSpeciale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoSpeciale2.png"));
        bloccoSpeciale2 = bloccoSpeciale2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTubo = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTubo.png"));
        bloccoTubo = bloccoTubo.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTubo2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTubo2.png"));
        bloccoTubo2 = bloccoTubo.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTubo3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTubo3.png"));
        bloccoTubo3 = bloccoTubo3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoBarile = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoBarile.png"));
        bloccoBarile = bloccoBarile.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoFine = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoFine.png"));
        bloccoFine = bloccoFine.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoFine3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoFine3.png"));
        bloccoFine3 = bloccoFine3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoCuore = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoCuore.png"));
        bloccoCuore = bloccoCuore.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMoneta = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoMoneta.png"));
        bloccoMoneta = bloccoMoneta.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPortale = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoPortale.png"));
        bloccoPortale = bloccoPortale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPortale2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoPortale2.png"));
        bloccoPortale2= bloccoPortale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPortale3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoPortale3.png"));
        bloccoPortale3 = bloccoPortale3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoAcqua = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoAcqua.png"));
        bloccoAcqua = bloccoAcqua.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoLava = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoLava.png"));
        bloccoLava = bloccoLava.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPonte2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoPonte2.png"));
        bloccoPonte2 = bloccoPonte2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPonte3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoPonte3.png"));
        bloccoPonte3 = bloccoPonte3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTeletrasporto2 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTeletrasporto2.png"));
        bloccoTeletrasporto2 = bloccoTeletrasporto2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTeletrasporto3 = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoTeletrasporto3.png"));
        bloccoTeletrasporto3 = bloccoTeletrasporto3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoUsato = ImageIO.read(new File("src/application/resources/GraphicsManager/Blocks/bloccoUsato.png"));
        bloccoUsato = bloccoUsato.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);

        //NEMICI
        bloccoNemico1 = ImageIO.read(new File("src/application/resources/GraphicsManager/Enemy/bloccoNemico1.png"));
        bloccoNemico1 = bloccoNemico1.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);

        animazioneMovimento[0] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/muove.png"));
        animazioneMovimento[1] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/muove.png"));
        animazioneMovimento[2] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/muove_1.png"));
        animazioneMovimento[3] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/muove_1.png"));

        animazioneSalto[0] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/SALTO.png"));
        animazioneSalto[1] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/SALTO.png"));
        animazioneSalto[2] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/SALTO.png"));
        animazioneSalto[3] = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/SALTO.png"));
    }

    public Image getPersonaggio() {
        return personaggio;
    }
    public Image getBloccoCuore() {return bloccoCuore;}
    public Image getBackgroundImage(int liv) {
        if(liv==1) return backgroundImage;
        else if(liv==2) return backgroundImage2;
        return backgroundImage3;
    }
    public Image getBloccoMoneta() {
        return bloccoMoneta;
    }
    public Image getBloccoTerra(int liv) {
        if(liv==1) return bloccoTerra;
        else if(liv==2) return bloccoTerra2;
        return bloccoTerra3;
    }
    public Image getBloccoErba() {
        return bloccoErba;
    }

    public Image getBloccoMuro(int liv) {
        if(liv==1) return bloccoMuro;
        else if(liv==2) return bloccoMuro2;
        return bloccoMuro3;
    }

    public Image getBloccoSpeciale(int liv) {
        if(liv==1) return bloccoSpeciale;
        return bloccoSpeciale2;
    }
    public Image getBloccoTubo(int liv) {
        if(liv==1) return bloccoTubo;
        else if(liv==2) return bloccoTubo2;
        return bloccoTubo3;
    }

    public Image getBloccoBarile(int liv) {
        if(liv==1) return bloccoBarile;
        else if(liv==2) return bloccoOro;
        return bloccoEndFrame;
    }

    public Image getBloccoFine(int liv) {
        if(liv==1 || liv==2) return bloccoFine;
        return bloccoFine3;
    }

    public Image getBloccoPortale(int liv) {
        if(liv==1) return bloccoPortale;
        else if(liv==2) return bloccoPortale2;
        return bloccoPortale3;
    }

    public Image getBloccoMorte(int liv) {
        if(liv==1) return bloccoAcqua;
        return bloccoLava;
    }
    public Image getBloccoPonte(int liv) {
        if(liv==2) return bloccoPonte2;
        return bloccoPonte3;
    }
    public Image getBloccoTeletrasporto(int liv) {
        if(liv==2) return bloccoTeletrasporto2;
        return bloccoTeletrasporto3;
    }
    public Image getBloccoNemico1() {return bloccoNemico1;}
    public Image[] getAnimazioneMovimento() {
        return animazioneMovimento;
    }

    public Image[] getAnimazioneSalto() {
        return animazioneSalto;
    }

    public Image getBloccoUsato() {
        return bloccoUsato;
    }
}
