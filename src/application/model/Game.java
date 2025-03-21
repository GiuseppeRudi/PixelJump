package application.model;

import application.GameStatus;
import application.controller.Controller;
import application.view.GamePanel;

import java.io.IOException;

public class Game {
    private World world;

    private Game() throws IOException {this.gameStatus=GameStatus.START_SCREEN;}
    private static final Game instance;
    private GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    static {
        try {
            instance = new Game();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int passo=-1;
    public static Game getInstance() {return instance;}

    public void setDirection(int direction)  { world.updateDirection(direction);}

    //viene chiamata dal player controller


    public void update(){
        if (gameStatus == GameStatus.IN_GAME) {
            if (world.getPlayer().getProgresso() != (world.getViewPort().getFirst().length() - Settings.Filtro_Size_Colonna)) {
                world.moveNemici();
                world.movePlayer();
                if(world.getLiv()==3){
                    world.moveBlocks();
                }
                world.getPlayer().checkAbilities();
                //System.out.println(world.getPlayer().getVelC()+" "+world.getPlayer().getLenC()+" "+world.getPlayer().getVelocita()+" "+world.getPlayer().getLentezza());
            } else if (world.getPlayer().getCoordinatePlayer().getFirst().j() < world.getViewPort().getFirst().length() - 9) {
                if (passo == -1) {
                    passo = 0;
                    Controller.getPressed().clear();
                }
                if (world.getPlayer().isFalling() || world.getPlayer().isJumping()) {
                    world.movePlayer();
                } else {
                    if (passo % 3 == 0) {
                        setDirection(Settings.MOVE_RIGHT);
                        Controller.getPressed().add(Settings.MOVE_RIGHT);
                        world.movePlayer();
                        Controller.getPressed().remove(Settings.MOVE_RIGHT);
                    }
                    passo++;
                }
            } else {
                if (GamePanel.getSoundtrack() != null) {
                    GamePanel.getSoundtrack().pause();
                    GamePanel.setSoundtrack(null);
                }
                if (!cont) {
                    cont = true;
                    setGameStatus(GameStatus.WIN);
                    if(world.getLiv()==1) GamePanel.setLock2(false);
                    else if(world.getLiv()==2) GamePanel.setLock3(false);
                }
                passo = -1;
                if (gameStatus == GameStatus.IN_GAME && world.getLiv() < 3) {
                    cont = false;
                    world.getPlayer().resetAbilities();
                    world = new World(world.getLiv() + 1, 3);
                }
            }
        }
    }
    private boolean cont=false;
    public World getWorld() { return world;}

    public void setWorld(World world) {
        this.world = world;
    }
}
