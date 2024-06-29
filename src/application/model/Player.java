package application.model;

import application.Block;
import application.GameLoop;
import application.audio.Sound;
import application.controller.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Player extends AbstractPlayer{
    private int preDirection=Settings.MOVE_RIGHT;

    public int getPreDirection() {
        return preDirection;
    }

    private LinkedList<Position> coordinatePlayer = new LinkedList<>();

    public LinkedList<Position> getCoordinatePlayer() {
        return coordinatePlayer;
    }

    private int direction = Settings.NOT_MOVING;
    //private World world;

    public Player(LinkedList<Position> coordinate,World world) { //costruttore player
        super(coordinate,world); //richiama la super classe
        //this.world=world;
    }

    private int coins=0;

    public int getCoins() {
        return coins;
    }
    public static int cont=0;
    private int velC=150;
    private int lenC=150;
    private boolean velocita=false;
    private boolean scudo=false;

    public boolean getScudo() {
        return scudo;
    }

    private boolean lentezza=false;

    public boolean getVelocita() {
        return velocita;
    }

    public boolean getLentezza() {
        return lentezza;
    }

    public void setVelocita(boolean velocita) {
        this.velocita = velocita;
    }

    public void setLentezza(boolean lentezza) {
        this.lentezza = lentezza;
    }

    public int getVelC() {
        return velC;
    }

    public int getLenC() {
        return lenC;
    }

    public void setVelC(int velC) {
        this.velC = velC;
    }

    public void setLenC(int lenC) {
        this.lenC = lenC;
    }

    private Sound cammina;
    private Sound sbatte;
    private Sound coin;
    private Sound tel;
    @Override
    public void move(){
        if(Controller.getPressed().contains(Settings.JUMP) && Game.getInstance().getWorld().isBlocco(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j()) && cont==0){
            Sound salta = new Sound("jump.wav");
            salta.play();
        }
        coordinatePlayer=super.move(direction);
        if(world.isCoin(coordinatePlayer.getLast().i(),coordinatePlayer.getLast().j()) || world.isCoin(coordinatePlayer.getFirst().i(),coordinatePlayer.getFirst().j())) coins++;
        else if(world.isVelocita(coordinatePlayer.getLast().i(),coordinatePlayer.getLast().j()) || world.isVelocita(coordinatePlayer.getFirst().i(),coordinatePlayer.getFirst().j())){
            if(lentezza){
                lentezza=false;
                lenC=150;
            }
            velocita=true;
            world.setAb(1);
        }
        else if(world.isScudo(coordinatePlayer.getLast().i(),coordinatePlayer.getLast().j()) || world.isScudo(coordinatePlayer.getFirst().i(),coordinatePlayer.getFirst().j())) scudo=true;
        else if(world.isLentezza(coordinatePlayer.getLast().i(),coordinatePlayer.getLast().j()) || world.isLentezza(coordinatePlayer.getFirst().i(),coordinatePlayer.getFirst().j())){
            if(velocita){
                velocita=false;
                velC=150;
            }
            lentezza=true;
            world.setAb(4);
        }
        else if(world.isVita(coordinatePlayer.getLast().i(),coordinatePlayer.getLast().j()) || world.isVita(coordinatePlayer.getFirst().i(),coordinatePlayer.getFirst().j())){
            if(getLives()!=4) setLives(getLives()+1);
        }
        if(direction!=Settings.NOT_MOVING && direction!=Settings.JUMP) preDirection=direction;
        if(Controller.getPressed().contains(Settings.MOVE_RIGHT) && progresso<(world.getViewPort().getFirst().length() -Settings.Filtro_Size_Colonna) && super.getPosition(0).j()>=Settings.Filtro_Size_Colonna+progresso-15) progresso+=(super.getPosition(0).j()-(Settings.Filtro_Size_Colonna+progresso-15));
        else if(Controller.getPressed().contains(Settings.MOVE_LEFT) && progresso>0 && super.getPosition(0).j()<=Settings.Filtro_Size_Colonna+progresso-21) progresso-=((Settings.Filtro_Size_Colonna+progresso-21)-super.getPosition(0).j());
        makeSounds();
        checkJump();

    }
    public void checkAbilities(){
        if(velocita){
            velC--;
            if(velC<=0){
                velocita=false;
                velC=150;
                world.setAb(2);
            }
        }
        else if(lentezza){
            lenC--;
            if(lenC<=0){
                lentezza=false;
                lenC=150;
                world.setAb(2);
            }
        }
    }
    private void makeSounds() {
        if(!Controller.getPressed().isEmpty() || isFalling() || isJumping()) {
            if(world.getViewPort().get(coordinatePlayer.getLast().i()).charAt(coordinatePlayer.getLast().j())=='n' && world.getViewPort().get(coordinatePlayer.getFirst().i()).charAt(coordinatePlayer.getFirst().j())=='n'){
                tel = new Sound("tel.wav");
                tel.play();
            }
            if (Game.getInstance().getWorld().isErba(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("grass.wav");
                cammina.play();
            } else if (Game.getInstance().getWorld().isTerra(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("dirt.wav");
                cammina.play();
            } else if (Game.getInstance().getWorld().isWall(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("wood.wav");
                cammina.play();
            } else if (Game.getInstance().getWorld().isSpeciale(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("sand.wav");
                cammina.play();
            } else if(Game.getInstance().getWorld().isTubo(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("tubo.wav");
                cammina.play();
            } else if(Game.getInstance().getWorld().isBarile(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("barrel.wav");
                cammina.play();
            } else if(Game.getInstance().getWorld().isPonte(coordinatePlayer.getLast().i() + 1, coordinatePlayer.getLast().j())) {
                cammina = new Sound("wood.wav");
                cammina.play();
            }
            if (Game.getInstance().getWorld().isBlocco(coordinatePlayer.getFirst().i() - 1, coordinatePlayer.getFirst().j())){
                if(isJumping()) {
                    sbatte = new Sound("hit.wav");
                    sbatte.play();
                }
                if(Game.getInstance().getWorld().isSpeciale(coordinatePlayer.getFirst().i() - 1, coordinatePlayer.getFirst().j())){
                    coin = new Sound("coin.wav");
                    coin.play();
                }
            }
        }
    }

    private void checkJump() {
        if(!isJumping() && !isFalling() && !world.isBlocco(coordinatePlayer.getLast().i()+1,coordinatePlayer.getLast().j())) setFalling(true);
        if(isJumping()){
            if(cont<3){
                if(world.getViewPort().get(coordinatePlayer.getFirst().i()-1).charAt(coordinatePlayer.getFirst().j())=='q' && !world.isUsato(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getFirst().j())) {
                    world.setMatrice_Principale(coordinatePlayer.getFirst().i() - 1, coordinatePlayer.getFirst().j(), Block.USATO);
                    generateRandomBlock();
                }
                if(world.isBlocco(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getFirst().j())){
                    if(world.isSpeciale(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getFirst().j())){
                        world.setMatrice_Principale(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getFirst().j(), Block.USATO);
                        generateRandomBlock();
                    }
                    setJumping(false);
                    setFalling(true);
                }
                else cont++;
            }
            else{
                setJumping(false);
                setFalling(true);
            }
        }
        if(isFalling()){
            if(world.isBlocco(coordinatePlayer.getLast().i()+1,coordinatePlayer.getLast().j())) {
                setFalling(false);
                cont = 0;
            }
            else if(world.isNemico(coordinatePlayer.getLast().i()+1,coordinatePlayer.getLast().j())){
                LinkedList<Object> toBeRemoved = new LinkedList<>();
                for(Object o: world.getNemici()){
                    if(o instanceof MiniZombie) {
                        if (((MiniZombie) o).getCoordinate().getFirst().i() == coordinatePlayer.getLast().i() + 1 && ((MiniZombie) o).getCoordinate().getFirst().j() == coordinatePlayer.getLast().j()){
//                            ((MiniZombie) o).kill();
                            toBeRemoved.add(o);
                        }
                    }
                    else if(o instanceof Creeper){
                        if (((Creeper) o).getCoordinate().getFirst().i() == coordinatePlayer.getLast().i() + 1 && ((Creeper) o).getCoordinate().getFirst().j() == coordinatePlayer.getLast().j()){
//                            ((Creeper) o).kill();
                            toBeRemoved.add(o);
                        }
                    }
                }
                world.removeEnemy(toBeRemoved);
                setFalling(false);
                setJumping(true);
                cont=1;
            }
        }
    }
    private void generateRandomBlock(){
        Random r=new Random();
        int n=r.nextInt(0,10);
        if(n<=5) world.setMatrice_Principale(coordinatePlayer.getFirst().i()-2,coordinatePlayer.getFirst().j(), Block.MONETA);
        else if(n==6) world.setMatrice_Principale(coordinatePlayer.getFirst().i()-2,coordinatePlayer.getFirst().j(), Block.VELOCITA);
        else if(n==7) world.setMatrice_Principale(coordinatePlayer.getFirst().i()-2,coordinatePlayer.getFirst().j(), Block.SCUDO);
        else if(n==8) world.setMatrice_Principale(coordinatePlayer.getFirst().i()-2,coordinatePlayer.getFirst().j(), Block.LENTEZZA);
        else world.setMatrice_Principale(coordinatePlayer.getFirst().i()-2,coordinatePlayer.getFirst().j(), Block.VITA);
    }
    public int getProgresso() {
        return progresso;
    }

    public void updateDirection(int direction) {
        //aggiorno la direction del player
//        System.out.println("vecchia direzione: " );
//        System.out.println(direction);
        this.direction = direction;

//        System.out.println("nuova direzione: " );
//        System.out.println(direction);
    }

    //la direzione che ce qui viene presa da default not moving e che puo essere aggiornata ogni volta che ce un update directions
    @Override
    public LinkedList<Position> simulateMove() {
        return super.simulateMove(direction);
    }

    public void setScudo(boolean b) {
        scudo=b;
    }
}
