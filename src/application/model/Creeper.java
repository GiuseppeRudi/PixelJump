package application.model;

import application.Block;

import java.util.LinkedList;

public class Creeper {
    private LinkedList<Position> coordinate;
    private int direction=1;
    private World world;
    public Creeper(LinkedList<Position> coordinate, World world){
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
    int esplosione=0;

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
        if(passo%4==0) {
            world.setMatrice_Principale(testa_i, testa_j, Block.VUOTO);
            world.setMatrice_Principale(corpo_i, corpo_j, Block.VUOTO);
            world.setMatrice_Principale(testa_i, testa_j + direction, Block.CREEPER);
            world.setMatrice_Principale(corpo_i, corpo_j + direction, Block.CREEPER);
            coordinate.set(0, new Position(testa_i, testa_j + direction));
            coordinate.set(1, new Position(corpo_i, corpo_j + direction));
        }
        passo+=1;
    }
//    public void kill(){
//        world.getNemici().remove(this);
//        world.setMatrice_Principale(coordinate.getFirst().i(), coordinate.getFirst().j(), Block.VUOTO);
//        world.setMatrice_Principale(coordinate.getLast().i(), coordinate.getLast().j(), Block.VUOTO);
//    }
}
