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
    private Image bloccoPonte;
    private Image bloccoTeletrasporto;
    private Image bloccoCuore;
    private Image bloccoMorte;
    private Image bloccoMoneta;
    private Image bloccoPortale;

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
        bloccoCuore = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoCuore.png"));
        bloccoCuore = bloccoCuore.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPonte = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoCuore.png"));
        bloccoPonte = bloccoPonte.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTeletrasporto = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoCuore.png"));
        bloccoTeletrasporto = bloccoTeletrasporto.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMorte = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoCuore.png"));
        bloccoMorte = bloccoMorte.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMoneta = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoCuore.png"));
        bloccoMoneta = bloccoMoneta.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoPortale = ImageIO.read(new File("src/application/resources/background/Blocks/bloccoCuore.png"));
        bloccoPortale = bloccoPortale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
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
    public Image getBloccoPonte() {return bloccoPonte;}
    public Image getBloccoTeletrasporto() {return bloccoTeletrasporto;}
    public Image getBloccoMorte() {return bloccoMorte;}
    public Image getBloccoMoneta() {return bloccoMoneta;}
    public Image getBloccoPortale() {return bloccoPortale;}
    public Image getBloccoCuore() {return bloccoCuore;}

//    public Image getPersonaggio(int direction) {
//        if(direction==Settings.MOVE_RIGHT)
//            return personaggioDes;
//        return personaggioSin;
//    }
//
//    public Image getBackgroundImage(int liv) {
//        if(liv==1) return backgroundImage;
//        else if(liv==2) return backgroundImage2;
//        return backgroundImage3;
//    }
//
//    public Image getMoneta() {
//        return moneta;
//    }
//
//    public Image getBloccoTerra(int liv) {
//        if(liv==1) return bloccoTerra;
//        else if(liv==2) return bloccoTerra2;
//        return bloccoTerra3;
//    }
//
//    public Image getBloccoErba() {
//        return bloccoErba;
//    }
//
//    public Image getBloccoMuro(int liv) {
//        if(liv==1) return bloccoMuro;
//        else if(liv==2) return bloccoMuro2;
//        return bloccoMuro3;
//    }
//
//    public Image getBloccoSpeciale(int liv) {
//        if(liv==1) return bloccoSpeciale;
//        return bloccoSpeciale2;
//    }
//    public Image getTubo(int liv) {
//        if(liv==1) return tubo;
//        else if(liv==2) return tubo2;
//        return tubo3;
//    }
//
//    public Image getBarile(int liv) {
//        if(liv==1) return barile;
//        else if(liv==2) return oro;
//        return end_frame;
//    }
//
//    public Image getFine(int liv) {
//        if(liv==1 || liv==2) return fine;
//        return fine3;
//    }
//
//    public Image getPortale(int liv) {
//        if(liv==1) return portale;
//        else if(liv==2) return portale_end;
//        return ender_egg;
//    }
//
//    public Image getAcqua(int liv) {
//        if(liv==1) return acqua;
//        return lava;
//    }
//    public Image getPonte(int liv) {
//        if(liv==2) return ponte2;
//        return ponte3;
//    }
//    public Image getTeletrasporto(int liv) {
//        if(liv==2) return teletrasporto;
//        return ender_dragon;
//    }
}
