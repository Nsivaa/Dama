package it.uniba.main;

/** La classe Casella si occupa della modellazione dei dati riguardanti le caselle della damiera.
 * I suoi metodi sono getter e setter degli attributi della classe.
 * Essendo una classe di tipo ENTITY, si interfaccia con Damiera (ENTITY) e Pedina (ENTITY).
 * Infatti la classe si avvale della classe Pedina per poter modellare anche i suoi dati con la
 * presenza di metodi getter e setter per Pedina.
 */

/** ENTITY */
public class Casella {

    private final short colore; /** Colore nero --> 1; Colore bianco --> !1 */
    private boolean stato = true; /** stato=false: casella piena; stato=true: casella vuota. */
    private Pedina pedina;
    private int numPedina; /** Identificativo pedina. */

    /** Costruttore casella. */
    public Casella(final short color) {
        colore = color;
    }

    /** Metodo che restituisce il colore casella. */
    final short getColore() {
            return colore;
    }

    /** Metodo che imposta la pedina. */
    final void setPedina(final int numPed, final short colorePedina) {
        numPedina = numPed;
        this.pedina = new Pedina(colorePedina);
        this.stato = false;
    }

    /** Metodo che restituisce il colore della pedina nella casella. */
    final short getColorePedina() {
        if (pedina != null) {
            return this.pedina.getColore();
        } else {
            return 0;
        }
    }

    /** Metodo che restituisce TRUE se la pedina all'interno della casella è una dama, FALSE altrimenti. */
    public final boolean getDamaPedina() {
        if (pedina != null) {
            return this.pedina.getDama();
        } else {
            return false;
        }
    }

    /** Metodo che restituisce l'identificativo della pedina. */
    final int getNumPedina() {
        return numPedina;
    }

    /** Metodo che restituisce TRUE se la casella è vuota, FALSE altrimenti. */
    public final boolean getStato() {
        return stato;
    }

    /** Metodo che elimina una pedina da una casella. */
    final void delPedina() {
        this.numPedina = 0;
        this.stato = true;
    }

    /** Metodo che setta una pedina su una casella a dama */
    public final void setDamaPedina() {
        this.pedina.setDama();
    }

}
