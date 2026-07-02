package wawi.datenhaltung.bestellungverwaltungs.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import wawi.datenhaltung.bestellungverwaltungs.service.IKundeService;
import wawi.datenhaltung.wawidbmodel.entities.Produkt;

public class IKundeServiceImpl implements IKundeService
{
    private EntityManager em;

    @Override
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }

    @Override
    public List<Produkt> getAktiveProdukte() 
    {
        List<Produkt> result = new ArrayList<>();
        
        if (em != null)
        {
            Query q = em.createNativeQuery("SELECT * FROM produkt WHERE aktiv = true", Produkt.class);
            result = q.getResultList();
        }
        
        return result;
    }
}
