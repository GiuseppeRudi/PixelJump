import model.World;
import model.leggiFile;

import java.io.IOException;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {
        World world = new World();
        world.inizializzaMatricePrincipale();
        world.stampa_matrice_principale();

    }
}
