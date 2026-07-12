package wawi.datenhaltung.bestellungverwaltungk.service;

import java.util.List;
import javax.persistence.EntityManager;
import wawi.datenhaltung.wawidbmodel.entities.Bestellung;
import wawi.datenhaltung.wawidbmodel.entities.Produkt;

public interface IBestellungKunde {

    void setEntityManager(EntityManager em);

    boolean bestellungAnlegen(Bestellung b);

    List<Bestellung> getBestellungListe(int kid);

    boolean setBestellungStornieren(int bid);

    Produkt getProduktByID(int pid);
}
