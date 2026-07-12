package wawi.datenhaltung.bestellungverwaltungk.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wawi.datenhaltung.bestellungverwaltungk.service.INachrichtKunde;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.entities.Nachricht;

public class INachrichtKundeImpl implements INachrichtKunde {

    private EntityManager em;

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean sendeNachrichtAnSach(Nachricht n) {
        boolean ret = false;

        if (em != null && n != null && n.getNid() == null && "ungelesen".equals(n.getStatus())
                && "anwawi".equals(n.getTyp()) && em.find(Kunde.class, n.getKunde().getKid()) != null) {
            em.persist(n);
            ret = true;
        }

        return ret;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Nachricht> getEigeneNachrichten(int kid) {
        List<Nachricht> eigeneNachrichten = new ArrayList<>();

        if (em != null && kid > 0) {
            Query q = em.createNativeQuery("SELECT * FROM nachricht WHERE kunde = " + kid, Nachricht.class);
            eigeneNachrichten = q.getResultList();
        }

        return eigeneNachrichten;
    }

    @Override
    public boolean deleteNachricht(int nid) {
        boolean ret = false;

        if (em != null && nid > 0) {
            Nachricht msg = em.find(Nachricht.class, nid);

            if (msg != null) {
                em.remove(msg);
                ret = true;
            }
        }

        return ret;
    }

    @Override
    public boolean setNachrichtGelesen(int nid) {
        boolean ret = false;

        if (em != null && nid > 0) {
            Nachricht n = em.find(Nachricht.class, nid);

            if (n != null && n.getTyp().equals("ankunde")) {
                n.setStatus("gelesen");
                em.merge(n);
                ret = true;
            }
        }

        return ret;
    }
}
