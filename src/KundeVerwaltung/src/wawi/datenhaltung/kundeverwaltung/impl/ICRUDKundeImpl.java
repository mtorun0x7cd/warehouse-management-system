package wawi.datenhaltung.kundeverwaltung.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wawi.datenhaltung.kundeverwaltung.service.ICRUDKunde;
import wawi.datenhaltung.wawidbmodel.entities.Kunde;

public class ICRUDKundeImpl implements ICRUDKunde {

    private EntityManager em;

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Kunde getKundeById(int kid) {
        Kunde kunde = null;

        if (em != null && kid > 0) {
            kunde = em.find(Kunde.class, kid);
        }

        return kunde;
    }

    @Override
    @SuppressWarnings("unchecked") // Ignore Generic List Warning q.getResultList
    public List<Kunde> getKundenListe() {
        List<Kunde> kundenListe = new ArrayList<>();

        if (em != null) {
            Query q = em.createNativeQuery("SELECT * FROM kunde", Kunde.class);
            kundenListe = q.getResultList();
        }

        return kundenListe;
    }

    @Override
    public boolean insertKunde(Kunde k) {
        boolean ret = false;

        if (em != null && k != null && k.getKid() == null) {
            em.persist(k);
            ret = true;
        }

        return ret;
    }

    @Override
    public boolean updateKunde(Kunde k) {
        // Hier braucht man kein em != null abfragen, da dies in getKundeById gemacht wird und im fall em = null dann auch null zurückgibt.
        if (getKundeById(k.getKid()) != null) {
            em.merge(k);

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteKunde(int kid) {
        Kunde k = getKundeById(kid);

        if (k != null) {
            em.remove(k);

            return true;
        }

        return false;
    }
}
