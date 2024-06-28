package application.view;

import application.model.Settings;
import application.resources.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImmaginiGioco {
    private Image personaggioDes;
    private Image personaggioSin;
    private Image backgroundImage;
    private Image backgroundImage2;
    private Image backgroundImage3;
    private Image bloccoTerra;
    private Image bloccoErba;
    private Image bloccoMuro;
    private Image bloccoSpeciale;
    private Image tubo;
    private Image barile;
    private Image fine;
    private Image fine3;
    private Image portale;
    private Image portale_end;
    private Image acqua;
    private Image ponte2;
    private Image ponte3;
    private Image teletrasporto;
    private Image bloccoTerra2;
    private Image bloccoTerra3;
    private Image bloccoMuro2;
    private Image bloccoMuro3;
    private Image bloccoSpeciale2;
    private Image tubo2;
    private Image tubo3;
    private Image oro;
    private Image lava;
    private Image ender_dragon;
    private Image end_frame;
    private Image ender_egg;
    private Image moneta;
    private Image moneta2;
    private Image moneta3;
    private Image start_screen;
    private Image skin_screen;
    private Image usato;
    private Image vita;
    private Image tubo3_sotto;
    private Image tubo1_sotto;
    private Image tubo2_sotto;
    private static Image riconoscimenti;
    private static Image lingue;
    private Image titolo;
    private static Image pulsante;
    private Image skin_button;
    private static Image steve;
    private static Image alex;
    private static Image steve_completo;
    private static Image alex_completo;
    private Image pausa;
    private Image minizombieDX;
    private Image creeperDX;
    private Image minizombieSX;
    private Image creeperSX;
    private Image velocita;
    private Image lentezza;
    private Image scudoDX;
    private Image scudoSX;
    private Image scudo;
    public ImmaginiGioco() throws IOException {
        backgroundImage = ImageIO.read(new File("src/application/resources/GraphicsManager/sfondo_liv1.png"));
        backgroundImage = backgroundImage.getScaledInstance(6240,Settings.WINDOW_SIZE_Y,Image.SCALE_SMOOTH);
        backgroundImage2 = ImageIO.read(new File("src/application/resources/GraphicsManager/sfondo_liv2.png"));
        backgroundImage2 = backgroundImage2.getScaledInstance(1830,Settings.WINDOW_SIZE_Y,Image.SCALE_SMOOTH);
        backgroundImage3 = ImageIO.read(new File("src/application/resources/GraphicsManager/sfondo_liv3.jpg"));
        backgroundImage3 = backgroundImage3.getScaledInstance(3120,Settings.WINDOW_SIZE_Y,Image.SCALE_SMOOTH);
        personaggioDes= ImageIO.read(new File("src/application/resources/GraphicsManager/Player/steve.png"));
        personaggioDes = personaggioDes.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        personaggioSin= ImageUtil.flipImageHorizontally(personaggioDes);
        bloccoErba = ImageIO.read(new File("src/application/resources/blocks/bloccoErba.png"));
        bloccoErba = bloccoErba.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra = ImageIO.read(new File("src/application/resources/blocks/bloccoTerra.png"));
        bloccoTerra = bloccoTerra.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro = ImageIO.read(new File("src/application/resources/blocks/bloccoMuro.png"));
        bloccoMuro = bloccoMuro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale = ImageIO.read(new File("src/application/resources/blocks/bloccoSpeciale.png"));
        bloccoSpeciale = bloccoSpeciale.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        tubo = ImageIO.read(new File("src/application/resources/blocks/tubo.png"));
        tubo = tubo.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        tubo3 = ImageIO.read(new File("src/application/resources/blocks/tubo3.png"));
        tubo3 = tubo3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        tubo3_sotto = ImageIO.read(new File("src/application/resources/blocks/tubo3_sotto.png"));
        tubo3_sotto = tubo3_sotto.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        tubo2_sotto = ImageIO.read(new File("src/application/resources/blocks/tubo2_sotto.png"));
        tubo2_sotto = tubo2_sotto.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        tubo1_sotto = ImageIO.read(new File("src/application/resources/blocks/tubo1_sotto.png"));
        tubo1_sotto = tubo1_sotto.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        barile = ImageIO.read(new File("src/application/resources/blocks/barile.png"));
        barile = barile.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        fine = ImageIO.read(new File("src/application/resources/blocks/fine.png"));
        fine = fine.getScaledInstance(Settings.CELL_SIZE_RIGA*2,Settings.CELL_SIZE_COLONNA*9,Image.SCALE_SMOOTH);
        fine3 = ImageIO.read(new File("src/application/resources/blocks/fine3.gif"));
        fine3 = fine3.getScaledInstance(Settings.CELL_SIZE_RIGA*3,Settings.CELL_SIZE_COLONNA*3,Image.SCALE_SMOOTH);
        portale = ImageIO.read(new File("src/application/resources/blocks/portale.png"));
        portale = portale.getScaledInstance(Settings.CELL_SIZE_RIGA*4,Settings.CELL_SIZE_COLONNA*5,Image.SCALE_SMOOTH);
        portale_end = ImageIO.read(new File("src/application/resources/blocks/portale_end.png"));
        portale_end = portale_end.getScaledInstance(Settings.CELL_SIZE_RIGA*5,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        ponte2 = ImageIO.read(new File("src/application/resources/blocks/ponte2.png"));
        ponte2 = ponte2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        ponte3 = ImageIO.read(new File("src/application/resources/blocks/ponte3.png"));
        ponte3 = ponte3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA/2,Image.SCALE_SMOOTH);
        acqua = ImageIO.read(new File("src/application/resources/blocks/acqua.jpeg"));
        acqua = acqua.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        teletrasporto = ImageIO.read(new File("src/application/resources/blocks/teletrasporto.gif"));
        teletrasporto = teletrasporto.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        ender_dragon = ImageIO.read(new File("src/application/resources/blocks/ender_dragon.png"));
        ender_dragon = ender_dragon.getScaledInstance(Settings.CELL_SIZE_RIGA*8,Settings.CELL_SIZE_COLONNA*4,Image.SCALE_SMOOTH);
        bloccoTerra2 = ImageIO.read(new File("src/application/resources/blocks/bloccoTerra2.png"));
        bloccoTerra2 = bloccoTerra2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoTerra3 = ImageIO.read(new File("src/application/resources/blocks/bloccoTerra3.png"));
        bloccoTerra3 = bloccoTerra3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro2 = ImageIO.read(new File("src/application/resources/blocks/bloccoMuro2.png"));
        bloccoMuro2 = bloccoMuro2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoMuro3 = ImageIO.read(new File("src/application/resources/blocks/bloccoMuro3.png"));
        bloccoMuro3 = bloccoMuro3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        bloccoSpeciale2 = ImageIO.read(new File("src/application/resources/blocks/bloccoSpeciale2.png"));
        bloccoSpeciale2 = bloccoSpeciale2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        tubo2 = ImageIO.read(new File("src/application/resources/blocks/tubo2.png"));
        tubo2 = tubo2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        oro = ImageIO.read(new File("src/application/resources/blocks/oro.png"));
        oro = oro.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        lava = ImageIO.read(new File("src/application/resources/blocks/lava.jpg"));
        lava = lava.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        end_frame = ImageIO.read(new File("src/application/resources/blocks/end_frame.png"));
        end_frame = end_frame.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        ender_egg = ImageIO.read(new File("src/application/resources/blocks/ender_egg.png"));
        ender_egg = ender_egg.getScaledInstance(Settings.CELL_SIZE_RIGA*2,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        moneta = ImageIO.read(new File("src/application/resources/blocks/coin.png"));
        moneta = moneta.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        moneta2 = ImageIO.read(new File("src/application/resources/blocks/coin2.png"));
        moneta2 = moneta2.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        moneta3 = ImageIO.read(new File("src/application/resources/blocks/coin3.png"));
        moneta3 = moneta3.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        start_screen = ImageIO.read(new File("src/application/resources/GraphicsManager/main_screen_image.png"));
        start_screen = start_screen.getScaledInstance(Settings.WINDOW_SIZE_X,Settings.WINDOW_SIZE_Y,Image.SCALE_SMOOTH);
        usato = ImageIO.read(new File("src/application/resources/blocks/usato.png"));
        usato = usato.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        vita = ImageIO.read(new File("src/application/resources/items/vita.png"));
        vita = vita.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        riconoscimenti = ImageIO.read(new File("src/application/resources/riconoscimenti.png"));
        riconoscimenti = riconoscimenti.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        lingue = ImageIO.read(new File("src/application/resources/lingue.png"));
        lingue = lingue.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        titolo = ImageIO.read(new File("src/application/resources/titolo.png"));
        titolo = titolo.getScaledInstance(Settings.CELL_SIZE_RIGA*18,Settings.CELL_SIZE_COLONNA*4,Image.SCALE_SMOOTH);
        skin_screen = ImageIO.read(new File("src/application/resources/GraphicsManager/skin_change.png"));
        skin_screen = skin_screen.getScaledInstance(Settings.WINDOW_SIZE_X,Settings.WINDOW_SIZE_Y,Image.SCALE_SMOOTH);
        pulsante = ImageIO.read(new File("src/application/resources/GraphicsManager/pulsante.png"));
        pulsante = pulsante.getScaledInstance(Settings.CELL_SIZE_RIGA*8,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        skin_button = ImageIO.read(new File("src/application/resources/GraphicsManager/pulsante.png"));
        skin_button = skin_button.getScaledInstance(Settings.CELL_SIZE_RIGA*6,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        steve = ImageIO.read(new File("src/application/resources/GraphicsManager/steve_i.png"));
        steve = steve.getScaledInstance(Settings.CELL_SIZE_RIGA*2,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        alex = ImageIO.read(new File("src/application/resources/GraphicsManager/alex_i.png"));
        alex = alex.getScaledInstance(Settings.CELL_SIZE_RIGA*2,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        steve_completo = ImageIO.read(new File("src/application/resources/GraphicsManager/steve_completo.png"));
        steve_completo = steve_completo.getScaledInstance(Settings.CELL_SIZE_RIGA*5,Settings.CELL_SIZE_COLONNA*10,Image.SCALE_SMOOTH);
        alex_completo = ImageIO.read(new File("src/application/resources/GraphicsManager/alex_completo.png"));
        alex_completo = alex_completo.getScaledInstance(Settings.CELL_SIZE_RIGA*5,Settings.CELL_SIZE_COLONNA*10,Image.SCALE_SMOOTH);
        pausa = ImageIO.read(new File("src/application/resources/GraphicsManager/pausa.png"));
        pausa = pausa.getScaledInstance(Settings.CELL_SIZE_RIGA*4,Settings.CELL_SIZE_COLONNA*4,Image.SCALE_SMOOTH);
        minizombieDX = ImageIO.read(new File("src/application/resources/nemici/minizombie.gif"));
        minizombieDX = minizombieDX.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        minizombieSX=ImageUtil.flipImageHorizontally(minizombieDX);
        creeperDX = ImageIO.read(new File("src/application/resources/nemici/creeper.png"));
        creeperDX = creeperDX.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        creeperSX=ImageUtil.flipImageHorizontally(creeperDX);
        //velocita= new ImageIcon("src/application/resources/abilità/velocita.gif");
        velocita = ImageIO.read(new File("src/application/resources/abilità/velocita.gif"));
        velocita = velocita.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        lentezza = ImageIO.read(new File("src/application/resources/abilità/lentezza.png"));
        lentezza = lentezza.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
        scudoDX = ImageIO.read(new File("src/application/resources/GraphicsManager/Player/steveScudo.png"));
        scudoDX = scudoDX.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        scudoSX=ImageUtil.flipImageHorizontally(scudoDX);
        scudo = ImageIO.read(new File("src/application/resources/abilità/scudo.png"));
        scudo = scudo.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA,Image.SCALE_SMOOTH);
    }
    public Image getScudoIcon(){
        return scudo;
    }
    public Image getScudo(int direction) {
        if(direction==Settings.MOVE_RIGHT) return scudoDX;
        return scudoSX;
    }

    public Image getLentezza() {
        return lentezza;
    }

    public Image getVelocita() {
        return velocita;
    }

    public Image getMiniZombie(int direction){
        if(direction==1){
            return minizombieDX;
        }
        return minizombieSX;
    }
    public Image getCreeper(int direction){
        if(direction==1){
            return creeperDX;
        }
        return creeperSX;
    }
    public Image getPausa() {return pausa;}
    public static Image getPulsante() {
        return pulsante;
    }

    public Image getPersonaggio(int direction) {
        if (direction == Settings.MOVE_RIGHT) return personaggioDes;
        else return personaggioSin;
    }

    public static Image getSteve_completo() {
        return steve_completo;
    }
    public static Image getAlex_completo() {
        return alex_completo;
    }

    public static Image getSteve() {
        return steve;
    }

    public static Image getAlex() {
        return alex;
    }

    public void setPersonaggio(String p) throws IOException {
        personaggioDes= ImageIO.read(new File("src/application/resources/personaggio/"+p+".png"));
        personaggioDes = personaggioDes.getScaledInstance(Settings.CELL_SIZE_RIGA,Settings.CELL_SIZE_COLONNA*2,Image.SCALE_SMOOTH);
        personaggioSin=ImageUtil.flipImageHorizontally(personaggioDes);
    }

    public Image getSkin_screen() {
        return skin_screen;
    }
    public Image getSkin_button(){return skin_button;}

    public Image getTitolo() {
        return titolo;
    }

    public static Image getRiconoscimenti() {
        return riconoscimenti;
    }

    public static Image getLingue() {
        return lingue;
    }

    public Image getBackgroundImage(int liv) {
        if(liv==1) return backgroundImage;
        else if(liv==2) return backgroundImage2;
        return backgroundImage3;
    }

    public Image getMoneta(int liv) {
        if(liv==1) return moneta;
        else if(liv==2) return moneta2;
        return moneta3;
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
    public Image getTubo_sopra(int liv) {
        if(liv==1) return tubo;
        else if(liv==2) return tubo2;
        return tubo3;
    }
    public Image getTubo_sotto(int liv) {
        if(liv==1) return tubo1_sotto;
        else if(liv==2) return tubo2_sotto;
        return tubo3_sotto;
    }

    public Image getBarile(int liv) {
        if(liv==1) return barile;
        else if(liv==2) return oro;
        return end_frame;
    }

    public Image getFine(int liv) {
        if(liv==1 || liv==2) return fine;
        return fine3;
    }

    public Image getPortale(int liv) {
        if(liv==1) return portale;
        else if(liv==2) return portale_end;
        return ender_egg;
    }

    public Image getMorte(int liv) {
        if(liv==1) return acqua;
        return lava;
    }
    public Image getPonte(int liv) {
        if(liv==2) return ponte2;
        return ponte3;
    }
    public Image getTeletrasporto(int liv) {
        if(liv==2) return teletrasporto;
        return ender_dragon;
    }
    public Image getUsato() {
        return usato;
    }

    public Image getStart_screen() {
        return start_screen;
    }
    public Image getVita(){
        return vita;
    }
}
