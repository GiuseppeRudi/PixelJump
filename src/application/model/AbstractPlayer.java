package application.model;

//questa classe serve per creare piu player , nemici che utilizzano questo modello
// generale su cui altri classi che la estendono possono usare

import java.util.LinkedList;

public abstract class AbstractPlayer {

    private LinkedList<Position> coordinate;

    public AbstractPlayer(LinkedList<Position> coordinate) { //super classe di player
        this.coordinate= coordinate;
    }


    LinkedList<Position> getPosition()
    {return coordinate;}

    protected LinkedList<Position> simulateMove(int direction)
    {
        int testa_i = coordinate.getFirst().i();
        int testa_j = coordinate.getFirst().j();

        int corpo_i = coordinate.get(1).i();
        int corpo_J = coordinate.get(1).j();

        // per il momento commetanto serve per controllare se il gioco va in contro a qualcosa
//
//        switch (direction) {
//            case Settings.MOVE_LEFT -> x = (worldSize+x-1) % worldSize;
//            case Settings.MOVE_RIGHT -> x = (x+1) % worldSize;
//            case Settings.JUMP -> y = (worldSize+y-1) % worldSize;
//
//        }
        return coordinate;
    }

    //la direzione che ce qui viene presa  da default not moving e che puo essere aggiornata ogni volta che ce un update directions



    // perche questo simulateMove di player è diverso da simulateplayer di abstract
    abstract LinkedList<Position> simulateMove();
}
