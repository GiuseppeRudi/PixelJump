package application.model;

public class Player  extends AbstractPlayer{

    private int direction = Settings.NOT_MOVING;
    public Player(Position position, int filtroSizeRiga, int filtroSizeColonna) { //costruttore player
        super(position,filtroSizeRiga,filtroSizeColonna); //richiama la super classe
    }


    public void updateDirection(int direction) {
        this.direction = direction;
    }
}
