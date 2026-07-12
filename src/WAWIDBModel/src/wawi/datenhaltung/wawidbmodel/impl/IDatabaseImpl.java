package wawi.datenhaltung.wawidbmodel.impl;

import wawi.datenhaltung.wawidbmodel.service.EntityManagerSingleton;
import wawi.datenhaltung.wawidbmodel.service.IDatabase;
import javax.persistence.EntityManager;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
public class IDatabaseImpl implements IDatabase {

	@Override
	public EntityManager getEntityManager() {
		return EntityManagerSingleton.getInstance().getEntityManager();
	}

	@Override
	public void useDevPU() {
		EntityManagerSingleton.getInstance().useDevPU();
	}

	@Override
	public void useProdPU() {
		EntityManagerSingleton.getInstance().useProdPU();
	}

	@Override
	public String getCurrentPU() {
		return EntityManagerSingleton.getInstance().getCurrentPU();
	}
}
