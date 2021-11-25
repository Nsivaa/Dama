package it.uniba.main;

/** La classe Pedina si occupa della modellazione dei dati riguardanti le pedine da posizionare
 * dentro le caselle della damiera.
 * I suoi metodi sono getter e setter per modellare i dati.
 * Essendo una classe di tipo ENTITY, si interfaccia con Casella (ENTITY).
 */

/** ENTITY */
public class Pedina {
    private short colore; /** Colore bianco --> 0; Colore nero --> 1 */
    private boolean dama; /** dama=false: la pedina non è dama; dama=true: la pedina è dama. */

    /** Costruttore pedina */
    public Pedina(final short col) {
        colore = col;
        this.dama = false;
    }

    /** Metodo che restituisce il colore della pedina. */
    final short getColore() {
        return colore;
    }

    /** Metodo che imposta il valore dama della pedina. */
    final void setDama() {
        this.dama = true;
    }

    /** Metodo che restituisce il valore TRUE se la pedina è dama, FALSE altrimenti. */
    final boolean getDama() {
        return dama;
    }
}
