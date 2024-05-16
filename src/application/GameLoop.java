package application;

import application.controller.ControllerPlayer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop {
    private final ControllerPlayer controllerPlayer;


    public GameLoop(ControllerPlayer controllerPlayer) {
        this.controllerPlayer = controllerPlayer;
    }

    private ScheduledExecutorService executorService = null;

    public void inizioGioco()
    {
        if(executorService !=null)
        {
            return;
        }

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(controllerPlayer::update,0,1000/15, TimeUnit.MILLISECONDS);
        //creo un thread che gestisce l'esecuzione di gioco e permette di aggiornamre ogni 60 frame per secondo le dinamiche di gioco
    }
}
