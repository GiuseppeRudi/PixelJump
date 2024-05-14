package application.model;

import java.util.LinkedList;

public class Player  extends AbstractPlayer{

    private LinkedList<Position> coordinatePlayer = new LinkedList<>();

    private int direction = Settings.NOT_MOVING;


    public Player(LinkedList<Position> coordinate) { //costruttore player
        super(coordinate); //richiama la super classe

    }


    @Override
    public void move(){super.move(direction);}



    public void updateDirection(int direction) {
        //aggiorno la direction del player
//        System.out.println("vecchia direzione: " );
//        System.out.println(direction);
        this.direction = direction;

//        System.out.println("nuova direzione: " );
//        System.out.println(direction);
    }

    //la direzione che ce qui viene presa  da default not moving e che puo essere aggiornata ogni volta che ce un update directions
    @Override
    public LinkedList<Position> simulateMove() {
        return super.simulateMove(direction);
    }
}
