package application.model;

//questa classe serve per creare piu player , nemici che utilizzano questo modello
// generale su cui altri classi che la estendono possono usare

import application.Sound;

import java.util.LinkedList;

public abstract class AbstractPlayer {


    private boolean isJumping1 = false;
    private boolean isFalling1 = false;

    private boolean isJumping = false;
    private boolean isFalling = false;

    private int cont;
    private int cont1;
    private LinkedList<Position> coordinate;
    private World world ;

    public AbstractPlayer(LinkedList<Position> coordinate) { //super classe di player
        this.coordinate = coordinate;
        this.world= world;
    }


    Position getPosition(int i) {
        return coordinate.get(i);
    }


    protected LinkedList<Position> simulateMove1(int direction) {


        int testa_i = coordinate.getFirst().i();
        int testa_j = coordinate.getFirst().j();

        int corpo_i = coordinate.get(1).i();
        int corpo_j = coordinate.get(1).j();


        if (direction == Settings.MOVE_LEFT)
        {
//            if(isFalling1 || isJumping1)
//            {
//                testa_j = testa_j - 1;
//                corpo_j = corpo_j - 1;
//            }
            testa_j = testa_j - 1;
            corpo_j = corpo_j - 1;
        }
        else if (direction == Settings.MOVE_RIGHT) {
//            if(isJumping1 || isFalling1)
//            {
//                testa_j = testa_j + 1;
//                corpo_j = corpo_j + 1;
//            }
            testa_j = testa_j + 1;
            corpo_j = corpo_j + 1;
        }
        else if (direction == Settings.JUMP)
            isJumping1=true;


        System.out.println(cont1);


        if (cont1==0)
        {
            isFalling1=false;
        }
        if (cont1 == 5) {
            isFalling1 = true;
            isJumping1= false;
        }

        if (isJumping1 && cont1 < 5) {
            testa_i = testa_i - 1;
            corpo_i = corpo_i - 1;
            cont1++;
        }


        if (isFalling1 && cont1 <= 5) {
            testa_i = testa_i + 1;
            corpo_i = corpo_i + 1;
            cont1--;
        }


//        System.out.print(testa_i);
//        System.out.println(corpo_i);
        LinkedList<Position> newCoordinate = new LinkedList<>();
        newCoordinate.add(new Position(testa_i, testa_j));
        newCoordinate.add(new Position(corpo_i, corpo_j));

        System.out.println("************************");
        System.out.println(newCoordinate.getFirst().i());
        System.out.println(newCoordinate.getFirst().j());
        System.out.println("************************");
        return newCoordinate;
    }

    protected LinkedList<Position> simulateMove(int direction) {


        int testa_i = coordinate.getFirst().i();
        int testa_j = coordinate.getFirst().j();

        int corpo_i = coordinate.get(1).i();
        int corpo_j = coordinate.get(1).j();


        if (direction == Settings.MOVE_LEFT) {
//            if(isJumping || isFalling){
//
//                testa_j = testa_j - 1;
//                corpo_j = corpo_j - 1;
//            }
            testa_j = testa_j - 1;
            corpo_j = corpo_j - 1;
        }
        else if (direction == Settings.MOVE_RIGHT) {
//            if(isJumping || isFalling){
//
//                testa_j = testa_j + 1;
//                corpo_j = corpo_j + 1;
//            }

            testa_j = testa_j + 1;
            corpo_j = corpo_j + 1;
        }
        else if (direction == Settings.JUMP)
            isJumping=true;


        System.out.println(cont);


        if (cont==0)
        {
            isFalling=false;
        }

        if (cont == 5) {
            isFalling = true;
            isJumping= false;
        }

        if (isJumping && cont < 5) {
            testa_i = testa_i - 1;
            corpo_i = corpo_i - 1;
            cont++;
        }


        if (isFalling && cont <= 5 ) {
            testa_i = testa_i + 1;
            corpo_i = corpo_i + 1;
            cont--;
        }



//        System.out.print(testa_i);
//        System.out.println(corpo_i);

        LinkedList<Position> newCoordinate = new LinkedList<>();
        newCoordinate.add(new Position(testa_i, testa_j));
        newCoordinate.add(new Position(corpo_i, corpo_j));

        System.out.println("************************");
        System.out.println(newCoordinate.getFirst().i());
        System.out.println(newCoordinate.getFirst().j());
        System.out.println("************************");
        return newCoordinate;}

        //la direzione che ce qui viene presa  da default not moving e che puo essere aggiornata ogni volta che ce un update directions

        protected void move ( int direction)
        {
            //qui aggiorniamo la linked list con le nuove coordinate che abbiamo precedentemnete controllato nel movePlayer in world
            coordinate = simulateMove1(direction);

            if (direction != Settings.NOT_MOVING) {
                if (direction == Settings.JUMP) {
                    Sound salta = new Sound("jump.wav");
                    salta.play();
                }
                //questo serve poiche quando salti controlliamo se lui atterra su un blocco e a seconda del blocco fa
                //coordiante get last prendo le coordinate del corpo che gia sono state controllate e vedo per vedere che blocco ce sotto le gambe
                if (Game.getInstance().getWorld().isErba(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
                    Sound cammina = new Sound("grass.wav");
                    cammina.play();
                } else if (Game.getInstance().getWorld().isWall(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
                    Sound cammina = new Sound("wood.wav");
                    cammina.play();
                } else if (Game.getInstance().getWorld().isSpeciale(coordinate.getLast().i() + 1, coordinate.getLast().j())) {
                    Sound cammina = new Sound("sand.wav");
                    cammina.play();
                }
            }
        }

        abstract void move ();

        // perche questo simulateMove di player è diverso da simulateplayer di abstract
        abstract LinkedList<Position> simulateMove ();
    }


