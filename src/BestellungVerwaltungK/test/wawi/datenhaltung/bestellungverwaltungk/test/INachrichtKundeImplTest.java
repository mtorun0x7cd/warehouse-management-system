package wawi.datenhaltung.bestellungverwaltungk.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wawi.datenhaltung.bestellungverwaltungk.impl.INachrichtKundeImpl;
import static org.junit.Assert.*;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.entities.Nachricht;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;

public class INachrichtKundeImplTest {

    private EntityManager em;
    private INachrichtKundeImpl classUnderTest;
    private Kunde testKunde;
    private Nachricht testNachricht;
    private List<Nachricht> testNachrichtListe;

    private void initTestEntities() {
        testKunde = new Kunde(null, "Schmitz", "Hans", "Muster Str. 20", Date.valueOf("2019-01-01"));

        testNachrichtListe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testNachrichtListe.add(new Nachricht(null, "Betreff", "BspNachricht", Date.valueOf("2019-01-01"), "ungelesen", "anwawi"));
            testNachrichtListe.get(i).setKunde(testKunde);
        }

        testNachricht = testNachrichtListe.get(0);
    }

    /*
    @Before: angenommen()
    ANGENOMMEN der EntityManager wird korrekt geholt,
    UND die Implementierung der INachrichtKunde Schnittstelle wird als classUnderTest instanziiert,
    UND der EntityManager wird per setEntityManager Methode der classUnderTest gesetzt,
    UND die Transaktion von em wird gestartet,
    UND die Daten der betreffenden Entitäten wurden in der DB gelöscht.
     */
    @Before
    public void angenommen() {
        em = new IDatabaseImpl().getEntityManager();
        classUnderTest = new INachrichtKundeImpl();
        classUnderTest.setEntityManager(em);
        initTestEntities();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM nachricht").executeUpdate();
    }

    /*
    @After: amEnde()
    AM ENDE wird die Transaktion zurück gesetzt.
     */
    @After
    public void amEnde() {
        em.getTransaction().rollback();
    }

    /*
    @Test: sendeNachrichtAnSach_00()
    WENN die zu sendende Nachricht vom Typ anwawi ist,
    UND der Status der Nachricht ungelesen ist,
    UND die übergebene Kunden ID in der DB existiert,
    UND die Methode sendNachrichtAnSach mit ID und Nachricht aufgerufen wird,
    DANN sollte sie TRUE zurückliefern,
    UND die Nachricht in der DB vorhanden sein.
     */
    @Test
    public void sendeNachrichtAnSach_00() {
        em.persist(testKunde);
        em.flush();
        boolean istWert = classUnderTest.sendeNachrichtAnSach(testNachricht);
        em.flush();
        assertTrue(istWert);
        assertEquals(testNachricht, em.find(Nachricht.class, testNachricht.getNid()));
    }

    /*
    @Test: sendeNachrichtAnSach_01()
    WENN die zu sendende Nachricht vom Typ ankunde ist,
    UND der Status der Nachricht ungelesen ist,
    UND die übergebene Kunden ID in der DB existiert,
    UND die Methode sendeNachrichtAnSach mit ID und Nachricht aufgerufen wird,
    DANN sollte sie FALSE zurückliefern,
    UND die Nachricht nicht in der DB vorhanden sein.
     */
    @Test
    public void sendeNachrichtAnSach_01() {
        testNachricht.setTyp("ankunde");
        em.persist(testKunde);
        em.flush();
        boolean istWert = classUnderTest.sendeNachrichtAnSach(testNachricht);
        em.flush();
        assertFalse(istWert);
        assertNull(testNachricht.getNid());
    }

    /*
    @Test: sendeNachrichtAnSach_02()
    WENN die zu sendende Nachricht vom Typ anwawi ist,
    UND der Status der Nachricht gelesen ist,
    UND die übergebene Kunden ID in der DB existiert,
    UND die Methode sendeNachrichtAnSach mit ID und Nachricht aufgerufen wird,
    DANN sollte sie FALSE zurückliefern,
    UND die Nachricht nicht in der DB vorhanden sein.
     */
    @Test
    public void sendeNachrichtAnSach_02() {
        testNachricht.setTyp("anwawi");
        testNachricht.setStatus("gelesen");
        em.persist(testKunde);
        em.flush();
        boolean istWert = classUnderTest.sendeNachrichtAnSach(testNachricht);
        em.flush();
        assertFalse(istWert);
        assertNull(testNachricht.getNid());
    }

    /*
    @Test: sendeNachrichtAnSach_03()
    WENN die zu sendende Nachricht vom Typ anwawi ist,
    UND der Status der Nachricht ungelesen ist,
    UND die übergebene Kunden ID nicht in der DB existiert,
    UND die Methode sendeNachrichtAnSach mit ID und Nachricht aufgerufen wird,
    DANN sollte sie FALSE zurückliefern,
    UND die Nachricht nicht in der DB vorhanden sein.
     */
    @Test
    public void sendeNachrichtAnSach_03() {
        testNachricht.setTyp("anwawi");
        em.persist(testKunde);
        em.flush();
        em.remove(testKunde);
        boolean istWert = classUnderTest.sendeNachrichtAnSach(testNachricht);
        em.flush();
        assertFalse(istWert);
        assertNull(testNachricht.getNid());
    }

    /*
    @Test: getEigeneNachrichten_00()
    WENN x (x>0) Nachrichten in der DB existieren,
    UND y (y<x) Nachrichten eines Kunden in der DB existieren,
    UND die Methode getEigeneNachrichten mit der ID des Kunden aufgerufen wird,
    DANN sollte sie eine Liste mit y Nachrichten zurückliefern.
     */
    @Test
    public void getEigeneNachrichten_00() {
        em.persist(testKunde);
        Kunde dummyKunde = new Kunde(null, "Donald", "Duck", "Muster Str. 55", Date.valueOf("2019-01-01"));
        em.persist(dummyKunde);
        em.flush();
        int x = testNachrichtListe.size();
        int y = 3;
        List<Nachricht> istArray = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            testNachricht = testNachrichtListe.get(i);

            if (i < y) {
                testNachricht.setKunde(testKunde);
                istArray.add(testNachricht);
            } else {
                testNachricht.setKunde(dummyKunde);
            }

            em.persist(testNachricht);
        }
        em.flush();
        testKunde.setNachrichtList(istArray);
        testNachricht = testNachrichtListe.get(0);
        List<Nachricht> istWert = classUnderTest.getEigeneNachrichten(testKunde.getKid());
        assertEquals(y, istWert.size());
    }

    /*
    @Test: getEigeneNachrichten_01()
    WENN x (x>0) Nachrichten in der DB existieren,
    UND keine Nachrichten eines bestimmten Kunden in der DB existieren,
    UND die Methode getEigeneNachrichten mit der ID des Kunden aufgerufen wird,
    DANN sollte sie eine leere Liste zurückliefern.
     */
    @Test
    public void getEigeneNachrichten_01() {
        em.persist(testKunde);
        Kunde dummyKunde = new Kunde(null, "Heinz", "Tomaten Ketchup", "Muster Str. 222", Date.valueOf("2019-01-01"));
        em.persist(dummyKunde);
        em.flush();
        for (Nachricht n : testNachrichtListe) {
            n.setKunde(dummyKunde);
            em.persist(n);
        }
        dummyKunde.setNachrichtList(testNachrichtListe);
        List<Nachricht> istWert = classUnderTest.getEigeneNachrichten(testKunde.getKid());
        assertTrue(istWert.isEmpty());
    }

    /*
    @Test: deleteNachricht_00()
    WENN eine Testnachricht in der DB existiert,
    UND die Methode deleteNachricht mit der ID der Testnachricht aufgerufen wird,
    DANN sollte sie TRUE zurückliefern,
    UND die Testnachricht sollte nicht mehr in der DB existieren.
     */
    @Test
    public void deleteNachricht_00() {
        em.persist(testKunde);
        em.persist(testNachricht);
        em.flush();
        boolean istWert = classUnderTest.deleteNachricht(testNachricht.getNid());
        assertTrue(istWert);
        assertNull(em.find(Nachricht.class, testNachricht.getNid()));
    }

    /*
    @Test: deleteNachricht_01()
    WENN eine Testnachricht nicht in der DB existiert,
    UND die Methode deleteNachricht mit der ID der Testnachricht aufgerufen wird,
    DANN sollte sie FALSE zurückliefern.
     */
    @Test
    public void deleteNachricht_01() {
        em.persist(testKunde);
        em.persist(testNachricht);
        em.flush();
        em.remove(testNachricht);
        boolean istWert = classUnderTest.deleteNachricht(testNachricht.getNid());
        assertFalse(istWert);
    }
}
