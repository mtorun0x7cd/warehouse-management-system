package wawi.datenhaltung.bestellungverwaltungk.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wawi.datenhaltung.bestellungverwaltungk.service.IBestellungKunde;
import wawi.datenhaltung.wawidbmodel.entities.Bestellung;
import wawi.datenhaltung.wawidbmodel.entities.Produkt;

public class IBestellungKundeImpl implements IBestellungKunde {

    private EntityManager em;

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean bestellungAnlegen(Bestellung b) {
        boolean ret = false;

        if (em != null && b != null && b.getBid() == null && "n".equals(b.getStatus())) {
            em.persist(b);
            ret = true;
        }

        return ret;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Bestellung> getBestellungListe(int kid) {
        List<Bestellung> eigeneBestellungen = new ArrayList<>();

        if (em != null && kid > 0) {
            Query q = em.createNativeQuery("SELECT * FROM bestellung WHERE kunde = " + kid, Bestellung.class);
            eigeneBestellungen = q.getResultList();
        }

        return eigeneBestellungen;
    }

    @Override
    public boolean setBestellungStornieren(int bid) {
        boolean ret = false;

        if (em != null && bid > 0) {
            Bestellung b = em.find(Bestellung.class, bid);

            if (b != null && b.getStatus().equals("n")) {
                b.setStatus("s");
                em.merge(b);
                ret = true;
            }
        }

        return ret;
    }

    @Override
    public Produkt getProduktByID(int pid) {
        Produkt produkt = null;

        if (em != null && pid > 0) {
            produkt = em.find(Produkt.class, pid);
        }

        return produkt;
    }
}
