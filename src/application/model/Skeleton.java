package application.model;

import application.Block;

import java.util.*;

public class Skeleton {
    private LinkedList<Position> coordinate;
    private int direction=1;
    private World world;
    public Skeleton(LinkedList<Position> coordinate, World world){
        this.coordinate=coordinate;
        this.world=world;
    }

    public int getDirection() {
        return direction;
    }

    public LinkedList<Position> getCoordinate() {
        return coordinate;
    }
    int passo=0;
    private LinkedList<Arrow> arrows=new LinkedList<>();

    public LinkedList<Arrow> getArrows() {
        return arrows;
    }
    private int passoFreccia=0;
    public void move(){
        int testa_i = coordinate.getFirst().i();
        int testa_j = coordinate.getFirst().j();
        int corpo_i = coordinate.getLast().i();
        int corpo_j = coordinate.getLast().j();
        if (testa_j + direction<11 || !world.isValidPosition(testa_i, testa_j + direction) || !world.isValidPosition(corpo_i, corpo_j + direction) || !world.isBlocco(corpo_i + 1, corpo_j + direction) || world.isBlocco(testa_i, testa_j + direction) || world.isBlocco(corpo_i, corpo_j + direction) || world.isNemico(testa_i, testa_j + direction) || world.isNemico(corpo_i, corpo_j + direction) || world.isNemico(testa_i, testa_j + (direction * 2)) || world.isNemico(corpo_i, corpo_j + (direction * 2))) {
            direction = -direction;
        }
        if (world.isPlayer(testa_i, testa_j + direction) || world.isPlayer(corpo_i, corpo_j + direction)) {
            world.getPlayer().killPlayer();
        }
        if(passoFreccia%2==0) moveArrows();
        if(passo%8==0) {
            if(world.isBlocco(corpo_i + 1, corpo_j + direction) && !world.isBlocco(testa_i, testa_j + direction) && !world.isBlocco(corpo_i, corpo_j + direction)) {
                world.setMatrice_Principale(testa_i, testa_j, Block.VUOTO);
                world.setMatrice_Principale(corpo_i, corpo_j, Block.VUOTO);
                world.setMatrice_Principale(testa_i, testa_j + direction, Block.SKELETON);
                world.setMatrice_Principale(corpo_i, corpo_j + direction, Block.SKELETON);
                coordinate.set(0, new Position(testa_i, testa_j + direction));
                coordinate.set(1, new Position(corpo_i, corpo_j + direction));
            }
            if(passo%16==0) shootArrow();
        }
        passo+=1;
        passoFreccia+=1;
    }
    private List<Arrow> toBeRemoved=new ArrayList<>();

    public List<Arrow> getToBeRemoved() {
        return toBeRemoved;
    }

    private void moveArrows(){
        for(Arrow a:arrows){
            if(a.getTempo()<10){
                a.move();
            }
            else toBeRemoved.add(a);
        }
        for(Arrow a:toBeRemoved){
            world.setMatrice_Principale(a.getPos().i(),a.getPos().j(),Block.VUOTO);
            arrows.remove(a);
        }
        toBeRemoved.clear();
    }
    private void shootArrow(){
        if(!world.isBlocco(coordinate.getFirst().i(),coordinate.getFirst().j()+direction) && !world.isPlayer(coordinate.getFirst().i(),coordinate.getFirst().j()+direction)){
            Arrow arrow=new Arrow(this,world,direction,new Position(coordinate.getFirst().i(),coordinate.getFirst().j()+direction),0);
            world.setMatrice_Principale(coordinate.getFirst().i(),coordinate.getFirst().j()+direction, Block.FRECCIA);
            arrows.add(arrow);
        }
        else if(world.isPlayer(coordinate.getFirst().i(),coordinate.getFirst().j()+direction)){
            world.getPlayer().killPlayer();
        }
    }
//    public void kill(){
//        world.getNemici().remove(this);
//        world.setMatrice_Principale(coordinate.getFirst().i(), coordinate.getFirst().j(), Block.VUOTO);
//        world.setMatrice_Principale(coordinate.getLast().i(), coordinate.getLast().j(), Block.VUOTO);
//    }
}

