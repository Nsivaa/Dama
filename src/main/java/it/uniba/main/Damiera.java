package it.uniba.main;
import java.util.ArrayList;

/** La classe Damiera si occupa della modellaione dei dati riguardanti la damiera.
 * Contiene metodi per lo spostamento, la presa di una o più pedine e metodi per
 * controllare che tutto funzioni correttamente.
 * Essendo una classe di tipo ENTITY, si interfaccia con AppMain (CONTROL) e Casella (ENTITY).
 * Infatti questa classe si avvale della classe Casella per poter creare il campo di gioco,
 * le pedine e effettuare spostamenti, prese e controlli.
 * Viene creato l'oggetto Damiera in AppMain da passare, successivamente, a GestioneCasi.
 */

/** ENTITY */
public class Damiera {
    private static final int SIZE = 8; /** Dimensione della damiera. */
    private Casella[][] damiera = new Casella[SIZE][SIZE];
    /** Costruttore damiera **/
    public Damiera() {
        inizializzaDamiera();
        inserimentoBianche();
        inserimentoNere();
    }

    /** Metodo che inizializza la damiera aggiungedole le caselle. */
    private void inizializzaDamiera() {
        short startColore = 1;
        short colore = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                damiera[i][j] = new Casella(colore);
                if (colore == 1) {
                    colore = -1;
                } else {
                    colore = 1;
                }
            }
            if (startColore == 1) {
                startColore = -1;
                colore = startColore;
            } else {
                startColore = 1;
                colore = startColore;
            }
        }
    }

    /** Metodo che inserisce le pedine nere nella damiera. */
    private void inserimentoNere() {
        int i = 0;
        int j = 0;
        int idPedina = 1;
        final int nPedine = 12;
        final int nCaselle = 8;
        while (idPedina <= nPedine) {
            if (damiera[i][j].getColore() == 1) {
                damiera[i][j].setPedina(idPedina, (short) 1);
                idPedina++;
                if (j < nCaselle) {
                    j++;
                }
                if (j == nCaselle) {
                    j = 0;
                    i++;
                }
            } else {
                if (j < nCaselle) {
                    j++;
                }
                if (j == nCaselle) {
                    j = 0;
                    i++;
                }
            }
        }
    }

    /** Metodo che inserisce le pedine bianche nella damiera. */
    private void inserimentoBianche() {
        final int posizioneIniz = 5;
        final int idIniz = 13;
        final int idFin = 24;
        final int nColonne = 8;
        int i = posizioneIniz;
        int j = 0;
        int idPedina = idIniz;
        while (idPedina <= idFin) {
            if (damiera[i][j].getColore() == 1) {
                damiera[i][j].setPedina(idPedina, (short) 0);
                idPedina++;
                if (j < nColonne) {
                    j++;
                }
                if (j == nColonne) {
                    j = 0;
                    i++;
                }
            } else {
                if (j < nColonne) {
                    j++;
                }
                if (j == nColonne) {
                    j = 0;
                    i++;
                }
            }
        }
    }

    public static int getSIZE() {
        return SIZE;
    }

    public final Casella getDamiera(final int i, final int j) {
        return damiera[i][j];
    }

    /** Metodo che trasforma una coordinata della damiera nella corrispettiva x di una matrice 8x8 */
    public int traduzioneAscissa(final double numero) {
        final int divisore = 4;
        double numeroApprox;
        numeroApprox = Math.ceil(numero / divisore);
        int x = (int) numeroApprox - 1;
        return x;
    }

    /** Metodo che trasforma una coordinata della damiera nella corrispettiva y di una matrice 8x8 */
    public int traduzioneOrdinata(final double numero, final int x) {
        final int divisore = 4;
        final int nColonne = 8;
        double k;
        int y;
        if (x % 2 == 0) {
            k = (numero % divisore) * 2;
            if (k == 0) {
                k = nColonne;
            }
            y = (int) k - 2;
        } else {
            k = ((numero % divisore) * 2) - 1;
            if (k == -1) {
                k = nColonne - 1;
            }
            y = (int) k;
        }
        return y;
    }

    /** Metodo che restituisce 1 se è possibile effettuare uno spostamento di una
     * pedina su una casella e un codice errore altrimenti */
    public int controlloSpostamento(final double a, final double b, final int coloreGiocatore) {
        final int nCaselle = 32;
        final int nColonne = 8;
        final int operatore = 4;
        final int errColPed = 3;
        final int errCasellaPiena = 4;
        final int errCasellaVuota = 5;
        final int errCaselleInex = 6;
        int xA, yA, xB, yB;
        int coeff, diff;
        int mossaValida = 1;
        if (a >= 1 && b >= 1 && a <= nCaselle && b <= nCaselle) {
            xA = traduzioneAscissa(a);
            yA = traduzioneOrdinata(a, xA);
            xB = traduzioneAscissa(b);
            yB = traduzioneOrdinata(b, xB);
            if (!damiera[xA][yA].getStato()) {
                if (damiera[xB][yB].getStato()) {
                    if (damiera[xA][yA].getColorePedina() == coloreGiocatore) {
                        if (coloreGiocatore == 1) {
                            coeff = 1;
                            diff = 0;
                        } else {
                            coeff = -1; diff = 1;
                        }
                        if (a % nColonne != 0 && (a - 1) % nColonne != 0) {
                            if ((xA + 1) % 2 == 0) {
                                if (b == a + coeff * (operatore - diff) || b == a + coeff * (operatore + 1 - diff)) {
                                    mossaValida = 1;
                                } else {
                                    mossaValida = 2;
                                }
                            } else {
                                if (b == a + coeff * (operatore - 1 + diff) || b == a + coeff * (operatore + diff)) {
                                    mossaValida = 1;
                                } else {
                                    mossaValida = 2;
                                }
                            }
                        } else {
                            if (b == a + coeff * operatore) {
                                mossaValida = 1;
                            } else {
                                mossaValida = 2;
                            }
                        }
                    } else {
                        mossaValida = errColPed;
                    }
                } else {
                    mossaValida = errCasellaPiena;
                    if (damiera[xA][yA].getColorePedina() != coloreGiocatore) {
                        mossaValida = errColPed;
                    }
                }
            } else {
                mossaValida = errCasellaVuota;
            }
        } else {
            mossaValida = errCaselleInex;
        }
        return mossaValida;
    }

    /** Metodo che sposta una pedina dalla casella a alla casella b */
    public void spostarePedina(final double a, final double b) {
        int xA, xB, yA, yB;
        xA = traduzioneAscissa(a);
        yA = traduzioneOrdinata(a, xA);
        xB = traduzioneAscissa(b);
        yB = traduzioneOrdinata(b, xB);
        int num = damiera[xA][yA].getNumPedina();
        if (damiera[xA][yA].getDamaPedina()) {
            damiera[xB][yB].setDamaPedina();
        }
        short colorePedina = damiera[xA][yA].getColorePedina();
        damiera[xA][yA].delPedina();
        damiera[xB][yB].setPedina(num, colorePedina);
    }

    public final void resettaDamiera() {
        inizializzaDamiera();
        inserimentoBianche();
        inserimentoNere();
    }

    /** Metodo che restituisce true se una pedina entra in una casella di damatura e false altrimenti */
    public boolean controllareDamatura(final double posizionePedina, final int colorePedina) {
        final int nRighe = 7;
        boolean damatura = false;
        int asc = traduzioneAscissa(posizionePedina);
        if ((colorePedina == 1 && asc == nRighe) || (colorePedina == 0 && asc == 0)) {
            damatura = true;
        }
        return damatura;
    }

    /** Metodo che trasforma una pedina in dama */
    public final void damatura(final double posPed) {
        int asc = traduzioneAscissa(posPed);
        int ord = (traduzioneOrdinata(posPed, asc));
        damiera[asc][ord].setDamaPedina();
    }

    /** Metodo che restituisce true se una pedina può effettuare una presa  e false altrimenti */
    final int controlloPresaSemplice(final double a, final double b, final int coloreGiocatore,
                                     final boolean presaMultipla) {
        final int nCaselle = 32;
        final int nColonne = 8;
        final int operatore = 9;
        final int errColPed = 3;
        final int errCasellaPiena = 4;
        final int errCasellaVuota = 5;
        final int errCaselleInex = 6;
        final int errColUguale = 7;
        final int errNoPedina = 8;
        int xA, yA, xC, xB, yB, yC;
        int coeff1 = 1, coeff2 = 1;
        int mossaValida = 1;
        if (a >= 1 && b >= 1 && a <= nCaselle && b <= nCaselle) {
            xA = traduzioneAscissa(a);
            yA = traduzioneOrdinata(a, xA);
            xB = traduzioneAscissa(b);
            yB = traduzioneOrdinata(b, xB);
            if (!damiera[xA][yA].getStato() || presaMultipla) {
                if (damiera[xB][yB].getStato()) {
                    if (damiera[xA][yA].getColorePedina() == coloreGiocatore || presaMultipla) {
                        if (coloreGiocatore == 0) {
                            coeff2 = -1;
                        }
                        if (b == a - operatore || b == a + operatore - 2) {
                            coeff1 = -1;
                        }
                        if (b == a + (operatore - 2) * coeff2 || b == a + operatore * coeff2) {
                            xC = xA + coeff2;
                            yC = yA + coeff1;
                            if (!damiera[xC][yC].getStato()) {
                                if (damiera[xC][yC].getColorePedina() != coloreGiocatore) {
                                    mossaValida = 1;
                                } else {
                                    mossaValida = errColUguale;
                                }
                            } else {
                                mossaValida = errNoPedina;
                            }
                        } else {
                            mossaValida = 2;
                        }
                        if (a % nColonne == 0 || (a - 1) % nColonne == 0) {
                            if (!(b == a + coeff2 * operatore || b == a + coeff2 * (operatore - 2))) {
                                mossaValida = 2;
                            }
                        }
                    } else {
                        mossaValida = errColPed;
                    }
                } else {
                    mossaValida = errCasellaPiena;
                    if (damiera[xA][yA].getColorePedina() != coloreGiocatore && !presaMultipla) {
                        mossaValida = errColPed;
                    }
                }
            } else {
                mossaValida = errCasellaVuota;
                if (damiera[xA][yA].getColorePedina() != coloreGiocatore && !presaMultipla) {
                    mossaValida = errColPed;
                }
            }
        } else {
            mossaValida = errCaselleInex;
        }
        return mossaValida;
    }

    /** Metodo che effettua una presa semplice */
    public final void presaSemplice(final double a, final double b, final int coloreGiocatore) {
        final int operatore = 9;
        int xA, xB, yA, yB, xC, yC, coeff1 = 1, coeff2 = 1;
        xA = traduzioneAscissa(a);
        yA = traduzioneOrdinata(a, xA);
        xB = traduzioneAscissa(b);
        yB = traduzioneOrdinata(b, xB);
        if (coloreGiocatore == 0) {
            coeff2 = -1;
        }
        if (b == a - operatore || b == a + operatore) {
            coeff1 = -1;
        }
        xC = xA + coeff2;
        yC = yA + coeff1;
        int num = damiera[xA][yA].getNumPedina();
        if (damiera[xA][yA].getDamaPedina()) {
            damiera[xB][yB].setDamaPedina();
        }
        short colorePedina = damiera[xA][yA].getColorePedina();
        damiera[xA][yA].delPedina();
        damiera[xB][yB].setPedina(num, colorePedina);
        damiera[xC][yC].delPedina();
    }

    /** Metodo che restituisce true se una pedina può effettuare una presa multipla e false altrimenti */
    public final int controlloPresaMultipla(final ArrayList<Double> coordinate, final int colore) {
        int mossaValida = 1;
        boolean presaMultipla = false;
        int i, n = coordinate.size() - 1;
        for (i = 0; i < n; i++) {
            mossaValida = controlloPresaSemplice(coordinate.get(i), coordinate.get(i + 1), colore, presaMultipla);
            if (mossaValida == 1) {
                presaMultipla = true;
            }
        }
        return mossaValida;
    }

    /** Metodo che effettua una presa multipla */
    public final void presaMultipla(final ArrayList<Double> coordinate, final int colore) {
        int i, n = coordinate.size() - 1;
        for (i = 0; i < n; i++) {
            presaSemplice(coordinate.get(i), coordinate.get(i + 1), colore);
        }
    }

}







