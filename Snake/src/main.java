import controller.controller;
import model.file;
import  view.panel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.AbstractMap.SimpleEntry;

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
