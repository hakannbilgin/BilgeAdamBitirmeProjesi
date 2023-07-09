package hospital.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfig {

private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
	
	
	static {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HospitalAutomation");
	}
	
	
	private JPAConfig() {
		//
	}

	
	public static EntityManagerFactory getEntityManagerFactory() {
		return ENTITY_MANAGER_FACTORY;
	}
	
	public static void closeEntityManagerFactory() {
		if(ENTITY_MANAGER_FACTORY != null) {
			ENTITY_MANAGER_FACTORY.close();
		}
	}
}
