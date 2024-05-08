package model;

//questa classe serve per creare piu player , nemici che utilizzano questo modello
// generale su cui altri classi che la estendono possono usare

public abstract class AbstractPlayer {

    private Position position;
    private final int filtroSizeRiga ;
    private final int filtroSizeColonna;

    public AbstractPlayer(Position position, int filtroSizeRiga, int filtroSizeColonna) {
        this.filtroSizeColonna= filtroSizeColonna;
        this.filtroSizeRiga= filtroSizeRiga;
        this.position= position;
    }
}
