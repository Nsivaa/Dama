package TestDama;

import static org.junit.jupiter.api.Assertions.assertEquals;
import it.uniba.main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

public class TestGestioneOutput {
    GestioneOutput stampa;

    @BeforeEach
    void setUp() {
        this.stampa = new GestioneOutput();
    }

    @Test
    void testStampareFeedback() throws UnsupportedEncodingException, FileNotFoundException {
        String feedback = "";
        String output = "";
        String outputS = "";
        GestioneCasi gestioneCasi = new GestioneCasi();
        Damiera damiera = new Damiera();
        Tempo tempo = new Tempo();
        System.setIn(new FileInputStream("src/test/java/TestDama/test.txt"));
        gestioneCasi.menu(damiera, tempo);
        /** test feedback = 1 (mossa valida) */
        feedback = "Mossa valida\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
        stampa.stampareFeedback(1);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 2 (mossa non valida) */
        feedback = "Mossa non valida\n";
        outContent.reset();
        stampa.stampareFeedback(2);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 3 (colore non valido) */
        feedback = "Puoi muovere solo pedine del tuo colore\n";
        outContent.reset();
        stampa.stampareFeedback(3);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 4 (casella di arrivo piena) */
        feedback = "La casella verso cui ti vuoi spostare non è vuota\n";
        outContent.reset();
        stampa.stampareFeedback(4);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 5 (casella di partenza vuota) */
        feedback = "Non hai selezionato nessuna pedina. La casella scelta è vuota\n";
        outContent.reset();
        stampa.stampareFeedback(5);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 6 (casella non esistente) */
        feedback = "Hai selezionato caselle non esistenti\n";
        outContent.reset();
        stampa.stampareFeedback(6);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 7 (pedina stesso colore) */
        feedback = "Non puoi mangiare le tue stesse pedine\n";
        outContent.reset();
        stampa.stampareFeedback(7);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);        /** test feedback = 8 (pedina da mangiare inesistente) */
        feedback = "Non c'e' nessuna pedina avversaria da mangiare\n";
        outContent.reset();
        stampa.stampareFeedback(8);
        outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(feedback, outputS);
    }

    @Test
    void testHelp() throws UnsupportedEncodingException {
        String help = "Questa applicazione permette di giocare a dama in locale. Il gioco utilizza la notazione "
                + "algebrica per eseguire le mosse.\n"
                + "I comandi sono: \n"
                + "gioca\n"
                + "esci\n"
                + "numeri\n"
                + "I comandi disponibili durante il corso di una partita sono: \n"
                + "damiera\n"
                + "tempo\n"
                + "mosse\n"
                + "prese\n"
                + "abbandona\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
        stampa.help();
        String outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(help, outputS);
    }

    @Test
    void testStampaTempoGiocatore() throws UnsupportedEncodingException {
        double tempo = 100.0;
        String stampaTempo = ((int) (tempo / 60) + " m " + (int) tempo % 60 + " s \n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
        stampa.stampaTempoGiocatore(tempo);
        String outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(stampaTempo, outputS);
    }

    @Test
    void testMostrareMosse() throws UnsupportedEncodingException {
        ArrayList mosse = new ArrayList<String>();
        mosse.add(0, "21-17");
        mosse.add(1, "9-13");
        String output = "B: " + mosse.get(0) + "\n" + "N: " + mosse.get(1) + "\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
        stampa.mostrareMosse(mosse);
        String outputS = outContent.toString("UTF-8");
        outputS = outputS.replaceAll("\r\n", "\n");
        assertEquals(output, outputS);
    }

    @Test
    void testMostrarePrese() throws UnsupportedEncodingException {
        int pN = 5, pB = 5;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        StringBuffer buffer = new StringBuffer();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
        String output = "";
        buffer.append("Bianco:");
        for (int i = 0; i < pN; i++) {
            buffer.append("\u26C0 ");
        }
        buffer.append("\nNero:");
        for (int i = 0; i < pB; i++) {
            buffer.append("\u26C2 ");
        }
        buffer.append("\n");
        stampa.mostrarePrese(pN, pB);
        output = buffer.toString();
        assertEquals(output, outContent.toString("UTF-8"));
    }
}
