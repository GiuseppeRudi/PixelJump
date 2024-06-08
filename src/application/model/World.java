package application.model;

import application.Block;
import application.view.GamePanel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


public class World {

    private ScheduledFuture<?> futureEnemy;
    private ScheduledFuture<?> futureEnemy1;
    private ScheduledFuture<?> futureEnemy2;
    private int livello = 1;
//    private  ScheduledExecutorService executorService = null;
//    private  ScheduledExecutorService executorService1 = null;

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    private LinkedList<Position> coordinateNemici = new LinkedList<>();
    private LinkedList<Position> coordinateNemici1 = new LinkedList<>();
    private LinkedList<Position> coordinateNemico2 = new LinkedList<>();

    private  LinkedList<Position> coordinatePlayer = new LinkedList<>();

    private final Player player; //mette il player in posizione 0,0 , la grandezza della riga
    private final Enemy enemy ;
    private final Enemy enemy1 ;
    private final Enemy enemy2 ;
    private  LinkedList<Position> arrayVita = new LinkedList<>();

    private LinkedList<Enemy> arrayNemici = new LinkedList<>();
    private LinkedList<ScheduledFuture<?>> arrayFuture = new LinkedList<>();
    private List<String> viewPort;
//    private Thread threadNemico1 = new Thread(enemy);
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);


    public LinkedList<Position> getArrayVita() {
        return arrayVita;
    }

    World() throws IOException {
        coordinatePlayer.add(new Position(15,5)); //la posizione 0 ha la testa
        coordinatePlayer.add(new Position(16,5)); //la posizione 1 ha il corpo
        arrayVita.add(new Position(0,0));
        arrayVita.add(new Position(0,1));
        arrayVita.add(new Position(0,2));

        coordinateNemici.add(new Position(16,73));
        coordinateNemici1.add(new Position(16,23));

        coordinateNemico2.add(new Position(16,50));  //indice 0 corpo
        coordinateNemico2.add(new Position(15,50));  //indice 1 testa


        inizializzaMatricePrincipale();
//        threadNemico1.start();
        enemy = new Enemy(coordinateNemici,this);
        enemy1= new Enemy(coordinateNemici1,this);
        enemy2 = new Enemy(coordinateNemico2,this);


        arrayNemici.add(enemy);
        arrayNemici.add(enemy1);
        arrayNemici.add(enemy2);

        player = new Player(coordinatePlayer,this);

        futureEnemy=  executorService.scheduleAtFixedRate(enemy, 500, 500, TimeUnit.MILLISECONDS);
        futureEnemy1= executorService.scheduleAtFixedRate(enemy1, 200, 500, TimeUnit.MILLISECONDS);
        futureEnemy2= executorService.scheduleAtFixedRate(enemy2, 100, 700, TimeUnit.MILLISECONDS);
        arrayFuture.add(futureEnemy);
        arrayFuture.add(futureEnemy1);
        arrayFuture.add(futureEnemy2);

    }

    //serve per far muovere il player e aggiornare la sua posizione sulla matrice principale
    public void movePlayer() {
        //restituisce a seconda del movimento le nuove posizione della testa e del corpo del giocatore
        LinkedList<Position> newPosition = player.simulateMove();

//
            //devo verificare che le nuove posizioni sia valide
            int count = 0;
            for (Position p : newPosition) {
                //is valid position controlliamo sia il range del mondo e sia se andiamo contro muro,oggetti
                if (isValidPosition(p.i(), p.j()) && !isBlocco(p.i(), p.j())) {
                    count++;

                }
            }
//        System.out.println(count);
            if (count == newPosition.size()) {
                //getPosition prende da parametro un intero che se è 0 restituisce la position della testa 1 il corpo
                //  abbiamo prima controllato che possiamo cambiare posizione e se la possiamo cambiare
                // prima prendiamo le coordinate precedenti e ci mettiamo il blocco vuoto

                for (int k = 0; k < coordinatePlayer.size(); k++) {
                    matrice_Principale[player.getPosition(k).i()][player.getPosition(k).j()] = Block.VUOTO;
                }

                player.move();


                //qui dopo che si muove metto not moving cosi sta ferma e non va in quella direzione in loop
                //if (player.getDirection()==Settings.JUMP) updateDirection(Settings.NOT_MOVING);

                //aggiorniamo nella matrice principale la nuova posizione del personaggio


                for (int k = 0; k < coordinatePlayer.size(); k++) {
                    matrice_Principale[newPosition.get(k).i()][newPosition.get(k).j()] = Block.PERSONAGGIO;
                }



            }

    }

    public Player getPlayer() {
        return player;
    }

    private Block[][] matrice_Principale ;

    public void inizializzaMatricePrincipale() throws IOException {  //legge il file e quindi la matrice
        leggiFile file = new leggiFile();
        viewPort = file.leggi("src/application/resources/GraphicsManager/Levels/Livello1.txt");
//        System.out.println(viewPort.size());
//        System.out.println(viewPort.get(0).length());
        matrice_Principale= new Block[viewPort.size()][viewPort.getFirst().length()];
        for (int i=0; i<viewPort.size(); i++) {
            String riga = viewPort.get(i);
            for (int j=0; j<viewPort.getFirst().length();j++) {
                char cella = riga.charAt(j);
                if (cella=='a'){
                    matrice_Principale[i][j] =Block.VUOTO;
                }
                else if (cella=='b') {
                    matrice_Principale[i][j] =Block.TERRA;
                }
                else if (cella=='d') {
                    matrice_Principale[i][j] =Block.NEMICO;
                }
                else if (cella=='e') {
                    matrice_Principale[i][j] = Block.MURO;
                }
                else if (cella=='f'){
                    matrice_Principale[i][j] = Block.ERBA;
                }
                else if (cella=='g'){
                    matrice_Principale[i][j] = Block.SPECIALE;
                }
                else if (cella=='h'){
                    matrice_Principale[i][j] = Block.TUBO;
                }
                else if (cella=='i'){
                    matrice_Principale[i][j] = Block.BARILE;
                }
                else if (cella=='j'){
                    matrice_Principale[i][j] = Block.FINE;
                }
                else if (cella=='k'){
                    matrice_Principale[i][j] = Block.PORTALE;
                }
                else if (cella=='l'){
                    matrice_Principale[i][j] = Block.PONTE;
                }
                else if (cella=='m'){
                    matrice_Principale[i][j] = Block.MORTE;
                }
                else if (cella=='n'){
                    matrice_Principale[i][j] = Block.TELETRASPORTO;
                }
                else if (cella=='y')
                {
                    matrice_Principale[i][j] = Block.NEMICO;
                }
            }
        }
        matrice_Principale[coordinatePlayer.getFirst().i()][coordinatePlayer.getFirst().j()]=Block.PERSONAGGIO;
        matrice_Principale[coordinatePlayer.get(1).i()][coordinatePlayer.get(1).j()]=Block.PERSONAGGIO;
    }

    public Block[][] getMatrice_Principale() {
        return matrice_Principale;
    }

    public void stampamatrice() {
        for (int i=0; i<matrice_Principale.length;i++) {
            for(int j=0; j<matrice_Principale[i].length;j++) {
                System.out.print(matrice_Principale[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void updateDirection(int direction) {
        //CHIAMO LA FUNZIONE PER AGGIORNARE LA DIREZIONE DEL PLAYER
        player.updateDirection(direction);
        //stampamatrice();
    }

    private boolean isType(int i,int j,Block b) {
        if(isValidPosition(i,j))
            return matrice_Principale[i][j] == b;
        return false;
    }

     public boolean isValidPosition(int i, int j) {
        return i>=0 && i<viewPort.size() && j>=0 && j<viewPort.getFirst().length();
    }

    //usata per inserire la moneta e inserire blocco gia usato
    public void setMatrice_Principale(int i,int j,Block block) {
        matrice_Principale[i][j]=block;
    }

    public boolean isWall(int i, int j) {return isType(i,j,Block.MURO);}
    public boolean isPlayer(int i,int j) {return isType(i,j,Block.PERSONAGGIO);}
    public boolean isTerra(int i, int j) { return isType(i,j,Block.TERRA);}
    public boolean isErba(int i, int j) { return isType(i,j,Block.ERBA);}
    public boolean isCoin(int i,int j) {return isType(i,j,Block.MONETA);}
    public boolean isSpeciale(int i, int j) { return isType(i,j,Block.SPECIALE);}
    public boolean isFine(int i, int j) { return isType(i,j,Block.FINE);}
    public boolean isBarile(int i, int j) { return isType(i,j,Block.BARILE);}
    public boolean isTubo(int i, int j) { return isType(i,j,Block.TUBO);}
    public boolean isMorte(int i, int j) {return isType(i,j,Block.MORTE);}
    public boolean isUsato(int i, int j) {return isType(i,j,Block.USATO);}
    public boolean isBlocco(int i , int j ) { return isWall(i,j) || isErba(i,j) || isSpeciale(i,j) || isTerra(i,j) || isTubo(i,j) || isBarile(i,j) || isFine(i,j) || isUsato(i,j);}
    public boolean isPonte(int i, int j) {return isType(i,j,Block.PONTE);}
    public boolean isPortale(int i, int j) {return isType(i,j,Block.PORTALE);}
    public boolean isTeletrasporto(int i, int j) {return isType(i,j,Block.TELETRASPORTO);}
    public boolean isNemico(int i, int j) {return isType(i,j,Block.NEMICO);}
    public void restart()  {
        player.setVita(player.getVita() - 1);

        if (player.getVita() == 0) {
            System.out.println("SEI MORTO");
        }

        player.setProgresso(0);


    }

    public int getLivello() {
        return livello;
    }

    public List<String> getViewPort() {
        return viewPort;
    }

    public synchronized void moveNemico(Enemy enemy) {

        //restituisce a seconda del movimento le nuove posizione della testa e del corpo del giocatore
        LinkedList<Position> newPosition = enemy.simulateMove();


        //devo verificare che le nuove posizioni sia valide
        int count =0;
        for (Position p : newPosition) {
            //is valid position controlliamo sia il range del mondo e sia se andiamo contro muro,oggetti
            if(isValidPosition(p.i(),p.j()) && !isBlocco(p.i(),p.j()) && !isMorte(p.i()+1,p.j())) {
                count++;

            }
        }
//        System.out.println(count);
//        System.out.println(newPosition.size());


        if (count==newPosition.size()) {
            //getPosition prende da parametro un intero che se è 0 restituisce la position della testa 1 il corpo
            //  abbiamo prima controllato che possiamo cambiare posizione e se la possiamo cambiare
            // prima prendiamo le coordinate precedenti e ci mettiamo il blocco vuoto

            for (int k=0; k<enemy.getCoordinate().size();k++)
            {
//                System.out.println("4844848484848448");
//                System.out.println(enemy.getPosition(0));
//                System.out.println("484848484448");
                matrice_Principale[enemy.getPosition(k).i()][enemy.getPosition(k).j()] = Block.VUOTO;
            }

            enemy.move();

            //qui dopo che si muove metto not moving cosi sta ferma e non va in quella direzione in loop
            //if (player.getDirection()==Settings.JUMP) updateDirection(Settings.NOT_MOVING);

            //aggiorniamo nella matrice principale la nuova posizione del personaggio
            for(int k=0; k<enemy.getCoordinate().size();k++) {
                matrice_Principale[newPosition.get(k).i()][newPosition.get(k).j()] = Block.NEMICO;
            }
        }
        //se la posizione dove vuole andare non va piu bene torna inditro
        else {
            enemy.changeMovimento();
        }

        }


    public void trovaNemico(int nemicoI, int nemicoJ) {
        //array di nemici

        for (int i = 0; i < arrayNemici.size(); i++) {

            //getlast in quello da 2 blocchi è la testa mentre nel blocco uno getlast e getfirst non fa differenza
            if(arrayNemici.get(i).getCoordinate().getLast().i()==nemicoI && arrayNemici.get(i).getCoordinate().getLast().j()==nemicoJ)
            {

                if (arrayFuture.get(i) != null) {
                    arrayFuture.get(i).cancel(true); // Cancella l'esecuzione futura e interrompe se attualmente in esecuzione.
                }

                for(int j=0 ; j<arrayNemici.get(i).getCoordinate().size();j++)
                {
                    this.setMatrice_Principale(arrayNemici.get(i).getCoordinate().get(j).i(),arrayNemici.get(i).getCoordinate().get(j).j(),Block.VUOTO);
                }
            }
        }
    }




}
