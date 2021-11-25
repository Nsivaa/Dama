package it.uniba.main;
import java.util.ArrayList;


/** La classe GestioneOutput si occupa di effettuare la stampa
 * della griglia della damiera con la relativa numerazione e dei pezzi,
 * della stampa delle pedine prese e delle mosse in generale effettuate da parte dell'utente
 * e della stampa del tempo trascorso ad ogni turno per entrambi i giocatori.
 * Per eventuali mosse non permesse da parte dell'applicazione, verranno lanciati dei messaggi di errore.
 * Essendo una classe di tipo BOUNDARY, si interfaccia con AppMain (CONTROL) e con GestioneCasi (BOUNDARY).
 */

/** BOUNDARY */
public class GestioneOutput {

    /** Metodo che stampa la damiera con la numerazione delle caselle nere. */
    final void stampareDamiera(final Damiera damiera) {
        final int nRighe = 7;
        final int nCaselle = 32;
        final int operatore = 9;
        int i = 0, j = 0, e = 1, k = 0;
        StringBuffer buffer = new StringBuffer();
        String s2 = "";
        String s = "\u2554\u2550\u2550\u2550\u2550";
        for (k = 0; k < nRighe; k++) {
            buffer.append("\u2566\u2550\u2550\u2550\u2550");
        }
        buffer.append(s);
        buffer.append("\u2557 \n");
        while (e <= nCaselle) {
            buffer.append("\u2551 ");
            if (j < damiera.getSIZE()) {
                if (damiera.getDamiera(i, j).getColore() == 1) {
                    if (e <= operatore) {
                        buffer.append("0");
                    }
                    buffer.append(e);
                    buffer.append(" ");
                    e++;
                } else {
                    buffer.append("   ");
                }
                j++;
            } else {
                buffer.append("\n");
                j = 0;
                i++;
                s2 = "\u2560\u2550\u2550\u2550\u2550";
                buffer.append(s2);
                for (k = 0; k < nRighe; k++) {
                    buffer.append("\u256C\u2550\u2550\u2550\u2550");
                }
                buffer.append("\u2563\n");
            }
        }
        buffer.append("\u2551\n");
        s2 = "\u255A\u2550\u2550\u2550\u2550";
        buffer.append(s2);
        for (k = 0; k < nRighe; k++) {
            buffer.append("\u2569\u2550\u2550\u2550\u2550");
        }
        buffer.append("\u255D\n");
        s = buffer.toString();
        System.out.println(s);
    }

    /** Metodo che stampa la damiera con le pedine in formato unicode. */
    final void stamparePezzi(final Damiera damiera) {
        final int nRighe = 7;
        int k = 0;
        System.out.print("\u2554" + "\u2550" + "\u2550" + "\u2550" + "\u2550");
        for (k = 0; k < nRighe; k++) {
            System.out.print("\u2566" + "\u2550" + "\u2550" + "\u2550" + "\u2550");
        }
        System.out.print("\u2557" + "\n");
        for (int i = 0; i < damiera.getSIZE(); i++) {
            System.out.print("\u2551 ");
            for (int j = 0; j < damiera.getSIZE(); j++) {
                if (!damiera.getDamiera(i, j).getStato()) {
                    if (damiera.getDamiera(i, j).getColorePedina() == 0) {
                        if (!damiera.getDamiera(i, j).getDamaPedina()) {
                            System.out.print('\u26C2');
                        } else {
                            System.out.print('\u26C3');
                        }
                    } else {
                        if (!damiera.getDamiera(i, j).getDamaPedina()) {
                            System.out.print('\u26C0');
                        } else {
                            System.out.print('\u26C1');
                        }
                    }
                } else {
                    if (damiera.getDamiera(i, j).getColore() == 1) {
                        System.out.print('\u25AF');
                    } else {
                        System.out.print('\u25AE');
                    }
                }
                System.out.print(" " + "\u2009" + "\u2551 ");
            }
            System.out.print("\n");
            if (i != damiera.getSIZE() - 1) {
                System.out.print("\u2560" + "\u2550" + "\u2550" + "\u2550" + "\u2550");
                for (k = 0; k < nRighe; k++) {
                    System.out.print("\u256C" + "\u2550" + "\u2550" + "\u2550" + "\u2550");
                }
                System.out.print("\u2563" + "\n");
            }
        }
        System.out.print("\u255A" + "\u2550" + "\u2550" + "\u2550" + "\u2550");
        for (k = 0; k < nRighe; k++) {
            System.out.print("\u2569" + "\u2550" + "\u2550" + "\u2550" + "\u2550");
        }
        System.out.println("\u255D" + "\n");
    }

