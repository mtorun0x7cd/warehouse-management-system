package wawi.datenhaltung.wawidbmodel.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
public class EntityManagerSingleton {
	private static EntityManagerSingleton instance;
	private EntityManager emDev;
	private EntityManager emProd;
	private String pu = "WAWIDBDEVPU";
	
	private EntityManagerSingleton() {
		emDev = Persistence.createEntityManagerFactory("WAWIDBDEVPU").createEntityManager();
		emProd = Persistence.createEntityManagerFactory("WAWIDBPRODPU").createEntityManager();
	}
	
	public static EntityManagerSingleton getInstance() {
		if (instance == null) {
			instance = new EntityManagerSingleton();
		}
		return instance;
	}
	
	public EntityManager getEntityManager() {
		if (pu.equals("WAWIDBDEVPU")) {
			return emDev;
		}
		else if (pu.equals("WAWIDBPRODPU")){
			return emProd;
		}
		else 
			return null;
	}
	
	public void useDevPU() {
		this.pu = "WAWIDBDEVPU";
	}
		
	public void useProdPU() {
		this.pu = "WAWIDBPRODPU";
	}
		
	public String getCurrentPU() {
		return pu;
	}
}
