package wawi.datenhaltung.bestellungverwaltungk.test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wawi.datenhaltung.bestellungverwaltungk.impl.IBestellungKundeImpl;
import static org.junit.Assert.*;
import wawi.datenhaltung.wawidbmodel.entities.Bestellung;
import wawi.datenhaltung.wawidbmodel.entities.Kategorie;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.entities.Lager;
import wawi.datenhaltung.wawidbmodel.entities.Lagerort;
import wawi.datenhaltung.wawidbmodel.entities.Produkt;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;

public class IBestellungKundeImplTesten {

    private EntityManager em;
    private IBestellungKundeImpl classUnderTest;
    private Kunde testKunde;
    private Bestellung testBestellung;
    private List<Bestellung> testBestellungListe;

    private void initTestEntities() {
        testKunde = new Kunde(null, "Schmitz", "Hans", "Muster Str. 20", Date.valueOf("2019-01-01"));

        testBestellungListe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testBestellungListe.add(new Bestellung(null, "Muster Str. 22", "Muster Str. 21", Date.valueOf("2019-01-01"), "n", BigDecimal.valueOf(9500), BigDecimal.valueOf(10500)));
        }

        testBestellung = testBestellungListe.get(0);
    }

    /*
    @Before: angenommen()
    ANGENOMMEN der EntityManager wird korrekt geholt,
    UND die Implementierung der IBestellungKunde Schnittstelle wird als classUnderTest instanziiert,
    UND der EntityManager wird per setEntityManager Methode der classUnderTest gesetzt,
    UND die Transaktion von em wird gestartet,
    UND die Daten der betreffenden Entitäten wurden in der DB gelöscht.
     */
    @Before
    public void angenommen() {
        em = new IDatabaseImpl().getEntityManager();
        classUnderTest = new IBestellungKundeImpl();
        classUnderTest.setEntityManager(em);
        initTestEntities();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM bestellungsposition").executeUpdate();
        em.createNativeQuery("DELETE FROM lieferposition").executeUpdate();
        em.createNativeQuery("DELETE FROM lagerverkehr").executeUpdate();
        em.createNativeQuery("DELETE FROM bestellung").executeUpdate();
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
    @Test: bestellungAnlegen_00()
    WENN die Methode bestellungAnlegen mit einer Testbestellung aufgerufen wird,
    UND die ID der Testbestellung gleich null ist,
    UND der Status der Testbestellung neu ist,
    DANN sollte sie TRUE zurückliefern,
    UND die Testbestellung sollte in der DB existieren.
     */
    @Test
    public void bestellungAnlegen_00() {
        em.persist(testKunde);
        em.flush();
        testBestellung.setKunde(testKunde);
        boolean istWert = classUnderTest.bestellungAnlegen(testBestellung);
        em.flush();
        assertNotNull(testBestellung.getBid());
        assertTrue(istWert);
        assertNotNull(em.find(Bestellung.class, testBestellung.getBid()));
    }

    /*
    @Test: bestellungAnlegen_01()
    WENN die ID einer Testbestellung mit einem Wert ungleich null besetzt ist,
    UND die Methode bestellungAnlegen mit der Testbestellung aufgerufen wird,
    UND der Status der Testbestellung neu ist,
    DANN sollte sie FALSE zurückliefern,
    UND und die DB wurde nicht verändert.
     */
    @Test
    public void bestellungAnlegen_01() {
        testBestellung.setKunde(testKunde);
        testBestellung.setBid(42);
        boolean istWert = classUnderTest.bestellungAnlegen(testBestellung);
        Query pruefeAnzNachrichten = em.createNativeQuery("SELECT * FROM bestellung", Bestellung.class);
        List<Bestellung> nachrichtenLst = pruefeAnzNachrichten.getResultList();
        assertTrue(nachrichtenLst.isEmpty());
        assertFalse(istWert);
    }

    /*
    @Test: bestellungAnlegen_02()
    WENN die ID einer Testbestellung mit dem Wert null besetzt ist,
    UND die Methode bestellungAnlegen mit der Testbestellung aufgerufen wird,
    UND der Status der Testbestellung nicht neu ist,
    DANN sollte sie FALSE zurückliefern,
    UND und die DB wurde nicht verändert.
     */
    @Test
    public void bestellungAnlegen_02() {
        em.persist(testKunde);
        em.flush();
        testBestellung.setKunde(testKunde);
        testBestellung.setStatus("s");
        boolean istWert = classUnderTest.bestellungAnlegen(testBestellung);
        em.flush();
        Query pruefeAnzNachrichten = em.createNativeQuery("SELECT * FROM bestellung", Bestellung.class);
        List<Bestellung> nachrichtenLst = pruefeAnzNachrichten.getResultList();
        assertTrue(nachrichtenLst.isEmpty());
        assertFalse(istWert);
    }

    /*
    Es existieren Bestellungen in der DB
    UND für jedes Element in testBestellungListe ist die bid != null
    UND testBestellung (Member Variable) => testBestellungListe[0]
    UND testBestellung.status => "n"
    GIBT y zurück (anzahl der Bestellungen für unseren testKunden)
     */
    private int bestellungenEinfuegen() {
        Kunde dummyKunde = new Kunde(null, "Donald", "Duck", "Muster Str. 55", Date.valueOf("2019-01-01"));
        em.persist(testKunde);
        em.persist(dummyKunde);
        em.flush();
        int x = testBestellungListe.size();
        int y = 3;
        List<Bestellung> istArray = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            testBestellung = testBestellungListe.get(i);

            if (i < y) {
                testBestellung.setKunde(testKunde);
                istArray.add(testBestellung);
            } else {
                testBestellung.setKunde(dummyKunde);
            }

            em.persist(testBestellung);
        }
        em.flush();
        testKunde.setBestellungList(istArray);
        em.persist(testKunde);
        testBestellung = testBestellungListe.get(0);
        return y;
    }

    /*
    @Test: getBestellungListe_00()
    WENN x (x>0) Bestellungen in der DB existieren,
    UND y (y>0 und y<x) Bestellung für einen Testunden existieren,
    UND die Methode getBestellungListe mit der ID des Testkunden aufgerufen wird,
    DANN sollte sie eine Liste mit y Bestellungen zurückliefern.
     */
    @Test
    public void getBestellungListe_00() {
        int y = bestellungenEinfuegen();
        List<Bestellung> istWert = classUnderTest.getBestellungListe(testKunde.getKid());
        assertEquals(y, istWert.size());
    }

    /*
    @Test: getBestellungListe_01()
    WENN x (x>0) Bestellungen in der Datenbank existieren,
    UND keine Bestellungen für einen Testkunden existieren,
    UND die Methode getBestellungListe mit der Id des Testkunden aufgerufen wird,
    DANN sollte sie eine leere Liste zurückliefern.
     */
    @Test
    public void getBestellungListe_01() {
        em.persist(testKunde);
        Kunde dummyKunde = new Kunde(null, "Heinz", "Tomaten Ketchup", "Muster Str. 222", Date.valueOf("2019-01-01"));
        em.persist(dummyKunde);
        for (Bestellung b : testBestellungListe) {
            b.setKunde(dummyKunde);
            em.persist(b);
        }
        dummyKunde.setBestellungList(testBestellungListe);
        em.flush();
        List<Bestellung> istWert = classUnderTest.getBestellungListe(testKunde.getKid());
        assertTrue(istWert.isEmpty());
    }

    /*
    @Test: setBestellungStornieren_00()
    WENN Bestellungen in der DB existieren,
    UND die Methode setBestellungStornieren aufgerufen wird,
    UND die ID einer existierenden Bestellung übergeben wird,
    UND die Bestellung den Status neu hat,
    DANN sollte sie TRUE zurückliefern,
    UND die Bestellung in der DB den Status storniert haben.
     */
    @Test
    public void setBestellungStornieren_00() {
        bestellungenEinfuegen();
        boolean istWert = classUnderTest.setBestellungStornieren(testBestellung.getBid());
        assertTrue(istWert);
        assertEquals("s", testBestellung.getStatus());
    }

    /*
    @Test: setBestellungStornieren_01()
    WENN Bestellungen in der DB existieren,
    UND die Methode setBestellungStornieren aufgerufen wird,
    UND die ID einer nicht existierenden Bestellung übergeben wird,
    DANN sollte sie FALSE zurückliefern.
     */
    @Test
    public void setBestellungStornieren_01() {
        bestellungenEinfuegen();
        em.remove(testBestellung);
        boolean istWert = classUnderTest.setBestellungStornieren(testBestellung.getBid());
        assertFalse(istWert);
    }

    /*
    @Test: setBestellungStornieren_02()
    WENN Bestellungen in der DB existieren,
    UND die Methode setBestellungStornieren aufgerufen wird,
    UND die ID einer existierenden Bestellung übergeben wird,
    UND die Bestellung nicht den Status neu hat,
    DANN sollte sie FALSE zurückliefern,
    UND der Status der Bestellung in der DB nicht verändert sein.
     */
    @Test
    public void setBestellungStornieren_02() {
        bestellungenEinfuegen();
        String testStatus = "b";
        testBestellung.setStatus(testStatus);
        boolean istWert = classUnderTest.setBestellungStornieren(testBestellung.getBid());
        Bestellung dbBestellung = em.find(Bestellung.class, testBestellung.getBid());
        assertFalse(istWert);
        assertEquals(testStatus, dbBestellung.getStatus());
    }

    private Produkt erstelleTestProdukt() {
        Kategorie dummyKategorie = new Kategorie(null, 2, "dummyKat", "dummyBeschr");
        Lagerort dummyLagerort = new Lagerort(null, "dummyBez", 200);
        Lager dummyLager = new Lager(null, "dummyLager", "dummyAdd");
        dummyLagerort.setLager(dummyLager);
        em.persist(dummyLager);
        em.persist(dummyLagerort);
        em.persist(dummyKategorie);
        Produkt testProdukt = new Produkt(null, "Eis", "le Eis", Date.valueOf("2019-01-01"), 2000000, BigDecimal.valueOf(1), 3, false);
        testProdukt.setKategorie(dummyKategorie);
        testProdukt.setLagerort(dummyLagerort);

        return testProdukt;
    }

    /*
    @Test: getProduktById_00()
    WENN ein Testprodukt bereits in der DB existiert,
    UND die Methode getProduktById mit der Id des Testprodukts aufgerufen wird,
    DANN sollte sie das Testprodukt zurückliefern.
     */
    @Test
    public void getProduktById_00() {
        Produkt testProdukt = erstelleTestProdukt();
        em.persist(testProdukt);
        em.flush();
        Produkt istWert = classUnderTest.getProduktByID(testProdukt.getProdid());
        assertEquals(testProdukt, istWert);
    }

    /*
    @Test: getProduktById_01()
    WENN ein Testprodukt nicht in der DB existiert,
    UND die Methode getProduktById mit der Id des Testprodukts aufgerufen wird,
    DANN sollte sie NULL zurückliefern.
     */
    @Test
    public void getProduktById_01() {
        Produkt testProdukt = erstelleTestProdukt();
        em.persist(testProdukt);
        em.flush();
        em.remove(testProdukt);
        Produkt istWert = classUnderTest.getProduktByID(testProdukt.getProdid());
        assertNull(istWert);
    }
}
