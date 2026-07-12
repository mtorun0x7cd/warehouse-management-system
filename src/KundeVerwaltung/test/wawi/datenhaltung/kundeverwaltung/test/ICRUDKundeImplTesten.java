package wawi.datenhaltung.kundeverwaltung.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wawi.datenhaltung.kundeverwaltung.impl.ICRUDKundeImpl;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;

public class ICRUDKundeImplTesten {

    private EntityManager em;
    private ICRUDKundeImpl classUnderTest;
    private List<Kunde> testKundenListe;
    private Kunde testKunde;

    private void setTestKundenListe() {
        testKundenListe = new ArrayList<>();
        testKundenListe.add(new Kunde(null, "Krueger", "Schmitz", "MusterStr. 2", Date.valueOf("2019-01-01")));
        testKundenListe.add(new Kunde(null, "Wilhelm", "Hans", "MusterStr. 3", Date.valueOf("2019-01-01")));
        testKundenListe.add(new Kunde(null, "Cengiz", "Five", "MusterStr. 4", Date.valueOf("2019-01-01")));
        testKunde = testKundenListe.get(0);
    }

    /*
    @Before: angenommen()
    ANGENOMMEN der EntityManager wird korrekt geholt,
    UND die Implementierung der ICRUDKunde Schnittstelle wird als classUnderTest instanziiert,
    UND der EntityManager wird per setEntityManager Methode der classUnderTest gesetzt,
    UND die Transaktion von em wird gestartet,
    UND die Daten der betreffenden Entitäten wurden in der DB gelöscht.
     */
    @Before
    public void angenommen() {
        em = new IDatabaseImpl().getEntityManager();
        classUnderTest = new ICRUDKundeImpl();
        classUnderTest.setEntityManager(em);
        em.getTransaction().begin();
        setTestKundenListe();
        // Löscht alle Daten aus der DB der betreffenden Entitäten und ihrer Relationen
        em.createNativeQuery("DELETE FROM nachricht").executeUpdate();
        em.createNativeQuery("DELETE FROM bestellungsposition").executeUpdate();
        em.createNativeQuery("DELETE FROM lagerverkehr").executeUpdate(); 
        em.createNativeQuery("DELETE FROM lieferposition").executeUpdate();
        em.createNativeQuery("DELETE FROM bestellung").executeUpdate();
        em.createNativeQuery("DELETE FROM kunde").executeUpdate();   
        em.createNativeQuery("DELETE FROM produkt").executeUpdate();
        em.createNativeQuery("DELETE FROM lagerort").executeUpdate();
        em.createNativeQuery("DELETE FROM lager").executeUpdate();
        em.createNativeQuery("DELETE FROM kategorie").executeUpdate();
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
    @Test: getKundeById_00()
    WENN ein Testkunde bereits in der DB existiert,
    UND die Methode getKundeById mit der Id des Testkunden aufgerufen wird,
    DANN sollte sie den Testkunden zurückliefern.
     */
    @Test
    public void getKundeById_00() {
        em.persist(testKunde);
        em.flush();
        Kunde istWert = classUnderTest.getKundeById(testKunde.getKid());
        assertEquals(testKunde, istWert);
    }

    /*
    @Test: getKundeById_01()
    WENN ein Testkunde nicht in der DB existiert,
    UND die Methode getKundeById mit der Id des Testkunden aufgerufen wird,
    DANN sollte sie NULL zurückliefern.
     */
    @Test
    public void getKundeById_01() {
        em.persist(testKunde);
        em.flush();
        em.remove(testKunde);
        Kunde istWert = classUnderTest.getKundeById(testKunde.getKid());
        assertNull(istWert);
    }

    /*
    @Test: getKundenListe_00()
    WENN x (x>0) Kunden in der DB existieren,
    UND die Methode getKundenListe aufgerufen wird,
    DANN sollte sie eine Liste mit x Kunden zurückliefern.
     */
    @Test
    public void getKundenListe_00() {
        for (Kunde k : testKundenListe) {
            em.persist(k);
        }
        em.flush();
        List<Kunde> istWert = classUnderTest.getKundenListe();
        assertEquals(testKundenListe.size(), istWert.size());
    }

    /*
    @Test: getKundenListe_01()
    WENN keine Kunden in der DB existieren,
    UND die Methode getKundenListe aufgerufen wird,
    DANN sollte sie eine leere Liste zurückliefern.
     */
    @Test
    public void getKundenListe_01() {
        List<Kunde> istWert = classUnderTest.getKundenListe();
        assertTrue(istWert.isEmpty());
    }

    /*
    @Test: insertKunde_00()
    WENN die Methode insertKunde mit einem Testkunden aufgerufen wird,
    UND die ID des Testkunden gleich null ist,
    DANN sollte sie TRUE zurückliefern,
    UND der Testkunde sollte in der DB existieren.
     */
    @Test
    public void insertKunde_00() {
        // testKunde.kid ist per default = null
        boolean istWert = classUnderTest.insertKunde(testKunde);
        em.flush();
        assertTrue(istWert);
        assertNotNull(em.find(Kunde.class, testKunde.getKid()));
    }

    /*
    @Test: insertKunde_01()
    WENN die ID eines Testkunden mit einem Wert ungleich null besetzt ist,
    UND die Methode insertKunde mit dem Testkunden aufgerufen wird,
    DANN sollte sie FALSE zurückliefern,
    UND und die DB wurde nicht verändert.
     */
    @Test
    public void insertKunde_01() {
        testKunde.setKid(999);
        boolean istWert = classUnderTest.insertKunde(testKunde);
        assertFalse(istWert);
        assertNull(em.find(Kunde.class, testKunde.getKid()));
    }

    /*
    @Test: updateKunde_00()
    WENN ein Testkunde in der DB existiert,
    UND die Methode updateKunde mit einem verändertem Testkunden (aber gleicher ID)
    aufgerufen wird,
    DANN sollte sie TRUE zurückliefern,
    UND der Testkunde sollte in der DB verändert sein.
     */
    @Test
    public void updateKunde_00() {
        em.persist(testKunde);
        em.flush();
        testKunde.setName("Jim");
        boolean istWert = classUnderTest.updateKunde(testKunde);
        assertTrue(istWert);
        assertEquals("Jim", testKunde.getName());
    }

    /*
    @Test: updateKunde_01()
    WENN ein Testkunde nicht in der DB existiert,
    UND die Methode updateKunde mit dem Testkunden aufgerufen wird,
    DANN sollte sie FALSE zurückliefern,
    UND der Testkunde sollte nicht in der DB existieren.
     */
    @Test
    public void updateKunde_01() {
        em.persist(testKunde);
        em.flush();
        em.remove(testKunde);
        boolean istWert = classUnderTest.updateKunde(testKunde);
        assertFalse(istWert);
        assertNull(em.find(Kunde.class, testKunde.getKid())); // und danach auch nicht
    }

    /*
    @Test: deleteKunde_00()
    WENN ein Testkunde in der DB existiert,
    UND die Methode deleteKunde mit der ID des Testkunden aufgerufen wird,
    DANN sollte sie TRUE zurückliefern,
    UND der Testkunde sollte nicht mehr in der DB existieren.
     */
    @Test
    public void deleteKunde_00() {
        em.persist(testKunde);
        em.flush();
        boolean istWert = classUnderTest.deleteKunde(testKunde.getKid());
        assertTrue(istWert);
        assertNull(em.find(Kunde.class, testKunde.getKid()));
    }

    /*
    @Test: deleteKunde_01()
    WENN ein Testkunde nicht in der DB existiert,
    UND die Methode deleteKunde mit der ID des Testkunden aufgerufen wird,
    DANN sollte sie FALSE zurückliefern.
     */
    @Test
    public void deleteKunde_01() {
        em.persist(testKunde);
        em.flush();
        em.remove(testKunde);
        boolean istWert = classUnderTest.deleteKunde(testKunde.getKid());
        assertFalse(istWert);
    }
}
