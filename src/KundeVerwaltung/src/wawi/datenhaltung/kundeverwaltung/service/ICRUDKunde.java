package wawi.datenhaltung.kundeverwaltung.service;

import java.util.List;
import javax.persistence.EntityManager;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;

public interface ICRUDKunde {

    void setEntityManager(EntityManager em);

    Kunde getKundeById(int kid);

    List<Kunde> getKundenListe();

    boolean insertKunde(Kunde k);

    boolean updateKunde(Kunde k);

    boolean deleteKunde(int kid);
}
