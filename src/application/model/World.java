package application.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class World {


    World() throws IOException {
        inizializzaMatricePrincipale();

        coordinatePlayer.add(new Position(15,5));
        coordinatePlayer.add(new Position(16,5));
    }

    enum Block {VUOTO,TERRA,PERSONAGGIO,NEMICO,MURO};
    //vuoto == 0 ; terra == 1 ; personaggio == 2 nemico == 3 ; muro == 4

    private final Block[][] matrice_Principale  = new Block[Settings.World_Size_Riga][Settings.World_Size_Colonna];


    private  LinkedList<Position> coordinatePlayer = new LinkedList<>();

    private final Player player = new Player(coordinatePlayer); //mette il player in posizione 0,0 , la grandezza della riga
    public void inizializzaMatricePrincipale() throws IOException {  //legge il file e quindi la matrice


        leggiFile file = new leggiFile();
        List<String>  viewPort = file.leggi("src/application.resources/livelli/LivelloProva.txt");

        System.out.println(viewPort.size());
        System.out.println(viewPort.get(0).length());
        for (int i=0; i<Settings.World_Size_Riga; i++)
        {
            String riga = viewPort.get(i);

            for (int j=0; j<Settings.World_Size_Colonna;j++)
            {
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
            }
        }


        matrice_Principale[coordinatePlayer.getFirst().i()][coordinatePlayer.getFirst().j()]=Block.PERSONAGGIO;
        matrice_Principale[coordinatePlayer.get(1).j()][coordinatePlayer.get(1).j()]=Block.PERSONAGGIO;
    }

    public void updateDirection(int direction) {
        player.updateDirection(direction);
    }

    private boolean isType(int i,int j,Block b)
    {
        if(isValidPosition(i,j))
            return matrice_Principale[i][j] == b;
        return false;
    }

    private boolean isValidPosition(int i, int j) {
        return i>=0 && i<Settings.World_Size_Riga && j>=0 && j<Settings.World_Size_Colonna;
    }


    public boolean isWall(int i,int j) {return isType(i,j,Block.MURO);}



}
