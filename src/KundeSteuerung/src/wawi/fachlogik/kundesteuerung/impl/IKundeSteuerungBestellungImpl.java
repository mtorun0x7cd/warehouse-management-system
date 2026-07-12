package wawi.fachlogik.kundesteuerung.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import wawi.datenhaltung.bestellungverwaltungk.impl.IBestellungKundeImpl;
import wawi.datenhaltung.bestellungverwaltungk.service.IBestellungKunde;
import wawi.datenhaltung.bestellungverwaltungs.impl.IKundeServiceImpl;
import wawi.datenhaltung.bestellungverwaltungs.service.IKundeService;
import wawi.datenhaltung.wawidbmodel.entities.Bestellung;
import wawi.datenhaltung.wawidbmodel.entities.Bestellungsposition;
import wawi.datenhaltung.wawidbmodel.entities.Kategorie;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.entities.Lager;
import wawi.datenhaltung.wawidbmodel.entities.Lagerort;
import wawi.datenhaltung.wawidbmodel.entities.Produkt;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;
import wawi.fachlogik.kundesteuerung.grenzklassen.BestellungGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.BestellungspositionGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.EnumBestellStatus;
import wawi.fachlogik.kundesteuerung.grenzklassen.KategorieGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.KundeGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.LagerGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.LagerortGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.ProduktGrenz;
import wawi.fachlogik.kundesteuerung.service.IKundeSteuerungBestellung;

/**
 * Steuerungsklasse IKundeSteuerungBestellungImpl implementiert alle
 * Anwendungsfälle, die sich mit der Verwaltung von Bestellungen beschäftigen.
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 * @version 0.1
 * @see IBestellungKunde
 * @see IKundeService
 */
public class IKundeSteuerungBestellungImpl implements IKundeSteuerungBestellung {

    /**
     * Instanz des EntityManager, genutzt als wrap-around um die Schnittstellen
     * aus der Datenhaltung für die Datenbank Transaktionen.
     */
    private EntityManager em;
    /**
     * Instanz der Implementation von IBestellungKunde, welche zum Ausführen von
     * Aufgaben auf Schicht der Datenhaltung benötigt wird.
     */
    private IBestellungKunde bestellungKunde;
    /**
     * Instanz der Implementation von IKundeService, welche zum Ausführen von
     * Aufgaben auf Schicht der Datenhaltung benötigt wird.
     */
    private IKundeService kundeService;

    /**
     * Konstruktor der Klasse IKundeSteuerungBestellungImpl, dieser
     * Initialisiert die Klassen-Attribute em: EntityManager, bestellungKunde:
     * IBestellungKunde und kundeService: IKundeService gegeben den Vorgaben aus
     * den WAWI-Dokumenten.
     */
    public IKundeSteuerungBestellungImpl() throws Exception {
        em = new IDatabaseImpl().getEntityManager();
        if (em == null) {
            throw new Exception("Fehler: EntityManager konnte nicht geholt werden.");
        }
        bestellungKunde = new IBestellungKundeImpl();
        bestellungKunde.setEntityManager(em);
        kundeService = new IKundeServiceImpl();
        kundeService.setEntityManager(em);
    }

    /**
     * Hilfsmethode um die Produkt-Objekte via ID zu holen. /LF150/ Produkt via
     * ID
     *
     * @param pid eindeutige Produktnummer
     * @return result das Objekt ist null, falls es nicht in der Datenbank
     * gefunden wurde
     */
    @Override
    public ProduktGrenz getProduktByID(int pid) {
        ProduktGrenz result = null;

        bestellungKunde.setEntityManager(em);
        Produkt tmp = bestellungKunde.getProduktByID(pid);

        Kategorie tmpKat = tmp.getKategorie();
        if (tmpKat != null) {
            KategorieGrenz kGrenz = new KategorieGrenz(
                    tmpKat.getKatid(),
                    tmpKat.getParentkatid(),
                    tmpKat.getName(),
                    tmpKat.getBeschreibung()
            );
            Lagerort tmpLort = tmp.getLagerort();

            if (tmpLort != null) {
                Lager tmpL = tmpLort.getLager();

                if (tmpL != null) {
                    LagerGrenz lGrenz = new LagerGrenz(
                            tmpL.getLagerid(),
                            tmpL.getName(),
                            tmpL.getAddresse()
                    );
                    LagerortGrenz lOrtGrenz = new LagerortGrenz(
                            tmpLort.getLgortid(),
                            tmpLort.getBezeichnung(),
                            tmpLort.getKapazitaet(),
                            lGrenz
                    );
                    result = new ProduktGrenz(
                            tmp.getProdid(),
                            tmp.getName(),
                            tmp.getBeschreibung(),
                            tmp.getAngelegt(),
                            tmp.getStueckzahl(),
                            tmp.getNettopreis(),
                            tmp.getMwstsatz(),
                            tmp.getAktiv()
                    );
                    result.setLagerort(lOrtGrenz);
                    result.setKategorie(kGrenz);
                }
            }
        }

        return result;
    }

