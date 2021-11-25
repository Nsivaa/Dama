package it.uniba.main;

/** La classe Tempo si occupa di modellare i dati riguardanti la gestione del tempo di gioco.
 * Questa classe contiene quindi i metodi che permettono di modellare questi dati.
 * Essendo di tipo ENTITY, si interfaccia con AppMain (CONTROL).
 * Viene, infatti, passata in input al metodo "menu" di GestioneCasi da Appmain.
 */

/** ENTITY */
public class Tempo {
    /** Metodo che restituisce il tempo di sistema in millisecondi */
    final long setTempoIniz() {
        long tempoIniz = System.currentTimeMillis();
        return tempoIniz;
    }

    /** Metodo che restituisce il tempo in secondi trascorso dall'istante ricevuto come argomento */
    final double tempoCorr(final long tempoIniz) {
        final int divisore = 1000;
        double tempoCorr = (double) (System.currentTimeMillis() - tempoIniz) / (divisore);
        return tempoCorr;
    }

    /** Metodo che aggiorna il tempo ricevuto come argomento */
    final double aggiornaTempo(final double tempo, final long tempoIniz) {
        double tempoAgg;
        tempoAgg = tempo + tempoCorr(tempoIniz);
        return tempoAgg;
    }
}
