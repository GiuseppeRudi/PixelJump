package application.model;

import application.view.GamePanel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class World {


    World() throws IOException {
        coordinatePlayer.add(new Position(15,5)); //la posizione 0 ha la testa
        coordinatePlayer.add(new Position(16,5)); //la posizione 1 ha il corpo
        inizializzaMatricePrincipale();
    }

    //serve per far muovere il player e aggiornare la sua posizione sulla matrice principale
    public void movePlayer() {
        //restituisce a seconda del movimento le nuove posizione della testa e del corpo del giocatore
        LinkedList<Position> newPosition = player.simulateMove();
        //devo verificare che le nuove posizioni sia valide
        int count =0;
        for (Position p : newPosition) {
            //is valid position controlliamo sia il range del mondo e sia se andiamo contro muro,oggetti
            if(isValidPosition(p.i(),p.j()) && !isWall(p.i(),p.j()) && !isSpeciale(p.i(), p.j())) {
                count++;
            }
        }
//        System.out.println(count);
        if (count==newPosition.size()) {
            //getPosition prende da parametro un intero che se è 0 restituisce la position della testa 1 il corpo
            //  abbiamo prima controllato che possiamo cambiare posizione e se la possiamo cambiare
            // prima prendiamo le coordinate precedenti e ci mettiamo il blocco vuoto

            for (int k=0; k<coordinatePlayer.size();k++)
            {
                matrice_Principale[player.getPosition(k).i()][player.getPosition(k).j()] = Block.VUOTO;
            }

            player.move();
            //qui dopo che si muove metto not moving cosi sta ferma e non va in quella direzione in loop
            if(player.getDirection()==Settings.JUMP)  updateDirection(Settings.NOT_MOVING);
            //aggiorniamo nella matrice principale la nuova posizione del personaggio
            for(int k=0; k<coordinatePlayer.size();k++) {
                matrice_Principale[newPosition.get(k).i()][newPosition.get(k).j()] = Block.PERSONAGGIO;
            }
        }
    }

    enum Block {VUOTO,TERRA,PERSONAGGIO,NEMICO,MURO,ERBA,SPECIALE};
    //vuoto == 0 ; terra == 1 ; personaggio == 2 nemico == 3 ; muro == 4
    private final Block[][] matrice_Principale  = new Block[Settings.World_Size_Riga][Settings.World_Size_Colonna];
    private  LinkedList<Position> coordinatePlayer = new LinkedList<>();
    private final Player player = new Player(coordinatePlayer); //mette il player in posizione 0,0 , la grandezza della riga
    public void inizializzaMatricePrincipale() throws IOException {  //legge il file e quindi la matrice
        leggiFile file = new leggiFile();
        List<String>  viewPort = file.leggi("src/application/resources/livelli/LivelloProva.txt");
//        System.out.println(viewPort.size());
//        System.out.println(viewPort.get(0).length());
        for (int i=0; i<Settings.World_Size_Riga; i++) {
            String riga = viewPort.get(i);
            for (int j=0; j<Settings.World_Size_Colonna;j++) {
                char cella = riga.charAt(j);
                if (cella=='0'){
                    matrice_Principale[i][j] =Block.VUOTO;
                }
                else if (cella=='1') {
                    matrice_Principale[i][j] =Block.TERRA;
                }
                else if (cella=='3') {
                    matrice_Principale[i][j] =Block.NEMICO;
                }
                else if (cella=='4') {
                    matrice_Principale[i][j] = Block.MURO;
                }
                else if (cella=='5'){
                    matrice_Principale[i][j] = Block.ERBA;
                }
                else if (cella=='6'){
                    matrice_Principale[i][j] = Block.SPECIALE;
                }
            }
        }
        matrice_Principale[coordinatePlayer.getFirst().i()][coordinatePlayer.getFirst().j()]=Block.PERSONAGGIO;
        matrice_Principale[coordinatePlayer.get(1).i()][coordinatePlayer.get(1).j()]=Block.PERSONAGGIO;
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

    private boolean isValidPosition(int i, int j) {
        return i>=0 && i<Settings.World_Size_Riga && j>=0 && j<Settings.World_Size_Colonna;
    }


    public boolean isWall(int i,int j) {return isType(i,j,Block.MURO);}

    public boolean isPlayer(int i,int j) {return isType(i,j,Block.PERSONAGGIO);}

    public boolean isTerra(int i, int j) { return isType(i,j,Block.TERRA);}
    public boolean isErba(int i, int j) { return isType(i,j,Block.ERBA);}
    public boolean isSpeciale(int i, int j) { return isType(i,j,Block.SPECIALE);}

}
