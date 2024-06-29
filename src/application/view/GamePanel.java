package application.view;

import application.GameStatus;
import application.audio.Sound;
import application.controller.Controller;
import application.model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GamePanel extends JPanel {

    private ImmaginiGioco immaginiGioco;
    private Font Alt;
    private Font Reg;
    private Font Start;
    private Font Win;
    private Font Skin;
    private Font Pause;

    public void setController(Controller controllerPlayer) {
        this.addMouseListener(controllerPlayer);
        this.addKeyListener(controllerPlayer);
    }
    private MainScreen startScreen;
    private WinScreen winScreen;
    private SkinScreen skinScreen;
    private PauseScreen pauseScreen;
    private Map<Object, GameStatus> contenutoMap;
    public GamePanel(ImmaginiGioco immaginigioco, LevelProgress progress){
        this.immaginiGioco = immaginigioco;
        this.progress=progress;
        contenutoMap = new HashMap<>();
        addInMap();
        startScreen=new MainScreen();
        winScreen=new WinScreen();
        skinScreen=new SkinScreen();
        pauseScreen=new PauseScreen();
        Alt=loadFont("Alt");
        Reg=loadFont("Reg");
        Start=loadFont("Start");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Alt);
        ge.registerFont(Reg);
        ge.registerFont(Start);
    }

    private void addInMap() {
        contenutoMap.put("Gioca",GameStatus.IN_GAME);
        contenutoMap.put("Indietro",GameStatus.START_SCREEN);
        contenutoMap.put("Scegli skin", GameStatus.CHOOSE_SKIN);
        contenutoMap.put("Obiettivi", GameStatus.ACHIEVEMENT);
        contenutoMap.put("Comandi", GameStatus.COMMANDS_SCREEN);
        contenutoMap.put(ImmaginiGioco.getLingue(), GameStatus.LANGUAGES);
        contenutoMap.put(ImmaginiGioco.getRiconoscimenti(), GameStatus.ABOUT_SCREEN);
        contenutoMap.put("Menu Principale", GameStatus.START_SCREEN);
        contenutoMap.put("Livello Successivo", GameStatus.IN_GAME);
        contenutoMap.put(ImmaginiGioco.getSteve(), GameStatus.STEVE);
        contenutoMap.put(ImmaginiGioco.getAlex(), GameStatus.ALEX);
    }

    private Font loadFont(String t){
        try {
            InputStream is = getClass().getResourceAsStream("/resources/font/Minecrafter-"+t+".ttf");
            if(t.equals("Alt")) return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f);
            else if(t.equals("Start")) return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            if(t.equals("Alt")) return new Font("Verdana", Font.PLAIN, 18);
            else if(t.equals("Start")) return new Font("Arial", Font.PLAIN, 14);
            return new Font("Verdana", Font.PLAIN, 12);
        }
    }
    public void updateDirection(int direction) {
        //il panel aggiorna la direzione del player sulla view
        //playerView.setDirection(direction,immaginiGioco,this);
    }
    private World world;

    public World getWorld() {
        return world;
    }

    private LevelProgress progress;

    //public World getWorld() {return world;}
    private static Sound soundtrack;
    private String personaggio="steve";

    public static Sound getSoundtrack() {
        return soundtrack;
    }

    public static void setSoundtrack(Sound soundtrack) {
        GamePanel.soundtrack = soundtrack;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GameStatus gameStatus=Game.getInstance().getGameStatus();
        if(gameStatus == GameStatus.START_SCREEN){
            drawStartScreen(g2d);
            if(soundtrack==null) {
                soundtrack = new Sound("pigstep.wav");
                soundtrack.loop();
            }
        }
        else if(gameStatus == GameStatus.MAP_SELECTION){
            drawMapSelection(g2d);
        }
        else if(gameStatus == GameStatus.ABOUT_SCREEN){
            drawAbout(g2d);
        }
        else if(gameStatus == GameStatus.COMMANDS_SCREEN){
            drawCommands(g2d);
        }
        else if(gameStatus == GameStatus.GAME_OVER){
            drawGameOver(g2d);
        }
        else if(gameStatus == GameStatus.CHOOSE_SKIN || gameStatus == GameStatus.STEVE || gameStatus == GameStatus.ALEX){
            drawChooseSkin(g2d);
            if(soundtrack==null) {
                soundtrack = new Sound("skin.wav");
                soundtrack.loop();
            }
            if(gameStatus == GameStatus.STEVE || (gameStatus == GameStatus.CHOOSE_SKIN && personaggio.equals("steve"))) {
                personaggio="steve";
                createImageHover(g2d,skinScreen.getScreenFunctions()[1].getLocation(),skinScreen.getScreenFunctions()[1].getDimension());
            }
            else if(gameStatus == GameStatus.ALEX || personaggio.equals("alex")) {
                personaggio="alex";
                createImageHover(g2d,skinScreen.getScreenFunctions()[2].getLocation(),skinScreen.getScreenFunctions()[2].getDimension());
            }
            if(personaggio.equals("steve")){
                drawSkin(ImmaginiGioco.getSteve_completo(), g2d);
                try {
                    immaginiGioco.setPersonaggio(personaggio);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                drawSkin(ImmaginiGioco.getAlex_completo(),g2d);
                if(!personaggio.equals("alex")) personaggio="alex";
                try {
                    immaginiGioco.setPersonaggio(personaggio);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(gameStatus == GameStatus.ACHIEVEMENT){
            drawAchievement(g2d);
        }
        else if(gameStatus == GameStatus.LANGUAGES){
            drawLanguages(g2d);
        }
        else{
            drawMap(g2d);
            if(gameStatus == GameStatus.IN_GAME && soundtrack==null) {
                soundtrack = new Sound("blocks.wav");
                soundtrack.loop();
            }
            if(gameStatus == GameStatus.PAUSE) drawPause(g2d);
            else if(gameStatus == GameStatus.WIN){
                drawWin(g2d);
                if(soundtrack==null) {
                    soundtrack = new Sound("endLevel.wav");
                    soundtrack.play();
                }
            }
        }
        g2d.dispose();
    }

    private void drawSkin(Image i,Graphics2D g2d){
        g2d.drawImage(i,370,125,this);
    }
    private void drawWin(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        Win=Alt.deriveFont(50.0f);
        g2d.setFont(Win);
        g2d.drawString("Level "+world.getLiv()+" completed",170,200);
        g2d.drawImage(immaginiGioco.getPulsante(),100,400,this);
        if(world.getLiv()!=3) {
            g2d.drawImage(immaginiGioco.getPulsante(),560,400,this);
            for (Function item: winScreen.getScreenFunctions()) {
                drawWinScreenButtons(item, g2d);
            }
        }
        else drawWinScreenButtons(winScreen.getScreenFunctions()[0], g2d);
    }

    private void drawWinScreenButtons(Function item, Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        Win=Start.deriveFont(20.0f);
        g2d.setFont(Win);
        int width,height;
        width = g2d.getFontMetrics().stringWidth((String) item.getObject());
        height = g2d.getFontMetrics().getHeight();
        item.setDimension(new Dimension(width, height));
        item.setLocation(new Point(item.getLocation().x, item.getLocation().y));
        g2d.drawString((String) item.getObject(), item.getLocation().x, item.getLocation().y);
        if(getMousePosition()!=null) {
            clickWinScreenButton(item,g2d);
        }
    }

    private void clickWinScreenButton(Function item, Graphics2D g2d) {
        Dimension dimension = item.getDimension();
        Point location = item.getLocation();
        boolean inX = location.x <= getMousePosition().x && location.x + dimension.width >= getMousePosition().x;
        boolean inY=location.y >= getMousePosition().y && location.y - dimension.height <= getMousePosition().y;
        if (inX && inY) {
            createTextHover(g2d,location,dimension);
        }
    }

    private void drawPause(Graphics2D g2d) {
        g2d.drawImage(ImmaginiGioco.getPulsante(),(Settings.WINDOW_SIZE_X- ImmaginiGioco.getPulsante().getWidth(this))/2,400,this);
        g2d.drawImage(immaginiGioco.getPausa(),(Settings.WINDOW_SIZE_X-immaginiGioco.getPausa().getWidth(this))/2,(Settings.WINDOW_SIZE_Y-immaginiGioco.getPausa().getHeight(this))/2,this);
        for(Function item : pauseScreen.getScreenFunctions()) {
            drawPauseScreenButton(item,g2d);
        }
    }
    private void drawPauseScreenButton(Function item,Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        Pause=Start.deriveFont(20.0f);
        g2d.setFont(Pause);
        int width,height;
        width = g2d.getFontMetrics().stringWidth((String) item.getObject());
        height = g2d.getFontMetrics().getHeight();
        item.setDimension(new Dimension(width, height));
        item.setLocation(new Point(item.getLocation().x, item.getLocation().y));
        g2d.drawString((String) item.getObject(), item.getLocation().x, item.getLocation().y);

        if(getMousePosition()!=null) {
            clickPauseScreenButton(item,g2d);
        }
    }
    private void clickPauseScreenButton(Function item,Graphics2D g2d){
        Dimension dimension = item.getDimension();
        Point location = item.getLocation();
        boolean inX = location.x <= getMousePosition().x && location.x + dimension.width >= getMousePosition().x;
        boolean inY = location.y >= getMousePosition().y && location.y - dimension.height <= getMousePosition().y;
        if (inX && inY) createTextHover(g2d,location,dimension);
    }
    private void drawChooseSkin(Graphics2D g2d) {
        g2d.drawImage(immaginiGioco.getSkin_screen(),0,0,this);
        g2d.drawImage(immaginiGioco.getSkin_button(),20,20,this);
        for(Function item : skinScreen.getScreenFunctions()) {
            drawSkinScreenButton(item,g2d);
        }
    }

    private void drawSkinScreenButton(Function item, Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        Skin=Start.deriveFont(25.0f);
        g2d.setFont(Skin);
        int width,height;
        if(item.getObject() instanceof String) {
            width = g2d.getFontMetrics().stringWidth((String) item.getObject());
            height = g2d.getFontMetrics().getHeight();
        }
        else{
            width = ((Image) item.getObject()).getWidth(this);
            height = ((Image) item.getObject()).getHeight(this);
        }
        item.setDimension(new Dimension(width, height));
        item.setLocation(new Point(item.getLocation().x, item.getLocation().y));

        if(item.getObject() instanceof String)
            g2d.drawString((String) item.getObject(), item.getLocation().x, item.getLocation().y);
        else g2d.drawImage((Image) item.getObject(), item.getLocation().x, item.getLocation().y,this);

        if(getMousePosition()!=null) {
            clickSkinScreenButton(item,g2d);
        }
    }

    private void clickSkinScreenButton(Function item, Graphics2D g2d) {
        Dimension dimension = item.getDimension();
        Point location = item.getLocation();
        boolean inX = location.x <= getMousePosition().x && location.x + dimension.width >= getMousePosition().x;
        boolean inY;
        if (item.getObject() instanceof String)
            inY = location.y >= getMousePosition().y && location.y - dimension.height <= getMousePosition().y;
        else inY = location.y <= getMousePosition().y && location.y + dimension.height >= getMousePosition().y;
        if (inX && inY) {
            if (item.getObject() instanceof Image) {
                createImageHover(g2d,location,dimension);
            } else {
                createTextHover(g2d,location,dimension);
            }
        }
    }

    private void drawGameOver(Graphics2D g2d) {
    }

    private void drawCommands(Graphics2D g2d) {
    }

    private void drawAbout(Graphics2D g2d) {

    }
    private void drawLanguages(Graphics2D g2d) {
    }
    private void drawAchievement(Graphics2D g2d) {
    }
    private void drawMapSelection(Graphics2D g2d) {
    }

    private void drawStartScreen(Graphics2D g2d) {
        g2d.drawImage(immaginiGioco.getStart_screen(),0,0,this);
        g2d.drawImage(immaginiGioco.getTitolo(),180,60,this);
        for(Function item : startScreen.getScreenFunctions()) {
            drawMainScreenButtons(item,g2d,Start);
        }
    }

    private void drawMainScreenButtons(Function item, Graphics2D g2d, Font font) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        int width,height;
        if(item.getObject() instanceof String) {
            width = g2d.getFontMetrics().stringWidth((String) item.getObject());
            height = g2d.getFontMetrics().getHeight();
        }
        else{
            width = ((Image) item.getObject()).getWidth(this);
            height = ((Image) item.getObject()).getHeight(this);
        }
        item.setDimension(new Dimension(width, height));
        item.setLocation(new Point(item.getLocation().x, item.getLocation().y));

        if(item.getObject() instanceof String)
            g2d.drawString((String) item.getObject(), item.getLocation().x, item.getLocation().y);
        else g2d.drawImage((Image) item.getObject(), item.getLocation().x, item.getLocation().y,this);

        if(getMousePosition()!=null) {
            clickMainScreenButton(item,g2d);
        }
    }

    private void clickMainScreenButton(Function item,Graphics2D g2d) {
        Dimension dimension = item.getDimension();
        Point location = item.getLocation();
        boolean inX = location.x <= getMousePosition().x && location.x + dimension.width >= getMousePosition().x;
        boolean inY;
        if (item.getObject() instanceof String)
            inY = location.y >= getMousePosition().y && location.y - dimension.height <= getMousePosition().y;
        else inY = location.y <= getMousePosition().y && location.y + dimension.height >= getMousePosition().y;

        if (inX && inY) {
            if (item.getObject() instanceof Image) {
                createImageHover(g2d,location,dimension);
            } else {
                createTextHover(g2d,location,dimension);
            }
        }
    }

    private void createTextHover(Graphics2D g2d, Point l, Dimension d){
        g2d.fillRect(l.x, l.y, d.width, 2);
        g2d.fillRect(l.x-2, l.y- d.height+2, 2, d.height);
        g2d.fillRect(l.x, l.y- d.height+2, d.width, 2);
        g2d.fillRect(l.x+ d.width, l.y- d.height+2, 2, d.height);
    }

    private void createImageHover(Graphics2D g2d,Point l,Dimension d){
        g2d.fillRect(l.x, l.y, d.width, 2);
        g2d.fillRect(l.x, l.y, 2, d.height);
        g2d.fillRect(l.x + d.height - 2, l.y, 2, d.height);
        g2d.fillRect(l.x, l.y + d.height - 2, d.width, 2);
    }

    private void drawMap(Graphics2D g2d){
        world = Game.getInstance().getWorld();
        if(progress.getMaximum()!=world.getViewPort().getFirst().length()-Settings.Filtro_Size_Colonna) progress.setRange(0,world.getViewPort().getFirst().length()-Settings.Filtro_Size_Colonna);
        progress.updateProgress(world.getPlayer().getProgresso());
        g2d.drawImage(immaginiGioco.getBackgroundImage(world.getLiv()),-(world.getPlayer().getProgresso()*5),0,this);
        for(int i = 0; i < Settings.Filtro_Size_Riga; i++) {
            int y = i * Settings.CELL_SIZE_RIGA; //coordinate x sulla view
            for(int j = 0; j < Settings.Filtro_Size_Colonna; j++) {
                int x = j * Settings.CELL_SIZE_COLONNA; //coordinate y sulla view
                if(world.getViewPort().get(i).charAt(j+world.getPlayer().getProgresso())=='j'){
                    g2d.drawImage(immaginiGioco.getFine(world.getLiv()), x+8,y,this);
                }
                if(world.getViewPort().get(i).charAt(j+world.getPlayer().getProgresso())=='k'){
                    g2d.drawImage(immaginiGioco.getPortale(world.getLiv()), x,y,this);
                }
                if(world.getViewPort().get(i).charAt(j+world.getPlayer().getProgresso())=='n'){
                    g2d.drawImage(immaginiGioco.getTeletrasporto(world.getLiv()), x,y,this);
                }
                if(world.isCoin(i,j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getMoneta(world.getLiv()),x,y,this);
                }
                else if(world.isVelocita(i,j+world.getPlayer().getProgresso())) {
                    //immaginiGioco.getVelocita().paintIcon(this,g2d,x,y);
                    g2d.drawImage(immaginiGioco.getVelocita(),x,y,this);
                }
                else if(world.isScudo(i,j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getScudoIcon(),x,y,this);
                }
                else if(world.isLentezza(i,j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getLentezza(),x,y,this);
                }
                else if(world.isVita(i,j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getVita(),x,y,this);
                }
                else if(world.isUsato(i,j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getUsato(),x,y,this);
                }
                else if(world.isWall(i, j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getBloccoMuro(world.getLiv()),x,y,this);
                }
                else if(world.isTerra(i,j+world.getPlayer().getProgresso())) {
                    g2d.drawImage(immaginiGioco.getBloccoTerra(world.getLiv()),x,y,this);
                }
                else if(world.isErba(i,j+world.getPlayer().getProgresso())){
                    g2d.drawImage(immaginiGioco.getBloccoErba(),x,y,this);
                }
                else if(world.isSpeciale(i,j+world.getPlayer().getProgresso())){
                    g2d.drawImage(immaginiGioco.getBloccoSpeciale(world.getLiv()), x,y,this);
                }
                else if(world.isTubo(i,j + world.getPlayer().getProgresso())){

                    if(!world.isTubo(i-1,j + world.getPlayer().getProgresso()))
                        g2d.drawImage(immaginiGioco.getTubo_sopra(world.getLiv()), x, y, this);
                    else g2d.drawImage(immaginiGioco.getTubo_sotto(world.getLiv()), x, y, this);
                }
                else if(world.isBarile(i,j+world.getPlayer().getProgresso())){
                    g2d.drawImage(immaginiGioco.getBarile(world.getLiv()), x,y,this);
                }
                else if(world.isMorte(i,j+world.getPlayer().getProgresso())){
                    g2d.drawImage(immaginiGioco.getMorte(world.getLiv()), x,y,this);
                }
                else if(world.isPonte(i,j+world.getPlayer().getProgresso())){
                    g2d.drawImage(immaginiGioco.getPonte(world.getLiv()), x,y,this);
                }
                else if(world.isPlayer(i, j+world.getPlayer().getProgresso())) {
                    if (world.isPlayer(i-1, j+world.getPlayer().getProgresso())) {
                        if(Controller.getPressed().contains(Settings.MOVE_RIGHT))
                            if(world.getPlayer().getScudo()){
                                g2d.drawImage(immaginiGioco.getScudo(Settings.MOVE_RIGHT),x,y-Settings.CELL_SIZE_RIGA,this);
                            }
                            else g2d.drawImage(immaginiGioco.getPersonaggio(Settings.MOVE_RIGHT),x,y-Settings.CELL_SIZE_RIGA,this);
                        else if(Controller.getPressed().contains(Settings.MOVE_LEFT))
                            if(world.getPlayer().getScudo()){
                                g2d.drawImage(immaginiGioco.getScudo(Settings.MOVE_LEFT),x,y-Settings.CELL_SIZE_RIGA,this);
                            }
                            else g2d.drawImage(immaginiGioco.getPersonaggio(Settings.MOVE_LEFT),x,y-Settings.CELL_SIZE_RIGA,this);
                        else {
                            if(world.getPlayer().getScudo()){
                                g2d.drawImage(immaginiGioco.getScudo(world.getPlayer().getPreDirection()),x,y-Settings.CELL_SIZE_RIGA,this);
                            }
                            else g2d.drawImage(immaginiGioco.getPersonaggio(world.getPlayer().getPreDirection()), x, y - Settings.CELL_SIZE_RIGA, this);
                        }
                    }
                }
                else if(world.isMiniZombie(i, j+world.getPlayer().getProgresso())){
                    for(Object o: world.getNemici()){
                        if(o instanceof MiniZombie){
                            if (((MiniZombie) o).getCoordinate().getFirst().i()==i && ((MiniZombie) o).getCoordinate().getFirst().j()==j+world.getPlayer().getProgresso()){
                                g2d.drawImage(immaginiGioco.getMiniZombie(((MiniZombie) o).getDirection()),x,y,this);
                                break;
                            }
                        }
                    }
                }
                else if(world.isCreeper(i, j+world.getPlayer().getProgresso()) && world.isCreeper(i+1, j+world.getPlayer().getProgresso())){
                    for(Object o: world.getNemici()){
                        if(o instanceof Creeper){
                            if (((Creeper) o).getCoordinate().getFirst().i()==i && ((Creeper) o).getCoordinate().getFirst().j()==j+world.getPlayer().getProgresso()){
                                g2d.drawImage(immaginiGioco.getCreeper(((Creeper) o).getDirection()),x,y,this);
                                break;
                            }
                        }
                    }
                }
            }
        }
        drawAbilityIcons(g2d);
        drawProgress(g2d,world.getPlayer().getProgresso());
        drawCoins(g2d,world.getPlayer().getCoins());
        drawLives(g2d,world.getPlayer().getLives());
        drawLevel(g2d,world.getLiv());
    }
    private void drawAbilityIcons(Graphics2D g2d){
        if(world.getPlayer().getVelocita()){
            g2d.drawImage(immaginiGioco.getFlash(),15,80,this);
            Alt=Alt.deriveFont(25.0f);
            g2d.setFont(Alt);
            g2d.setColor(Color.WHITE);
            g2d.drawString(world.getPlayer().getVelC()*66/100+"", 80, 120);
        }
        else if(world.getPlayer().getLentezza()){
            g2d.drawImage(immaginiGioco.getTartaruga(),15,80,this);
            Alt=Alt.deriveFont(25.0f);
            g2d.setFont(Alt);
            g2d.setColor(Color.WHITE);
            g2d.drawString(world.getPlayer().getLenC()*66/100+"", 80, 120);
        }
    }
    private void drawLevel(Graphics2D g2d,int liv) {
        g2d.setFont(Alt);
        g2d.drawString("Level "+liv, 40, 560);
    }
    private void drawLives(Graphics2D g2d,int lives) {
        for(int i=1;i<=lives;i++){
            g2d.drawImage(immaginiGioco.getVita(),i*30,18, this);
        }
    }
    private void drawCoins(Graphics2D g2d,int coins) {
        g2d.setFont(Alt);
        g2d.drawString(coins+"", 830, 40);
        g2d.drawImage(immaginiGioco.getMoneta(world.getLiv()),850,18, this);
    }

    private void drawProgress(Graphics2D g2d,int progresso) {
        int x=Settings.WINDOW_SIZE_X/2-progress.getMaximum();
        int y=30;
        for(int i=1;i<=progresso;i++){
            g2d.setColor(Color.WHITE);
            g2d.fillRect((i*2)+x,y,2,2);
        }
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x+progress.getMaximum()*2+5,y-3,2,8);
        g2d.fillRect(x-3,y-3,2,8);
        g2d.fillRect(x-1,y-3,progress.getMaximum()*2+6,2);
        g2d.fillRect(x-1,y+3,progress.getMaximum()*2+6,2);
        String completion = String.format("%.0f", progress.getPercentComplete()*100);
        g2d.setFont(Reg);
        g2d.drawString(completion, x-33, y+5);
    }




    public void update() {
        //
        //playerView.update();

        //ristampa ogni volta il paintcomponent che ristampa la matrice
        this.repaint();
        //viene chiamato da Game/controllerPlayer

    }
    private Object path;
    public void select() {
        if(Game.getInstance().getGameStatus().equals(GameStatus.START_SCREEN))
            path = startScreen.select(getMousePosition());
        else if (Game.getInstance().getGameStatus().equals(GameStatus.WIN)) {
            path = winScreen.select(getMousePosition(), world.getLiv());
        }
        else if (Game.getInstance().getGameStatus().equals(GameStatus.CHOOSE_SKIN) || Game.getInstance().getGameStatus().equals(GameStatus.STEVE) || Game.getInstance().getGameStatus().equals(GameStatus.ALEX)) {
            path = skinScreen.select(getMousePosition());
        }
        else if (Game.getInstance().getGameStatus().equals(GameStatus.PAUSE)){
            path = pauseScreen.select(getMousePosition());
        }
        System.out.println(path);
        if (path != null) {
            if(soundtrack!=null) {
                GameStatus preGS=Game.getInstance().getGameStatus();
                GameStatus aftGS=contenutoMap.get(path);
                if(!((preGS==GameStatus.CHOOSE_SKIN || preGS==GameStatus.STEVE || preGS==GameStatus.ALEX) && (aftGS==GameStatus.CHOOSE_SKIN || aftGS==GameStatus.STEVE || aftGS==GameStatus.ALEX))) {
                    soundtrack.pause();
                    soundtrack = null;
                }
            }
            if(path=="Esci"){
                System.exit(1);
            }
            else Game.getInstance().setGameStatus(contenutoMap.get(path));
            path=null;
        }
    }
}
