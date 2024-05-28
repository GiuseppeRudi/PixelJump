package application.model;

import application.Block;
import application.controller.ControllerPlayer;

import java.io.IOException;
import java.util.LinkedList;

public class Player  extends AbstractPlayer{

    private int progresso=0;
    private int moneta =0;


    int vita = 3;

    private LinkedList<Position> coordinatePlayer = new LinkedList<>();

    private int direction = Settings.NOT_MOVING;
    private World world;


    public Player(LinkedList<Position> coordinate,World world) { //costruttore player
        super(coordinate,world);//richiama la super classe
        this.world=world;

    }

    public static int cont =0;

    @Override
    public void move(){
        coordinatePlayer=super.move(direction);

        //SPIEGARE PERCHE COSI NON FUNZIONA
//        if (direction==Settings.MOVE_RIGHT && progresso<(Settings.World_Size_Colonna-Settings.Filtro_Size_Colonna) && super.getPosition(0).j()>=Settings.Filtro_Size_Colonna+progresso-15)
//        {
//            progresso+= (super.getPosition(0).j()-(Settings.Filtro_Size_Colonna+progresso-15));
//        }
//        else if(direction==Settings.MOVE_LEFT && progresso>0 && super.getPosition(0).j()<=Settings.Filtro_Size_Colonna+progresso-21)
//        {
//            progresso-= ((Settings.Filtro_Size_Colonna+progresso-21)-super.getPosition(0).j());
//        }
        if (ControllerPlayer.getPressed().contains(Settings.MOVE_RIGHT) && progresso<(world.getViewPort().getFirst().length()-Settings.Filtro_Size_Colonna) && super.getPosition(0).j()>=Settings.Filtro_Size_Colonna+progresso-15) {
            progresso+= (super.getPosition(0).j()-(Settings.Filtro_Size_Colonna+progresso-15));
        }
        else if(ControllerPlayer.getPressed().contains(Settings.MOVE_LEFT) && progresso>0 && super.getPosition(0).j()<=Settings.Filtro_Size_Colonna+progresso-21) {
            progresso-= ((Settings.Filtro_Size_Colonna+progresso-21)-super.getPosition(0).j());
        }

        //Questo controllo serve per verificare che se non stiamo saltando o cadendo e ce un blocco vuoto allora cadiamo
        if (!isJumping() && !isFalling() && !world.isBlocco(coordinatePlayer.getLast().i()+1,coordinatePlayer.getLast().j())) setFalling(true);

        if(isJumping())
        {
            if(cont<3)
            {
                //se prima di completare il salto completo trovo un blocco sopra allora finisce il salto e inizia la caduta
                if(world.isBlocco(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getLast().j()))
                {
                    if(world.isSpeciale(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getLast().j()))
                    {
                        //moneta principale
                        world.setMatrice_Principale(coordinatePlayer.getFirst().i()-2,coordinatePlayer.getLast().j(), Block.MONETA);
                        //blocco gia usato
                        world.setMatrice_Principale(coordinatePlayer.getFirst().i()-1,coordinatePlayer.getLast().j(), Block.USATO);
                    }
                    setJumping(false);
                    setFalling(true);
                }

                else cont++;
            }
            else
            {
                setJumping(false);
                setFalling(true);
            }
        }

        if(isFalling())
        {
            //se ce un blocco sotto di me , fermo la caduta
            if(world.isBlocco(coordinatePlayer.getLast().i()+1,coordinatePlayer.getLast().j()))
            {
                setFalling(false);
                cont =0;
            }

        }



    }

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        this.progresso = progresso;
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
    public LinkedList<Position> simulateMove()   {
        return super.simulateMove(direction);
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getMoneta() {
        return moneta;
    }
    public void setMoneta()
    {
        moneta++;
    }

}