    /**
     * Methode zur Realisierung des holens aller Aktiven Produkte aus der
     * Datenbank. Hierzu wird die Schnittstelle BestellungVerwaltungS unter
     * anderem genutzt. /LF150/ alle Aktiven Produkte aus der Datenbank
     *
     * @return List(ProduktGrenz) die Liste ist leer, falls es keine oder keine
     * aktiven Produkte in der Datenbank gibt.
     */
    @Override
    public List<ProduktGrenz> getAktiveProdukte() {
        List<ProduktGrenz> produkte = new ArrayList<>();

        kundeService.setEntityManager(em);
        em.getTransaction().begin();
        for (Produkt p : kundeService.getAktiveProdukte()) {
            ProduktGrenz pGrenz = getProduktByID(p.getProdid());

            if (pGrenz != null) {
                produkte.add(pGrenz);
            }
        }
        em.flush();
        em.getTransaction().commit();

        return produkte;
    }

    /**
     * Methode zur Realisierung des berechnens der Gesamt Werte einer Bestellung
     * darunter fallen gesamtNetto und gesamtBrutto, diese werden direkt in dem
     * als Parameter übergebenen Objekt gespeichert. /LF150/ gesamt Berechnen
     *
     * @param b BestellungGrenz
     */
    @Override
    public void berechneGesamt(BestellungGrenz b) {
        double gesamtNetto = 0.0;
        double gesamtBrutto = 0.0;

        for (BestellungspositionGrenz bposGrenz : b.getBestellungspositionsList()) {
            ProduktGrenz pGrenz = bposGrenz.getProdukt();
            bposGrenz.setProdukt(getProduktByID(pGrenz.getProdid())); // fix relations
            double nettoEinzelPreis = Double.parseDouble(pGrenz.getNettopreis().toString());
            int mwstsatz = pGrenz.getMwstsatz();
            int anzahl = bposGrenz.getAnzahl();
            double bruttoEinzelPreis = nettoEinzelPreis * (1 + ((double) mwstsatz / 100));

            gesamtNetto += (nettoEinzelPreis * anzahl);
            gesamtBrutto += (bruttoEinzelPreis * anzahl);
        }

        b.setGesamtNetto(BigDecimal.valueOf(gesamtNetto));
        b.setGesamtBrutto(BigDecimal.valueOf(gesamtBrutto));
    }

    /**
     * Methode zur Realisierung des anlegen einer neuen Bestellung. /LF160/ neue
     * Bestellung anlegen
     *
     * @param b BestellungGrenz die neue Bestellung, die eingefügt werden soll
     * @return result gibt das resultat der Operation zurück
     */
    @Override
    public boolean neueBestellungAnlegen(BestellungGrenz b) {
        boolean result = false;

        em.getTransaction().begin();
        Bestellung nB = new Bestellung(
                b.getBid(),
                b.getLieferadresse(),
                b.getRechnungsadresse(),
                b.getAngelegt(),
                b.getStatus().toString(),
                b.getGesamtNetto(),
                b.getGesamtBrutto()
        );
        KundeGrenz kuGrenz = b.getKunde();
        Kunde kunde = new Kunde(
                kuGrenz.getKid(),
                kuGrenz.getName(),
                kuGrenz.getVorname(),
                kuGrenz.getAdresse(),
                kuGrenz.getAngelegt()
        );
        nB.setKunde(kunde);
        List<Bestellungsposition> bposLst = new ArrayList<>();
        for (BestellungspositionGrenz bposGrenz : b.getBestellungspositionsList()) {
            Produkt prod = bestellungKunde.getProduktByID(bposGrenz.getProdukt().getProdid());

            Bestellungsposition bpos = new Bestellungsposition(
                    bposGrenz.getBpid(),
                    bposGrenz.getAnzahl()
            );
            bpos.setProdukt(prod);
            bpos.setBestellung(nB);
            bposLst.add(bpos);
        }

        nB.setBestellungspositionList(bposLst);
        result = bestellungKunde.bestellungAnlegen(nB);

        em.flush();

        em.getTransaction()
                .commit();

        return result;
    }

