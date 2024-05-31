package application.model;

import application.Block;
import application.controller.ControllerPlayer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends  AbstractPlayer implements Runnable {

//    private int ultimoMovimento = 1;
//    private int passiNellaStessaDirezione = 0;
//    private Random random = new Random();

    static int indice =0;

    int direction=1;
    int tipo=0;
    private World world;
    private LinkedList<Position> coordinateNemico ;
    public Enemy(LinkedList<Position> coordinate , World world) {
        super( coordinate, world,indice);
        indice++;
        this.world=world;
        this.coordinateNemico = coordinate;
        System.out.println(coordinateNemico);

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
        System.out.println(coordinateNemico);
        world.setCoordinateNemici(coordinateNemico);
        world.moveNemico(this);
        System.out.println("fine");

    }

//    public int movimentoRandom() {
//        // Decide se cambiare direzione
//        if (passiNellaStessaDirezione >= 2 + random.nextInt(3)) { // Dopo 2-4 passi nella stessa direzione
//            ultimoMovimento *= -1; // Cambia direzione
//            passiNellaStessaDirezione = 0;
//        } else {
//            passiNellaStessaDirezione++;
//        }
//        return ultimoMovimento;
//    }

    public void changeMovimento() {
        if(direction==1)
        {
            direction=-1;
        }
        else direction=1;
    }

    public int getDirection() {
        return direction;
    }


}
