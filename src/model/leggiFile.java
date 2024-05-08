package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class leggiFile {
    public List<String> leggi(String nomeFile) throws IOException { //legge tutte le righe del file prendendo come parametro il nomefile
        return Files.readAllLines(Path.of(nomeFile));
    }
}