    /** Metodo che stampa il tempo corrente del giocatore. */
    public final void stampaTempoGiocatore(final double tempo) {
        final int secondi = 60;
        System.out.println((int) (tempo / secondi) + " m " + (int) tempo % secondi + " s ");
    }

    /** Metodo che stampa a video le mosse eseguite nel corso della partita. */
    public final void mostrareMosse(final ArrayList mosse) {
        int n = mosse.size();
        int i;
        for (i = 0; i < n; i++) {
            if (i % 2 == 0) {
                System.out.print("B: ");
            } else {
                System.out.print("N: ");
            }
            System.out.println(mosse.get(i));
        }
    }

    /**
     * Metodo che stampa a video le catture avvenute nel corso della partita, per ogni rispettiva pedina.
     */
    public final void mostrarePrese(final int pN, final int pB) {

        System.out.print("Bianco:");
        for (int i = 0; i < pN; i++) {
            System.out.print("\u26C0 ");
        }
        System.out.print("\n");
        System.out.print("Nero:");
        for (int i = 0; i < pB; i++) {
            System.out.print("\u26C2 ");
        }
        System.out.print("\n");
    }

    /**
     * Metodo che riceve come argomento il codice errore generato dai metodi di presa e spostamento, e
     * stampa a video il messaggio appropriato.
     */
    public final void stampareFeedback(final int n) {
        final int errColPed = 3;
        final int errCasellaPiena = 4;
        final int errCasellaVuota = 5;
        final int errCaselleInex = 6;
        final int errColUguale = 7;
        final int errNoPedina = 8;
        if (n == 1) {
            System.out.println("Mossa valida");
        }
        if (n == 2) {
            System.out.println("Mossa non valida");
        }
        if (n == errColPed) {
            System.out.println("Puoi muovere solo pedine del tuo colore");
        }
        if (n == errCasellaPiena) {
            System.out.println("La casella verso cui ti vuoi spostare non è vuota");
        }
        if (n == errCasellaVuota) {
            System.out.println("Non hai selezionato nessuna pedina. La casella scelta è vuota");
        }
        if (n == errCaselleInex) {
            System.out.println("Hai selezionato caselle non esistenti");
        }
        if (n == errColUguale) {
            System.out.println("Non puoi mangiare le tue stesse pedine");
        }
        if (n == errNoPedina) {
            System.out.println("Non c'e' nessuna pedina avversaria da mangiare");
        }
    }
    /** Funzione che mostra, al richiamo del comando "help", "-h" o "--help", tutti i comandi disponibili all'utente. */
    public static void help() {
        System.out.println("Questa applicazione permette di giocare a dama in locale. Il gioco utilizza la notazione "
                + "algebrica per eseguire le mosse.");
        System.out.println("I comandi sono: ");
        System.out.println("gioca");
        System.out.println("esci");
        System.out.println("numeri");
        System.out.println("I comandi disponibili durante il corso di una partita sono: ");
        System.out.println("damiera");
        System.out.println("tempo");
        System.out.println("mosse");
        System.out.println("prese");
        System.out.println("abbandona");
    }
}
