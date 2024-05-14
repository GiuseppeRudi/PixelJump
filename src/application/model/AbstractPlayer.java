package application.model;

//questa classe serve per creare piu player , nemici che utilizzano questo modello
// generale su cui altri classi che la estendono possono usare

import java.util.LinkedList;

public abstract class AbstractPlayer {

    private LinkedList<Position> coordinate;

    public AbstractPlayer(LinkedList<Position> coordinate) { //super classe di player
        this.coordinate= coordinate;
    }


    Position getPosition(int i)
    {
        return coordinate.get(i);
    }


    protected LinkedList<Position> simulateMove(int direction)
    {
        int testa_i = coordinate.getFirst().i();
        int testa_j = coordinate.getFirst().j();

        int corpo_i = coordinate.get(1).i();
        int corpo_j = coordinate.get(1).j();

        switch (direction) {
            case Settings.MOVE_LEFT -> { testa_j= testa_j-1; corpo_j=corpo_j-1;}
            case Settings.MOVE_RIGHT -> { testa_j= testa_j+1; corpo_j=corpo_j+1;}
            case Settings.JUMP -> { testa_i= testa_i-4;  corpo_i= corpo_i-4;}
        }

//        System.out.print(testa_i);
//        System.out.println(corpo_i);
        LinkedList<Position> newCoordinate = new LinkedList<>();
        newCoordinate.add(new Position(testa_i,testa_j));
        newCoordinate.add(new Position(corpo_i,corpo_j));
        return newCoordinate;
    }

    //la direzione che ce qui viene presa  da default not moving e che puo essere aggiornata ogni volta che ce un update directions

    protected void move(int direction)
    {
        //qui aggiorniamo la linked list con le nuove coordinate che abbiamo precedentemnete controllato nel movePlayer in world
        coordinate = simulateMove(direction);
    }

    abstract void move();

    // perche questo simulateMove di player è diverso da simulateplayer di abstract
    abstract LinkedList<Position> simulateMove();
}
