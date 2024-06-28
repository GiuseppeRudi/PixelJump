package application.model;

import application.Block;

import java.util.LinkedList;

public class MiniZombie{
    private LinkedList<Position> coordinate;
    private int direction=1;
    private World world;
    public MiniZombie(LinkedList<Position> coordinate, World world){
        this.coordinate=coordinate; //richiama la super classe
        this.world=world;
    }

    public int getDirection() {
        return direction;
    }

    public LinkedList<Position> getCoordinate() {
        return coordinate;
    }
    int passo=0;

    public void move(){
        int i = coordinate.getFirst().i();
        int j = coordinate.getFirst().j();
        if (j + direction<11 || !world.isValidPosition(i, j + direction) || !world.isBlocco(i + 1, j + direction) || world.isBlocco(i, j + direction) || world.isNemico(i, j + direction) || world.isNemico(i, j + (direction * 2))) {
            direction = -direction;
        }
        if (world.isPlayer(i, j + direction)) {
            world.getPlayer().killPlayer();
        }
        if(passo%3==0) {
            world.setMatrice_Principale(i, j, Block.VUOTO);
            world.setMatrice_Principale(i, j + direction, Block.MINIZOMBIE);
            coordinate.set(0, new Position(i, j + direction));
        }
        passo+=1;
    }
//    public void kill(){
//        world.setMatrice_Principale(coordinate.getFirst().i(), coordinate.getFirst().j(), Block.VUOTO);
//        world.getNemici().remove(this);
//    }
}
