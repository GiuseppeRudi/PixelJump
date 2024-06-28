package application.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class file {
    public List<String> leggi(String nomeFile) throws IOException { //legge tutte le righe del file prendendo come parametro il nomefile
        return Files.readAllLines(Path.of(nomeFile));
    }
    public void scrivi(List<String> f,String nomeFile) throws IOException { //legge tutte le righe del file prendendo come parametro il nomefile
        StringBuilder w = new StringBuilder();
        for(String i:f){
            for(char c:i.toCharArray()){
                if(c=='0') w.append('a');
                else if(c=='1') w.append('b');
                else if(c=='2') w.append('c');
                else if(c=='3') w.append('d');
                else if(c=='4') w.append('e');
                else if(c=='5') w.append('f');
                else if(c=='6') w.append('g');
                else if(c=='7') w.append('h');
                else if(c=='8') w.append('i');
                else if(c=='9') w.append('j');
            }
        }
        Files.writeString(Path.of(nomeFile), w);
    }
}
