package application.model;

import java.util.LinkedList;

public class Player  extends AbstractPlayer{

    private int progresso=0;

    private LinkedList<Position> coordinatePlayer = new LinkedList<>();

    private int direction = Settings.NOT_MOVING;


    public Player(LinkedList<Position> coordinate) { //costruttore player
        super(coordinate); //richiama la super classe

    }


    @Override
    public void move(){
        super.move(direction);
        if (direction==Settings.MOVE_RIGHT && progresso<(Settings.World_Size_Colonna-Settings.Filtro_Size_Colonna) && super.getPosition(0).j()>=Settings.Filtro_Size_Colonna+progresso-15)
        {
            progresso+= (super.getPosition(0).j()-(Settings.Filtro_Size_Colonna+progresso-15));
        }
        else if(direction==Settings.MOVE_LEFT && progresso>0 && super.getPosition(0).j()<=Settings.Filtro_Size_Colonna+progresso-21)
        {
            progresso-= ((Settings.Filtro_Size_Colonna+progresso-21)-super.getPosition(0).j());
        }

    }

    public int getProgresso() {
        return progresso;
    }

    public int getDirection() {
        return direction;
    }

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
