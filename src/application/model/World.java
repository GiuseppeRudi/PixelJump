package application.model;

import application.Block;
import application.audio.Sound;
import application.controller.Controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class World {

    public World(int liv, int lives){
        this.liv=liv;
        player.setLives(lives);
        coordinatePlayer.add(getPlayerStartPosition(liv).getFirst());
        coordinatePlayer.add(getPlayerStartPosition(liv).getLast());
        inizializzaMatricePrincipale();
    }

    public List<Position> getPlayerStartPosition(int liv) {
        if(liv==1 || liv==2){
            return List.of(new Position(15,5),new Position(16,5));
        }
        return List.of(new Position(9,1),new Position(10,1));
    }

    private boolean tel1;
    private boolean tel2;

    public boolean isTel1() {
        return tel1;
    }

    public boolean isTel2() {
        return tel2;
    }

    public void setTel1(boolean tel1) {
        this.tel1 = tel1;
    }

    public void setTel2(boolean tel2) {
        this.tel2 = tel2;
    }

    private LinkedList<Position> newPosition;
    private boolean morte=false;
    public void setMorte(boolean morte){
        this.morte=morte;
    }
    //serve per far muovere il player e aggiornare la sua posizione sulla matrice principale
    public synchronized void movePlayer() {
        if(velP%ab==0) {
            //restituisce a seconda del movimento le nuove posizione della testa e del corpo del giocatore
            newPosition = player.simulateMove();

            //devo verificare che le nuove posizioni sia valide
            int count = 0;
            for (Position p : newPosition) {
                //is valid position controlliamo sia il range del mondo e sia se andiamo contro muro,oggetti
                //            System.out.println(p.i()+" "+p.j());
                if (isValidPosition(p.i(), p.j()) && !isBlocco(p.i(), p.j())) {
                    count++;
                }
            }
            //        System.out.println(count);
            if (count == newPosition.size()) {
                telCheck();
                //getPosition prende da parametro un intero che se è 0 restituisce la position della testa 1 il corpo
                //  abbiamo prima controllato che possiamo cambiare posizione e se la possiamo cambiare
                // prima prendiamo le coordinate precedenti e ci mettiamo il blocco vuoto
                for (int k = 0; k < coordinatePlayer.size(); k++) {
                    matrice_Principale[player.getPosition(k).i()][player.getPosition(k).j()] = Block.VUOTO;
                }
                player.move();
                //aggiorniamo nella matrice principale la nuova posizione del personaggio

                if (!morte) {
                    for (int k = 0; k < coordinatePlayer.size(); k++) {
                        matrice_Principale[newPosition.get(k).i()][newPosition.get(k).j()] = Block.PERSONAGGIO;
                    }
                } else {
                    morte = false;
                    for (int k = 0; k < coordinatePlayer.size(); k++) {
                        matrice_Principale[player.getCoordinatePlayer().get(k).i()][player.getCoordinatePlayer().get(k).j()] = Block.PERSONAGGIO;
                    }
                }
            }
        }
        velP+=1;
    }
    private int velP=0;
    private int velN=0;
    private int ab=2;

    public void setAb(int ab) {
        this.ab = ab;
    }
    public synchronized void moveNemici(){
        if(velN%2==0) {
            for (Object o : nemici) {
                if (o instanceof MiniZombie) {
                    ((MiniZombie) o).move();
                } else if (o instanceof Creeper) {
                    ((Creeper) o).move();
                } else if (o instanceof Skeleton) {
                    ((Skeleton) o).move();
                }
                if(morte) break;
            }
        }
        velN+=1;
    }
    private int add=1;
    private int blockToRemove=69;
    private int blockToAdd=72;
    private int blockCount=0;
    private int moved=0;
    public void moveBlocks(){
        if(blockCount%4==0) {
            if(moved!=0){
                moved=0;
                Controller.getPressed().remove(add);
            }
            if(matrice_Principale[17][blockToAdd]==Block.PERSONAGGIO){
                Controller.getPressed().add(add);
                moved=add;
            }
            matrice_Principale[17][blockToRemove] = Block.VUOTO;
            matrice_Principale[17][blockToAdd] = Block.TERRA;
            blockToAdd+=add;
            blockToRemove+=add;
            if(blockToAdd==74 || blockToAdd==66){
                int temp=blockToRemove;
                blockToRemove=blockToAdd;
                blockToAdd=temp;
                add=-add;
            }
        }
        blockCount++;
    }
    private void telCheck() {
        if (viewPort.get(newPosition.getFirst().i()).charAt(newPosition.getFirst().j())=='n' && viewPort.get(newPosition.getLast().i()).charAt(newPosition.getLast().j())=='n'){
            if(newPosition.getFirst().j()==126 && (player.getPosition(0).j()!=126 || player.getPosition(0).i()!=15)) {
                tel1=true;
                newPosition.set(0, new Position(10, 130));
                newPosition.set(1, new Position(11, 130));
            } else if(newPosition.getFirst().j()==130 && (player.getPosition(0).j()!=130 || player.getPosition(0).i()!=10)){
                tel2=true;
                newPosition.set(0, new Position(15, 126));
                newPosition.set(1, new Position(16, 126));
            }
        }
    }

    private Block[][] matrice_Principale;

//    public Block[][] getMatrice_Principale() {
//        return matrice_Principale;
//    }

    private  LinkedList<Position> coordinatePlayer = new LinkedList<>();

    public Player getPlayer() {
        return player;
    }
    private  LinkedList<Object> nemici = new LinkedList<>();

    public LinkedList<Object> getNemici() {
        return nemici;
    }

    private List<String> viewPort;

    public List<String> getViewPort() {
        return viewPort;
    }

    public void setMatrice_Principale(int i, int j,Block block) {
        matrice_Principale[i][j]=block;
    }

    private final int liv;

    public int getLiv() {
        return liv;
    }

    private final Player player = new Player(coordinatePlayer,this);
    public void inizializzaMatricePrincipale(){  //legge il file e quindi la matrice
        file file = new file();
        try{
            viewPort = file.leggi("src/application/resources/GraphicsManager/Levels/Livello"+liv+".txt");
        } catch (IOException e){
            e.printStackTrace();
        }
        matrice_Principale =new Block[viewPort.size()][viewPort.getLast().length()];
//        System.out.println(viewPort.size());
//        System.out.println(viewPort.get(0).length());
        for (int i = 0; i<viewPort.size(); i++) {
            String riga = viewPort.get(i);
            for (int j = 0; j<riga.length(); j++) {
                char cella = riga.charAt(j);
                if (cella=='a'){
                    matrice_Principale[i][j] =Block.VUOTO;
                }
                else if (cella=='b') {
                    matrice_Principale[i][j] =Block.TERRA;
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
//                else if (cella=='j'){
//                    matrice_Principale[i][j] = Block.FINE;
//                }
//                else if (cella=='k'){
//                    matrice_Principale[i][j] = Block.PORTALE;
//                }
                else if (cella=='l'){
                    matrice_Principale[i][j] = Block.PONTE;
                }
                else if (cella=='m'){
                    matrice_Principale[i][j] = Block.MORTE;
                }
                else if (cella=='o'){
                    matrice_Principale[i][j] = Block.MINIZOMBIE;
                    LinkedList<Position> l = new LinkedList<>();
                    l.add(new Position(i,j));
                    MiniZombie mz=new MiniZombie(l,this);
                    nemici.add(mz);
                }
                else if (cella=='p' && viewPort.get(i+1).charAt(j)=='p'){
                    matrice_Principale[i][j] = Block.CREEPER;
                    LinkedList<Position> l = new LinkedList<>();
                    l.add(new Position(i,j));
                    l.add(new Position(i+1,j));
                    Creeper c=new Creeper(l,this);
                    nemici.add(c);
                }
                else if (cella=='r' && viewPort.get(i+1).charAt(j)=='r'){
                    matrice_Principale[i][j] = Block.SKELETON;
                    LinkedList<Position> l = new LinkedList<>();
                    l.add(new Position(i,j));
                    l.add(new Position(i+1,j));
                    Skeleton s=new Skeleton(l,this);
                    nemici.add(s);
                }
            }
        }
        //matrice_Principale[coordinatePlayer.getFirst().i()][coordinatePlayer.getFirst().j()]=Block.PERSONAGGIO;
        //matrice_Principale[coordinatePlayer.get(1).i()][coordinatePlayer.get(1).j()]=Block.PERSONAGGIO;
    }
    public void removeEnemy(LinkedList<Object> l){
        if(l == null)
            return;

        for(Object o : l){
            if(o instanceof MiniZombie){
                matrice_Principale[((MiniZombie) o).getCoordinate().getFirst().i()][((MiniZombie) o).getCoordinate().getFirst().j()]=Block.VUOTO;
            }
            else if(o instanceof Creeper){
                matrice_Principale[((Creeper) o).getCoordinate().getFirst().i()][((Creeper) o).getCoordinate().getFirst().j()]=Block.VUOTO;
                matrice_Principale[((Creeper) o).getCoordinate().getLast().i()][((Creeper) o).getCoordinate().getLast().j()]=Block.VUOTO;
            }
            else if(o instanceof Skeleton){
                matrice_Principale[((Skeleton) o).getCoordinate().getFirst().i()][((Skeleton) o).getCoordinate().getFirst().j()]=Block.VUOTO;
                matrice_Principale[((Skeleton) o).getCoordinate().getLast().i()][((Skeleton) o).getCoordinate().getLast().j()]=Block.VUOTO;
            }
            nemici.remove(o);
        }
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
        return i>=0 && i<viewPort.size() && j>=0 && j< viewPort.get(i).length();
    }
    public boolean isWall(int i,int j) {return isType(i,j,Block.MURO);}

    public boolean isPlayer(int i,int j) {return isType(i,j,Block.PERSONAGGIO);}

    public boolean isTerra(int i, int j) { return isType(i,j,Block.TERRA);}
    public boolean isErba(int i, int j) { return isType(i,j,Block.ERBA);}
    public boolean isCoin(int i, int j) { return isType(i,j,Block.MONETA);}
    public boolean isSpeciale(int i, int j) { return isType(i,j,Block.SPECIALE);}
    public boolean isTubo(int i, int j) { return isType(i,j,Block.TUBO);}
    public boolean isBarile(int i, int j) { return isType(i,j,Block.BARILE);}
    public boolean isPonte(int i, int j) { return isType(i,j,Block.PONTE);}
    public boolean isMorte(int i, int j) { return isType(i,j,Block.MORTE);}
    public boolean isUsato(int i, int j) { return isType(i,j,Block.USATO);}
    public boolean isMiniZombie(int i, int j) { return isType(i,j,Block.MINIZOMBIE);}
    public boolean isCreeper(int i, int j) { return isType(i,j,Block.CREEPER);}
    public boolean isSkeleton(int i, int j) { return isType(i,j,Block.SKELETON);}
    public boolean isVelocita(int i, int j) { return isType(i,j,Block.VELOCITA);}
    public boolean isScudo(int i, int j) { return isType(i,j,Block.SCUDO);}
    public boolean isLentezza(int i, int j) { return isType(i,j,Block.LENTEZZA);}
    public boolean isVita(int i, int j) { return isType(i,j,Block.VITA);}
    public boolean isNemico(int i, int j) { return isMiniZombie(i,j) || isCreeper(i,j) || isSkeleton(i,j);}
    public boolean isFreccia(int i, int j) { return isType(i,j,Block.FRECCIA);}
    //public boolean isTeletrasporto(int i, int j) { return isType(i,j,Block.TELETRASPORTO);}
    public boolean isBlocco(int i, int j){return isWall(i,j) || isErba(i,j) || isTerra(i,j) || isSpeciale(i,j) || isTubo(i,j) || isBarile(i,j) || isPonte(i,j) || isUsato(i,j);}

}
