package model;

public enum Blocco {
    Vuoto(0),
    Muro(1),
    Personaggio(2);

    private final int valore;

    Blocco(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return this.valore;
    }
}
