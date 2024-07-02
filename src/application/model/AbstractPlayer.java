package application.model;

//questa classe serve per creare piu player , nemici che utilizzano questo modello
// generale su cui altri classi che la estendono possono usare

import application.Block;
import application.GameStatus;
import application.audio.Sound;
import application.controller.Controller;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPlayer {

    protected World world;
    private LinkedList<Position> coordinate;

    public AbstractPlayer(LinkedList<Position> coordinate,World world) { //super classe di player
        this.coordinate= coordinate;
        this.world= world;
    }

    Position getPosition(int i)
    {
        return coordinate.get(i);
    }


    private boolean isJumping=false;
    private boolean isFalling=false;
    public boolean isJumping() {
        return isJumping;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }
    protected int progresso=0;

    protected int lives;

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    protected LinkedList<Position> simulateMove(int direction) {
        LinkedList<Position> newCoordinate = new LinkedList<>();

        int testa_i = coordinate.getFirst().i();
        int testa_j = coordinate.getFirst().j();

        int corpo_i = coordinate.get(1).i();
        int corpo_j = coordinate.get(1).j();


        if (Controller.getPressed().contains(Settings.JUMP)) {
            if (!isJumping && !isFalling) {
                isJumping = true;
            }
        }
//        if (Controller.getPressed().contains(Settings.MOVE_LEFT)){
//            if (world.isNemico(testa_i, testa_j -1) || world.isNemico(corpo_i, corpo_j -1)) {
//                killPlayer();
//            }
//        }
//        if(Controller.getPressed().contains(Settings.MOVE_RIGHT)){
//            if (world.isNemico(testa_i, testa_j +1) || world.isNemico(corpo_i, corpo_j +1)) {
//                killPlayer();
//            }
//        }

        if(isJumping){
            testa_i = testa_i - 1;
            corpo_i = corpo_i - 1;
        }
        else if(isFalling){
            testa_i = testa_i + 1;
            corpo_i = corpo_i + 1;
        }
        if (Controller.getPressed().contains(Settings.MOVE_LEFT)){
            if((!isJumping && !isFalling) || (!world.isBlocco(testa_i, testa_j - 1) && !world.isBlocco(corpo_i, corpo_j - 1) && !world.isBlocco(testa_i - 1, testa_j - 1) && world.isValidPosition(testa_i, testa_j - 1)/* && world.isValidPosition(corpo_i, corpo_j - 1) && world.isValidPosition(testa_i - 1, testa_j - 1)*/)) {
                testa_j = testa_j - 1;
                corpo_j = corpo_j - 1;
            }
        }
        if (Controller.getPressed().contains(Settings.MOVE_RIGHT)){
            if((!isJumping && !isFalling) || (!world.isBlocco(testa_i, testa_j + 1) && !world.isBlocco(corpo_i, corpo_j + 1) && !world.isBlocco(testa_i - 1, testa_j + 1) && world.isValidPosition(testa_i, testa_j + 1)/* && world.isValidPosition(corpo_i, corpo_j + 1) && world.isValidPosition(testa_i - 1, testa_j + 1)*/)) {
                testa_j = testa_j + 1;
                corpo_j = corpo_j + 1;
            }
        }
        if(world.isTel1()){
            newCoordinate.add(new Position(10, 130));
            newCoordinate.add(new Position(11, 130));
            world.setTel1(false);
            return newCoordinate;
        }
        else if(world.isTel2()){
            newCoordinate.add(new Position(15, 126));
            newCoordinate.add(new Position(16, 126));
            world.setTel2(false);
            return newCoordinate;
        }

//        System.out.print(testa_i);
//        System.out.println(corpo_i);
        newCoordinate.add(new Position(testa_i,testa_j));
        newCoordinate.add(new Position(corpo_i,corpo_j));
        return newCoordinate;
    }

    //la direzione che ce qui viene presa  da default not moving e che puo essere aggiornata ogni volta che ce un update directions


    protected LinkedList<Position> move(int direction) {
        //qui aggiorniamo la linked list con le nuove coordinate che abbiamo precedentemnete controllato nel movePlayer in world
        coordinate = simulateMove();
        //System.out.println(coordinate.getLast().i()+" "+ coordinate.getLast().j());
        if(coordinate.getLast().i()+1>=world.getViewPort().size() || world.isMorte(coordinate.getLast().i()+1,coordinate.getLast().j()) || world.isNemico(coordinate.getFirst().i(),coordinate.getFirst().j()) || world.isNemico(coordinate.getLast().i(), coordinate.getLast().j())) {
            //se cade nel vuoto muore perde una vita ne ha 3 , quando perde tutte le vite muore del tutto
            killPlayer();
        }
        return coordinate;
    }
    public void resetAbilities(){
        if(world.getPlayer().getVelocita()){
            world.getPlayer().setVelocita(false);
            world.getPlayer().setVelC(150);
            world.setAb(2);
        } else if(world.getPlayer().getLentezza()){
            world.getPlayer().setLentezza(false);
            world.getPlayer().setLenC(150);
            world.setAb(2);
        }
    }
    public void killPlayer(){
        if(!world.getPlayer().getScudo()) {
            resetAbilities();
            if (lives == 1) {
                Sound morte = new Sound("death.wav");
                morte.play();
            } else {
                Sound morte = new Sound("damage.wav");
                morte.play();
            }
            world.setMatrice_Principale(coordinate.getFirst().i(),coordinate.getFirst().j(),Block.VUOTO);
            world.setMatrice_Principale(coordinate.getLast().i(),coordinate.getLast().j(),Block.VUOTO);
            coordinate.set(0, world.getPlayerStartPosition(world.getLiv()).getFirst());
            coordinate.set(1, world.getPlayerStartPosition(world.getLiv()).getLast());
            progresso = 0;
            if (lives > 0) lives--;
            if (lives==0) Game.getInstance().setGameStatus(GameStatus.GAME_OVER);
            world.updateDirection(Settings.MOVE_RIGHT);
            world.setMorte(true);
        }
        else world.getPlayer().setScudo(false);
    }
    abstract void move();
    // perche questo simulateMove di player è diverso da simulateplayer di abstract
    abstract LinkedList<Position> simulateMove();
}
