package application.model;

import application.Block;
import application.controller.ControllerPlayer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends  AbstractPlayer implements Runnable {

    int direction=0;
    int tipo=0;
    private World world;
    private LinkedList<Position> coordinateNemico ;
    public Enemy(LinkedList<Position> coordinate , World world) {
        super( coordinate, world);
        this.coordinateNemico = coordinate;
        this.world=world;
    }

    @Override
    public void move(){
        coordinateNemico=super.move(direction,tipo);
    }

    @Override
    public LinkedList<Position> simulateMove()   {
        return super.simulateMove(direction,tipo);
    }


    @Override
    public void run() {
        direction=movimentoRandom();
        world.setCoordinateNemici(coordinateNemico);
        world.moveNemico();

    }

    public int movimentoRandom()
    {
        Random random = new Random();
        return random.nextBoolean() ? 1 : -1;

    }

}
