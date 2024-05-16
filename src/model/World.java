package model;

import java.io.IOException;
import java.util.List;
import java.util.Set;



public class World {

    public enum Block {VUOTO,MURO,PERSONAGGIO,NEMICO};
    //vuoto == 0 ; muro == 1 ; personaggio == 2 nemico == 3

    private final Block[][] matrice_Principale  = new Block[Settings.World_Size_Riga][Settings.World_Size_Colonna];
    private final int [][] matrice_filtro = new int[Settings.Filtro_Size_Riga][Settings.Filtro_Size_Colonna];
    private final Player player = new Player(new Position(0,0), Settings.Filtro_Size_Riga,Settings.Filtro_Size_Colonna); //mette il player in posizione 0,0 , la grandezza della riga
    public void inizializzaMatricePrincipale() throws IOException {  //legge il file e quindi la matrice
        leggiFile file = new leggiFile();
        List<String>  viewPort = file.leggi("src/resources/livelli/LivelloProva.txt");

        System.out.println(viewPort.size());
        System.out.println(viewPort.get(0).length());
        for (int i=0; i<Settings.World_Size_Riga; i++)
        {
            String riga = viewPort.get(i);

            for (int j=0; j<Settings.World_Size_Colonna;j++)
            {
                char cella = riga.charAt(j);
                if (cella==0){
                    matrice_Principale[i][j] =Block.VUOTO;
                }
                else if (cella==1) {
                    matrice_Principale[i][j] =Block.MURO;
                }
                else if (cella==2) {
                    matrice_Principale[i][j] =Block.PERSONAGGIO;
                }
                else if (cella==3) {
                    matrice_Principale[i][j] =Block.NEMICO;
                }
            }
        }
    }

    public void stampa_matrice_principale()
    {
        for (int i=0; i<matrice_Principale.length;i++)
        {
            for (int j=0;j<matrice_Principale[i].length;j++)
                System.out.print(matrice_Principale[i][j]);

            System.out.println();
        }
    }


}
