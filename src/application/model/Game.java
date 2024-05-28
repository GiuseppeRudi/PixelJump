package application.model;

import java.io.IOException;

public class Game
{
    private final World world = new World();

    private Game() throws IOException {}
    private static final Game instance;

    static {
        try {
            instance = new Game();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Game getInstance() {return instance;}

    public void setDirection(int direction)  {

        world.updateDirection(direction);}


    //viene chiamata dal player controller
    public void update()  {
//        if(world.movePlayer())
//        {
//           //qualcosa
//        }
        //AL MOMENTO SENZA IF PERCHE LE MONETE NON CI SERVONO
        world.movePlayer();
    }

    public World getWorld() { return world;}

}
