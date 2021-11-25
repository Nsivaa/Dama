package TestDama;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniba.main.Damiera;
import java.util.ArrayList;

public class TestDamiera {
    Damiera damiera;
    @BeforeEach
    void setUp() {
        this.damiera = new Damiera();
    }
    @Test
    void testTraduzioneAscissa() {
        setUp();
        double coordinata;
        // primo test coordinata valida
        coordinata = 14;
        assertEquals(3,damiera.traduzioneAscissa(coordinata));
        // secondo test coordinata valida
        coordinata = 27;
        assertEquals(6,damiera.traduzioneAscissa(coordinata));
        // test coordinata nulla
        coordinata = 0;
        assertEquals(-1,damiera.traduzioneAscissa(coordinata));
        //test coordinata negativa
        coordinata = -1;
        assertEquals(-1,damiera.traduzioneAscissa(coordinata));
        //test coordinata non esistente
        coordinata = 33;
        assertEquals(8,damiera.traduzioneAscissa(coordinata));
    }
    @Test
    void testTraduzioneOrdinata() {
        setUp();
        double coordinata;
        int ascissa;
        // primo test coordinata valida
        coordinata = 14;
        ascissa = damiera.traduzioneAscissa(coordinata);
        assertEquals(3,damiera.traduzioneOrdinata(coordinata,ascissa));
        // secondo test coordinata valida
        coordinata = 27;
        ascissa = damiera.traduzioneAscissa(coordinata);
        assertEquals(4,damiera.traduzioneOrdinata(coordinata,ascissa));
        // test coordinata nulla
        coordinata = 0;
        ascissa = damiera.traduzioneAscissa(coordinata);
        assertEquals(7,damiera.traduzioneOrdinata(coordinata,ascissa));
        //test coordinata negativa
        coordinata = -1;
        ascissa = damiera.traduzioneAscissa(coordinata);
        assertEquals(-3,damiera.traduzioneOrdinata(coordinata,ascissa));
        //test coordinata non esistente
        coordinata = 33;
        ascissa = damiera.traduzioneAscissa(coordinata);
        assertEquals(0,damiera.traduzioneOrdinata(coordinata,ascissa));
    }
    @Test
    void testGetSIZE(){
        setUp();
        assertEquals(8,damiera.getSIZE());
    }
    @Test
    void testControlloSpostamento(){
        setUp();
        double partenza, arrivo;
        // test mossa valida bianco
        partenza = 21;
        arrivo = 17;
        assertEquals(1,(damiera.controlloSpostamento(partenza, arrivo, 0)));
        //test mossa valida nero
        partenza = 10;
        arrivo = 13;
        assertEquals(1,(damiera.controlloSpostamento(partenza, arrivo, 1)));
        //test mossa casella irraggiungibile
        partenza = 21;
        arrivo = 14;
        assertEquals(2,(damiera.controlloSpostamento(partenza, arrivo, 0)));
        //test mossa non diagonale nero
        partenza = 10;
        arrivo = 15;
        assertEquals(2,(damiera.controlloSpostamento(partenza, arrivo, 1)));
        //test mossa pedina esterna
        partenza = 9;
        arrivo = 13;
        assertEquals(1,(damiera.controlloSpostamento(partenza, arrivo, 1)));
        //test mossa pedina esterna non valida
        partenza = 9;
        arrivo = 14;
        assertEquals(2,(damiera.controlloSpostamento(partenza, arrivo, 1)));
        //test mossa casella di arrivo piena e colore errato
        damiera.spostarePedina(21,13);
        partenza = 13;
        arrivo = 9;
        assertEquals(3,(damiera.controlloSpostamento(partenza, arrivo, 1)));
        setUp();
        // test mossa valida ma pedina giocatore opposto
        partenza = 21;
        arrivo = 17;
        assertEquals(3,(damiera.controlloSpostamento(partenza, arrivo, 1)));
        // test mossa casella occupata
        partenza = 21;
        arrivo = 10;
        assertEquals(4,(damiera.controlloSpostamento(partenza, arrivo, 0)));
        // test mossa da casella vuota
        partenza = 18;
        arrivo = 13;
        assertEquals(5,(damiera.controlloSpostamento(partenza, arrivo, 0)));
        // test mossa da casella inesistente
        partenza = 33;
        arrivo = 17;
        assertEquals(6,(damiera.controlloSpostamento(partenza, arrivo, 0)));
        // test mossa a casella inesistente
        partenza = 21;
        arrivo = 33;
        assertEquals(6,(damiera.controlloSpostamento(partenza, arrivo, 0)));
        // test mossa da casella inesistente (negativa)
        partenza = -1;
        arrivo = 17;
        assertEquals(6,(damiera.controlloSpostamento(partenza, arrivo, 0)));
    }
    @Test
    void testControlloPresaMultipla() {
        setUp();
        ArrayList<Double> coordinate = new ArrayList<>();
        damiera.spostarePedina(21,13);
        coordinate.add(0,9.0);
        coordinate.add(1,18.0);
        // test mossa valida
        assertEquals(1,damiera.controlloPresaMultipla(coordinate,1));
        setUp();
        coordinate.clear();
        coordinate.add(0,21.0);
        coordinate.add(1,20.0);
        // test mossa casella irraggiungibile
        assertEquals(2,damiera.controlloPresaMultipla(coordinate,0));
        // test mossa pedina colore opposto
        assertEquals(3,damiera.controlloPresaMultipla(coordinate,1));
        damiera.spostarePedina(21,13);
        coordinate.clear();
        coordinate.add(0,13.0);
        coordinate.add(1,6.0);
        // test mossa casella occupata
        assertEquals(4,damiera.controlloPresaMultipla(coordinate,0));
        coordinate.clear();
        coordinate.add(0,18.0);
        coordinate.add(1,6.0);
        // test mossa casella di partenza vuota
        assertEquals(5,damiera.controlloPresaMultipla(coordinate,0));
        coordinate.clear();
        coordinate.add(0,-1.0);
        coordinate.add(1,6.0);
        // test mossa casella di partenza non esistente
        assertEquals(6,damiera.controlloPresaMultipla(coordinate,0));
        coordinate.clear();
        coordinate.add(0,13.0);
        coordinate.add(1,33.0);
        // test mossa casella di partenza non esistente
        assertEquals(6,damiera.controlloPresaMultipla(coordinate,0));
        setUp();
        damiera.spostarePedina(22,18);
        coordinate.clear();
        coordinate.add(0,21.0);
        coordinate.add(1,14.0);
        // test mossa cattura pedina alleata
        assertEquals(7,damiera.controlloPresaMultipla(coordinate,0));
        setUp();
        coordinate.clear();
        coordinate.add(0,21.0);
        coordinate.add(1,14.0);
        // test mossa cattura pedina inesistente
        assertEquals(8,damiera.controlloPresaMultipla(coordinate,0));
        setUp();
        damiera.spostarePedina(27,14);
        damiera.spostarePedina(28,16);
        coordinate.clear();
        coordinate.add(0,10.0);
        coordinate.add(1,19.0);
        coordinate.add(2,28.0);
        // test mossa presa multipla valida
        assertEquals(1,damiera.controlloPresaMultipla(coordinate,1));
    }
    @Test
    void testSpostamento() {
        setUp();
        double partenza = 4, arrivo = 32;
        int asc = damiera.traduzioneAscissa(32);
        int ord = damiera.traduzioneOrdinata(32, asc);
        damiera.spostarePedina(partenza, arrivo);
        damiera.damatura(arrivo);
        damiera.spostarePedina(arrivo, partenza);
        assertTrue(damiera.getDamiera(asc, ord).getDamaPedina());
    }
    @Test
    void testDamatura() {
        setUp();
        //test su casella contenente dama
        damiera.spostarePedina(26.0,2.0);
        if(damiera.controllareDamatura(2.0,0)) {
            damiera.damatura(2);
        }
        assertTrue(damiera.getDamiera(0,2).getDamaPedina());
        //test su casella non contenente dama
        assertFalse(damiera.getDamiera(2,2).getDamaPedina());
    }

