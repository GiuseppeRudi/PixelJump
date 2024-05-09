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
}
