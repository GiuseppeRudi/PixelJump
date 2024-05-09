package application.model;

import java.util.LinkedList;

public class Player  extends AbstractPlayer{

    private LinkedList<Position> coordinatePlayer = new LinkedList<>();

    private int direction = Settings.NOT_MOVING;


    public Player(LinkedList<Position> coordinate) { //costruttore player
        super(coordinate); //richiama la super classe

    }



    public void updateDirection(int direction) {
        this.direction = direction;
    }
}
