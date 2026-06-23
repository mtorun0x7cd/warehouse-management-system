package wawi.fachlogik.kundesteuerung.test;

import java.sql.Date;
import javax.persistence.EntityManager;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;
import wawi.fachlogik.componentcontroller.service.CompType;
import wawi.fachlogik.kundesteuerung.impl.IActivateComponentImpl;

public class IActivateComponentImplTesten {

    private EntityManager em;
    private IActivateComponentImpl classUnderTest;

    /*
    @Before: angenommen()
    ANGENOMMEN die Implementierung der IActivateComponent Schnittstelle wird als classUnderTest
    instanziiert,
    UND der EntityManager wird korrekt geholt, (nicht immer erforderlich),
    UND die Transaktion von em wird gestartet, (nicht immer erforderlich).
     */
    @Before
    public void angenommen() {
        em = new IDatabaseImpl().getEntityManager();
        classUnderTest = new IActivateComponentImpl();
        em.getTransaction().begin();
    }

    /*
    @After: amEnde()
    AM ENDE wird die Transaktion zurück gesetzt. (nicht immer erforderlich)
     */
    @After
    public void amEnde() {
        em.getTransaction().rollback();
    }

    /*
    @Test: getCompType()
    WENN die Methode getCompType aufgerufen wird,
    DANN sollte sie den entsprechenden CompType der Komponente zurückliefern.
     */
    @Test
    public void getCompType() {
        CompType sollWert = CompType.KUNDE;
        CompType istWert = classUnderTest.getComponentType();
        assertEquals(sollWert, istWert);
    }

    /*
    @Test: activateComponent()_00
    WENN die Methode activateComponent aufgerufen wird,
    UND die Komponente sich im Zustand deactivated befindet,
    UND für die jeweilige Komponente eine gültige ID übergeben wird,
    DANN sollte sie TRUE zurückliefern,
    UND der Zustand in activated gewechselt sein.
     */
    @Test
    public void activateComponent_00() {
        Kunde testKunde = testKundeAnlegen();
        boolean istWert = classUnderTest.activateComponent(testKunde.getKid());
        assertTrue(istWert);
        assertTrue(classUnderTest.isActivated());
    }

    private Kunde testKundeAnlegen() {
        Kunde testKunde = new Kunde(null, "Mustermann", "Max", "Musteraddr. 202", Date.valueOf("2019-01-01"));
        em.persist(testKunde);
        em.flush();
        return testKunde;
    }

    /*
    @Test: activateComponent()_01
    WENN die Methode activateComponent aufgerufen wird,
    UND die Komponente sich im Zustand activated befindet,
    UND für die jeweilige Komponente eine gültige ID übergeben wird,
    DANN sollte sie FALSE zurückliefern,
    UND der Zustand in activated bleiben.
     */
    @Test
    public void activateComponent_01() {
        Kunde testKunde = testKundeAnlegen();
        classUnderTest.activateComponent(testKunde.getKid());
        boolean istWert = classUnderTest.activateComponent(testKunde.getKid());
        assertTrue(classUnderTest.isActivated());
        assertFalse(istWert);
    }

    /*
    @Test: activateComponent()_02
    WENN die Methode activateComponent aufgerufen wird,
    UND die Komponente sich im Zustand deactivated befindet,
    UND für die jeweilige Komponente eine ungültige ID übergeben wird,
    DANN sollte sie FALSE zurückliefern,
    UND der Zustand in deactivated bleiben.
     */
    @Test
    public void activateComponent_02() {
        Kunde testKunde = testKundeAnlegen();
        em.remove(testKunde);
        boolean istWert = classUnderTest.activateComponent(testKunde.getKid());
        assertFalse(classUnderTest.isActivated());
        assertFalse(istWert);
    }

    /*
    @Test: activateComponent()_03
    WENN die Methode activateComponent aufgerufen wird,
    UND die Komponente sich im Zustand activated befindet,
    UND für die jeweilige Komponente eine ungültige ID übergeben wird,
    DANN sollte sie FALSE zurückliefern,
    UND der Zustand in activated bleiben.
     */
    @Test
    public void activateComponent_03() {
        Kunde testKunde = testKundeAnlegen();
        classUnderTest.activateComponent(testKunde.getKid());
        em.remove(testKunde);
        boolean istWert = classUnderTest.activateComponent(testKunde.getKid());
        assertTrue(classUnderTest.isActivated());
        assertFalse(istWert);
    }

    /*
    @Test: deactivateComponent()_00
    WENN die Methode deactivateComponent aufgerufen wird,
    UND die Komponente sich im Zustand activated befindet,
    DANN sollte sie TRUE zurückliefern,
    UND der Zustand in deactivated gewechselt sein.
     */
    @Test
    public void deactivateComponent_00() {
        Kunde testKunde = testKundeAnlegen();
        classUnderTest.activateComponent(testKunde.getKid());
        boolean istWert = classUnderTest.deactivateComponent();
        assertTrue(istWert);
        assertFalse(classUnderTest.isActivated());
    }

    /*
    @Test: deactivateComponent()_01
    WENN die Methode deactivateComponent aufgerufen wird,
    UND die Komponente sich im Zustand deactivated befindet,
    DANN sollte sie FALSE zurückliefern,
    UND der Zustand in deactivated bleiben.
     */
    @Test
    public void deactivateComponent_01() {
        boolean istWert = classUnderTest.deactivateComponent();
        assertFalse(istWert);
        assertFalse(classUnderTest.isActivated());
    }

    /*
    @Test: isActivated()_00
    WENN die Methode isActivated aufgerufen wird,
    UND die Komponente aktiviert ist,
    DANN sollte sie TRUE zurückliefern.
     */
    @Test
    public void isActivated_00() {
        Kunde testKunde = testKundeAnlegen();
        classUnderTest.activateComponent(testKunde.getKid());
        boolean istWert = classUnderTest.isActivated();
        assertTrue(istWert);
    }

    /*
    @Test: isActivated()_01
    WENN die Methode isActivated aufgerufen wird,
    UND die Komponente deaktiviert ist,
    DANN sollte sie FALSE zurückliefern.
     */
    @Test
    public void isActivated_01() {
        boolean istWert = classUnderTest.isActivated();
        assertFalse(istWert);
    }
}
