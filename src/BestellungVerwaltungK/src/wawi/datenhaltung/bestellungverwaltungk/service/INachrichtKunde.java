package wawi.datenhaltung.bestellungverwaltungk.service;

import java.util.List;
import javax.persistence.EntityManager;
import wawi.datenhaltung.wawidbmodel.entities.Nachricht;

public interface INachrichtKunde {

    void setEntityManager(EntityManager em);

    boolean sendeNachrichtAnSach(Nachricht n);

    List<Nachricht> getEigeneNachrichten(int kid);

    boolean deleteNachricht(int nid);
    
    boolean setNachrichtGelesen(int nid);
}
