package wawi.fachlogik.kundesteuerung.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import wawi.datenhaltung.bestellungverwaltungk.impl.INachrichtKundeImpl;
import wawi.datenhaltung.bestellungverwaltungk.service.INachrichtKunde;
import wawi.datenhaltung.kundeverwaltung.service.ICRUDKunde;
import wawi.datenhaltung.kundeverwaltung.impl.ICRUDKundeImpl;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.entities.Nachricht;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;
import wawi.fachlogik.kundesteuerung.grenzklassen.EnumNachrichtStatus;
import wawi.fachlogik.kundesteuerung.grenzklassen.EnumNachrichtTyp;
import wawi.fachlogik.kundesteuerung.grenzklassen.KundeGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.NachrichtGrenz;
import wawi.fachlogik.kundesteuerung.service.IKundeSteuerungNachricht;

/**
 * Steuerungsklasse IKundeSteuerungNachrichtImpl implementiert alle
 * Anwendungsfälle, die sich mit der Verwaltung von Nachrichten beschäftigen.
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 * @version 0.1
 * @see INachrichtKunde
 */
public class IKundeSteuerungNachrichtImpl implements IKundeSteuerungNachricht {

    /**
     * Instanz des EntityManager, genutzt als wrap-around um die Schnittstellen
     * aus der Datenhaltung für die Datenbank Transaktionen.
     */
    private EntityManager em;
    /**
     * Instanz der Implementation von INachrichtKunde, welche zum Ausführen von
     * Aufgaben auf Schicht der Datenhaltung benötigt wird.
     */
    private INachrichtKunde nachrichtKunde;

    /**
     * Konstruktor der Klasse IKundeSteuerungNachrichtImpl, dieser Initialisiert
     * die Klassen-Attribute em: EntityManager und nachrichtKunde:
     * INachrichtKunde gegeben den Vorgaben aus den WAWI-Dokumenten.
     *
     * @throws java.lang.Exception wenn der EntityManager nicht geholt werden
     * kann.
     */
    public IKundeSteuerungNachrichtImpl()
            throws Exception {
        em = new IDatabaseImpl().getEntityManager();
        if (em == null) {
            throw new Exception("Fehler: EntityManager konnte nicht geholt werden.");
        }
        nachrichtKunde = new INachrichtKundeImpl();
        nachrichtKunde.setEntityManager(em);
    }

    /**
     * Methode zur Realisierung des senden einer Nachrichts an Sach /LF170/
     * Nachricht senden .
     *
     * @param n NachrichtGrenz, das Objekt, dass unsere zu sendende Nachricht
     * enthält
     * @return result Boolscher Status, ob die Nachricht erfolgreich gesendet
     * wurden konnte (true | false).
     */
    @Override
    public boolean sendeNachrichtAnSach(NachrichtGrenz n) {
        boolean result = false;

        ICRUDKunde crudKunde = new ICRUDKundeImpl();
        em.getTransaction().begin();
        crudKunde.setEntityManager(em);
        Nachricht nE = new Nachricht(
                n.getNid(),
                n.getBetreff(),
                n.getNachricht(),
                n.getAngelegt(),
                n.getStatus().name(),
                n.getTyp().name()
        );
        nE.setKunde(crudKunde.getKundeById(n.getKunde().getKid()));

        result = nachrichtKunde.sendeNachrichtAnSach(nE);
        em.getTransaction().commit();

        return result;
    }

    /**
     * Methode zur Realisierung des Abrufen aller Nachrichten eines Kunden
     * /LF170/ Nachrichten abrufen, lesen
     *
     * @param kid eindeutige Kundennummer
     * @return result Objekt, dass alle Nachrichten zur zugehörigen Kundennummer
     * enthält
     */
    @Override
    public List<NachrichtGrenz> nachrichtenAbrufen(int kid) {
        List<NachrichtGrenz> result = new ArrayList<>();

        em.getTransaction().begin();
        for (Nachricht n : nachrichtKunde.getEigeneNachrichten(kid)) {
            Kunde k = n.getKunde();

            NachrichtGrenz nGrenz = new NachrichtGrenz(
                    n.getNid(),
                    n.getBetreff(),
                    n.getNachricht(),
                    n.getCreated(),
                    EnumNachrichtStatus.valueOf(n.getStatus()),
                    EnumNachrichtTyp.valueOf(n.getTyp()),
                    new KundeGrenz(
                            k.getKid(),
                            k.getName(),
                            k.getVorname(),
                            k.getAdresse(),
                            k.getCreated()
                    )
            );

            result.add(nGrenz);
        }
        em.getTransaction().commit();

        return result;
    }

    /**
     * Methode zur Realisierung des löschen einer Nachricht von einem Kunden
     * /LF170/ Nachricht löschen
     *
     * @param nid eindeutige Nachrichtennummer
     * @return result Boolscher Status, ob die Nachricht erfolgreich gelöscht
     * wurde (true | false).
     */
    @Override
    public boolean loescheNachricht(int nid) {
        boolean result = false;

        em.getTransaction().begin();
        result = nachrichtKunde.deleteNachricht(nid);
        em.flush();
        em.getTransaction().commit();

        return result;
    }
    
    /**
     * Methode zur Realisierung des lesens einer Nachricht an Kunde.
     * /LF170/ Nachricht lesen
     *
     * @param nid eindeutige Nachrichtennummer
     * @return result Boolscher Status, ob die Nachricht erfolgreich als gelesen 
     * gesetzt wurde (true | false).
     */
    @Override
    public boolean setNachrichtGelesen(int nid)
    {
        boolean result = false;
        
        em.getTransaction().begin();
        result = nachrichtKunde.setNachrichtGelesen(nid);
        em.flush();
        em.getTransaction().commit();
        
        return result;
    }
}