    /**
     * Methode zur Realisierung des anzeigens aller Bestellungen eines
     * spezifischen Kunden /LF160/ eigene Bestellungen anzeigen
     *
     * @param kid eindeutige Kundennummer
     * @return result gibt die Liste von Bestellungen eines Kunden zurück, diese
     * Liste ist leer wenn keine Bestellungen in der Datenbank existieren
     */
    @Override
    public List<BestellungGrenz> getBestellungListe(int kid) {
        List<BestellungGrenz> result = new ArrayList<>();

        bestellungKunde.setEntityManager(em);
        em.getTransaction().begin();
        for (Bestellung b : bestellungKunde.getBestellungListe(kid)) {
            Kunde k = b.getKunde();
            EnumBestellStatus stat = EnumBestellStatus.valueOf(b.getStatus());

            BestellungGrenz bGrenz = new BestellungGrenz(
                    b.getBid(),
                    b.getLieferadresse(),
                    b.getRechnungsadresse(),
                    b.getCreated(),
                    stat,
                    b.getGesamtnetto(),
                    b.getGesamtbrutto(),
                    new KundeGrenz(
                            k.getKid(),
                            k.getName(),
                            k.getVorname(),
                            k.getAdresse(),
                            k.getCreated()
                    )
            );

            List<BestellungspositionGrenz> bposGrenzLst = new ArrayList<>();
            for (Bestellungsposition bpos : b.getBestellungspositionList()) {
                Produkt prod = bpos.getProdukt();
                Kategorie kat = prod.getKategorie();

                if (kat != null) {
                    Lagerort lgort = prod.getLagerort();

                    if (lgort != null) {
                        Lager lag = lgort.getLager();

                        if (lag != null) {
                            KategorieGrenz kGrenz = new KategorieGrenz(
                                    kat.getKatid(),
                                    kat.getParentkatid(),
                                    kat.getName(),
                                    kat.getBeschreibung()
                            );
                            LagerGrenz lGrenz = new LagerGrenz(
                                    lag.getLagerid(),
                                    lag.getName(),
                                    lag.getAddresse()
                            );
                            LagerortGrenz lOrtGrenz = new LagerortGrenz(
                                    lgort.getLgortid(),
                                    lgort.getBezeichnung(),
                                    lgort.getKapazitaet(),
                                    lGrenz
                            );
                            ProduktGrenz pGrenz = new ProduktGrenz(
                                    prod.getProdid(),
                                    prod.getName(),
                                    prod.getBeschreibung(),
                                    prod.getAngelegt(),
                                    prod.getStueckzahl(),
                                    prod.getNettopreis(),
                                    prod.getMwstsatz(),
                                    prod.getAktiv()
                            );
                            pGrenz.setLagerort(lOrtGrenz);
                            pGrenz.setKategorie(kGrenz);

                            BestellungspositionGrenz bposGrenzItem = new BestellungspositionGrenz(
                                    bpos.getBpid(),
                                    bpos.getAnzahl(),
                                    pGrenz
                            );

                            bposGrenzLst.add(bposGrenzItem);
                        }
                    }
                }
            }
            bGrenz.setBestellungspositionsList(bposGrenzLst);

            result.add(bGrenz);
        }
        em.getTransaction().commit();

        return result;
    }

    /**
     * Methode zur Realisierung des stornierens einer Bestellung /LF160/ eigene
     * Bestellung stornieren
     *
     * @param bid eindeutige Bestellnummer
     * @return result Boolscher Status, ob die Nachricht erfolgreich storniert
     * wurden konnte (true | false).
     */
    @Override
    public boolean storniereBestellung(int bid) {
        boolean result = false;

        em.getTransaction().begin();
        bestellungKunde.setEntityManager(em);
        result = bestellungKunde.setBestellungStornieren(bid);
        em.flush();
        em.getTransaction().commit();

        return result;
    }
}
