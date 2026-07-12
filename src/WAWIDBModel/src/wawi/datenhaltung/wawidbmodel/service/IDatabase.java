package wawi.datenhaltung.wawidbmodel.service;

import javax.persistence.EntityManager;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
public interface IDatabase {	
	public EntityManager getEntityManager();
	public void useDevPU();
	public void useProdPU();
	public String getCurrentPU();
}
