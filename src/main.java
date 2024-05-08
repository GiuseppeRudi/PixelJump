package src;

import controller.controller;
import model.file;
import view.panel;

import java.io.IOException;

public class main
{
    public static void main(String[] args) throws IOException {


        panel boardWindow = new panel();
        boardWindow.riempiGriglia();
        file prova = new file(boardWindow);
        controller controller = new controller(prova,boardWindow);
        boardWindow.setController(controller);
        boardWindow.schermataPrincipale();

        prova.start();

        boardWindow.attiva();

    }
}
