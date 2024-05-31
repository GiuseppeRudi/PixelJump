package application.model;

//questa classe serve per creare piu player , nemici che utilizzano questo modello
// generale su cui altri classi che la estendono possono usare

import application.Block;
import application.Sound;
import application.controller.ControllerPlayer;

import java.io.IOException;
import java.util.LinkedList;

public abstract class AbstractPlayer {

    private boolean isJumping = false;
    private boolean isFalling = false;

    private int indice;

    private LinkedList<Position> coordinate;
    protected World world ;

    public AbstractPlayer(LinkedList<Position> coordinate,World world) { //super classe di player

        this.coordinate = coordinate;
        this.world= world;
    }

    public AbstractPlayer(LinkedList<Position> coordinate,World world,int indice) { //super classe di player
        this.indice= indice;
        this.coordinate = coordinate;
        this.world= world;
    }

    public void setCoordinate(LinkedList<Position> coordinate) {
        this.coordinate = coordinate;
    }

    public LinkedList<Position> getCoordinate() {
        return coordinate;
    }

    Position getPosition(int i) {
        return coordinate.get(i);
    }


    protected LinkedList<Position> simulateMove(int direction, int tipo)   {

        LinkedList<Position> newCoordinate = new LinkedList<>();

        if(tipo==1) {
            int testa_i = coordinate.getFirst().i();
            int testa_j = coordinate.getFirst().j();

            int corpo_i = coordinate.get(1).i();
            int corpo_j = coordinate.get(1).j();



            if (ControllerPlayer.getPressed().contains(Settings.JUMP)) {
                if (!isJumping && !isFalling) {
                    //questo serve poiche sto gia cadendo o sto saltando non posso saltare di nuovo
                    isJumping = true;
                }
            }
            if(world.isNemico(corpo_i+1,corpo_j))
            {
                System.out.println("MUORI");
                kill();
                isJumping=true;            }

            if (isJumping) {
                testa_i = testa_i - 1;
                corpo_i = corpo_i - 1;
            } else if (isFalling) {
                testa_i = testa_i + 1;
                corpo_i = corpo_i + 1;
            }

            if (ControllerPlayer.getPressed().contains(Settings.MOVE_LEFT)) {
                if ((!isJumping && !isFalling) || (!world.isBlocco(testa_i, testa_j - 1) && !world.isBlocco(corpo_i, corpo_j - 1) && !world.isBlocco(testa_i - 1, testa_j - 1) && world.isValidPosition(testa_i, testa_j - 1))) {

                    testa_j = testa_j - 1;
                    corpo_j = corpo_j - 1;
                }
            }

            if (ControllerPlayer.getPressed().contains(Settings.MOVE_RIGHT)) {

                if ((!isJumping && !isFalling) || (!world.isBlocco(testa_i, testa_j + 1) && !world.isBlocco(corpo_i, corpo_j + 1) && !world.isBlocco(testa_i - 1, testa_j + 1) && world.isValidPosition(testa_i, testa_j + 1))) {

                    testa_j = testa_j + 1;
                    corpo_j = corpo_j + 1;
                }
            }


            //        System.out.println(corpo_i);
            //        System.out.println(corpo_j);



            newCoordinate.add(new Position(testa_i, testa_j));
            newCoordinate.add(new Position(corpo_i, corpo_j));
        }

        else if(tipo==0)
        {
            System.out.println("++++");
            System.out.println(coordinate);
            System.out.println("++++");
            int corpo_i= coordinate.getFirst().i();
            int corpo_j= coordinate.getFirst().j();
            //nemico di un blocco
            if(coordinate.size()==1)
            {

//                System.out.println("++++++");
//                System.out.println(direction);
//                System.out.println("++++++");
                if(direction==Settings.MOVE_LEFT)
                {
                    corpo_j--;
                }
                if(direction==Settings.MOVE_RIGHT)
                {
                    corpo_j++;
                }
            }

            newCoordinate.add((new Position(corpo_i,corpo_j)));
        }

        return newCoordinate;}

    private void kill() {
        world.stopEnemy(indice);
        world.setMatrice_Principale(getCoordinate().getFirst().i(),getCoordinate().getFirst().j(), Block.VUOTO);
    }


    //la direzione che ce qui viene presa  da default not moving e che puo essere aggiornata ogni volta che ce un update directions

    protected LinkedList<Position> move ( int direction, int tipo)   {
        //qui aggiorniamo la linked list con le nuove coordinate che abbiamo precedentemnete controllato nel movePlayer in world
        coordinate = simulateMove(direction,tipo);

//        if(world.isCoin(coordinate.getLast().i(), coordinate.getLast().j()))
//        {
//            world.getPlayer().setMoneta();
//
//        }
        //tipo 1 = personaggio ; tipo = 0 nemici
        if(tipo==1) {
            if (world.isNemico(coordinate.getLast().i(), coordinate.getLast().j())) {

                world.restart();
                coordinate.set(0, new Position(15, 5));
                coordinate.set(1, new Position(16, 5));
            } else if (world.isMorte(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
                //se cade nel vuoto muore perde una vita ne ha 3 , quando perde tutte le vite muore del tutto

                world.restart();
                coordinate.set(0, new Position(15, 5));
                coordinate.set(1, new Position(16, 5));

            }
        }
        else if(tipo==0)
        {
            System.out.println("sono entrato");
            if(world.isPlayer(coordinate.getFirst().i(),coordinate.getFirst().j()))
            {


                world.restart();
                LinkedList<Position> coordinatePlayer = new LinkedList<>();
                coordinatePlayer.add(new Position(15,5));
                coordinatePlayer.add(new Position(16,5));
                world.getPlayer().setCoordinate(coordinatePlayer);

            }
        }
//            if (world.isMorte(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
//                //se cade nel vuoto muore perde una vita ne ha 3 , quando perde tutte le vite muore del tutto
//
//                coordinate.clear();
//                coordinate.add(new Position(15, 5));
//                coordinate.add(new Position(16, 5));
//                world.restart();
//
//            }

//            else if (world.isNemico(coordinate.getLast().i() , coordinate.getLast().j())) {
//                //se cade nel vuoto muore perde una vita ne ha 3 , quando perde tutte le vite muore del tutto
//
//                coordinate.clear();
//                coordinate.add(new Position(15, 5));
//                coordinate.add(new Position(16, 5));
//                world.restart();
//
//            }


//        if (direction != Settings.NOT_MOVING) {
//            if (direction == Settings.JUMP) {
//                Sound salta = new Sound("jump.wav");
//                salta.play();
//            }
//            //questo serve poiche quando salti controlliamo se lui atterra su un blocco e a seconda del blocco fa
//            //coordiante get last prendo le coordinate del corpo che gia sono state controllate e vedo per vedere che blocco ce sotto le gambe
//            if (Game.getInstance().getWorld().isErba(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
//                Sound cammina = new Sound("grass.wav");
//                cammina.play();
//            } else if (Game.getInstance().getWorld().isWall(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
//                Sound cammina = new Sound("wood.wav");
//                cammina.play();
//            } else if (Game.getInstance().getWorld().isSpeciale(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
//                Sound cammina = new Sound("sand.wav");
//                cammina.play();
//            }
//        }
        return coordinate;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    abstract void move ();

    // perche questo simulateMove di player è diverso da simulateplayer di abstract
    abstract LinkedList<Position> simulateMove () throws IOException;
}


