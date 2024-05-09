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

    public void setDirections(int direction)  {world.updateDirection(direction);}

    public void update()
    {
        if(world.movePlayer)
        {
           //qualcosa
        }
    }

    public World getWorld() { return world;}




}
