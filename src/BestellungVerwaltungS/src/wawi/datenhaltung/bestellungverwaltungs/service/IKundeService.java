package wawi.datenhaltung.bestellungverwaltungs.service;

import java.util.List;
import javax.persistence.EntityManager;
import wawi.datenhaltung.wawidbmodel.entities.Produkt;

public interface IKundeService {

    void setEntityManager(EntityManager em);

    List<Produkt> getAktiveProdukte();
}
