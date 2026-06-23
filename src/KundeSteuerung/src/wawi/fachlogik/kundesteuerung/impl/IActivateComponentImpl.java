package wawi.fachlogik.kundesteuerung.impl;

import wawi.datenhaltung.wawidbmodel.entities.Kunde;
import wawi.datenhaltung.wawidbmodel.impl.IDatabaseImpl;
import wawi.fachlogik.componentcontroller.service.CompType;
import wawi.fachlogik.componentcontroller.service.IActivateComponent;
import wawi.fachlogik.kundesteuerung.grenzklassen.KundeGrenz;
import javax.persistence.EntityManager;
import wawi.datenhaltung.kundeverwaltung.impl.ICRUDKundeImpl;
import wawi.datenhaltung.kundeverwaltung.service.ICRUDKunde;

public class IActivateComponentImpl implements IActivateComponent {

    private boolean activated;
    public static KundeGrenz kunde;

    public IActivateComponentImpl() {
        this.activated = false;
    }

    @Override
    public CompType getComponentType() {
        return CompType.KUNDE;
    }

    @Override
    public boolean activateComponent(int userid) {
        if (this.activated) {
            return false;
        }

        EntityManager em = new IDatabaseImpl().getEntityManager();

        if (em != null) {
            ICRUDKunde crudKunde = new ICRUDKundeImpl();
            crudKunde.setEntityManager(em);

            Kunde k = crudKunde.getKundeById(userid);

            if (k != null) {
                kunde = new KundeGrenz(k.getKid(), k.getName(), k.getVorname(), k.getAdresse(), k.getCreated());
                this.activated = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deactivateComponent() {
        if (this.activated) {
            this.activated = false;
            return true;
        }

        return false;
    }

    @Override
    public boolean isActivated() {
        return this.activated;
    }
}
