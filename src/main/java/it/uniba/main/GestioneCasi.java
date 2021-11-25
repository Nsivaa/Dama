package it.uniba.main;

import java.util.ArrayList;
import java.util.Scanner;

/** La classe GestioneCasi si occupa di creare il menu dell'applicazione per l'utente.
 * Questa classe permette di fare tutte le operazioni che prevede l'applicazione per l'utente.
 * Essendo una classe di tipo BOUNDARY, si interfaccia con AppMain (CONTROL) e con GestioneOutput (BOUNDARY).
 * Infatti il metodo "menu" prende in input un oggetto di tipo Damiera e un oggetto di tipo Tempo,
 * passati entrambi da AppMain.
 */

/** BOUNDARY */
public class GestioneCasi {
    @SuppressWarnings("checkstyle:MethodLength")
    public final void menu(final Damiera damiera, final Tempo tempoGioco) {
        GestioneOutput stampa = new GestioneOutput();
        int colore, feedback, pB = 0, pN = 0;
        double turno = 1, tempoB = 0, tempoN = 0;
        String colGiocatore, sCommand;
        Scanner command = new Scanner(System.in, "UTF-8");
        boolean isStarted = false, exit = false, turnoFinito;
        ArrayList<String> mosse = new ArrayList<String>();
        long tempoInizB = 0, tempoInizN = 0;
        System.out.println("Applicazione avviata");
        do {
            double tempo = 0;
            if (isStarted) {
                if (turno % 2 != 0) {
                    tempoInizB = tempoGioco.setTempoIniz();
                } else if (turno % 2 == 0) {
                    tempoInizN = tempoGioco.setTempoIniz();
                }
            }
            do {
                turnoFinito = false;
                if (turno % 2 == 0) {
                    colore = 1; colGiocatore = "nero";
                } else {
                    colore = 0; colGiocatore = "bianco";
                }
                if (isStarted) {
                    System.out.println("\n\nTurno " + (int) turno + " giocatore " + colGiocatore);
                }
                System.out.println("Inserisci un comando :");
                sCommand = command.nextLine();
                if ((sCommand.contains("-") || sCommand.contains("x")) && isStarted) {
                    if (sCommand.contains("-")) {
                        String[] parts = sCommand.split("-");
                        double o = Double.parseDouble(parts[0]), e = Double.parseDouble(parts[1]);
                        feedback = damiera.controlloSpostamento(o, e, colore);
                        if (feedback == 1) {
                            damiera.spostarePedina(o, e);
                            if (damiera.controllareDamatura(e, colore)) {
                                damiera.damatura(e);
                            }
                            mosse.add(sCommand);
                            turnoFinito = true; turno++;
                            if (colore == 0) {
                                tempoB = tempoGioco.aggiornaTempo(tempoB, tempoInizB);
                            } else {
                                tempoN = tempoGioco.aggiornaTempo(tempoN, tempoInizN);
                            }
                        }
                        stampa.stampareFeedback(feedback);
                    }
                    if (sCommand.contains("x")) {
                        String[] parts = sCommand.split("x");
                        int sottoStringhe = parts.length;
                        ArrayList<Double> coordinate = new ArrayList<Double>();
                        for (int i = 0; i < sottoStringhe; i++) {
                            coordinate.add(Double.parseDouble(parts[i]));
                        }
                        feedback = damiera.controlloPresaMultipla(coordinate, colore);
                        if (feedback == 1) {
                            damiera.presaMultipla(coordinate, colore);
                            if (colore == 0) {
                                pN = pN + coordinate.size() - 1;
                            } else {
                                pB = pB + coordinate.size() - 1;
                            }
                            if (damiera.controllareDamatura(coordinate.get(sottoStringhe - 1), colore)) {
                                damiera.damatura(coordinate.get(sottoStringhe - 1));
                            }
                            coordinate.clear();
                            mosse.add(sCommand);
                            turnoFinito = true; turno++;
                            if (colore == 0) {
                                tempoB = tempoGioco.aggiornaTempo(tempoB, tempoInizB);
                            } else {
                                tempoN = tempoGioco.aggiornaTempo(tempoN, tempoInizN);
                            }
                        }
                        stampa.stampareFeedback(feedback);
                    }
                } else {
                    switch (sCommand) {
                        case "damiera":
                            if (!isStarted) {
                                System.out.println("Il comando inserito non è valido, "
                                        + "inizia la partita con il comando gioca"
                                        + "prima di poter utilizzare il comando.");
                            } else {
                                stampa.stamparePezzi(damiera); /** Stampa damiera con le pedine posizionate. */
                            }
                            break;
                        case "help": /** Mostra tutti i comandi disponibili all'utente. */
                            stampa.help();
                            break;
                        case "esci": /** Permette all'utente di poter uscire dall'applicazione. */
                            String risposta;
                            do {
                                System.out.println("Sei sicuro di voler uscire? (si) (no)");
                                risposta = command.nextLine();
                                if (risposta.equals("si")) {
                                    System.out.println("Uscita in corso..."); exit = true; turnoFinito = true;
                                } else if (risposta.equals("no")) {
                                    System.out.println("Puoi continuare a giocare");
                                } else {
                                    System.out.println("La risposta non e' accettata, rispondere (si) o (no)");
                                }
                            } while (!exit && !risposta.equals("no"));
                            break;
                        case "gioca": /** Permette all'utente di poter avviare una partita. */
                            if (!isStarted) {
                                isStarted = true; turnoFinito = true; tempoB = 0; tempoN = 0; pB = 0; pN = 0;
                                System.out.println("La partita e' iniziata!");
                                System.out.println("Per spostare una pedina utillizare la dicitura numero-numero");
                                System.out.println("Per mangiare una o più pedine utillizare la dicitura "
                                        + "numeroxnumero");
                            } else {
                                System.out.println("C'e' una partita in corso... \n");
                                System.out.println("Per spostare una pedina utillizare la dicitura numero-numero");
                                System.out.println("Per mangiare una o più pedine utillizare la dicitura "
                                        + "numeroxnumero");
                                System.out.println("Oppure digita help per mostrare gli altri comandi");
                            }
                            break;
                        case "abbandona":
                            if (isStarted) {
                                do {
                                    System.out.println("Sei sicuro di voler abbandonare? (si/no)");
                                    risposta = command.nextLine();
                                    if (risposta.equals("si")) {
                                        turnoFinito = true; isStarted = false; turno = 1; mosse.clear();
                                        damiera.resettaDamiera();
                                        if (colore == 0) {
                                            System.out.println("Il nero vince per abbandono del bianco");
                                        } else {
                                            System.out.println("Il bianco vince per abbandono del nero");
                                        }
                                    } else if (risposta.equals("no")) {
                                        System.out.println("Puoi continuare la partita");
                                    } else {
                                        System.out.println("La risposta non e' accettata, rispondere con (si) o (no)");
                                    }
                                } while ((!risposta.equals("si")) && (!risposta.equals("no")));
                            } else {
                                System.out.println("Non e' presente nessuna partita in corso");
                            }
                            break;
                        case "tempo":
                            if (!isStarted) {
                                System.out.println("Il comando inserito non e' valido, inizia la partita con il "
                                        + "comando gioca prima di poter utilizzare il comando.");
                            } else {
                                if (colore == 0) {
                                    tempo = tempoGioco.tempoCorr(tempoInizB) + tempoB;
                                    aggiornaTempo(stampa, tempo, tempoN);
                                } else {
                                    tempo = tempoGioco.tempoCorr(tempoInizN) + tempoN;
                                    aggiornaTempo(stampa, tempoB, tempo);
                                }
                            }
                            break;
                        case "numeri":
                            stampa.stampareDamiera(damiera); /** Stampa la damiera con la numerazione. */
                            break;

                        case "mosse":
                            if (!isStarted) {
                                System.out.println("Il comando inserito non e' valido, inizia la partita con il comando"
                                        + "gioca prima di poter utilizzare il comando.");
                            } else {
                                stampa.mostrareMosse(mosse);
                            }
                            break;
                        case "prese":
                            if (!isStarted) {
                                System.out.println("Il comando inserito non e' valido, inizia la partita con il"
                                        + "comando gioca prima di poter utilizzare il comando.");
                            } else {
                                stampa.mostrarePrese(pN, pB);
                            }
                            break;
                        default:
                            System.out.println("Il comando inserito non e' valido, digita gioca per iniziare una"
                                    + "partita o help per mostrare gli altri comandi");
                    }
                }
            } while (!turnoFinito);
        } while (!exit);
    }

    final void aggiornaTempo(final GestioneOutput stampa, final double tempoB, final double tempoN) {
        System.out.println("Tempo giocatore bianco: ");
        stampa.stampaTempoGiocatore(tempoB);
        System.out.println("Tempo giocatore nero: ");
        stampa.stampaTempoGiocatore(tempoN);
    }
}
