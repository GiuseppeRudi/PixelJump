package model;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class World {

    private final int[][] matrice_Principale  = new int[Settings.World_Size_Riga][Settings.World_Size_Colonna];

    private final Player player = new Player(new Position(0,0), Settings.Filtro_Size_Riga,Settings.Filtro_Size_Colonna);
    public void inizializzaMatrice() throws IOException {
        leggiFile file = new leggiFile();
        List<String> l = file.leggi("src/resources/livelli/LivelloProva.txt");
        for (String s : l)
        {
            System.out.println(s);
        }
    }

}