    @Test
    void testResettaDamiera() {
        setUp();
        damiera.spostarePedina(9,13);
        damiera.resettaDamiera();
        assertFalse(damiera.getDamiera(0,2).getStato());
    }
    @Test
    void testControllareDamatura() {
        setUp();
        damiera.spostarePedina(26.0,2.0);
        assertTrue(damiera.controllareDamatura(2.0,0));
        setUp();
        damiera.spostarePedina(26.0,10.0);
        assertFalse(damiera.controllareDamatura(10.0,0));
    }
    @Test
    void testPresaSemplice() {
        damiera.spostarePedina(21.0,17.0);
        damiera.spostarePedina(13.0,10.0);
        damiera.presaSemplice(17,10,0);
        int asc= damiera.traduzioneAscissa(13.0);
        int ord= damiera.traduzioneOrdinata(13.0,asc);
        assertTrue(damiera.getDamiera(asc,ord).getStato());
    }
    @Test
    void testPresaMultipla() {
        ArrayList<Double> coordinate = new ArrayList<>();
        int asc1, asc2, ord1, ord2;
        damiera.spostarePedina(27,14);
        damiera.spostarePedina(28,16);
        coordinate.add(0,10.0);
        coordinate.add(1,19.0);
        coordinate.add(2,28.0);
        asc1 = damiera.traduzioneAscissa(23);
        ord1 = damiera.traduzioneOrdinata(23,(damiera.traduzioneAscissa(23)));
        asc2 = damiera.traduzioneAscissa(14);
        ord2 = damiera.traduzioneOrdinata(14,(damiera.traduzioneAscissa(14)));
        // test mossa presa multipla valida
        damiera.presaMultipla(coordinate,1);
        assertFalse(damiera.getDamiera(asc1,ord1).getStato());
        assertFalse(damiera.getDamiera(asc2,ord2).getStato());
    }
}
